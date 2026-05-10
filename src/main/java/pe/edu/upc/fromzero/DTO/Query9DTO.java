package pe.edu.upc.fromzero.DTO;

public class Query9DTO {
    private String usuario;
    private String lenguaje;
    private int totalGeneraciones;
    private String ultimaGeneracion;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public int getTotalGeneraciones() {
        return totalGeneraciones;
    }

    public void setTotalGeneraciones(int totalGeneraciones) {
        this.totalGeneraciones = totalGeneraciones;
    }

    public String getUltimaGeneracion() {
        return ultimaGeneracion;
    }

    public void setUltimaGeneracion(String ultimaGeneracion) {
        this.ultimaGeneracion = ultimaGeneracion;
    }
}
