package com.novacapital.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.novacapital.models.Usuario;

/**
 * DAO (Data Access Object) para Usuario.
 * Define las operaciones de base de datos para la tabla "usuarios".
 */
@Dao
public interface UsuarioDao {

    // Insertar usuario nuevo
    @Insert
    void insertar(Usuario usuario);

    // Actualizar datos del usuario (saldo, nombre...)
    @Update
    void actualizar(Usuario usuario);

    // Obtener el primer usuario (solo hay uno en la app)
    @Query("SELECT * FROM usuarios LIMIT 1")
    Usuario obtenerUsuario();

    // Contar cuántos usuarios existen (para saber si hay que crear uno)
    @Query("SELECT COUNT(*) FROM usuarios")
    int contarUsuarios();
}
