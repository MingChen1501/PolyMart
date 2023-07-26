package com.mingchencodelab.polymart;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mingchencodelab.polymart.lab4.Insert;
import com.mingchencodelab.polymart.lab4.Product;
import com.mingchencodelab.polymart.lab4.ResponseInsert;
import com.mingchencodelab.polymart.lab4.ResponseSelect;
import com.mingchencodelab.polymart.lab4.Select;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity4 extends AppCompatActivity {
    ArrayList<Product> products;
    String result;
    EditText txt1, txt2, txt3;
    Button btnInsert, btnSelect;
    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo4);
        txt1 = findViewById(R.id.demo41Txt1);
        txt2 = findViewById(R.id.demo41Txt2);
        txt3 = findViewById(R.id.demo41Txt3);
        btnInsert = findViewById(R.id.demo41BtnInsert);
        btnSelect = findViewById(R.id.demo41BtnSelect);
        tvResult = findViewById(R.id.demo41Tv);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertRetrofit();
            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectRetrofit();
            }
        });
    }
    void insertRetrofit() {
        Product product = new Product();
        product.setName(txt1.getText().toString());
        product.setPrice(txt2.getText().toString());
        product.setDescription(txt3.getText().toString());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://batdongsanabc.000webhostapp.com/mob403lab4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Insert insert = retrofit.create(Insert.class);
        Call<ResponseInsert> call = insert.insertProduct(
                product.getName(),
                product.getPrice(),
                product.getDescription());
        call.enqueue(new Callback<ResponseInsert>() {
            @Override
            public void onResponse(Call<ResponseInsert> call, Response<ResponseInsert> response) {
                ResponseInsert responseInsert = response.body();
                tvResult.setText(responseInsert.getMessage());

            }

            @Override
            public void onFailure(Call<ResponseInsert> call, Throwable t) {
                tvResult.setText(t.getMessage());
            }
        });
    }
    void selectRetrofit() {
        Retrofit retrofit =  new Retrofit.Builder()
                .baseUrl("https://batdongsanabc.000webhostapp.com/mob403lab4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Select select = retrofit.create(Select.class);
        Call<ResponseSelect> call = select.selectData();
        call.enqueue(new Callback<ResponseSelect>() {
            @Override
            public void onResponse(Call<ResponseSelect> call, Response<ResponseSelect> response) {
                ResponseSelect responseSelect = response.body();
                products = new ArrayList<>(Arrays.asList(responseSelect.getProducts()));
                for (Product p : products) {
                    result += p.toString();
                }
                Log.d(TAG, "onResponse: " + result);
                tvResult.setText(result);
            }

            @Override
            public void onFailure(Call<ResponseSelect> call, Throwable t) {
                tvResult.setText(t.getMessage());
            }
        });
    }
}