package com.novacapital.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Modelo Usuario
 * Representa al único usuario local de la aplicación.
 * Comienza con 1000 Aurus al instalar la app.
 */
@Entity(tableName = "usuarios")
public class Usuario {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nombre;
    private double saldoAurus;

    // Constructor
    public Usuario(String nombre, double saldoAurus) {
        this.nombre = nombre;
        this.saldoAurus = saldoAurus;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getSaldoAurus() { return saldoAurus; }
    public void setSaldoAurus(double saldoAurus) { this.saldoAurus = saldoAurus; }
}
