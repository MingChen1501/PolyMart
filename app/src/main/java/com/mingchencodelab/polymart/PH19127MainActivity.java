package com.mingchencodelab.polymart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class PH19127MainActivity extends AppCompatActivity {
    private TextView textViewMinhTCPH19127;
    private Button buttonPH19127MinhTC, buttonPH19127MinhTC2;
    private RequestQueue requestQueuePH19127MinhTC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ph19127_main);
        textViewMinhTCPH19127 = findViewById(R.id.minhtcph19127_b1_tv_result);
        buttonPH19127MinhTC = findViewById(R.id.minhtcph19127_b1_btn_get);
        buttonPH19127MinhTC2 = findViewById(R.id.minhtcph19127_b1_btn_b2);
        requestQueuePH19127MinhTC = Volley.newRequestQueue(this);
        buttonPH19127MinhTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDataWithVolley();
            }
        });
        buttonPH19127MinhTC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PH19127MainActivity.this, MainActivity2PH19127MinhTC.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadDataWithVolley() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("https://jsonplaceholder.typicode.com/albums", response -> {
            Album album = new Album();
            album.setNamePH19127MinhTC(response.toString());
            textViewMinhTCPH19127.setText(album.getNamePH19127MinhTC());
        }, error -> textViewMinhTCPH19127.setText(error.getMessage()));
        requestQueuePH19127MinhTC.add(jsonArrayRequest);
    }
}