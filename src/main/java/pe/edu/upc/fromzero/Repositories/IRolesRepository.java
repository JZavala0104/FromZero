package pe.edu.upc.fromzero.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.fromzero.Entities.Roles;
import java.util.List;

@Repository
public interface IRolesRepository extends JpaRepository<Roles, Integer> {
    List<Roles> findAllByOrderByIdRolAsc();
}

