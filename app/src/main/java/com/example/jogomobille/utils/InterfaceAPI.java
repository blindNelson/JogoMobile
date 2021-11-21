package com.example.jogomobille.utils;

import com.example.jogomobille.utils.LoginCadastroRequest;
import com.example.jogomobille.utils.LoginResponse;
import com.example.jogomobille.utils.RankingRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterfaceAPI {

    @POST("autenticacao")
    Call<LoginResponse> login(@Body LoginCadastroRequest loginCadastroRequest);

    @POST("cadastro")
    Call<String> cadastro(@Body LoginCadastroRequest loginCadastroRequest);

    @POST("rankings")
    Call<String> pontuar(@Body RankingRequest loginCadastroRequest);
}
