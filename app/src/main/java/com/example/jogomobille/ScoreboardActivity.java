package com.example.jogomobille;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jogomobille.utils.InterfaceAPI;
import com.example.jogomobille.utils.LoginResponse;
import com.example.jogomobille.utils.Ranking;
import com.example.jogomobille.utils.RetrofitClientInstance;
import com.example.jogomobille.utils.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ScoreboardActivity extends AppCompatActivity {

    ListView scoreView;
    Button backButton;
    EditText nicknameTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set window to fullscreen (will hide status bar)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = this.getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        getSupportActionBar().hide();
        setContentView(R.layout.activity_scoreboard);

        nicknameTxt = findViewById(R.id.nicknameTxt);
        backButton = findViewById(R.id.backButton);
        scoreView = findViewById(R.id.scoreView);
        getRankings(null);

        nicknameTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
                final InterfaceAPI api = retrofit.create(InterfaceAPI.class);
                Call<Usuario> call = api.getUsuarioByName(nicknameTxt.getText().toString());

                call.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        Usuario usuario = response.body();
                        getRankings(usuario);
                    }
                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        getRankings(null);

                    }
                });
            }
        });
    }
    private void getRankings(Usuario usuario) {
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        final InterfaceAPI api = retrofit.create(InterfaceAPI.class);
        Call<List<Ranking>> call;
        if(usuario == null || usuario.getNomeUsuario().equals(""))
            call = api.getRankings();
        else
            call = api.getRankingsByUser(usuario.getIdUsuario());

        call.enqueue(new Callback<List<Ranking>>() {
            @Override
            public void onResponse(Call<List<Ranking>> call, Response<List<Ranking>> response) {
                List<Ranking> rankingArray = response.body();
                String[] rankingStrings = new String[rankingArray.size()];

                rankingStrings[0] = "N°      Pontuação      Fase      Nome";
                for (int i = 1; i < rankingArray.size(); i++) {
                    rankingStrings[i] = padRight(i+"", 5) + "     " + padRight((rankingArray.get(i).getPontuacao() + ""), 10)
                            + "             " + padRight((rankingArray.get(i).getFase()+""), 3)
                            + "          " + padRight((rankingArray.get(i).getUsuario().getNomeUsuario()+""), 50);
                }

                scoreView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.score_list_design, rankingStrings));
            }

            @Override
            public void onFailure(Call<List<Ranking>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
    }
    public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

    public static String padLeft(String s, int n) {
        return String.format("%" + n + "s", s);
    }
}