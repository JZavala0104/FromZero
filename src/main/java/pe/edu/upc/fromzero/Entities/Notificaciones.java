package pe.edu.upc.fromzero.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Notificaciones")
public class Notificaciones {
    @Id
    @GeneratedValue
    private int idNotification;
    @ManyToOne
    @JoinColumn(name = "IdUser")
    private Usuarios idUser;
    @Column(name = "Mensaje",nullable = false,columnDefinition = "TEXT")
    private String mensaje;
    @Column(name = "Leido",nullable = false)
    private boolean leido;
    @Column(name = "Fecha",nullable = false)
    private LocalDateTime fecha;

    public Notificaciones() {
    }

    public Notificaciones(int idNotification, Usuarios idUser, String mensaje, boolean leido, LocalDateTime fecha) {
        this.idNotification = idNotification;
        this.idUser = idUser;
        this.mensaje = mensaje;
        this.leido = leido;
        this.fecha = fecha;
    }

    public int getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(int idNotification) {
        this.idNotification = idNotification;
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

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
