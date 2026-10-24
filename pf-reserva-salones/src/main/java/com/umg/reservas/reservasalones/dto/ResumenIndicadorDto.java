package com.umg.reservas.reservasalones.dto;

import java.math.BigDecimal;

public class ResumenIndicadorDto {

    private Long totalReservas;
    private Long reservasConfirmadas;
    private BigDecimal ingresosTotales;

    public ResumenIndicadorDto() {
    }

    public ResumenIndicadorDto(Long totalReservas, Long reservasConfirmadas, BigDecimal ingresosTotales) {
        this.totalReservas = totalReservas;
        this.reservasConfirmadas = reservasConfirmadas;
        this.ingresosTotales = ingresosTotales;
    }

    public Long getTotalReservas() { return totalReservas; }
    public void setTotalReservas(Long totalReservas) { this.totalReservas = totalReservas; }

    public Long getReservasConfirmadas() { return reservasConfirmadas; }
    public void setReservasConfirmadas(Long reservasConfirmadas) { this.reservasConfirmadas = reservasConfirmadas; }

    public BigDecimal getIngresosTotales() { return ingresosTotales; }
    public void setIngresosTotales(BigDecimal ingresosTotales) { this.ingresosTotales = ingresosTotales; }
}
