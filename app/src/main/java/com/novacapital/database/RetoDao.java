package com.novacapital.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.novacapital.models.Reto;

import java.util.List;

/**
 * DAO para Reto.
 * Gestiona los retos disponibles en la aplicación.
 */
@Dao
public interface RetoDao {

    // Insertar un reto (se hace al inicializar la BD)
    @Insert
    void insertar(Reto reto);

    // Actualizar reto (para marcarlo como completado)
    @Update
    void actualizar(Reto reto);

    // Obtener todos los retos
    @Query("SELECT * FROM retos")
    List<Reto> obtenerTodos();

    // Obtener un reto por su ID
    @Query("SELECT * FROM retos WHERE id = :id")
    Reto obtenerPorId(int id);

    // Contar cuántos retos hay en total (para saber si hay que inicializarlos)
    @Query("SELECT COUNT(*) FROM retos")
    int contarRetos();
}
