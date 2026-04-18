package com.novacapital.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.novacapital.R;
import com.novacapital.models.ClienteRetoResponse;

import java.util.List;

public class RetoApiAdapter extends RecyclerView.Adapter<RetoApiAdapter.ViewHolder> {

    private final List<ClienteRetoResponse> lista;

    public RetoApiAdapter(List<ClienteRetoResponse> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reto, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int pos) {
        ClienteRetoResponse reto = lista.get(pos);

        h.tvTitulo.setText(reto.getTitulo());
        h.tvRecompensa.setText("+" + reto.getRecompensa() + " Aurus");

        if (reto.isCompletado()) {
            h.tvEstado.setText("Completado");
            h.tvEstado.setTextColor(Color.parseColor("#4CAF50"));
            h.itemView.setBackgroundColor(Color.parseColor("#E8F5E9"));
        } else {
            h.tvEstado.setText("Pendiente");
            h.tvEstado.setTextColor(Color.parseColor("#9E9E9E"));
            h.itemView.setBackgroundColor(Color.parseColor("#FAFAFA"));
        }
    }

    @Override
    public int getItemCount() { return lista.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvRecompensa, tvEstado;

        ViewHolder(View v) {
            super(v);
            tvTitulo     = v.findViewById(R.id.tvTitulo);
            tvRecompensa = v.findViewById(R.id.tvRecompensa);
            tvEstado     = v.findViewById(R.id.tvEstado);
        }
    }
}