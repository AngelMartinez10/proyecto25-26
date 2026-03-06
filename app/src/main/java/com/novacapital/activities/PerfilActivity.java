package com.novacapital.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.novacapital.R;
import com.novacapital.adapters.InversionAdapter;
import com.novacapital.database.AppDatabase;
import com.novacapital.models.Inversion;
import com.novacapital.models.Usuario;

import java.util.List;

/**
 * PerfilActivity - Muestra el perfil del usuario y su historial de inversiones.
 *
 * Información mostrada:
 * - Nombre del usuario
 * - Saldo actual de Aurus
 * - Número total de inversiones realizadas
 * - Número de proyectos creados
 * - Lista del historial de inversiones
 */
public class PerfilActivity extends AppCompatActivity {

    private TextView tvNombreUsuario;
    private TextView tvSaldoAurus;
    private TextView tvNumInversiones;
    private TextView tvNumProyectos;
    private RecyclerView recyclerHistorial;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Mi Perfil");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Enlazar vistas
        tvNombreUsuario = findViewById(R.id.tvNombreUsuario);
        tvSaldoAurus = findViewById(R.id.tvSaldoAurus);
        tvNumInversiones = findViewById(R.id.tvNumInversiones);
        tvNumProyectos = findViewById(R.id.tvNumProyectos);
        recyclerHistorial = findViewById(R.id.recyclerHistorial);

        recyclerHistorial.setLayoutManager(new LinearLayoutManager(this));

        db = AppDatabase.getInstance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarPerfil();
    }

    /**
     * Carga y muestra los datos del usuario y su historial.
     */
    private void cargarPerfil() {
        Usuario usuario = db.usuarioDao().obtenerUsuario();

        if (usuario != null) {
            tvNombreUsuario.setText(usuario.getNombre());
            tvSaldoAurus.setText(usuario.getSaldoAurus() + " Aurus");
        }

        // Estadísticas
        int numInversiones = db.inversionDao().contarInversiones();
        int numProyectos = db.proyectoDao().contarProyectos();
        tvNumInversiones.setText("Inversiones realizadas: " + numInversiones);
        tvNumProyectos.setText("Proyectos creados: " + numProyectos);

        // Historial de inversiones
        List<Inversion> historial = db.inversionDao().obtenerTodas();
        InversionAdapter adapter = new InversionAdapter(historial, db.proyectoDao());
        recyclerHistorial.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
