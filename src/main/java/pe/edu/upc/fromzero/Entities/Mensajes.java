package pe.edu.upc.fromzero.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Mensajes")
public class Mensajes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMensaje;
    @ManyToOne
    @JoinColumn(name = "IdProyecto")
    private Proyectos idProyecto;
    @ManyToOne
    @JoinColumn(name = "IdUser")
    private Usuarios idUser;
    @Column(name = "Mensaje",nullable = false,columnDefinition = "TEXT")
    private String mensaje;
    @Column(name = "Fecha",nullable = false)
    private LocalDateTime fecha;
    public Mensajes() {
    }

    public Mensajes(int idMensaje, Proyectos idProyecto, Usuarios idUser, String mensaje, LocalDateTime fecha) {
        this.idMensaje = idMensaje;
        this.idProyecto = idProyecto;
        this.idUser = idUser;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    public Proyectos getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Proyectos idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Usuarios getIdUser() {
        return idUser;
    }

    public void setIdUser(Usuarios idUser) {
        this.idUser = idUser;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
