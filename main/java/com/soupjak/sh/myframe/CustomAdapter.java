package com.soupjak.sh.myframe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by SH on 2017-03-12.
 */

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private int[] images;
    private String path;
    private File[] files;
    private int counts;
    private LayoutInflater inflater;

    public CustomAdapter(Context context) {
        this.context = context;
        this.counts = 0;
        inflater = (LayoutInflater.from(context));
    }

    public CustomAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
        this.counts = images.length;
        inflater = (LayoutInflater.from(context));
    }

    public CustomAdapter(Context context, File[] files) {
        this.context = context;
        this.files = files;
        this.counts = files.length;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return counts;
    }

    @Override
    public Object getItem(int position) {
        return files[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.list_item, null);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.Image);

        if(files!=null) {
            Bitmap myBitmap = BitmapFactory.decodeFile(files[position].getPath());
            imageView.setImageBitmap(myBitmap);
        }else {
            Log.d("Flipper", "Files[] is null!");
        }

        Log.d("Flipper", "Next Image");
        return convertView;
    }
}
