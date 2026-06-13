package pe.edu.upc.fromzero.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Desarrolladores")
public class Desarrolladores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDesarrollador;
    @ManyToOne
    @JoinColumn(name = "IdUser")
    private Usuarios idUser;
    @Column(name = "Habilidades", nullable = false,columnDefinition = "TEXT")
    private String habilidades;
    @Column(name = "Experiencia",nullable = false)
    private int experiencia;
    @Column(name = "Portafolio",length = 255,nullable = false)
    private String portafolio;

    public Desarrolladores() {
    }

    public Desarrolladores(int idDesarrollador, Usuarios idUser, String habilidades, int experiencia, String portafolio) {
        this.idDesarrollador = idDesarrollador;
        this.idUser = idUser;
        this.habilidades = habilidades;
        this.experiencia = experiencia;
        this.portafolio = portafolio;
    }

    public int getIdDesarrollador() {
        return idDesarrollador;
    }

    public void setIdDesarrollador(int idDesarrollador) {
        this.idDesarrollador = idDesarrollador;
    }

    public Usuarios getIdUser() {
        return idUser;
    }

    public void setIdUser(Usuarios idUser) {
        this.idUser = idUser;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public String getPortafolio() {
        return portafolio;
    }

    public void setPortafolio(String portafolio) {
        this.portafolio = portafolio;
    }
}
