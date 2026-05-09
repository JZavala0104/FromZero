package pe.edu.upc.fromzero.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.fromzero.Entities.TipsIA;

import java.util.List;

@Repository
public interface ITipsIARepository extends JpaRepository<TipsIA, Integer> {
    @Query(value = "SELECT p.titulo, COUNT(t.id) " +
            "FROM tipsia t " +
            "JOIN proyectos p ON t.proyecto_id = p.id_proyecto " +
            "GROUP BY p.titulo", nativeQuery = true)
    List<Object[]> GetQuery8();
}
