package com.umg.vetcare.semana12vetcareapi.model;

public class Mascota {
    private Long id;
    private String nombre;
    private String especie;

    public Mascota(Long id, String nombre, String especie) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
    }

    public Long getId() { return id; }

    public String getNombre() { return nombre; }

    public String getEspecie() { return especie; }
}
