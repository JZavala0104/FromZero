package pe.edu.upc.fromzero.DTO;

public class ChatMensajeDTO {

    // "user" o "assistant"
    private String role;
    private String contenido;

    public ChatMensajeDTO() {
    }

    public ChatMensajeDTO(String role, String contenido) {
        this.role = role;
        this.contenido = contenido;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
