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
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.jogomobille.utils.LevelDifficulty;

public class LevelDifficultyActivity extends AppCompatActivity {

    SeekBar levelBar, difficultyBar;
    Button startGameButton;
    TextView levelTxt, difficultyTxt;

    public static String getDifficultyTxt(int difficulty)
    {
        switch (difficulty)
        {
            case 0: return "VERY EASY";
            case 1: return "EASY";
            case 2: return "NORMAL";
            case 3: return "HARD";
            default:return "ULTRA HARD";
        }
    }
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
        setContentView(R.layout.activity_level_difficulty);

        levelBar = findViewById(R.id.levelBar);
        difficultyBar = findViewById(R.id.difficultyBar);
        startGameButton = findViewById(R.id.startGameButton);
        levelTxt = findViewById(R.id.levelTxt);
        difficultyTxt = findViewById(R.id.difficultyTxt);

        LevelDifficulty levelDifficulty = new LevelDifficulty(LevelDifficultyActivity.this);
        levelBar.setProgress(levelDifficulty.getLevel());
        difficultyBar.setProgress(levelDifficulty.getDifficulty());
        levelTxt.setText("LEVEL " + levelBar.getProgress());
        difficultyTxt.setText("DIFFICULTY " + getDifficultyTxt(difficultyBar.getProgress()));

        levelBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                levelDifficulty.setLevel(levelBar.getProgress());
                levelTxt.setText("LEVEL " + levelBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        difficultyBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                levelDifficulty.setDifficulty(difficultyBar.getProgress());
                difficultyTxt.setText("DIFFICULTY " + getDifficultyTxt(difficultyBar.getProgress()));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LevelDifficultyActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }
}