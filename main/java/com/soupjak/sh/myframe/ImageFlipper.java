package com.soupjak.sh.myframe;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.AdapterViewFlipper;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by SH on 2017-03-14.
 */

public class ImageFlipper extends AdapterViewFlipper {
    private Context context;
    private LayoutInflater inflater;
    private AdapterViewFlipper viewFlipper;
    private ArrayList<File> files;
    private Handler mHandler = new Handler();
    Random mRandom = new Random();

    private static final int iDelay = 10000;

    private Runnable mFlip = new Runnable() {
        @Override
        public void run() {
            //Log.d("Flipper", "FileSize = " + files.size());
            int next = mRandom.nextInt(files.size());
            viewFlipper.setDisplayedChild(next);
            mHandler.postDelayed(this, iDelay);
        }
    };

    public ImageFlipper(Context context) {
        super(context);
        this.context = context;
        inflater = LayoutInflater.from(context);
        init();
    }

    public ImageFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        inflater = LayoutInflater.from(context);
        init();

    }

    void init(){
        inflater.inflate(R.layout.flipper_view, null);
        viewFlipper = (AdapterViewFlipper)findViewById(R.id.viewflipper);
    }

    public void FlipStart(){

        //Find Media
        MediaFounder mFounder = new MediaFounder(context.getApplicationContext());
        files = mFounder.getFiles();

        CustomAdapter cAdapter = new CustomAdapter(context.getApplicationContext(), files);
        if(cAdapter != null)
            viewFlipper.setAdapter(cAdapter);
        else
            Log.d("Flipper", "cAdapter is nUll!!");

        mHandler.postDelayed(mFlip, iDelay);
        Log.d("Flipper", "MainFunction");
    }
}
