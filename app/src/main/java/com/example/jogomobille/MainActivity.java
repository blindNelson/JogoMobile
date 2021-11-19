package com.example.jogomobille;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jogomobille.utils.Session;


public class MainActivity extends AppCompatActivity {

    Button startButton, scoreButton, exitButton;
    ImageButton illyButton;
    TextView usernameTxt;

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

        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        scoreButton = findViewById(R.id.scoreButton);
        exitButton = findViewById(R.id.exitButton);

        illyButton =  findViewById(R.id.illyButton);
        usernameTxt = findViewById(R.id.usernameTxt);

        Session session = new Session(MainActivity.this);
        if(session.getIdUsuario() != 0)
            usernameTxt.setText(session.getNomeUsuario());

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(session.getIdUsuario() == 0)
                {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    startActivity(intent);
                }
            }
        });
        illyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}