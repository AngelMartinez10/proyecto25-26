package com.novacapital.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.novacapital.R;
import com.novacapital.adapters.ProyectoAdapter;
import com.novacapital.database.AppDatabase;
import com.novacapital.models.Proyecto;

import java.util.List;

/**
 * ListaProyectosActivity - Muestra todos los proyectos en un RecyclerView.
 *
 * Cada elemento de la lista tiene un botón "Invertir" que abre
 * el DetalleProyectoActivity del proyecto seleccionado.
 */
public class ListaProyectosActivity extends AppCompatActivity implements ProyectoAdapter.OnProyectoClickListener {

    private RecyclerView recyclerView;
    private TextView tvSinProyectos;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_proyectos);

        // Configurar barra superior con botón atrás
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Proyectos");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Enlazar vistas
        recyclerView = findViewById(R.id.recyclerProyectos);
        tvSinProyectos = findViewById(R.id.tvSinProyectos);

        // Configurar RecyclerView con layout vertical
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtener base de datos
        db = AppDatabase.getInstance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Recargar lista cada vez que se vuelve a esta pantalla
        cargarProyectos();
    }

    /**
     * Carga los proyectos de la base de datos y los muestra en el RecyclerView.
     */
    private void cargarProyectos() {
        List<Proyecto> proyectos = db.proyectoDao().obtenerTodos();

        if (proyectos.isEmpty()) {
            // No hay proyectos: mostrar mensaje informativo
            tvSinProyectos.setVisibility(TextView.VISIBLE);
            recyclerView.setVisibility(RecyclerView.GONE);
        } else {
            // Hay proyectos: mostrar lista
            tvSinProyectos.setVisibility(TextView.GONE);
            recyclerView.setVisibility(RecyclerView.VISIBLE);

            // Crear y asignar el adapter
            ProyectoAdapter adapter = new ProyectoAdapter(proyectos, this);
            recyclerView.setAdapter(adapter);
        }
    }

    /**
     * Callback del adapter cuando el usuario pulsa "Invertir" en un proyecto.
     * Abre el DetalleProyectoActivity pasando el ID del proyecto.
     */
    @Override
    public void onInvertirClick(Proyecto proyecto) {
        Intent intent = new Intent(this, DetalleProyectoActivity.class);
        intent.putExtra("idProyecto", proyecto.getId());
        startActivity(intent);
    }

    // Botón atrás de la barra de navegación
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
