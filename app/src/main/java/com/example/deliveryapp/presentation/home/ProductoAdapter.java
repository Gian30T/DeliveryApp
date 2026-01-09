package com.example.deliveryapp.presentation.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.deliveryapp.R;
import com.example.deliveryapp.data.local.CarritoManager;
import com.example.deliveryapp.data.model.CarritoItem;
import com.example.deliveryapp.data.model.Producto;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder> {

    List<Producto> lista;

    public ProductoAdapter(List<Producto> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_producto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Producto p = lista.get(position);

        holder.tvNombre.setText(p.nombre);
        holder.tvPrecio.setText("S/ " + p.precio);

        Glide.with(holder.itemView.getContext())
                .load(p.imagen_url)
                .into(holder.imgProducto);

        //BOTÓN AGREGAR AL CARRITO
        holder.btnAgregar.setOnClickListener(v -> {

            CarritoItem item = new CarritoItem(
                    p.id,              // id del producto
                    p.nombre,          // nombre
                    p.precio,          // precio
                    1,                 // cantidad inicial
                    p.imagen_url       // imagen
            );

            CarritoManager.agregarProducto(item);

            Toast.makeText(
                    holder.itemView.getContext(),
                    "Producto agregado al carrito",
                    Toast.LENGTH_SHORT
            ).show();
        });
    }


    @Override
    public int getItemCount() {
        return lista.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombre, tvPrecio;
        ImageView imgProducto;
        Button btnAgregar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            imgProducto = itemView.findViewById(R.id.imgProducto);
            btnAgregar = itemView.findViewById(R.id.btnAgregar);
        }
    }
}
