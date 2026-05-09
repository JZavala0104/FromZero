package pe.edu.upc.fromzero.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.fromzero.Entities.Desarrolladores;
import java.util.List;

@Repository
public interface IDesarrolladoresRepository extends JpaRepository<Desarrolladores, Integer> {
}

