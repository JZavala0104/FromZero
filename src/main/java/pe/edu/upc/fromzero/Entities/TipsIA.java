package pe.edu.upc.fromzero.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "TipsIA")
public class TipsIA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTip;
    @ManyToOne
    @JoinColumn(name = "IdUser")
    private Usuarios idUser;
    @Column(name = "Contenido",nullable = false,columnDefinition = "TEXT")
    private String contenido;
    @Column(name = "Fecha", nullable = false)
    private LocalDate fecha;
    public TipsIA() {
    }

    public TipsIA(int idTip, Usuarios idUser, String contenido, LocalDate fecha) {
        this.idTip = idTip;
        this.idUser = idUser;
        this.contenido = contenido;
        this.fecha = fecha;
    }

    public int getIdTip() {
        return idTip;
    }

    public void setIdTip(int idTip) {
        this.idTip = idTip;
    }

    public Usuarios getIdUser() {
        return idUser;
    }

    public void setIdUser(Usuarios idUser) {
        this.idUser = idUser;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
