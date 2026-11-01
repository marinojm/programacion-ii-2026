package com.umg.reservas.reservasalones.dto;

import java.math.BigDecimal;

public class SalonResponseDto {

    private Long id;
    private String nombre;
    private String ubicacion;
    private Integer capacidad;
    private BigDecimal tarifaPorHora;
    private Boolean activo;

    public SalonResponseDto() {
    }

    public SalonResponseDto(Long id, String nombre, String ubicacion, Integer capacidad, BigDecimal tarifaPorHora, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.tarifaPorHora = tarifaPorHora;
        this.activo = activo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public Integer getCapacidad() { return capacidad; }
    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }

    public BigDecimal getTarifaPorHora() { return tarifaPorHora; }
    public void setTarifaPorHora(BigDecimal tarifaPorHora) { this.tarifaPorHora = tarifaPorHora; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}
