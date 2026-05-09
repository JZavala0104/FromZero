package pe.edu.upc.fromzero.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.fromzero.Repositories.IProyectosRepository;
import pe.edu.upc.fromzero.ServiceInterface.IUrgentProyectoService;

import java.util.List;

@Service
public class UrgentProyectoServiceImplement implements IUrgentProyectoService {
    @Autowired
    private IProyectosRepository IProyectosRepository;

    @Override
    public List<Object[]> GetQuery7(Long developerId) {
        return IProyectosRepository.GetQuery7(developerId);
    }
}
