package com.novacapital.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.novacapital.R;
import com.novacapital.database.AppDatabase;
import com.novacapital.database.InversionDao;
import com.novacapital.database.ProyectoDao;
import com.novacapital.database.RetoDao;
import com.novacapital.database.UsuarioDao;
import com.novacapital.models.Reto;
import com.novacapital.models.Usuario;

/**
 * MainActivity - Pantalla principal de Nova Capital.
 *
 * Aquí se inicializa la base de datos con los datos por defecto:
 * - Un usuario con 1000 Aurus
 * - 3 retos iniciales
 *
 * Muestra el saldo actual y los botones de navegación.
 */
public class MainActivity extends AppCompatActivity {

    // Vistas
    private TextView tvBienvenida;
    private TextView tvSaldo;
    private Button btnProyectos;
    private Button btnCrearProyecto;
    private Button btnPerfil;
    private Button btnRetos;

    // Base de datos
    private AppDatabase db;
    private UsuarioDao usuarioDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener instancia de la base de datos
        db = AppDatabase.getInstance(this);
        usuarioDao = db.usuarioDao();

        // Inicializar la base de datos si es la primera vez
        inicializarDatosDefecto();

        // Enlazar vistas con XML
        tvBienvenida = findViewById(R.id.tvBienvenida);
        tvSaldo = findViewById(R.id.tvSaldo);
        btnProyectos = findViewById(R.id.btnProyectos);
        btnCrearProyecto = findViewById(R.id.btnCrearProyecto);
        btnPerfil = findViewById(R.id.btnPerfil);
        btnRetos = findViewById(R.id.btnRetos);

        // Configurar botones de navegación
        btnProyectos.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListaProyectosActivity.class);
            startActivity(intent);
        });

        btnCrearProyecto.setOnClickListener(v -> {
            Intent intent = new Intent(this, CrearProyectoActivity.class);
            startActivity(intent);
        });

        btnPerfil.setOnClickListener(v -> {
            Intent intent = new Intent(this, PerfilActivity.class);
            startActivity(intent);
        });

        btnRetos.setOnClickListener(v -> {
            Intent intent = new Intent(this, RetosActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Actualizar saldo cada vez que se vuelve a esta pantalla
        actualizarSaldo();
    }

    /**
     * Inicializa los datos por defecto si la app se abre por primera vez.
     * Crea el usuario con 1000 Aurus y los retos iniciales.
     */
    private void inicializarDatosDefecto() {
        // Crear usuario si no existe
        if (usuarioDao.contarUsuarios() == 0) {
            Usuario usuario = new Usuario("Inversor", 1000.0);
            usuarioDao.insertar(usuario);
        }

        // Crear retos si no existen
        RetoDao retoDao = db.retoDao();
        if (retoDao.contarRetos() == 0) {
            retoDao.insertar(new Reto("Invertir por primera vez", 50.0));
            retoDao.insertar(new Reto("Crear tu primer proyecto", 75.0));
            retoDao.insertar(new Reto("Invertir en 3 proyectos distintos", 150.0));
            retoDao.insertar(new Reto("Financiar un proyecto completamente", 200.0));
        }
    }

    /**
     * Actualiza el texto del saldo y bienvenida con datos del usuario.
     */
    private void actualizarSaldo() {
        Usuario usuario = usuarioDao.obtenerUsuario();
        if (usuario != null) {
            tvBienvenida.setText("¡Hola, " + usuario.getNombre() + "!");
            tvSaldo.setText("Saldo: " + usuario.getSaldoAurus() + " Aurus");
        }
    }
}
