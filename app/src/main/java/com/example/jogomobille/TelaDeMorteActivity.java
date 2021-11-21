package com.example.jogomobille;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class TelaDeMorteActivity extends AppCompatActivity {
    Button restartButton, scoreButton, backButton;

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
        setContentView(R.layout.activity_tela_de_morte);
        Log.d("TelaDeMorte.java", "onCreate: sim");
        restartButton = findViewById(R.id.restartButton);
        scoreButton = findViewById(R.id.scoreButton);
        backButton = findViewById(R.id.backButton);

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaDeMorteActivity.this, LevelDifficultyActivity.class);
                startActivity(intent);
            }
        });
        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaDeMorteActivity.this, ScoreboardActivity.class); //ScoreActivity
                startActivity(intent);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaDeMorteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}