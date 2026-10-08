package com.estudiante.controldespensa.model;

public class Producto {
    private Long id;
    private String nombre;
    private String categoria;
    private int cantidad;
    private double precioUnitario;

    public Producto() {
    }

    public Producto(Long id, String nombre, String categoria, int cantidad, double precioUnitario) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
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
    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double calcularSubtotal() {
        return cantidad * precioUnitario;
    }

}
