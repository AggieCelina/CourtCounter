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

public class MainActivity extends AppCompatActivity {

    int timeMove = 0;
    int timeRest = 0;
    int timesRepeat = 0;
    int nbOfSets = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


/*
add and substract time for move
 */

    public void addOneToMove(View view) {

        timeMove = timeMove + 1;
        displayForTimeMove(timeMove);
    }

    public void substractOneFromMove(View view) {

        if (timeMove > 0) {

            timeMove = timeMove - 1;
            displayForTimeMove(timeMove);
        }
    }


    /*
    add and substract time for rest
    */

    public void addOneToRest(View view) {

        timeRest = timeRest + 1;
        displayForTimeRest(timeRest);
    }

    public void substractOneFromRest(View view) {

        timeRest = timeRest - 1;
        displayForTimeRest(timeRest);
    }

     /*
    add and substract time for repeat
    */

    public void addOneToRepeat(View view) {

        timesRepeat = timesRepeat + 1;
        displayForTimesRepeat(timesRepeat);
    }

    public void substractOneFromRepeat(View view) {

        timesRepeat = timesRepeat - 1;
        displayForTimesRepeat(timesRepeat);
    }

    /*

    reset everything
     */

    public void resetEverything(View view) {

        timeRest = 0;
        timeMove = 0;
        timesRepeat = 0;
        displayForTimeMove(timeMove);
        displayForTimeRest(timeRest);
        displayForTimesRepeat(timesRepeat);

        Chronometer chrono = (Chronometer) findViewById(R.id.chrono);
        chrono.setBase(SystemClock.elapsedRealtime());
        chrono.stop();
        chrono.setTextSize(120);

        PlayBeep();

        //enable buttons
        Button startButton = (Button) findViewById(R.id.start_button);
        startButton.setEnabled(true);
        Button movePlusButton = (Button) findViewById(R.id.move_plus_button);
        movePlusButton.setEnabled(true);
        Button moveSubButton = (Button) findViewById(R.id.move_sub_button);
        moveSubButton.setEnabled(true);
        Button restPlusButton = (Button) findViewById(R.id.rest_plus_button);
        restPlusButton.setEnabled(true);
        Button restSubButton = (Button) findViewById(R.id.rest_sub_button);
        restSubButton.setEnabled(true);
        Button setPlusButton = (Button) findViewById(R.id.set_plus_button);
        setPlusButton.setEnabled(true);
        Button setSubButton = (Button) findViewById(R.id.set_sub_button);
        setSubButton.setEnabled(true);


    }
    /*
    display methods
     */

    public void displayForTimeMove(int time) {

        TextView timeMoveView = (TextView) findViewById(R.id.move_time);
        timeMoveView.setText(String.valueOf(time));
    }


    public void displayForTimeRest(int time) {

        TextView timeRestView = (TextView) findViewById(R.id.rest_time);
        timeRestView.setText(String.valueOf(time));
    }

    public void displayForTimesRepeat(int time) {

        TextView timesRepeatView = (TextView) findViewById(R.id.repeat_time);
        timesRepeatView.setText(String.valueOf(time));
    }


    //starts chronometer

    public void startTimerMove(View view) {


        Chronometer chrono = (Chronometer) findViewById(R.id.chrono);
        chrono.setTextSize(120);
        chrono.setBase(SystemClock.elapsedRealtime());
        chrono.start();
        countTimerMove(chrono, timeMove + 1);
        PlayBeep();
//disable buttons
        Button startButton = (Button) findViewById(R.id.start_button);
        startButton.setEnabled(false);
        Button movePlusButton = (Button) findViewById(R.id.move_plus_button);
        movePlusButton.setEnabled(false);
        Button moveSubButton = (Button) findViewById(R.id.move_sub_button);
        moveSubButton.setEnabled(false);
        Button restPlusButton = (Button) findViewById(R.id.rest_plus_button);
        restPlusButton.setEnabled(false);
        Button restSubButton = (Button) findViewById(R.id.rest_sub_button);
        restSubButton.setEnabled(false);
        Button setPlusButton = (Button) findViewById(R.id.set_plus_button);
        setPlusButton.setEnabled(false);
        Button setSubButton = (Button) findViewById(R.id.set_sub_button);
        setSubButton.setEnabled(false);


    }

    public void startTimerRest(View view) {

        Chronometer chrono = (Chronometer) findViewById(R.id.chrono);
        chrono.setBase(SystemClock.elapsedRealtime());
        chrono.start();
        countTimerRest(chrono, timeRest + 1);
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


}