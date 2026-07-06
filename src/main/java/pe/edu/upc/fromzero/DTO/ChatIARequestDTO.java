package pe.edu.upc.fromzero.DTO;

import java.util.List;

public class ChatIARequestDTO {

    // Historial completo de la conversación, incluyendo el último mensaje del usuario
    private List<ChatMensajeDTO> mensajes;

    public ChatIARequestDTO() {
    }

    public ChatIARequestDTO(List<ChatMensajeDTO> mensajes) {
        this.mensajes = mensajes;
    }

    public List<ChatMensajeDTO> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<ChatMensajeDTO> mensajes) {
        this.mensajes = mensajes;
    }
}
