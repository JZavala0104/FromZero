package pe.edu.upc.fromzero.DTO;

import java.time.LocalDateTime;

public class UsuariosDTO {
    private int idUser;
    private String username;
    private String nombre;
    private String email;
    private String password;
    private LocalDateTime fechaRegistro;
    private boolean habilitado;
    private int idRol;

    public int getIdUser() {
        return this.idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre) {
        nombre = Nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        email = Email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String Password) {
        password = Password;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime FechaRegistro) {
        fechaRegistro = FechaRegistro;
    }

    public boolean isHabilitado() {
        return this.habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int IdRol) {
        idRol = IdRol;
    }
}
