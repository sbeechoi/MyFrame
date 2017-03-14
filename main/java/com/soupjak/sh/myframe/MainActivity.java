package com.soupjak.sh.myframe;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterViewFlipper;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private AdapterViewFlipper aVF;
    private ArrayList<File> files;
    private static final int iDelay2 = 10000;

    Handler mHandler = new Handler();
    Random mRandom = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set hide status bar.
        View decoreView = getWindow().getDecorView();
        int uiOptions = (View.SYSTEM_UI_FLAG_FULLSCREEN);// | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        decoreView.setSystemUiVisibility(uiOptions);

        //set hid Action Bar.
        getSupportActionBar().hide();

        //Find Media
        MediaFounder mFounder = new MediaFounder(getApplicationContext());
        files = mFounder.getFiles();

        //set AdapterView Flipper
        aVF = (AdapterViewFlipper)findViewById(R.id.avf);
        CustomAdapter cAdapter = new CustomAdapter(getApplicationContext(), files);
        if(cAdapter != null)
            aVF.setAdapter(cAdapter);
        else
            Log.d("Flipper", "cAdapter is nUll!!");

        mHandler.postDelayed(mFlip, iDelay2);
        Log.d("Flipper", "MainFunction");
        //aVF.setAutoStart(true);
    }

    private Runnable mFlip = new Runnable() {
        @Override
        public void run() {
            //Log.d("Flipper", "FileSize = " + files.size());
            int next = mRandom.nextInt(files.size());
            aVF.setDisplayedChild(next);
            mHandler.postDelayed(this, iDelay2);
        }
    };

}
