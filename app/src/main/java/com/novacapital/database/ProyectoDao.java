package com.novacapital.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.novacapital.models.Proyecto;

import java.util.List;

/**
 * DAO para Proyecto.
 * Define las consultas a la tabla "proyectos".
 */
@Dao
public interface ProyectoDao {

    // Insertar un proyecto nuevo
    @Insert
    void insertar(Proyecto proyecto);

    // Actualizar un proyecto (ej: cuando cambia inversionActual o estado)
    @Update
    void actualizar(Proyecto proyecto);

    // Obtener todos los proyectos
    @Query("SELECT * FROM proyectos ORDER BY id DESC")
    List<Proyecto> obtenerTodos();

    // Obtener un proyecto por su ID
    @Query("SELECT * FROM proyectos WHERE id = :id")
    Proyecto obtenerPorId(int id);

    // Contar cuántos proyectos hay
    @Query("SELECT COUNT(*) FROM proyectos")
    int contarProyectos();
}
