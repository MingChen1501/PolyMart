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

public class MainActivity6 extends AppCompatActivity {
    RequestQueue requestQueue;
    String host = "http://172.188.32.218/";
    String url = host + "/posts";
    EditText edtId;
    Button btnTimBaiVietVoiId;
    Button btnDanhSachBaiViet;
    TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        btnTimBaiVietVoiId = findViewById(R.id.lab6_btn_get);
        btnDanhSachBaiViet = findViewById(R.id.lab6_btn_get_array);
        edtId = findViewById(R.id.lab6_et_id);
        txtResult = findViewById(R.id.lab6_txt_res);
        requestQueue = Volley.newRequestQueue(this);
        btnTimBaiVietVoiId.setOnClickListener(v -> {
            getStringRequest();
        });
        btnDanhSachBaiViet.setOnClickListener(v -> {
            getArrayRequest();
        });

    }

    private void getArrayRequest() {
        txtResult.setText("");
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                url,
                null,
                response -> {
            for (int i = 0; i < response.length(); i++) {
                JSONObject jsonObject = response.optJSONObject(i);
                txtResult.append(jsonObject.optString("id") + "\n");
                txtResult.append(jsonObject.optString("title") + "\n");
            }
        }, error -> {
            txtResult.setText(error.getMessage());
        });
        requestQueue.add(request);
    }

    private void getStringRequest() {
        String id = edtId.getText().toString();
        txtResult.setText("");
        StringRequest request = new StringRequest(Request.Method.GET, url + "/" + id, response -> {
            txtResult.setText(response);
        }, error -> {
            txtResult.setText(error.getMessage());
        });
        requestQueue.add(request);
    }
}