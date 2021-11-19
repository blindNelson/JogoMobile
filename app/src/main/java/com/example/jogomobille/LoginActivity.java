package com.example.jogomobille;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jogomobille.utils.Session;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    private EditText usernameTxt, passwordTxt;
    private String username,password;
    private TextView cadastroTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = findViewById(R.id.loginButton);
        usernameTxt = findViewById((R.id.usernameTxt));
        passwordTxt = findViewById((R.id.passwordTxt));
        cadastroTxt = findViewById((R.id.cadastroTxt));

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameTxt.getText().toString();
                password = passwordTxt.getText().toString();
                Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
                final InterfaceAPI api = retrofit.create(InterfaceAPI.class);
                LoginRequest loginRequest = new LoginRequest(username,password);
                Call<LoginResponse> call = api.checkLogin(loginRequest);
                call.enqueue(new Callback<LoginResponse>() {

                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "deu certo", Toast.LENGTH_LONG).show();

                            LoginResponse loginResponse = response.body();
                            if (loginResponse.getMessage().equals("Authentication Successful!")) {
                                Session session = new Session(LoginActivity.this);
                                session.setIdNome(loginResponse.idUsuario,loginResponse.nomeUsuario);
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Usuario ou senha Inválidos.", Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Não obteve resposta da API", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.e("TAG", t.toString());
                        t.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Não Conseguiu conectar com a API", Toast.LENGTH_LONG).show();

                    }
                });
            }
        });

        cadastroTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}