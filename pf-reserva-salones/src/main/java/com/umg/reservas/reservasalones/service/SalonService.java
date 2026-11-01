package com.umg.reservas.reservasalones.service;

import com.umg.reservas.reservasalones.dto.SalonDto;
import com.umg.reservas.reservasalones.dto.SalonResponseDto;
import com.umg.reservas.reservasalones.entity.Salon;
import com.umg.reservas.reservasalones.exception.RecursoNoEncontradoException;
import com.umg.reservas.reservasalones.repository.SalonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalonService {

    private final SalonRepository salonRepository;

    public SalonService(SalonRepository salonRepository) {
        this.salonRepository = salonRepository;
    }

    private SalonResponseDto mapearADto(Salon salon) {
        return new SalonResponseDto(
                salon.getId(),
                salon.getNombre(),
                salon.getUbicacion(),
                salon.getCapacidad(),
                salon.getTarifaPorHora(),
                salon.getActivo()
        );
    }

    public List<SalonResponseDto> listarTodos() {
        return salonRepository.findAll().stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
    }

    public SalonResponseDto obtenerDtoPorId(Long id) {
        Salon salon = salonRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("El salón con ID " + id + " no existe."));
        return mapearADto(salon);
    }

    // Método auxiliar para uso interno del sistema (ej. en reservas)
    public Salon obtenerPorId(Long id) {
        return salonRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("El salón con ID " + id + " no existe."));
    }

    public List<SalonResponseDto> buscarPorCapacidad(Integer capacidad) {
        return salonRepository.findByActivoTrueAndCapacidadGreaterThanEqual(capacidad).stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
    }

    public SalonResponseDto crearSalon(SalonDto dto) {
        Salon salon = new Salon();
        salon.setNombre(dto.getNombre());
        salon.setUbicacion(dto.getUbicacion());
        salon.setCapacidad(dto.getCapacidad());
        salon.setTarifaPorHora(dto.getTarifaPorHora());
        salon.setActivo(dto.getActivo() != null ? dto.getActivo() : true);

        Salon guardado = salonRepository.save(salon);
        return mapearADto(guardado);
    }

    public SalonResponseDto actualizarSalon(Long id, SalonDto dto) {
        Salon salon = obtenerPorId(id);
        salon.setNombre(dto.getNombre());
        salon.setUbicacion(dto.getUbicacion());
        salon.setCapacidad(dto.getCapacidad());
        salon.setTarifaPorHora(dto.getTarifaPorHora());
        if (dto.getActivo() != null) {
            salon.setActivo(dto.getActivo());
        }
        Salon actualizado = salonRepository.save(salon);
        return mapearADto(actualizado);
    }

    public void cambiarEstado(Long id, boolean activo) {
        Salon salon = obtenerPorId(id);
        salon.setActivo(activo);
        salonRepository.save(salon);
    }
}