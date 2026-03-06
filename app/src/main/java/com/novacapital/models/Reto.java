package com.novacapital.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Modelo Reto
 * Representa un reto que el usuario puede completar para ganar Aurus.
 * El campo "completado" indica si ya fue reclamado.
 */
@Entity(tableName = "retos")
public class Reto {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String titulo;
    private double recompensa;
    private boolean completado;

    // Constructor
    public Reto(String titulo, double recompensa) {
        this.titulo = titulo;
        this.recompensa = recompensa;
        this.completado = false;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public double getRecompensa() { return recompensa; }
    public void setRecompensa(double recompensa) { this.recompensa = recompensa; }

    public boolean isCompletado() { return completado; }
    public void setCompletado(boolean completado) { this.completado = completado; }
}
