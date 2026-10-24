package com.umg.reservas.reservasalones.service;

import com.umg.reservas.reservasalones.dto.ReservaRequestDto;
import com.umg.reservas.reservasalones.entity.Reserva;
import com.umg.reservas.reservasalones.entity.Salon;
import com.umg.reservas.reservasalones.repository.ReservaRepository;
import com.umg.reservas.reservasalones.repository.SalonRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
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

    public Reserva crearReserva(ReservaRequestDto dto) {
        //1 validar que el salon exista en la base de datos
        Salon salon = salonRepository.findById(dto.getSalonId())
                .orElseThrow(() -> new RuntimeException("El salón especificado no existe."));

        // validar que el salon este activo
        if (!salon.getActivo()) {
            throw new RuntimeException("El salón seleccionado no se encuentra activo actualmente.");
        }

        //2regla de Negocio: Validar la capacidad del salon
        if (dto.getCantidadInvitados() > salon.getCapacidad()) {
            throw new RuntimeException("La cantidad de invitados (" + dto.getCantidadInvitados() +
                    ") supera la capacidad máxima permitida para este salón (" + salon.getCapacidad() + ").");
        }

        //3 Regla de Negocio: Impedir reservas duplicadas para el mismo salon y la misma fecha
        boolean fechaOcupada = reservaRepository.existsBySalonIdAndFechaReserva(dto.getSalonId(), dto.getFechaReserva());
        if (fechaOcupada) {
            throw new RuntimeException("El salón ya cuenta con una reserva confirmada para la fecha seleccionada.");
        }

        // 4 Regla de Negocio: Calcular automaticamente el costo total (Horas reservadas * tarifa por hora)
        BigDecimal costoTotal = salon.getTarifaPorHora().multiply(BigDecimal.valueOf(dto.getHorasReservadas()));

        //5 mapear los datos del DTO a la entidad rserva y guardar
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
}
