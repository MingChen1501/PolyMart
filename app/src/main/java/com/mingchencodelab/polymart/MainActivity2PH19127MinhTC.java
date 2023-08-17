package com.mingchencodelab.polymart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import org.json.JSONArray;

import java.util.List;

public class MainActivity2PH19127MinhTC extends AppCompatActivity {
    private Button buttonPH19127MinhTC, button2PH19127MinhTC, button3PH19127MinhTC;
    private TextView textViewPH19127MinhTC;
    private RequestQueue requestQueuePH19127MinhTC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_ph19127_minh_tc);
        buttonPH19127MinhTC = findViewById(R.id.button);
        button2PH19127MinhTC = findViewById(R.id.button2);
        button3PH19127MinhTC = findViewById(R.id.button3);
        textViewPH19127MinhTC = findViewById(R.id.textView);
        requestQueuePH19127MinhTC = Volley.newRequestQueue(this);
        buttonPH19127MinhTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yeucau1();
            }
        });
        button2PH19127MinhTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yeucau2();
            }
        });
        button3PH19127MinhTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yeucau3();
            }
        });
    }

    private void yeucau3() {

    }

    private void yeucau2() {


    }

    private void yeucau1() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("http://localhost:3000/BangDiemPH19127MinhTCs", response -> {
            Gson gson = new Gson();
            for (JsonElement jsonElement : response) {
                SinhVien sinhVien = gson.fromJson(jsonElement, SinhVien.class);
            }

        }, error -> textViewPH19127MinhTC.setText(error.getMessage()));
        requestQueuePH19127MinhTC.add(jsonArrayRequest);
    }
}