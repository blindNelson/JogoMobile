package com.example.jogomobille;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
<<<<<<< Updated upstream

public class MainActivity extends AppCompatActivity {

    Button btn;
=======
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jogomobille.utils.Session;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    ImageButton illyButton;
    TextView usernameTxt;
>>>>>>> Stashed changes

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

        /*Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);*/

<<<<<<< Updated upstream
        btn = findViewById(R.id.button);
=======
        startButton = findViewById(R.id.startButton);
        illyButton =  findViewById(R.id.illyButton);
        usernameTxt = findViewById(R.id.usernameTxt);

        Session session = new Session(MainActivity.this);
        if(session.getIdUsuario() != 0)
            usernameTxt.setText(session.getNomeUsuario());
>>>>>>> Stashed changes

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< Updated upstream
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);

=======
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
>>>>>>> Stashed changes
            }
        });
    }
}