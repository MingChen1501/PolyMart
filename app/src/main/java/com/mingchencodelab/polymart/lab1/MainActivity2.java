package com.mingchencodelab.polymart.lab1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mingchencodelab.polymart.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity2 extends AppCompatActivity{
    private TextView textView;
    private ImageView imageView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mappingView();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            new ImageView22AsyncTask().execute("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Unicode_0x0032.svg/800px-Unicode_0x0032.svg.png");
            }
        });
    }
    private void mappingView() {
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button);
    }
    class ImageView22AsyncTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                return BitmapFactory.decodeStream((InputStream) new URL(strings[0]).getContent());
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(bitmap!=null){
                imageView.setImageBitmap(bitmap);
            }else {
                textView.setText("co loi");
            }
        }
    }
}