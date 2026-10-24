package com.umg.reservas.reservasalones.service;

import com.umg.reservas.reservasalones.dto.ReservaRequestDto;
import com.umg.reservas.reservasalones.dto.ResumenIndicadorDto;
import com.umg.reservas.reservasalones.entity.Reserva;
import com.umg.reservas.reservasalones.entity.Salon;
import com.umg.reservas.reservasalones.repository.ReservaRepository;
import com.umg.reservas.reservasalones.repository.SalonRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final SalonRepository salonRepository;

    public ReservaService(ReservaRepository reservaRepository, SalonRepository salonRepository) {
        this.reservaRepository = reservaRepository;
        this.salonRepository = salonRepository;
    }

    public List<Reserva> listarTodas() {
        return reservaRepository.findAll();
    }

    public Reserva obtenerPorId(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La reserva con ID " + id + " no existe."));
    }

    public List<Reserva> buscarPorFecha(LocalDate fecha) {
        return reservaRepository.findByFechaReserva(fecha);
    }

    public List<Reserva> buscarPorEstado(String estado) {
        return reservaRepository.findByEstado(estado);
    }

    public ResumenIndicadorDto obtenerResumen() {
        List<Reserva> todas = reservaRepository.findAll();
        long total = todas.size();
        long confirmadas = todas.stream().filter(r -> "CONFIRMADA".equals(r.getEstado())).count();
        BigDecimal ingresos = reservaRepository.sumarTotalIngresos();

        return new ResumenIndicadorDto(total, confirmadas, ingresos);
    }

    public Reserva crearReserva(ReservaRequestDto dto) {
        Salon salon = salonRepository.findById(dto.getSalonId())
                .orElseThrow(() -> new RuntimeException("El salón especificado no existe."));

        if (!salon.getActivo()) {
            throw new RuntimeException("El salón seleccionado no se encuentra activo actualmente.");
        }

        if (dto.getCantidadInvitados() > salon.getCapacidad()) {
            throw new RuntimeException("La cantidad de invitados (" + dto.getCantidadInvitados() +
                    ") supera la capacidad máxima permitida para este salón (" + salon.getCapacidad() + ").");
        }

        boolean fechaOcupada = reservaRepository.existsBySalonIdAndFechaReservaAndEstadoNot(
                dto.getSalonId(), dto.getFechaReserva(), "CANCELADA");
        if (fechaOcupada) {
            throw new RuntimeException("El salón ya cuenta con una reserva confirmada para la fecha seleccionada.");
        }

        BigDecimal costoTotal = salon.getTarifaPorHora().multiply(BigDecimal.valueOf(dto.getHorasReservadas()));

        Reserva reserva = new Reserva();
        reserva.setClienteNombre(dto.getClienteNombre());
        reserva.setFechaReserva(dto.getFechaReserva());
        reserva.setHorasReservadas(dto.getHorasReservadas());
        reserva.setCantidadInvitados(dto.getCantidadInvitados());
        reserva.setCostoTotal(costoTotal);
        reserva.setEstado("CONFIRMADA");
        reserva.setSalon(salon);

        return reservaRepository.save(reserva);
    }

    public Reserva cancelarReserva(Long id) {
        Reserva reserva = obtenerPorId(id);
        reserva.setEstado("CANCELADA");
        return reservaRepository.save(reserva);
    }
}