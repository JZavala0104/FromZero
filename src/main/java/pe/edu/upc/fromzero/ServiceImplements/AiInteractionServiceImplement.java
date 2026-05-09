package pe.edu.upc.fromzero.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.fromzero.Repositories.ITipsIARepository;
import pe.edu.upc.fromzero.ServiceInterface.IAiInteractionService;

import java.util.List;

@Service
public class AiInteractionServiceImplement implements IAiInteractionService {
    @Autowired
    private ITipsIARepository tipsIARepository;

    @Override
    public List<Object[]> GetQuery8() {
        return tipsIARepository.GetQuery8();
    }
}
