package pe.edu.upc.fromzero.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.fromzero.Entities.ProyectoDesarrollador;
import java.util.List;

@Repository
public interface IProyectoDesarrolladorRepository extends JpaRepository<ProyectoDesarrollador, Integer> {
    List<ProyectoDesarrollador> findAllByOrderByIdProyDesarAsc();
}
