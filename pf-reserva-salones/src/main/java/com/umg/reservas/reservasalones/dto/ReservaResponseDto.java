package com.umg.reservas.reservasalones.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReservaResponseDto {

    private Long id;
    private String clienteNombre;
    private LocalDate fechaReserva;
    private Integer horasReservadas;
    private Integer cantidadInvitados;
    private BigDecimal costoTotal;
    private String estado;
    private Long salonId;
    private String salonNombre;

    public ReservaResponseDto() {
    }

    public ReservaResponseDto(Long id, String clienteNombre, LocalDate fechaReserva, Integer horasReservadas, Integer cantidadInvitados, BigDecimal costoTotal, String estado, Long salonId, String salonNombre) {
        this.id = id;
        this.clienteNombre = clienteNombre;
        this.fechaReserva = fechaReserva;
        this.horasReservadas = horasReservadas;
        this.cantidadInvitados = cantidadInvitados;
        this.costoTotal = costoTotal;
        this.estado = estado;
        this.salonId = salonId;
        this.salonNombre = salonNombre;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getClienteNombre() { return clienteNombre; }
    public void setClienteNombre(String clienteNombre) { this.clienteNombre = clienteNombre; }

    public LocalDate getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(LocalDate fechaReserva) { this.fechaReserva = fechaReserva; }

    public Integer getHorasReservadas() { return horasReservadas; }
    public void setHorasReservadas(Integer horasReservadas) { this.horasReservadas = horasReservadas; }

    public Integer getCantidadInvitados() { return cantidadInvitados; }
    public void setCantidadInvitados(Integer cantidadInvitados) { this.cantidadInvitados = cantidadInvitados; }

    public BigDecimal getCostoTotal() { return costoTotal; }
    public void setCostoTotal(BigDecimal costoTotal) { this.costoTotal = costoTotal; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Long getSalonId() { return salonId; }
    public void setSalonId(Long salonId) { this.salonId = salonId; }

    public String getSalonNombre() { return salonNombre; }
    public void setSalonNombre(String salonNombre) { this.salonNombre = salonNombre; }
}
