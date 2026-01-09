package com.example.deliveryapp.presentation.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deliveryapp.R;
import com.example.deliveryapp.data.model.LoginResponse;
import com.example.deliveryapp.data.repository.AuthRepository;
import com.example.deliveryapp.presentation.MainActivity;
import com.example.deliveryapp.presentation.direccion.DireccionActivity;
import com.example.deliveryapp.presentation.registro.RegistroActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin;
    AuthRepository repository;
    TextView tvRegistrarse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        repository = new AuthRepository();

        btnLogin.setOnClickListener(v -> {
            login();
        });

        tvRegistrarse = findViewById(R.id.tvRegistrarse);

        tvRegistrarse.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
            startActivity(intent);
        });

    }

    private void login() {
        String correo = etEmail.getText().toString().trim();
        String contraseña = etPassword.getText().toString().trim();

        repository.login(correo, contraseña).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful() && response.body() != null) {

                    int usuarioId = response.body().usuario.id;

                    Intent intent = new Intent(
                            LoginActivity.this,
                            DireccionActivity.class
                    );

                    intent.putExtra("usuario_id", usuarioId);

                    startActivity(intent);
                    finish(); // evita volver al login
                }
            }

                @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this,
                        "Error: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
