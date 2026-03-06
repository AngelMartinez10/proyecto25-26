package com.novacapital.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.novacapital.R;
import com.novacapital.database.AppDatabase;
import com.novacapital.models.Proyecto;

/**
 * CrearProyectoActivity - Formulario para crear un nuevo proyecto de inversión.
 *
 * El usuario introduce:
 * - Nombre del proyecto
 * - Descripción
 * - Inversión objetivo (en Aurus)
 *
 * Al crear el proyecto, se comprueba el reto "Crear tu primer proyecto".
 */
public class CrearProyectoActivity extends AppCompatActivity {

    // Campos del formulario
    private EditText etNombre;
    private EditText etDescripcion;
    private EditText etObjetivo;
    private Button btnCrear;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_proyecto);

        // Configurar barra
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Crear Proyecto");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Enlazar vistas
        etNombre = findViewById(R.id.etNombre);
        etDescripcion = findViewById(R.id.etDescripcion);
        etObjetivo = findViewById(R.id.etObjetivo);
        btnCrear = findViewById(R.id.btnCrear);

        db = AppDatabase.getInstance(this);

        // Acción al pulsar "Crear Proyecto"
        btnCrear.setOnClickListener(v -> crearProyecto());
    }

    /**
     * Valida los campos del formulario y crea el proyecto en la BD.
     */
    private void crearProyecto() {
        String nombre = etNombre.getText().toString().trim();
        String descripcion = etDescripcion.getText().toString().trim();
        String objetivoStr = etObjetivo.getText().toString().trim();

        // Validación: campos vacíos
        if (TextUtils.isEmpty(nombre)) {
            etNombre.setError("El nombre es obligatorio");
            return;
        }
        if (TextUtils.isEmpty(descripcion)) {
            etDescripcion.setError("La descripción es obligatoria");
            return;
        }
        if (TextUtils.isEmpty(objetivoStr)) {
            etObjetivo.setError("El objetivo de inversión es obligatorio");
            return;
        }

        // Validación: objetivo mayor que 0
        double objetivo;
        try {
            objetivo = Double.parseDouble(objetivoStr);
            if (objetivo <= 0) {
                etObjetivo.setError("El objetivo debe ser mayor que 0");
                return;
            }
        } catch (NumberFormatException e) {
            etObjetivo.setError("Introduce un número válido");
            return;
        }

        // Crear y guardar el proyecto
        Proyecto nuevoProyecto = new Proyecto(nombre, descripcion, objetivo);
        db.proyectoDao().insertar(nuevoProyecto);

        // Comprobar reto: "Crear tu primer proyecto" (id=2)
        comprobarRetoCrearProyecto();

        Toast.makeText(this, "Proyecto creado con éxito", Toast.LENGTH_SHORT).show();

        // Volver a la pantalla anterior
        finish();
    }

    /**
     * Comprueba si se cumple el reto de crear el primer proyecto.
     * El reto tiene id=2 según el orden de inserción en MainActivity.
     */
    private void comprobarRetoCrearProyecto() {
        // Buscar el reto "Crear tu primer proyecto"
        com.novacapital.models.Reto reto = db.retoDao().obtenerPorId(2);
        if (reto != null && !reto.isCompletado()) {
            // Solo se completa si es el primer proyecto
            if (db.proyectoDao().contarProyectos() == 1) {
                reto.setCompletado(true);
                db.retoDao().actualizar(reto);

                // Dar recompensa al usuario
                com.novacapital.models.Usuario usuario = db.usuarioDao().obtenerUsuario();
                usuario.setSaldoAurus(usuario.getSaldoAurus() + reto.getRecompensa());
                db.usuarioDao().actualizar(usuario);

                Toast.makeText(this,
                    "🏆 Reto completado: +" + reto.getRecompensa() + " Aurus",
                    Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
