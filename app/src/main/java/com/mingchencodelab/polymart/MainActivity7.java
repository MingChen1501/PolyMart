package com.mingchencodelab.polymart;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity7 extends AppCompatActivity {
    RequestQueue requestQueue;
    String host = "http://172.188.32.218/";
    String url = host + "/posts";
    Button btnSelect, btnInsert, btnUpdate, btnDelete;
    EditText edtId, edtTitle, edtContent;
    TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        btnSelect = findViewById(R.id.lab7btnselect);
        btnInsert = findViewById(R.id.lab7btninsert);
        btnUpdate = findViewById(R.id.lab7btnupdate);
        btnDelete = findViewById(R.id.lab7btndelete);
        edtId = findViewById(R.id.lab7edtId);
        edtTitle = findViewById(R.id.lab7edtTitle);
        edtContent = findViewById(R.id.lab7edtContent);
        txtResult = findViewById(R.id.lab7txtResult);

        requestQueue = Volley.newRequestQueue(this);

        btnSelect.setOnClickListener(v -> {
            selectVolley();
        });
        btnInsert.setOnClickListener(v -> {
            insertVolley();
        });
        btnUpdate.setOnClickListener(v -> {
            updateVolley();
        });
        btnDelete.setOnClickListener(v -> {
            deleteVolley();
        });

    }

    private void deleteVolley() {
        txtResult.setText("");
        StringRequest request = new StringRequest(Request.Method.DELETE,
                url + "/" + edtId.getText().toString(),
                response -> {
                    txtResult.setText("success");
                }, error -> {
            txtResult.setText(error.getMessage());
        });
        requestQueue.add(request);

    }

    private void updateVolley() {
        txtResult.setText("");
        StringRequest request = new StringRequest(Request.Method.PUT,
                url + "/" + edtId.getText().toString(),
                response -> {
                    txtResult.setText("success");
                }, error -> {
            txtResult.setText(error.getMessage());
        }) {
            @Override
            protected java.util.Map<String, String> getParams() {
                java.util.Map<String, String> params = new java.util.HashMap<>();
                params.put("title", edtTitle.getText().toString());
                params.put("content", edtContent.getText().toString());
                return params;
            }
        };
        requestQueue.add(request);
    }

    private void insertVolley() {
        txtResult.setText("");
        StringRequest request = new StringRequest(Request.Method.POST,
                url,
                response -> {
                    txtResult.setText("success");
                }, error -> {
            txtResult.setText(error.getMessage());
        }) {
            @Override
            protected java.util.Map<String, String> getParams() {
                java.util.Map<String, String> params = new java.util.HashMap<>();
                params.put("title", edtTitle.getText().toString());
                params.put("content", edtContent.getText().toString());
                return params;
            }
        };
        requestQueue.add(request);
    }

    private void selectVolley() {
        txtResult.setText("");
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                url,
                null,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.optJSONObject(i);
                        txtResult.append(jsonObject.optString("id") + "\n");
                        txtResult.append(jsonObject.optString("title") + "\n");
                        txtResult.append(jsonObject.optString("content") + "\n\n\n");
                    }
                }, error -> {
            txtResult.setText(error.getMessage());
        });
        requestQueue.add(request);
    }
}