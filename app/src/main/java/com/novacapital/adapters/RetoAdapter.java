package com.novacapital.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.novacapital.R;
import com.novacapital.models.Reto;

import java.util.List;

/**
 * RetoAdapter - Adapter para la lista de retos en RetosActivity.
 *
 * Muestra cada reto con:
 * - Título del reto
 * - Recompensa en Aurus
 * - Estado: completado (verde) o pendiente (gris)
 */
public class RetoAdapter extends RecyclerView.Adapter<RetoAdapter.RetoViewHolder> {

    private List<Reto> listaRetos;
    private OnRetoClickListener listener;

    public interface OnRetoClickListener {
        void onRetoClick(Reto reto);
    }

    public RetoAdapter(List<Reto> listaRetos, OnRetoClickListener listener) {
        this.listaRetos = listaRetos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RetoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reto, parent, false);
        return new RetoViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull RetoViewHolder holder, int position) {
        Reto reto = listaRetos.get(position);

        holder.tvTitulo.setText(reto.getTitulo());
        holder.tvRecompensa.setText("+" + reto.getRecompensa() + " Aurus");

        if (reto.isCompletado()) {
            holder.tvEstado.setText("✓ Completado");
            holder.tvEstado.setTextColor(Color.parseColor("#4CAF50"));   // Verde
            holder.itemView.setBackgroundColor(Color.parseColor("#E8F5E9")); // Fondo verde claro
        } else {
            holder.tvEstado.setText("⏳ Pendiente");
            holder.tvEstado.setTextColor(Color.parseColor("#9E9E9E"));   // Gris
            holder.itemView.setBackgroundColor(Color.parseColor("#FAFAFA")); // Fondo gris claro
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onRetoClick(reto);
        });
    }

    @Override
    public int getItemCount() {
        return listaRetos.size();
    }

    static class RetoViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo;
        TextView tvRecompensa;
        TextView tvEstado;

        RetoViewHolder(View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvRecompensa = itemView.findViewById(R.id.tvRecompensa);
            tvEstado = itemView.findViewById(R.id.tvEstado);
        }
    }
}
