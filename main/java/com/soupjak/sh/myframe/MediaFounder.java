package com.soupjak.sh.myframe;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by SH on 2017-03-13.
 */

public class MediaFounder {
    private static final String TAG = "Flipper";
    private Context context;
    private Uri dirPath;
    private File[] files;
    private ArrayList<File> jpgList = new ArrayList<File>();

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
                Environment.DIRECTORY_DCIM;
        Log.d(TAG, "Path = " + path);
        File root = new File(path);
        getJpgRecursively(root);
    }

    public void getJpgRecursively(File root){
        File[] tmpFiles = root.listFiles();
        if(tmpFiles != null) {
            Log.d(TAG, "Size = " + tmpFiles.length);
            for(int i=0; i<tmpFiles.length; ++i)
            {
                Log.d(TAG, "path = " + tmpFiles[i].getPath());
                if(tmpFiles[i].isDirectory()){
                    if(tmpFiles[i].getPath().contains(".thumbnail")){
                        continue;
                    }
                    Log.d(TAG, "Folder = " + tmpFiles[i].getPath());
                    getJpgRecursively(tmpFiles[i]);
                }
                else if(tmpFiles[i].getPath().contains(".jpg") || tmpFiles[i].getPath().contains(".JPG")) {
                    Log.d("TAG", "FileName : " + tmpFiles[i].getPath());
                    jpgList.add(tmpFiles[i]);
                }
            }
        }
        else
            Log.d(TAG, "files is NULl!!");
    }

    public ArrayList<File> getFiles() {
        return jpgList;
    }
}
