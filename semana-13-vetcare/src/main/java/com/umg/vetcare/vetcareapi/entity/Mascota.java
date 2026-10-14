package com.umg.vetcare.vetcareapi.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "mascota")
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String codigo;

    @Column(nullable = false, length = 80)
    private String nombre;

    @Column(nullable = false, length = 30)
    private String especie;

    @Column(name = "edad_meses", nullable = false)
    private Integer edadMeses;

    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal peso;

    @Column(nullable = false)
    private Boolean activa = true;

    public Mascota() { }

    public Mascota(String codigo, String nombre, String especie, Integer edadMeses, BigDecimal peso, Boolean activa) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.especie = especie;
        this.edadMeses = edadMeses;
        this.peso = peso;
        this.activa = activa;
    }

    public Long getId() { return id; }

    public String getCodigo() { return codigo; }

    public String getNombre() { return nombre; }

    public String getEspecie() { return especie; }

    public Integer getEdadMeses() { return edadMeses; }

    public BigDecimal getPeso() { return peso; }

    public void setId(Long id) { this.id = id; }

    public void setCodigo(String codigo) { this.codigo = codigo; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public void setEspecie(String especie) { this.especie = especie; }

    public void setEdadMeses(Integer edadMeses) { this.edadMeses = edadMeses; }

    public void setPeso(BigDecimal peso) { this.peso = peso; }

    public void setActiva(Boolean activa) { this.activa = activa; }

    public Boolean getActiva() { return activa; }
}