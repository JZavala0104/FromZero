package pe.edu.upc.fromzero.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.fromzero.Entities.Proyectos;
import java.util.List;

@Repository
public interface IProyectosRepository extends JpaRepository<Proyectos, Integer> {

    @Query(value = "SELECT p.IdProject, p.Titulo, p.Presupuesto, e.NombreEmpresa " +
            "FROM Proyectos p " +
            "INNER JOIN Empresas e ON p.IdEmpresa = e.IdEmpresa " +
            "WHERE p.Estado = 'Activo'", nativeQuery = true)
    List<Object[]> GetQuery3();

}

