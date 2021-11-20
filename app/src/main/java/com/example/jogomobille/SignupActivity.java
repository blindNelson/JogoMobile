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

import com.example.jogomobille.utils.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignupActivity extends AppCompatActivity {
    private Button cadastroButton;
    private EditText usernameTxt, passwordTxt, confirmPasswordTxt;
    private String username,password, confirmPassword;

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

        cadastroButton = findViewById(R.id.cadastroButton);
        usernameTxt = findViewById((R.id.usernameTxt));
        passwordTxt = findViewById((R.id.passwordTxt));
        confirmPasswordTxt = findViewById((R.id.confirmPasswordTxt));

        cadastroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameTxt.getText().toString();
                password = passwordTxt.getText().toString();
                confirmPassword = confirmPasswordTxt.getText().toString();
                if(!password.equals(confirmPassword))
                    Toast.makeText(getApplicationContext(), "Senhas não coincidem!", Toast.LENGTH_LONG).show();
                else if(username.length() < 1)
                    Toast.makeText(getApplicationContext(), "Digite seu nome de usuario!", Toast.LENGTH_LONG).show();
                else if(password.length() < 5)
                    Toast.makeText(getApplicationContext(), "Digite uma senha com pelo menos 5 caracteres!", Toast.LENGTH_LONG).show();
                else if(username.contains(" "))
                    Toast.makeText(getApplicationContext(), "Seu nome de usuario não pode ter espaços!", Toast.LENGTH_LONG).show();
                else
                {
                    Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
                    final InterfaceAPI api = retrofit.create(InterfaceAPI.class);
                    LoginCadastroRequest cadastroRequest = new LoginCadastroRequest(username,password);
                    Call<String> call = api.cadastro(cadastroRequest);

                    call.enqueue(new Callback<String>() {

                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Nome de usuario já ultilizado!", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.e("TAG", t.toString());
                            t.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Não Conseguiu conectar com a API", Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });
    }
}