package pe.edu.upc.fromzero.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Proyectos")
public class Proyectos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProject;
    @ManyToOne
    @JoinColumn(name = "IdEmpresa")
    private Empresas idEmpresa;
    @Column(name = "Titulo",length = 150,nullable = false)
    private String titulo;
    @Column(name = "Descripcion",nullable = false)
    private String descripcion;
    @Column(name = "Presupuesto",nullable = false)
    private double presupuesto;
    @Column(name = "Estado",length = 50,nullable = false)
    private String estado;
    @Column(name = "FechaInicio",nullable = false)
    private LocalDateTime fechaInicio;
    @Column(name = "FechaFin",nullable = false)
    private LocalDateTime fechaFin;

    public Proyectos() {
    }

    public Proyectos(int idProject, Empresas idEmpresa, String titulo, String descripcion, double presupuesto, String estado, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.idProject = idProject;
        this.idEmpresa = idEmpresa;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.presupuesto = presupuesto;
        this.estado = estado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public Empresas getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresas idEmpresa) {
        this.idEmpresa = idEmpresa;
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

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }
}
