package com.example.jogomobille.utils;

import com.example.jogomobille.utils.LoginCadastroRequest;
import com.example.jogomobille.utils.LoginResponse;
import com.example.jogomobille.utils.RankingRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface InterfaceAPI {

    @POST("autenticacao")
    Call<LoginResponse> login(@Body LoginCadastroRequest loginCadastroRequest);

    @POST("cadastro")
    Call<String> cadastro(@Body LoginCadastroRequest loginCadastroRequest);

    @POST("rankings")
    Call<String> pontuar(@Body RankingRequest loginCadastroRequest);

    @GET("orderRankings")
    Call<List<Ranking>> getRankings();

    @GET("usuariosByName/{nomeUsuario}")
    Call<Usuario> getUsuarioByName(@Path("nomeUsuario") String nomeUsuario);

    @GET("orderRankingsByFase/{fase}")
    Call<List<Ranking>> getRankingsByLevel(@Path("fase") int fase);

    @GET("orderRankingsByUser/{id}")
    Call<List<Ranking>> getRankingsByUser(@Path("id") int id);

    @GET("orderRankingsByUserFase/{id}/{fase}")
    Call<List<Ranking>> getRankingsByUserLevel(@Path("id") int id, @Path("fase") int fase);
}
