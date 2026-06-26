package pe.edu.upc.fromzero.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.fromzero.Entities.Mensajes;
import java.util.List;

@Repository
public interface IMensajesRepository extends JpaRepository<Mensajes, Integer> {
    List<Mensajes> findAllByOrderByIdMensajeAsc();
    List<Mensajes> findByIdProyecto_IdProject(int idProject);
}
