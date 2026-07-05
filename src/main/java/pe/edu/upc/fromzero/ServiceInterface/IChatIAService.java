package pe.edu.upc.fromzero.ServiceInterface;

import pe.edu.upc.fromzero.DTO.ChatMensajeDTO;

import java.util.List;

public interface IChatIAService {
    String Responder(List<ChatMensajeDTO> mensajes);
}
