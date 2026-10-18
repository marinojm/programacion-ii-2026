package com.umg.vetcare.vetcareapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class MascotaRequestDTO {
    @NotBlank(message = "El código es obligatorio")
    @Size(max = 20, message = "El código admite 20 caracteres")
    private String codigo;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 80, message = "El nombre admite 80 caracteres")
    private String nombre;

    @NotBlank(message = "La especie es obligatoria")
    private String especie;

    @NotNull(message = "La edad es obligatoria")
    @PositiveOrZero(message = "La edad no puede ser negativa")
    private Integer edadMeses;

    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso debe ser mayor que cero")
    private BigDecimal peso;

    @NotNull(message = "El estado es obligatorio")
    private Boolean activa;

    public MascotaRequestDTO() {
    }

    public void setCodigo(String codigo) { this.codigo = codigo; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public void setEspecie(String especie) { this.especie = especie; }

    public void setEdadMeses(Integer edadMeses) { this.edadMeses = edadMeses; }

    public void setPeso(BigDecimal peso) { this.peso = peso; }

    public void setActiva(Boolean activa) { this.activa = activa; }

    public String getCodigo() { return codigo; }

    public String getNombre() {
        return nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public Integer getEdadMeses() {
        return edadMeses;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public Boolean getActiva() {
        return activa;
    }
}