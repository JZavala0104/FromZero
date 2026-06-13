package pe.edu.upc.fromzero.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.fromzero.Entities.Proyectos;
import java.util.List;

@Repository
public interface IProyectosRepository extends JpaRepository<Proyectos, Integer> {

    @Query(value = "SELECT p.id_project, p.titulo, p.presupuesto, e.nombre_empresa " +
            "FROM proyectos p " +
            "INNER JOIN empresas e ON p.id_empresa = e.id_empresa " +
            "WHERE p.estado = 'Activo'", nativeQuery = true)
    List<Object[]> GetQuery3();
    @Query(value = "SELECT p.titulo, e.nombre_empresa, p.fecha_limite " +
            "FROM proyectos p " +
            "JOIN empresas e ON p.id_empresa = e.id_empresa " +
            "WHERE p.desarrollador_id = :developerId AND p.estado = 'En Progreso' " +
            "ORDER BY p.fecha_limite ASC", nativeQuery = true)
    List<Object[]> GetQuery7(@Param("developerId") Long developerId);
    @Query(value = "SELECT p.titulo, e.nombre_empresa, p.fecha_fin, p.estado, " +
            "COUNT(t.id_tarea) AS tareas_pendientes " +
            "FROM proyectos p " +
            "JOIN empresas e ON p.id_empresa = e.id_empresa " +
            "LEFT JOIN tareas t ON t.id_proyecto = p.id_project AND t.estado = 'Pendiente' " +
            "WHERE p.estado = 'En Progreso' AND p.fecha_fin <= NOW() + INTERVAL '30 days' " +
            "GROUP BY p.titulo, e.nombre_empresa, p.fecha_fin, p.estado " +
            "ORDER BY p.fecha_fin ASC", nativeQuery = true)
    List<Object[]> getQuery10();
    List<Proyectos> findAllByOrderByIdProjectAsc();
}

