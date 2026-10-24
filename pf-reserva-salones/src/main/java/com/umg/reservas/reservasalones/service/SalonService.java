package com.umg.reservas.reservasalones.service;

import com.umg.reservas.reservasalones.dto.SalonDto;
import com.umg.reservas.reservasalones.entity.Salon;
import com.umg.reservas.reservasalones.repository.SalonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalonService {

    private final SalonRepository salonRepository;

    public SalonService(SalonRepository salonRepository) {
        this.salonRepository = salonRepository;
    }

    public List<Salon> listarTodos() {
        return salonRepository.findAll();
    }

    public Salon obtenerPorId(Long id) {
        return salonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El salón con ID " + id + " no existe."));
    }

    public Salon crearSalon(SalonDto dto) {
        Salon salon = new Salon();
        salon.setNombre(dto.getNombre());
        salon.setUbicacion(dto.getUbicacion());
        salon.setCapacidad(dto.getCapacidad());
        salon.setTarifaPorHora(dto.getTarifaPorHora());
        salon.setActivo(dto.getActivo() != null ? dto.getActivo() : true);

        return salonRepository.save(salon);
    }
}
