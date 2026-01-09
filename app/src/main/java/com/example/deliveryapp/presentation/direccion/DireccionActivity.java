package com.example.deliveryapp.presentation.direccion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deliveryapp.R;
import com.example.deliveryapp.data.api.ApiClient;
import com.example.deliveryapp.data.api.AuthApi;
import com.example.deliveryapp.data.model.DireccionRequest;
import com.example.deliveryapp.data.model.SimpleResponse;
import com.example.deliveryapp.presentation.home.HomeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DireccionActivity extends AppCompatActivity {

    EditText etDireccion, etReferencia, etCiudad, etDistrito;
    Button btnGuardar, btnOmitir;
    int usuarioId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direccion);

        // RECIBIMOS EL USUARIO_ID
        usuarioId = getIntent().getIntExtra("usuario_id", -1);

        if (usuarioId == -1) {
            Toast.makeText(this,
                    "Usuario no válido",
                    Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        etDireccion = findViewById(R.id.etDireccion);
        etReferencia = findViewById(R.id.etReferencia);
        etCiudad = findViewById(R.id.etCiudad);
        etDistrito = findViewById(R.id.etDistrito);
        btnGuardar = findViewById(R.id.btnGuardarDireccion);
        btnOmitir = findViewById(R.id.btnOmitir);

        btnGuardar.setOnClickListener(v -> registrarDireccion());

        btnOmitir.setOnClickListener(v -> irAlHome());
    }

    // 👉 SOLO NAVEGACIÓN
    private void irAlHome() {
        Intent intent = new Intent(
                DireccionActivity.this,
                HomeActivity.class
        );
        startActivity(intent);
        finish();
    }

    private void registrarDireccion() {

        DireccionRequest request = new DireccionRequest(
                usuarioId,
                etDireccion.getText().toString(),
                etReferencia.getText().toString(),
                etCiudad.getText().toString(),
                etDistrito.getText().toString()
        );

        AuthApi api = ApiClient.getClient().create(AuthApi.class);

        api.registrarDireccion(request).enqueue(new Callback<SimpleResponse>() {
            @Override
            public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {

                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(DireccionActivity.this,
                            response.body().message,
                            Toast.LENGTH_SHORT).show();

                    // 👉 CUANDO SE GUARDA BIEN → HOME
                    irAlHome();

                } else {
                    Toast.makeText(DireccionActivity.this,
                            "Error al registrar dirección",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SimpleResponse> call, Throwable t) {
                Toast.makeText(DireccionActivity.this,
                        "Error: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
