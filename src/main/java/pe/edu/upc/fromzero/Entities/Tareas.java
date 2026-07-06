package pe.edu.upc.fromzero.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Tareas")
public class Tareas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTarea;
    @ManyToOne
    @JoinColumn(name = "idProject")
    private Proyectos idProject;
    @Column(name = "Titulo",length = 150,nullable = false)
    private String titulo;
    @Column(name = "Descripcion",nullable = false)
    private String descripcion;
    @Column(name = "FechaLimite",nullable = false)
    private LocalDateTime fechaLimite;
    @Column(name = "Estado",length = 50,nullable = false)
    private String estado;
    public Tareas() {
    }

    public Tareas(int idTarea, Proyectos idProject, String titulo, String descripcion, LocalDateTime fechaLimite, String estado) {
        this.idTarea = idTarea;
        this.idProject = idProject;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.estado = estado;
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public Proyectos getIdProject() {
        return idProject;
    }

    public void setIdProject(Proyectos idProject) {
        this.idProject = idProject;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(LocalDateTime fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
