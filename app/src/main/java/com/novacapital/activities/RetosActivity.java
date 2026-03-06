package com.novacapital.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.novacapital.R;
import com.novacapital.adapters.RetoAdapter;
import com.novacapital.database.AppDatabase;
import com.novacapital.models.Reto;

import java.util.List;

/**
 * RetosActivity - Muestra todos los retos disponibles.
 *
 * Los retos completados aparecen marcados.
 * Los retos pendientes muestran un botón para reclamarlos
 * (si se han cumplido las condiciones).
 *
 * Nota: La mayoría de retos se completan automáticamente al realizar
 * acciones. Esta pantalla permite ver el progreso.
 */
public class RetosActivity extends AppCompatActivity implements RetoAdapter.OnRetoClickListener {

    private RecyclerView recyclerRetos;
    private TextView tvSaldoActual;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retos);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Retos");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerRetos = findViewById(R.id.recyclerRetos);
        tvSaldoActual = findViewById(R.id.tvSaldoActual);
        recyclerRetos.setLayoutManager(new LinearLayoutManager(this));

        db = AppDatabase.getInstance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarRetos();
    }

    /**
     * Carga todos los retos y los muestra en el RecyclerView.
     */
    private void cargarRetos() {
        // Mostrar saldo actual
        double saldo = db.usuarioDao().obtenerUsuario().getSaldoAurus();
        tvSaldoActual.setText("Tu saldo: " + saldo + " Aurus");

        // Cargar lista de retos
        List<Reto> retos = db.retoDao().obtenerTodos();
        RetoAdapter adapter = new RetoAdapter(retos, this);
        recyclerRetos.setAdapter(adapter);
    }

    /**
     * Callback del adapter: el usuario pulsa en un reto completado para ver info.
     * En esta versión los retos se completan automáticamente, este callback
     * solo informa al usuario del estado.
     */
    @Override
    public void onRetoClick(Reto reto) {
        String mensaje;
        if (reto.isCompletado()) {
            mensaje = "Reto completado. Ganaste " + reto.getRecompensa() + " Aurus";
        } else {
            mensaje = "Este reto aún no está completado. ¡Sigue jugando!";
        }
        android.widget.Toast.makeText(this, mensaje, android.widget.Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
