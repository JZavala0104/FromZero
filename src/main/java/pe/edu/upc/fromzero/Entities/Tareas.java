package pe.edu.upc.fromzero.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Tareas")
public class Tareas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTarea;
    @ManyToOne
    @JoinColumn(name = "IdProyecto")
    private Proyectos idProyecto;
    @Column(name = "Titulo",length = 150,nullable = false)
    private String titulo;
    @Column(name = "Descripcion",nullable = false)
    private String descripcion;
    @Column(name = "FechaLimite",nullable = false)
    private String fechaLimite;
    @Column(name = "Estado",length = 50,nullable = false)
    private String estado;
    public Tareas() {
    }

    public Tareas(int idTarea, Proyectos idProyecto, String titulo, String descripcion, String fechaLimite, String estado) {
        this.idTarea = idTarea;
        this.idProyecto = idProyecto;
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

    public Proyectos getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Proyectos idProyecto) {
        this.idProyecto = idProyecto;
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

    public String getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
