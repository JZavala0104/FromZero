package pe.edu.upc.fromzero.Entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Usuarios")
public class Usuarios implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    @Column(name = "Username",length = 20,nullable = false)
    private String username;
    @Column(name = "Nombre",length = 100,nullable = false)
    private String nombre;
    @Column(name = "Email",length = 100,nullable = false)
    private String email;
    @Column(name = "Password",length = 255,nullable = false)
    private String password;
    @Column(name = "FechaRegistro",nullable = false)
    private LocalDateTime fechaRegistro;
    @Column(name = "Habilitado",nullable = false)
    private boolean habilitado;
    @ManyToOne
    @JoinColumn(name = "IdRol")
    private Roles idRol;

    public Usuarios() {
    }

    public Usuarios(int idUser, String username, String nombre, String email, String password, LocalDateTime fechaRegistro, boolean habilitado, Roles idRol) {
        this.idUser = idUser;
        this.username = username;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.fechaRegistro = fechaRegistro;
        this.habilitado = habilitado;
        this.idRol = idRol;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Roles getIdRol() {
        return idRol;
    }

    public void setIdRol(Roles idRol) {
        this.idRol = idRol;
    }
}
