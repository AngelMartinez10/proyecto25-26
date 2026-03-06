package com.novacapital.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.novacapital.R;
import com.novacapital.database.ProyectoDao;
import com.novacapital.models.Inversion;
import com.novacapital.models.Proyecto;

import java.util.List;

/**
 * InversionAdapter - Adapter para el historial de inversiones en PerfilActivity.
 *
 * Muestra cada inversión con:
 * - Nombre del proyecto
 * - Cantidad invertida
 * - Fecha
 */
public class InversionAdapter extends RecyclerView.Adapter<InversionAdapter.InversionViewHolder> {

    private List<Inversion> listaInversiones;
    private ProyectoDao proyectoDao;

    public InversionAdapter(List<Inversion> listaInversiones, ProyectoDao proyectoDao) {
        this.listaInversiones = listaInversiones;
        this.proyectoDao = proyectoDao;
    }

    @NonNull
    @Override
    public InversionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_inversion, parent, false);
        return new InversionViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull InversionViewHolder holder, int position) {
        Inversion inversion = listaInversiones.get(position);

        // Obtener el nombre del proyecto asociado
        Proyecto proyecto = proyectoDao.obtenerPorId(inversion.getIdProyecto());
        String nombreProyecto = (proyecto != null) ? proyecto.getNombre() : "Proyecto eliminado";

        holder.tvNombreProyecto.setText(nombreProyecto);
        holder.tvCantidad.setText(inversion.getCantidad() + " Aurus");
        holder.tvFecha.setText(inversion.getFecha());
    }

    @Override
    public int getItemCount() {
        return listaInversiones.size();
    }

    static class InversionViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreProyecto;
        TextView tvCantidad;
        TextView tvFecha;

        InversionViewHolder(View itemView) {
            super(itemView);
            tvNombreProyecto = itemView.findViewById(R.id.tvNombreProyecto);
            tvCantidad = itemView.findViewById(R.id.tvCantidad);
            tvFecha = itemView.findViewById(R.id.tvFecha);
        }
    }
}
