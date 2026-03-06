package com.novacapital.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.novacapital.models.Inversion;
import com.novacapital.models.Proyecto;
import com.novacapital.models.Reto;
import com.novacapital.models.Usuario;

/**
 * AppDatabase - Base de datos principal de la aplicación.
 *
 * Usa el patrón Singleton para garantizar que solo existe una instancia.
 * Todas las tablas se declaran aquí con @Database.
 *
 * Para acceder a la base de datos desde cualquier Activity:
 *   AppDatabase db = AppDatabase.getInstance(this);
 *   UsuarioDao usuarioDao = db.usuarioDao();
 */
@Database(
    entities = {Usuario.class, Proyecto.class, Inversion.class, Reto.class},
    version = 1,
    exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    // Instancia única (Singleton)
    private static AppDatabase instancia;

    // Nombre del archivo de base de datos
    private static final String NOMBRE_BD = "nova_capital.db";

    // DAOs abstractos que Room implementará automáticamente
    public abstract UsuarioDao usuarioDao();
    public abstract ProyectoDao proyectoDao();
    public abstract InversionDao inversionDao();
    public abstract RetoDao retoDao();

    /**
     * Devuelve la instancia única de la base de datos.
     * Si no existe, la crea. Thread-safe con synchronized.
     */
    public static synchronized AppDatabase getInstance(Context context) {
        if (instancia == null) {
            instancia = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    NOMBRE_BD
            )
            // Permite ejecutar consultas en el hilo principal (simplifica el código para DAM)
            .allowMainThreadQueries()
            .build();
        }
        return instancia;
    }
}
