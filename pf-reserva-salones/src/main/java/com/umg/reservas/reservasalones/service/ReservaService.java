package com.umg.reservas.reservasalones.service;

import com.umg.reservas.reservasalones.dto.ReservaRequestDto;
import com.umg.reservas.reservasalones.dto.ReservaResponseDto;
import com.umg.reservas.reservasalones.dto.ResumenIndicadorDto;
import com.umg.reservas.reservasalones.entity.Reserva;
import com.umg.reservas.reservasalones.entity.Salon;
import com.umg.reservas.reservasalones.exception.ConflictoExcepcion;
import com.umg.reservas.reservasalones.exception.RecursoNoEncontradoException;
import com.umg.reservas.reservasalones.repository.ReservaRepository;
import com.umg.reservas.reservasalones.repository.SalonRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final SalonRepository salonRepository;

    public ReservaService(ReservaRepository reservaRepository, SalonRepository salonRepository) {
        this.reservaRepository = reservaRepository;
        this.salonRepository = salonRepository;
    }

    private ReservaResponseDto mapearADto(Reserva reserva) {
        return new ReservaResponseDto(
                reserva.getId(),
                reserva.getClienteNombre(),
                reserva.getFechaReserva(),
                reserva.getHorasReservadas(),
                reserva.getCantidadInvitados(),
                reserva.getCostoTotal(),
                reserva.getEstado(),
                reserva.getSalon().getId(),
                reserva.getSalon().getNombre()
        );
    }

    public List<ReservaResponseDto> listarTodas() {
        return reservaRepository.findAll().stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
    }

    public ReservaResponseDto obtenerPorIdDto(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("La reserva con ID " + id + " no existe."));
        return mapearADto(reserva);
    }

    public List<ReservaResponseDto> buscarPorFecha(LocalDate fecha) {
        return reservaRepository.findByFechaReserva(fecha).stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
    }

    public List<ReservaResponseDto> buscarPorEstado(String estado) {
        return reservaRepository.findByEstado(estado).stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
    }

    public ResumenIndicadorDto obtenerResumen() {
        List<Reserva> todas = reservaRepository.findAll();
        long total = todas.size();
        long confirmadas = todas.stream().filter(r -> "CONFIRMADA".equals(r.getEstado())).count();
        BigDecimal ingresos = reservaRepository.sumarTotalIngresos();

        return new ResumenIndicadorDto(total, confirmadas, ingresos);
    }

    public ReservaResponseDto crearReserva(ReservaRequestDto dto) {
        Salon salon = salonRepository.findById(dto.getSalonId())
                .orElseThrow(() -> new RecursoNoEncontradoException("El salón especificado no existe."));

        if (!salon.getActivo()) {
            throw new ConflictoExcepcion("El salón seleccionado no se encuentra activo actualmente.");
        }

        if (dto.getCantidadInvitados() > salon.getCapacidad()) {
            throw new RuntimeException("La cantidad de invitados (" + dto.getCantidadInvitados() +
                    ") supera la capacidad máxima permitida para este salón (" + salon.getCapacidad() + ").");
        }

        boolean fechaOcupada = reservaRepository.existsBySalonIdAndFechaReservaAndEstadoNot(
                dto.getSalonId(), dto.getFechaReserva(), "CANCELADA");
        if (fechaOcupada) {
            throw new ConflictoExcepcion("El salón ya cuenta con una reserva confirmada para la fecha seleccionada.");
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

        Reserva guardada = reservaRepository.save(reserva);
        return mapearADto(guardada);
    }

    public ReservaResponseDto actualizarReserva(Long id, ReservaRequestDto dto) {
        Reserva reservaExistente = reservaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("La reserva con ID " + id + " no existe."));

        Salon salon = salonRepository.findById(dto.getSalonId())
                .orElseThrow(() -> new RecursoNoEncontradoException("El salón especificado no existe."));

        if (!salon.getActivo()) {
            throw new ConflictoExcepcion("El salón seleccionado no se encuentra activo actualmente.");
        }

        if (dto.getCantidadInvitados() > salon.getCapacidad()) {
            throw new RuntimeException("La cantidad de invitados (" + dto.getCantidadInvitados() +
                    ") supera la capacidad máxima permitida para este salón (" + salon.getCapacidad() + ").");
        }

        boolean fechaOcupada = reservaRepository.existsBySalonIdAndFechaReservaAndEstadoNotAndIdNot(
                dto.getSalonId(), dto.getFechaReserva(), "CANCELADA", id);
        if (fechaOcupada) {
            throw new ConflictoExcepcion("El salón ya cuenta con otra reserva confirmada para la fecha seleccionada.");
        }

        BigDecimal costoTotal = salon.getTarifaPorHora().multiply(BigDecimal.valueOf(dto.getHorasReservadas()));

        reservaExistente.setClienteNombre(dto.getClienteNombre());
        reservaExistente.setFechaReserva(dto.getFechaReserva());
        reservaExistente.setHorasReservadas(dto.getHorasReservadas());
        reservaExistente.setCantidadInvitados(dto.getCantidadInvitados());
        reservaExistente.setCostoTotal(costoTotal);
        reservaExistente.setSalon(salon);

        Reserva actualizada = reservaRepository.save(reservaExistente);
        return mapearADto(actualizada);
    }

    public ReservaResponseDto cancelarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("La reserva con ID " + id + " no existe."));
        reserva.setEstado("CANCELADA");
        Reserva cancelada = reservaRepository.save(reserva);
        return mapearADto(cancelada);
    }
}