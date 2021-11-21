package com.example.jogomobille;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jogomobille.utils.InterfaceAPI;
import com.example.jogomobille.utils.LoginCadastroRequest;
import com.example.jogomobille.utils.LoginResponse;
import com.example.jogomobille.utils.RetrofitClientInstance;
import com.example.jogomobille.utils.Session;

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
        // Set window to fullscreen (will hide status bar)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = this.getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        getSupportActionBar().hide();
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
                LoginCadastroRequest loginCadastroRequest = new LoginCadastroRequest(username,password);
                Call<LoginResponse> call = api.login(loginCadastroRequest);

                call.enqueue(new Callback<LoginResponse>() {

                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            LoginResponse loginResponse = response.body();
                            Session session = new Session(LoginActivity.this);
                            assert loginResponse != null;
                            session.setIdNome(loginResponse.getIdUsuario(),loginResponse.getNomeUsuario());
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Usuario ou senha Inválidos.", Toast.LENGTH_LONG).show();
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