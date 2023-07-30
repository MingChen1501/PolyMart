package com.mingchencodelab.polymart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONObject;

public class MainActivity6 extends AppCompatActivity {
    RequestQueue requestQueue;
    String host = "http://localhost:3000";
    String url = host + "/posts";
    Button btnGet;
    Button btnGet2;
    TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        btnGet = findViewById(R.id.lab6_btn_get);
        btnGet2 = findViewById(R.id.lab6_btn_get_array);
        txtResult = findViewById(R.id.lab6_txt_res);
        requestQueue = Volley.newRequestQueue(this);
        btnGet.setOnClickListener(v -> {
            getStringRequest();
        });
        btnGet2.setOnClickListener(v -> {
            getArrayRequest();
        });

    }

    private void getArrayRequest() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                url,
                null,
                response -> {
            for (int i = 0; i < response.length(); i++) {
                JSONObject jsonObject = response.optJSONObject(i);
                txtResult.append(jsonObject.optString("title") + "\n");
                txtResult.append(jsonObject.optString("content") + "\n");
            }
        }, error -> {
            txtResult.setText(error.getMessage());
        });
        requestQueue.add(request);
    }

    private void getStringRequest() {
        StringRequest request = new StringRequest(Request.Method.GET, url + "/1", response -> {
            txtResult.setText(response);
        }, error -> {
            txtResult.setText(error.getMessage());
        });
        requestQueue.add(request);
    }
}