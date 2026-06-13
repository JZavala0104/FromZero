package pe.edu.upc.fromzero.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Empresas")
public class Empresas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmpresa;
    @ManyToOne
    @JoinColumn(name = "IdUser")
    private Usuarios idUser;
    @Column(name = "NombreEmpresa",length = 150,nullable = false)
    private String nombreEmpresa;
    @Column(name = "Descripcion",nullable = false,columnDefinition = "TEXT")
    private String descripcion;

    public Empresas() {
    }

    public Empresas(int idEmpresa, Usuarios idUser, String nombreEmpresa, String descripcion) {
        this.idEmpresa = idEmpresa;
        this.idUser = idUser;
        this.nombreEmpresa = nombreEmpresa;
        this.descripcion = descripcion;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Usuarios getIdUser() {
        return idUser;
    }

    public void setIdUser(Usuarios idUser) {
        this.idUser = idUser;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
