package pe.edu.upc.fromzero.Entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Roles")
public class Roles implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRol;
    @Column(name = "Nombre",length = 50,nullable = false)
    private String Nombre;

    public Roles() {
    }

    public Roles(int ID, String name) {
        this.idRol = ID;
        Nombre = name;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int ID) {
        this.idRol = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String name) {
        Nombre = name;
    }
}
