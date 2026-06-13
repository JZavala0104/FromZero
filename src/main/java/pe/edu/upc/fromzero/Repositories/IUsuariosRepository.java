package pe.edu.upc.fromzero.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.fromzero.Entities.Usuarios;
import java.util.List;

@Repository
public interface IUsuariosRepository extends JpaRepository<Usuarios, Integer> {
    public Usuarios findOneByUsername(String username);
    List<Usuarios> findAllByOrderByIdUserAsc();
}

