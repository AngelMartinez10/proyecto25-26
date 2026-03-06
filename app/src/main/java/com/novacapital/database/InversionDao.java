package com.novacapital.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.novacapital.models.Inversion;

import java.util.List;

/**
 * DAO para Inversion.
 * Gestiona el historial de inversiones del usuario.
 */
@Dao
public interface InversionDao {

    // Insertar una nueva inversión
    @Insert
    void insertar(Inversion inversion);

    // Obtener todas las inversiones (historial completo)
    @Query("SELECT * FROM inversiones ORDER BY id DESC")
    List<Inversion> obtenerTodas();

    // Obtener inversiones de un proyecto concreto
    @Query("SELECT * FROM inversiones WHERE idProyecto = :idProyecto")
    List<Inversion> obtenerPorProyecto(int idProyecto);

    // Contar el total de inversiones hechas
    @Query("SELECT COUNT(*) FROM inversiones")
    int contarInversiones();

    // Contar en cuántos proyectos distintos ha invertido el usuario
    @Query("SELECT COUNT(DISTINCT idProyecto) FROM inversiones")
    int contarProyectosInvertidos();
}
