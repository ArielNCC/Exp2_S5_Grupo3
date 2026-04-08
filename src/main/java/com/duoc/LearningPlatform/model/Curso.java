package com.duoc.LearningPlatform.model;

public class Curso {

    private Long id;
    private String nombre;
    private String categoria;
    private boolean activo;

    public Curso(Long id, String nombre, String categoria, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public boolean isActivo() {
        return activo;
    }
}