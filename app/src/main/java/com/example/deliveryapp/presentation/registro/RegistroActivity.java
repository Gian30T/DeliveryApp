package com.example.deliveryapp.presentation.registro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deliveryapp.R;
import com.example.deliveryapp.data.model.RegistroRequest;
import com.example.deliveryapp.data.model.SimpleResponse;
import com.example.deliveryapp.data.repository.AuthRepository;
import com.example.deliveryapp.presentation.direccion.DireccionActivity;
import com.example.deliveryapp.presentation.login.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends AppCompatActivity {

    EditText etNombre, etApellido, etCorreo, etContraseña, etTelefono;
    Button btnRegistrar;

    AuthRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Vincular vistas
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etCorreo = findViewById(R.id.etCorreo);
        etContraseña = findViewById(R.id.etContraseña);
        etTelefono = findViewById(R.id.etTelefono);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        repository = new AuthRepository();

        btnRegistrar.setOnClickListener(v -> registrar());
    }

    private void registrar() {
        String nombre = etNombre.getText().toString().trim();
        String apellido = etApellido.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String contraseña = etContraseña.getText().toString().trim();
        String telefono = etTelefono.getText().toString().trim();

        if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty()
                || contraseña.isEmpty() || telefono.isEmpty()) {

            Toast.makeText(this,
                    "Complete todos los campos",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        RegistroRequest request = new RegistroRequest(
                nombre,
                apellido,
                correo,
                contraseña,
                telefono
        );

        repository.registrar(request).enqueue(new Callback<SimpleResponse>() {
            @Override
            public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                    Toast.makeText(RegistroActivity.this,
                            response.body().message,
                            Toast.LENGTH_SHORT).show();

                    // 👉 SOLO AL LOGIN
                    Intent intent = new Intent(
                            RegistroActivity.this,
                            LoginActivity.class
                    );

                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(RegistroActivity.this,
                            "Error al registrar",
                            Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<SimpleResponse> call, Throwable t) {
                Toast.makeText(RegistroActivity.this,
                        "Error: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
