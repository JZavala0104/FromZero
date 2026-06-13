package pe.edu.upc.fromzero.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.fromzero.Entities.CodigoGenerado;
import java.util.List;

@Repository
public interface ICodigoGeneradoRepository extends JpaRepository<CodigoGenerado, Integer> {
    @Query(value = "SELECT u.username AS usuario, cg.lenguaje, COUNT(cg.id_code) AS total_generaciones, " +
            "MAX(cg.fecha) AS ultima_generacion " +
            "FROM codigo_generado cg " +
            "JOIN usuarios u ON cg.id_user = u.id_user " +
            "GROUP BY u.username, cg.lenguaje " +
            "ORDER BY total_generaciones DESC", nativeQuery = true)
    List<Object[]> getQuery9();
    List<CodigoGenerado> findAllByOrderByIdCodeAsc();
}
