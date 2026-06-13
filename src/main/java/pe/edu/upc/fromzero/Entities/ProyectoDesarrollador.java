package pe.edu.upc.fromzero.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ProyectoDesarrollador")
public class ProyectoDesarrollador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProyDesar;
    @ManyToOne
    @JoinColumn(name = "IdProyecto")
    private Proyectos idProyecto;
    @ManyToOne
    @JoinColumn(name = "IdDesarrollador")
    private Desarrolladores idDesarrollador;

    public ProyectoDesarrollador() {
    }

    public ProyectoDesarrollador(int idProyDesar, Proyectos idProyecto, Desarrolladores idDesarrollador) {
        this.idProyDesar = idProyDesar;
        this.idProyecto = idProyecto;
        this.idDesarrollador = idDesarrollador;
    }

    public int getIdProyDesar() {
        return idProyDesar;
    }

    public void setIdProyDesar(int idProyDesar) {
        this.idProyDesar = idProyDesar;
    }

    public Proyectos getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Proyectos idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Desarrolladores getIdDesarrollador() {
        return idDesarrollador;
    }

    public void setIdDesarrollador(Desarrolladores idDesarrollador) {
        this.idDesarrollador = idDesarrollador;
    }
}
