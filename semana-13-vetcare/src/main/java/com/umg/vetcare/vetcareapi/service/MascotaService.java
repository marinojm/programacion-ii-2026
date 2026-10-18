import com.umg.vetcare.vetcareapi.entity.Mascota;
import com.umg.vetcare.vetcareapi.exception.CodigoDuplicadoException;
import com.umg.vetcare.vetcareapi.exception.RecursoNoEncontradoException;
import com.umg.vetcare.vetcareapi.dto.MascotaRequestDTO;
import com.umg.vetcare.vetcareapi.repository.MascotaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MascotaService {
    private final MascotaRepository repository;

    public MascotaService(MascotaRepository repository) {
        this.repository = repository;
    }

    public List<Mascota> listar() {
        return repository.findAll();
    }

    public void eliminar(Long id) {
        Mascota mascota = buscarPorId(id);
        repository.delete(mascota);
    }

    public Mascota buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Mascota no encontrada con ID: "
                                        + id
                        )
                );
    }

    private void copiarDatos(
            MascotaRequestDTO dto,
            Mascota mascota) {
        mascota.setCodigo(dto.getCodigo());
        mascota.setNombre(dto.getNombre());
        mascota.setEspecie(dto.getEspecie());
        mascota.setEdadMeses(dto.getEdadMeses());
        mascota.setPeso(dto.getPeso());
        mascota.setActiva(dto.getActiva());
    }

    public Mascota registrar(MascotaRequestDTO dto) {
        validarCodigoNuevo(dto.getCodigo());
        Mascota mascota = new Mascota();
        copiarDatos(dto, mascota);
        return repository.save(mascota);
    }

    public Mascota actualizar(Long id, MascotaRequestDTO dto) {
        Mascota mascota = buscarPorId(id);
        validarCodigoActualizado(dto.getCodigo(), id);
        copiarDatos(dto, mascota);

        return repository.save(mascota);
    }

    private void validarCodigoNuevo(String codigo) {
        if (repository.existsByCodigo(codigo)) {
            throw new CodigoDuplicadoException("El código ya está registrado");
        }
    }

    private void validarCodigoActualizado(String codigo, Long id) {
        if (repository.existsByCodigoAndIdNot(codigo, id)) {
            throw new CodigoDuplicadoException("El código ya está registrado");
        }
    }
}