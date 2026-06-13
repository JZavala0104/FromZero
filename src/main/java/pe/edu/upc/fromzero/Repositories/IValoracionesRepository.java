package pe.edu.upc.fromzero.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.fromzero.Entities.Valoraciones;
import java.util.List;

@Repository
public interface IValoracionesRepository extends JpaRepository<Valoraciones, Integer> {
    List<Valoraciones> findAllByOrderByIdValoracionAsc();
}

