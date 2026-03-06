package com.novacapital.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.novacapital.R;
import com.novacapital.models.Proyecto;

import java.util.List;

/**
 * ProyectoAdapter - Adapter para el RecyclerView de proyectos.
 *
 * Muestra cada proyecto con:
 * - Nombre
 * - Objetivo de inversión
 * - Inversión actual + barra de progreso
 * - Estado (ACTIVO / FINANCIADO)
 * - Botón "Invertir"
 *
 * Usa la interfaz OnProyectoClickListener para notificar a la Activity.
 */
public class ProyectoAdapter extends RecyclerView.Adapter<ProyectoAdapter.ProyectoViewHolder> {

    private List<Proyecto> listaProyectos;
    private OnProyectoClickListener listener;

    /**
     * Interfaz para manejar clics desde la Activity.
     */
    public interface OnProyectoClickListener {
        void onInvertirClick(Proyecto proyecto);
    }

    public ProyectoAdapter(List<Proyecto> listaProyectos, OnProyectoClickListener listener) {
        this.listaProyectos = listaProyectos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProyectoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el layout de cada elemento de la lista
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_proyecto, parent, false);
        return new ProyectoViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ProyectoViewHolder holder, int position) {
        Proyecto proyecto = listaProyectos.get(position);

        // Asignar datos a las vistas
        holder.tvNombre.setText(proyecto.getNombre());
        holder.tvObjetivo.setText("Objetivo: " + proyecto.getInversionObjetivo() + " Aurus");
        holder.tvInvertido.setText("Invertido: " + proyecto.getInversionActual() + " Aurus");
        holder.tvEstado.setText(proyecto.getEstado());
        holder.progressBar.setProgress(proyecto.getPorcentajeFinanciacion());
        holder.tvPorcentaje.setText(proyecto.getPorcentajeFinanciacion() + "%");

        // Color según estado
        if ("FINANCIADO".equals(proyecto.getEstado())) {
            holder.tvEstado.setTextColor(Color.parseColor("#4CAF50")); // Verde
            holder.btnInvertir.setEnabled(false);
            holder.btnInvertir.setText("Financiado ✓");
        } else {
            holder.tvEstado.setTextColor(Color.parseColor("#2196F3")); // Azul
            holder.btnInvertir.setEnabled(true);
            holder.btnInvertir.setText("Invertir");
        }

        // Listener del botón Invertir
        holder.btnInvertir.setOnClickListener(v -> {
            if (listener != null) {
                listener.onInvertirClick(proyecto);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaProyectos.size();
    }

    /**
     * ViewHolder: contiene las referencias a las vistas de cada elemento.
     */
    static class ProyectoViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        TextView tvObjetivo;
        TextView tvInvertido;
        TextView tvEstado;
        TextView tvPorcentaje;
        ProgressBar progressBar;
        Button btnInvertir;

        ProyectoViewHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvObjetivo = itemView.findViewById(R.id.tvObjetivo);
            tvInvertido = itemView.findViewById(R.id.tvInvertido);
            tvEstado = itemView.findViewById(R.id.tvEstado);
            tvPorcentaje = itemView.findViewById(R.id.tvPorcentaje);
            progressBar = itemView.findViewById(R.id.progressBar);
            btnInvertir = itemView.findViewById(R.id.btnInvertir);
        }
    }
}
