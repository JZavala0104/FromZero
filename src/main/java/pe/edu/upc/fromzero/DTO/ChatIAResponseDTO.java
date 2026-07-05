package pe.edu.upc.fromzero.DTO;

public class ChatIAResponseDTO {

    private String respuesta;

    public ChatIAResponseDTO() {
    }

    public ChatIAResponseDTO(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
