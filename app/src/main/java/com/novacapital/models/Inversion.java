package com.novacapital.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Modelo Inversion
 * Registra cada inversión que el usuario hace en un proyecto.
 * Guarda la fecha en formato String (ej: "2024-06-01").
 */
@Entity(tableName = "inversiones")
public class Inversion {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int idProyecto;
    private double cantidad;
    private String fecha;

    // Constructor
    public Inversion(int idProyecto, double cantidad, String fecha) {
        this.idProyecto = idProyecto;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdProyecto() { return idProyecto; }
    public void setIdProyecto(int idProyecto) { this.idProyecto = idProyecto; }

    public double getCantidad() { return cantidad; }
    public void setCantidad(double cantidad) { this.cantidad = cantidad; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
}
