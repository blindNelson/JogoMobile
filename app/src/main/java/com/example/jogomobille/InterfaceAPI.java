package com.example.jogomobille;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterfaceAPI {

    @POST("autenticacao")
    Call<LoginResponse> login(@Body LoginCadastroRequest loginCadastroRequest);

    @POST("cadastro")
    Call<String> cadastro(@Body LoginCadastroRequest loginCadastroRequest);
}
