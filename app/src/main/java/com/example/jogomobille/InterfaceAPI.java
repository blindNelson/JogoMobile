package com.example.jogomobille;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface InterfaceAPI {

    @POST("authenticate")
    Call<LoginResponse> checkLogin(@Body LoginRequest loginRequest);

}
