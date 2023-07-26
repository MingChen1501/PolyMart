package com.mingchencodelab.polymart.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingchencodelab.polymart.R;
import com.mingchencodelab.polymart.lab1.LoadImageAsyncTask;
import com.mingchencodelab.polymart.lab1.LoadImageInterface;

public class MainActivity extends AppCompatActivity implements LoadImageInterface, View.OnClickListener {
    private TextView textView;
    private ImageView imageView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mappingView();
        setLogicButton();

    }

    private void setLogicButton() {
        button.setOnClickListener(this);
    }

    private void mappingView() {
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button);
    }

    @Override
    public void onLoadImage(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public void onError() {
        textView.setText("error while loading image");
    }

    @Override
    public void onClick(View view) {
        new LoadImageAsyncTask(this, this)
                .execute("https://upload.wikimedia.org/wikipedia/commons/thumb/d/dc/Unicode_0x0031.svg/1200px-Unicode_0x0031.svg.png");
    }
}