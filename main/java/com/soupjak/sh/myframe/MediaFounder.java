package com.soupjak.sh.myframe;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by SH on 2017-03-13.
 */

public class MediaFounder {
    private static final String TAG = "Flipper";
    private Context context;
    private Uri dirPath;
    private File[] files;

    public MediaFounder(Context context) {
        this.context = context;
        init();
    }

    public MediaFounder(Uri dirPath, Context context) {
        this.dirPath = dirPath;
        this.context = context;
        init();
    }

    public void init(){
        String path = Environment.getExternalStorageDirectory().toString() +"/" +
                Environment.DIRECTORY_DCIM + "/Camera";
        Log.d(TAG, "Path = " + path);
        File dir = new File(path);
        files = dir.listFiles();
        if(files != null) {
            Log.d(TAG, "Size = " + files.length);
            for(int i=0; i<files.length; ++i)
            {
                if(files[i].getPath().contains(".jpg")) {
                    Log.d("Flipper", "FileName : " + files[i].getPath());
                }
                else{
                    files[i].delete();
                }

            }
        }
        else
            Log.d(TAG, "files is NULl!!");
    }

    public File[] getFiles() {
        return files;
    }
}
