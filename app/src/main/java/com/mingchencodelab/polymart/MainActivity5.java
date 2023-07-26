package com.mingchencodelab.polymart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mingchencodelab.polymart.lab5.Product;
import com.mingchencodelab.polymart.lab5.RequestProduct;
import com.mingchencodelab.polymart.lab5.ResponseDeleteProduct;
import com.mingchencodelab.polymart.lab5.ResponseSelectProduct;
import com.mingchencodelab.polymart.lab5.ResponseUpdateProduct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity5 extends AppCompatActivity {
    private static final String TAG = Class.class.getName();
    EditText  editTextId, editTextName, editTextPrice, editTextDescription;
    Button buttonSelect, buttonUpdate, buttonDelete;
    TextView textViewResult;
    List<Product> products;
    String result;

    Retrofit retrofit;
    RequestProduct requestProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        mappingView();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://batdongsanabc.000webhostapp.com/mob403lab5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        requestProduct = retrofit.create(RequestProduct.class);

        buttonSelect.setOnClickListener(v -> selectRetrofit());
        buttonUpdate.setOnClickListener(v -> updateRetrofit());
        buttonDelete.setOnClickListener(v -> deleteRetrofit());
    }

    private void deleteRetrofit() {
        Log.d(TAG, "deleteRetrofit: id to delete" + editTextId.getText().toString());
        Call<ResponseDeleteProduct> call = requestProduct
                                            .deleteProduct(editTextId.getText().toString());
        call.enqueue(new Callback<ResponseDeleteProduct>() {
            @Override
            public void onResponse(@NonNull Call<ResponseDeleteProduct> call, @NonNull Response<ResponseDeleteProduct> response) {
                ResponseDeleteProduct responseDeleteProduct = response.body();
                Log.d(TAG, "onResponse: " + response);
                Log.d(TAG, "onResponse: " + responseDeleteProduct);
                if (responseDeleteProduct != null) {
                    Log.d(TAG, "onResponse: " + responseDeleteProduct.getMessage());
                }
                if (responseDeleteProduct != null) {
                    textViewResult.setText(responseDeleteProduct.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseDeleteProduct> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void updateRetrofit() {
        Call<ResponseUpdateProduct> call = requestProduct.updateProduct(
                editTextId.getText().toString(),
                editTextName.getText().toString(),
                editTextPrice.getText().toString(),
                editTextDescription.getText().toString());
        call.enqueue(new Callback<ResponseUpdateProduct>() {
            @Override
            public void onResponse(Call<ResponseUpdateProduct> call, Response<ResponseUpdateProduct> response) {
                ResponseUpdateProduct responseUpdateProduct = response.body();
                if (responseUpdateProduct != null) {
                    textViewResult.setText(responseUpdateProduct.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateProduct> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

    }

    //mapping Widget to variable
    //id is in activity_main5.xml
    private void mappingView() {
        editTextId = findViewById(R.id.lab5_txt_id);
        editTextName = findViewById(R.id.lab5_txt_name);
        editTextPrice = findViewById(R.id.lab5_txt_price);
        editTextDescription = findViewById(R.id.lab5_txt_description);
        buttonSelect = findViewById(R.id.lab5_btn_select);
        buttonUpdate = findViewById(R.id.lab5_btn_update);
        buttonDelete = findViewById(R.id.lab5_btn_delete);
        textViewResult = findViewById(R.id.lab5_tv_result);
    }
    //TODO: create method to select, update, delete
    // method to select Product with Retrofit
    private void selectRetrofit() {
        Call<ResponseSelectProduct> call = requestProduct.selectProduct();
        call.enqueue(new Callback<ResponseSelectProduct>() {
            @Override
            public void onResponse(@NonNull Call<ResponseSelectProduct> call, @NonNull Response<ResponseSelectProduct> response) {
                ResponseSelectProduct responseSelectProduct = response.body();
                products = new ArrayList<>(Arrays.asList(responseSelectProduct.getProducts()));
                Log.d(TAG, "onResponse: " + products);
                result = "";
                for (Product product : products) {
                    result += product.getPid() + "\n" + product.getName() + "\n" + product.getPrice() + "\n" + product.getDescription() + "\n\n";
                }
                textViewResult.setText(result);
            }

            @Override
            public void onFailure(Call<ResponseSelectProduct> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}