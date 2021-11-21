package com.example.jogomobille;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.jogomobille.game.Game;
import com.example.jogomobille.utils.InterfaceAPI;
import com.example.jogomobille.utils.LevelDifficulty;
import com.example.jogomobille.utils.RankingRequest;
import com.example.jogomobille.utils.RetrofitClientInstance;
import com.example.jogomobille.utils.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Main activity is the entry point to our application
 */
public class GameActivity extends AppCompatActivity {

    private Game game;
    private LevelDifficulty levelDifficulty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        // Set window to fullscreen (will hide status bar)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = this.getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        getSupportActionBar().hide();
        levelDifficulty = new LevelDifficulty(GameActivity.this);
        // Set content view to game, so that objects in the Game class can be rendered to the screen
        game = new Game(this,levelDifficulty);

        game.setActivity2(this);

        setContentView(game);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        game.pause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
    }

    public void morreu() {
        Log.d("MAIANIANIAANNSAAAAAAAAAAAAAAAAAAAAAAAAA", "morreu: sim ele morreu");
        finish();
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        final InterfaceAPI api = retrofit.create(InterfaceAPI.class);
        Session session = new Session(GameActivity.this);

        RankingRequest rankingRequest = new RankingRequest(game.getScore(), levelDifficulty.getLevel(), session.getIdUsuario());
        Call<String> call = api.pontuar(rankingRequest);

        call.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful())
                    Toast.makeText(getApplicationContext(), "Pontuação Cadastrada!", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), "Crashou legal!", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("TAG", t.toString());
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), "Não Conseguiu conectar com a API", Toast.LENGTH_LONG).show();
            }
        });
        Bundle bundle = new Bundle();
        bundle.putSerializable("value", game.getScore());
        Intent intent = new Intent(GameActivity.this, TelaDeMorteActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void fugiu() {

    }
}