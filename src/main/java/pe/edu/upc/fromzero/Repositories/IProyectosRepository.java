package pe.edu.upc.fromzero.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    @Query(value = "SELECT p.titulo, e.nombre_empresa, p.fecha_limite " +
            "FROM proyectos p " +
            "JOIN empresas e ON p.id_empresa = e.id_empresa " +
            "WHERE p.desarrollador_id = :developerId AND p.estado = 'En Progreso' " +
            "ORDER BY p.fecha_limite ASC", nativeQuery = true)
    List<Object[]> GetQuery7(@Param("developerId") Long developerId);
}

