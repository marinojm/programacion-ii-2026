package com.umg.reservas.reservasalones.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class ReservaRequestDto {

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String clienteNombre;

    @NotNull(message = "La fecha de reserva es obligatoria")
    @FutureOrPresent(message = "La fecha de la reserva debe ser actual o futura")
    private LocalDate fechaReserva;

    @NotNull(message = "Las horas reservadas son obligatorias")
    @Min(value = 1, message = "Se debe reservar al menos 1 hora")
    private Integer horasReservadas;

    @NotNull(message = "La cantidad de invitados es obligatoria")
    @Min(value = 1, message = "Debe haber al menos 1 invitado")
    private Integer cantidadInvitados;

    @NotNull(message = "El ID del salón es obligatorio")
    private Long salonId;

    // constructores
    public ReservaRequestDto() {
    }

    // getters y setters
    public String getClienteNombre() { return clienteNombre; }
    public void setClienteNombre(String clienteNombre) { this.clienteNombre = clienteNombre; }

    public LocalDate getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(LocalDate fechaReserva) { this.fechaReserva = fechaReserva; }

    public Integer getHorasReservadas() { return horasReservadas; }
    public void setHorasReservadas(Integer horasReservadas) { this.horasReservadas = horasReservadas; }

    public Integer getCantidadInvitados() { return cantidadInvitados; }
    public void setCantidadInvitados(Integer cantidadInvitados) { this.cantidadInvitados = cantidadInvitados; }

    public Long getSalonId() { return salonId; }
    public void setSalonId(Long salonId) { this.salonId = salonId; }

}
