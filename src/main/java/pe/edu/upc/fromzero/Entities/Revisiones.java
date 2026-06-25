package pe.edu.upc.fromzero.Entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Revisiones")
public class Revisiones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRevision;
    @ManyToOne
    @JoinColumn(name = "IdTarea")
    private Tareas idTarea;
    @Column(name = "Comentar",nullable = false,columnDefinition = "TEXT")
    private String comentar;
    @Column(name = "Estado",length = 50,nullable = false)
    private String estado;
    @Column(name = "Fecha",nullable = false)
    private LocalDateTime fecha;
    public Revisiones() {
    }

    public Revisiones(int idRevision, Tareas idTarea, String comentar, String estado, LocalDateTime fecha) {
        this.idRevision = idRevision;
        this.idTarea = idTarea;
        this.comentar = comentar;
        this.estado = estado;
        this.fecha = fecha;
    }
    //holaprueba

    public int getIdRevision() {
        return idRevision;
    }

    public void setIdRevision(int idRevision) {
        this.idRevision = idRevision;
    }

    public Tareas getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Tareas idTarea) {
        this.idTarea = idTarea;
    }

    public String getComentar() {
        return comentar;
    }

    public void setComentar(String comentar) {
        this.comentar = comentar;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
