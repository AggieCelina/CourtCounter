package com.example.android.courtcounter;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int timeMove = 0;
    int timeRest = 0;
    int timesRepeat = 0;


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

    public void startTimer(View view) {

        Chronometer chrono = (Chronometer) findViewById(R.id.chrono);
        chrono.setBase(SystemClock.elapsedRealtime());
        chrono.start();
        countTimer(chrono);


    }

    //count ticks and break when ticks reach the value set by user in timeMove
    public void countTimer(Chronometer chronometer) {


        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
//the OnChronometerTickListener method gets called every time the chronometer has incremented.


            @Override
            public void onChronometerTick(Chronometer chronometer) {


                String timeMoveString = String.format("%02d:%02d", timeMove / 60, timeMove % 60);
                //The %02d means: "Format as a decimal number using at least 2 digits, and pad with 0 if less than 2 digits"
                //Note, that using the modulo 60 pTime % 60 gets the remainder of seconds when dividing by 60.
                // This is short hand for pTime - (ptime / 60) * 60
                String currentTime = chronometer.getText().toString();
                if (currentTime.equals(timeMoveString)) //put time according to you
                {
                    chronometer.stop();
                    chronometer.setText("00:00");


                }

            }
        });


    }


}