package com.mingchencodelab.polymart;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class LoadImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
    private final LoadImageInterface loadImageInterface;
    private final Context context;

    public LoadImageAsyncTask(LoadImageInterface loadImageInterface, Context context) {
        this.loadImageInterface = loadImageInterface;
        this.context = context;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        try {
            return BitmapFactory.decodeStream((InputStream) new URL(strings[0]).getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (bitmap != null) {
            loadImageInterface.onLoadImage(bitmap);
        } else {
            loadImageInterface.onError();
        }

    }
}
