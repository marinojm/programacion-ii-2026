package com.umg.reservas.reservasalones.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "reserva")
public class Reserva {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "cliente_nombre", nullable = false, length = 120)
        private String clienteNombre;

        @Column(name = "fecha_reserva", nullable = false)
        private LocalDate fechaReserva;

        @Column(name = "horas_reservadas", nullable = false)
        private Integer horasReservadas;

        @Column(name = "cantidad_invitados", nullable = false)
        private Integer cantidadInvitados;

        @Column(name = "costo_total", nullable = false, precision = 10, scale = 2)
        private BigDecimal costoTotal;

        @Column(nullable = false, length = 30)
        private String estado = "CONFIRMADA";

        // Relación Muchos a Uno (Muchas reservas pertenecen a un mismo Salón)
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "salon_id", nullable = false)
        private Salon salon;

        // Constructores
        public Reserva() {
        }

        public Reserva(Long id, String clienteNombre, LocalDate fechaReserva, Integer horasReservadas, Integer cantidadInvitados, BigDecimal costoTotal, String estado, Salon salon) {
            this.id = id;
            this.clienteNombre = clienteNombre;
            this.fechaReserva = fechaReserva;
            this.horasReservadas = horasReservadas;
            this.cantidadInvitados = cantidadInvitados;
            this.costoTotal = costoTotal;
            this.estado = estado;
            this.salon = salon;
        }

        // Getters y Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getClienteNombre() {
            return clienteNombre;
        }

        public void setClienteNombre(String clienteNombre) {
            this.clienteNombre = clienteNombre;
        }

        public LocalDate getFechaReserva() {
            return fechaReserva;
        }

        public void setFechaReserva(LocalDate fechaReserva) {
            this.fechaReserva = fechaReserva;
        }

        public Integer getHorasReservadas() {
            return horasReservadas;
        }

        public void setHorasReservadas(Integer horasReservadas) {
            this.horasReservadas = horasReservadas;
        }

        public Integer getCantidadInvitados() {
            return cantidadInvitados;
        }

        public void setCantidadInvitados(Integer cantidadInvitados) {
            this.cantidadInvitados = cantidadInvitados;
        }

        public BigDecimal getCostoTotal() {
            return costoTotal;
        }

        public void setCostoTotal(BigDecimal costoTotal) {
            this.costoTotal = costoTotal;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        public Salon getSalon() {
            return salon;
        }

        public void setSalon(Salon salon) {
            this.salon = salon;
        }
}
