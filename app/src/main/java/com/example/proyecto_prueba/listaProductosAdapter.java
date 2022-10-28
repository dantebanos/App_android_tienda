package com.example.proyecto_prueba;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_prueba.entidades.Productos;

import java.util.ArrayList;

public class listaProductosAdapter extends RecyclerView.Adapter<listaProductosAdapter.ProductoViewHolder> {

    ArrayList<Productos> listaProductos;

    public listaProductosAdapter(ArrayList<Productos> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_producto, null, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        holder.viewCodigo.setText(listaProductos.get(position).getCodigo());
        holder.viewNombre.setText(listaProductos.get(position).getNombre());
        holder.viewCategoria.setText(listaProductos.get(position).getCategoria());
        holder.viewPrecio.setText(listaProductos.get(position).getPrecio());

    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView viewCodigo, viewNombre, viewPrecio, viewCategoria;
        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewCodigo = itemView.findViewById(R.id.viewCodigo);
            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewPrecio = itemView.findViewById(R.id.viewPrecio);
            viewCategoria = itemView.findViewById(R.id.viewCategoria);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, verSelect.class);
                    intent.putExtra("codigo",listaProductos.get(getAdapterPosition()).getCodigo());
                    context.startActivity(intent);
                }
            });
        }
    }
}
