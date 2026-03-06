package com.novacapital.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.novacapital.R;
import com.novacapital.database.AppDatabase;
import com.novacapital.models.Inversion;
import com.novacapital.models.Proyecto;
import com.novacapital.models.Reto;
import com.novacapital.models.Usuario;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * DetalleProyectoActivity - Muestra los detalles de un proyecto y permite invertir.
 *
 * Recibe el ID del proyecto por Intent.
 * Al invertir:
 *   1. Descuenta Aurus del usuario
 *   2. Suma al proyecto
 *   3. Si llega al objetivo, cambia estado a FINANCIADO
 *   4. Comprueba retos
 */
public class DetalleProyectoActivity extends AppCompatActivity {

    // Vistas de información del proyecto
    private TextView tvNombre;
    private TextView tvDescripcion;
    private TextView tvObjetivo;
    private TextView tvActual;
    private TextView tvEstado;
    private TextView tvPorcentaje;
    private ProgressBar progressBar;

    // Inversión
    private EditText etCantidad;
    private Button btnInvertir;

    private AppDatabase db;
    private Proyecto proyecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_proyecto);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Detalle del Proyecto");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Enlazar vistas
        tvNombre = findViewById(R.id.tvNombre);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        tvObjetivo = findViewById(R.id.tvObjetivo);
        tvActual = findViewById(R.id.tvActual);
        tvEstado = findViewById(R.id.tvEstado);
        tvPorcentaje = findViewById(R.id.tvPorcentaje);
        progressBar = findViewById(R.id.progressBar);
        etCantidad = findViewById(R.id.etCantidad);
        btnInvertir = findViewById(R.id.btnInvertir);

        db = AppDatabase.getInstance(this);

        // Obtener el ID del proyecto pasado por Intent
        int idProyecto = getIntent().getIntExtra("idProyecto", -1);
        if (idProyecto == -1) {
            Toast.makeText(this, "Error: proyecto no encontrado", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Cargar el proyecto de la BD
        proyecto = db.proyectoDao().obtenerPorId(idProyecto);
        mostrarDatosProyecto();

        // Acción al pulsar "Invertir"
        btnInvertir.setOnClickListener(v -> realizarInversion());
    }

    /**
     * Muestra todos los datos del proyecto en pantalla.
     */
    private void mostrarDatosProyecto() {
        tvNombre.setText(proyecto.getNombre());
        tvDescripcion.setText(proyecto.getDescripcion());
        tvObjetivo.setText("Objetivo: " + proyecto.getInversionObjetivo() + " Aurus");
        tvActual.setText("Invertido: " + proyecto.getInversionActual() + " Aurus");
        tvEstado.setText("Estado: " + proyecto.getEstado());
        tvPorcentaje.setText(proyecto.getPorcentajeFinanciacion() + "%");
        progressBar.setProgress(proyecto.getPorcentajeFinanciacion());

        // Si el proyecto está financiado, deshabilitar el botón
        if ("FINANCIADO".equals(proyecto.getEstado())) {
            btnInvertir.setEnabled(false);
            btnInvertir.setText("Proyecto Financiado ✓");
        }
    }

    /**
     * Procesa la inversión del usuario en este proyecto.
     */
    private void realizarInversion() {
        String cantidadStr = etCantidad.getText().toString().trim();

        if (TextUtils.isEmpty(cantidadStr)) {
            etCantidad.setError("Introduce una cantidad");
            return;
        }

        double cantidad;
        try {
            cantidad = Double.parseDouble(cantidadStr);
            if (cantidad <= 0) {
                etCantidad.setError("La cantidad debe ser mayor que 0");
                return;
            }
        } catch (NumberFormatException e) {
            etCantidad.setError("Introduce un número válido");
            return;
        }

        // Comprobar que el usuario tiene suficiente saldo
        Usuario usuario = db.usuarioDao().obtenerUsuario();
        if (usuario.getSaldoAurus() < cantidad) {
            Toast.makeText(this, "Saldo insuficiente. Tienes " + usuario.getSaldoAurus() + " Aurus", Toast.LENGTH_LONG).show();
            return;
        }

        // --- REALIZAR LA INVERSIÓN ---

        // 1. Descontar Aurus del usuario
        usuario.setSaldoAurus(usuario.getSaldoAurus() - cantidad);
        db.usuarioDao().actualizar(usuario);

        // 2. Sumar al proyecto
        proyecto.setInversionActual(proyecto.getInversionActual() + cantidad);

        // 3. Cambiar estado si se alcanzó el objetivo
        if (proyecto.getInversionActual() >= proyecto.getInversionObjetivo()) {
            proyecto.setEstado("FINANCIADO");
            Toast.makeText(this, "🎉 ¡Proyecto financiado completamente!", Toast.LENGTH_LONG).show();
            comprobarRetoFinanciar();
        }
        db.proyectoDao().actualizar(proyecto);

        // 4. Guardar registro de inversión
        String fecha = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        Inversion inversion = new Inversion(proyecto.getId(), cantidad, fecha);
        db.inversionDao().insertar(inversion);

        // 5. Comprobar retos de inversión
        comprobarRetosInversion();

        Toast.makeText(this, "Inversión realizada: " + cantidad + " Aurus", Toast.LENGTH_SHORT).show();

        // Limpiar campo y actualizar pantalla
        etCantidad.setText("");
        mostrarDatosProyecto();
    }

    /**
     * Comprueba retos relacionados con inversiones.
     */
    private void comprobarRetosInversion() {
        // Reto 1: Invertir por primera vez (id=1)
        if (db.inversionDao().contarInversiones() == 1) {
            Reto reto = db.retoDao().obtenerPorId(1);
            if (reto != null && !reto.isCompletado()) {
                completarReto(reto);
            }
        }

        // Reto 3: Invertir en 3 proyectos distintos (id=3)
        if (db.inversionDao().contarProyectosInvertidos() >= 3) {
            Reto reto = db.retoDao().obtenerPorId(3);
            if (reto != null && !reto.isCompletado()) {
                completarReto(reto);
            }
        }
    }

    /**
     * Comprueba el reto de financiar un proyecto (id=4).
     */
    private void comprobarRetoFinanciar() {
        Reto reto = db.retoDao().obtenerPorId(4);
        if (reto != null && !reto.isCompletado()) {
            completarReto(reto);
        }
    }

    /**
     * Marca un reto como completado y da la recompensa al usuario.
     */
    private void completarReto(Reto reto) {
        reto.setCompletado(true);
        db.retoDao().actualizar(reto);

        Usuario usuario = db.usuarioDao().obtenerUsuario();
        usuario.setSaldoAurus(usuario.getSaldoAurus() + reto.getRecompensa());
        db.usuarioDao().actualizar(usuario);

        Toast.makeText(this,
            "🏆 Reto completado: +" + reto.getRecompensa() + " Aurus",
            Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
