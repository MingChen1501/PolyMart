package com.mingchencodelab.polymart;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Select {
    @GET("get_all_product.php")
    Call<ResponseSelect> selectData();
}
