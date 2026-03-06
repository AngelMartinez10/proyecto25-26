package com.novacapital.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Modelo Proyecto
 * Representa un proyecto de inversión creado por el usuario.
 * Cuando inversionActual >= inversionObjetivo, el estado cambia a FINANCIADO.
 */
@Entity(tableName = "proyectos")
public class Proyecto {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nombre;
    private String descripcion;
    private double inversionObjetivo;
    private double inversionActual;
    private String estado; // "ACTIVO" o "FINANCIADO"

    // Constructor
    public Proyecto(String nombre, String descripcion, double inversionObjetivo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.inversionObjetivo = inversionObjetivo;
        this.inversionActual = 0;
        this.estado = "ACTIVO";
    }

    // Método auxiliar: calcula el porcentaje de financiación
    public int getPorcentajeFinanciacion() {
        if (inversionObjetivo == 0) return 0;
        int porcentaje = (int) ((inversionActual / inversionObjetivo) * 100);
        return Math.min(porcentaje, 100); // Máximo 100%
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getInversionObjetivo() { return inversionObjetivo; }
    public void setInversionObjetivo(double inversionObjetivo) { this.inversionObjetivo = inversionObjetivo; }

    public double getInversionActual() { return inversionActual; }
    public void setInversionActual(double inversionActual) { this.inversionActual = inversionActual; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
