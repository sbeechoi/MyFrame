package com.soupjak.sh.myframe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterViewFlipper;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private AdapterViewFlipper aVF;
    int[] images = {R.drawable.a, R.drawable.b, R.drawable.c};
    private File files[];

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
        aVF.setFlipInterval(10000);
        aVF.setAutoStart(true);
        Log.d("Flipper", "MainFunction");

    }

}
