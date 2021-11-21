package com.example.jogomobille;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jogomobille.utils.Ranking;

public class TelaDeVitoriaActivity extends AppCompatActivity {
    Button restartButton, scoreButton, backButton;
    TextView scoreTxt;
    @SuppressLint("SetTextI18n")
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
        setContentView(R.layout.activity_tela_de_vitoria);

        Intent intent = getIntent();
        Ranking score = (Ranking)intent.getSerializableExtra("score");

        restartButton = findViewById(R.id.restartButton);
        scoreButton = findViewById(R.id.scoreButton);
        backButton = findViewById(R.id.backButton);
        scoreTxt = findViewById(R.id.scoreTxt);

        scoreTxt.setText(""+score.getScore());
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaDeVitoriaActivity.this, LevelDifficultyActivity.class);
                startActivity(intent);
            }
        });
        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaDeVitoriaActivity.this, MainActivity.class); //ScoreActivity
                startActivity(intent);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaDeVitoriaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}