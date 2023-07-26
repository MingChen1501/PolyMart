package com.mingchencodelab.polymart.lab5;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RequestProduct {
    @GET("get_all_product.php")
    Call<ResponseSelectProduct> selectProduct();

    @FormUrlEncoded
    @POST("update_product.php")
    Call<ResponseUpdateProduct> updateProduct(
            @Field("pid") String pid,
            @Field("name") String name,
            @Field("price") String price,
            @Field("description") String description
    );

    @FormUrlEncoded
    @POST("delete_product.php")
    Call<ResponseDeleteProduct> deleteProduct(
            @Field("pid") String pid
    );
}
