package com.example.deliveryapp.presentation.carrito;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.deliveryapp.R;
import com.example.deliveryapp.data.model.CarritoItem;
import com.example.deliveryapp.data.local.CarritoManager;

import java.util.List;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.ViewHolder> {

    private List<CarritoItem> lista;

    public CarritoAdapter(List<CarritoItem> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_carrito, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CarritoItem item = lista.get(position);

        holder.tvNombre.setText(item.getNombre());
        holder.tvPrecio.setText("S/ " + item.getPrecio());
        holder.tvCantidad.setText("Cantidad: " + item.getCantidad());

        double subtotal = item.getPrecio() * item.getCantidad();
        holder.tvSubtotal.setText("Subtotal: S/ " + subtotal);

        Glide.with(holder.itemView.getContext())
                .load(item.getImagenUrl())
                .into(holder.imgProducto);

        holder.btnEliminar.setOnClickListener(v -> {
            CarritoManager.eliminarProducto(item.getProductoId());
            notifyDataSetChanged();
        });
    }


    @Override
    public int getItemCount() {
        return lista.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProducto;
        TextView tvNombre, tvPrecio, tvCantidad, tvSubtotal;
        ImageButton btnEliminar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProducto = itemView.findViewById(R.id.imgProducto);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            tvCantidad = itemView.findViewById(R.id.tvCantidad);
            //Subtotal = itemView.findViewById(R.id.tvSubtotal);
            //btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}
