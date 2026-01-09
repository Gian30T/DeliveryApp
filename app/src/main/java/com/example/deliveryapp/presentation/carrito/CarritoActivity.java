package com.example.deliveryapp.presentation.carrito;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deliveryapp.R;
import com.example.deliveryapp.data.local.CarritoManager;

public class CarritoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView tvTotal;
    Button btnConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        recyclerView = findViewById(R.id.recyclerCarrito);
        tvTotal = findViewById(R.id.tvTotal);
        btnConfirmar = findViewById(R.id.btnConfirmar);

        if (CarritoManager.estaVacio()) {
            Toast.makeText(this,
                    "El carrito está vacío",
                    Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CarritoAdapter(CarritoManager.obtenerCarrito()));

        tvTotal.setText("Total: S/ " + CarritoManager.obtenerTotal());

        btnConfirmar.setOnClickListener(v -> {
            confirmarPedido();
        });
    }

    private void confirmarPedido() {

        if (CarritoManager.estaVacio()) {
            Toast.makeText(this,
                    "El carrito está vacío",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // POR AHORA SOLO CONFIRMAMOS
        Toast.makeText(this,
                "Pedido confirmado (pendiente de envío)",
                Toast.LENGTH_LONG).show();

        // Limpiar carrito
        CarritoManager.limpiar();

        finish(); // vuelve al home
    }

}
