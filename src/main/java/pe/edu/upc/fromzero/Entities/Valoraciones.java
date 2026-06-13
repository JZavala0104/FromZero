package pe.edu.upc.fromzero.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Valoraciones")
public class Valoraciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idValoracion;
    @ManyToOne
    @JoinColumn(name = "IdProyecto")
    private Proyectos idProyecto;
    @Column(name = "Puntuacion",nullable = false)
    private double puntuacion;
    @Column(name = "Comentario",nullable = false,columnDefinition = "TEXT")
    private String comentario;

    public Valoraciones() {
    }

    public Valoraciones(int idValoracion, Proyectos idProyecto, double puntuacion, String comentario) {
        this.idValoracion = idValoracion;
        this.idProyecto = idProyecto;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
    }

    public int getIdValoracion() {
        return idValoracion;
    }

    public void setIdValoracion(int idValoracion) {
        this.idValoracion = idValoracion;
    }

    public Proyectos getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Proyectos idProyecto) {
        this.idProyecto = idProyecto;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
