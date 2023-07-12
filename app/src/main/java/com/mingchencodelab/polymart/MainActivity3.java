package com.mingchencodelab.polymart;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity3 extends AppCompatActivity{
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
                Thread myThread= new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap=loadAnh("https://upload.wikimedia.org/wikipedia/commons/thumb/1/10/Unicode_0x0033.svg/1200px-Unicode_0x0033.svg.png");
                        imageView.post(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(bitmap);
                                textView.setText("thanh cong");
                            }
                        });
                    }
                });
                myThread.start();
            }
        });
    }
    private void mappingView() {
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button);
    }
    private Bitmap loadAnh(String link){
        Bitmap bitmap= null;
        try {
            URL url= new URL(link);
            bitmap=BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}