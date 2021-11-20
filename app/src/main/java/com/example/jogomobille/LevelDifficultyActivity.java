package com.example.jogomobille;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.example.jogomobille.utils.LevelDifficulty;

public class LevelDifficultyActivity extends AppCompatActivity {

    SeekBar levelBar, difficultyBar;
    Button startGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_difficulty);

        levelBar = findViewById(R.id.levelBar);
        difficultyBar = findViewById(R.id.difficultyBar);
        startGameButton = findViewById(R.id.startGameButton);

        LevelDifficulty levelDifficulty = new LevelDifficulty(LevelDifficultyActivity.this);
        levelBar.setProgress(levelDifficulty.getLevel());
        difficultyBar.setProgress(levelDifficulty.getDifficulty());

        levelBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                levelDifficulty.setLevel(levelBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        difficultyBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                levelDifficulty.setDifficulty(difficultyBar.getProgress());
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