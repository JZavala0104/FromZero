package pe.edu.upc.fromzero.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.fromzero.DTO.ChatIARequestDTO;
import pe.edu.upc.fromzero.DTO.ChatIAResponseDTO;
import pe.edu.upc.fromzero.ServiceInterface.IChatIAService;

@RestController
@RequestMapping("/api/chatia")
@PreAuthorize("hasAnyAuthority('ADMIN','OPERADOR','DESARROLLADOR','EMPRESARIO')")
public class ChatIAController {

    @Autowired
    private IChatIAService ChatIAService;

    @PostMapping("/Mensaje")
    public ResponseEntity<?> EnviarMensaje(@RequestBody ChatIARequestDTO dto) {
        if (dto == null || dto.getMensajes() == null || dto.getMensajes().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Debes enviar al menos un mensaje");
        }
        try {
            String respuesta = ChatIAService.Responder(dto.getMensajes());
            return ResponseEntity.ok(new ChatIAResponseDTO(respuesta));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al conectarse con el modelo de IA");
        }
    }
}
