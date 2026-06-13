package pe.edu.upc.fromzero.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "CodigoGenerado")
public class CodigoGenerado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCode;
    @ManyToOne
    @JoinColumn(name = "IdUser")
    private Usuarios idUser;
    @Column(name = "Prompt",nullable = false,columnDefinition = "TEXT")
    private String prompt;
    @Column(name = "Codigo",nullable = false,columnDefinition = "TEXT")
    private String codigo;
    @Column(name = "Lenguaje",length = 50,nullable = false)
    private String lenguaje;
    @Column(name = "Fecha",nullable = false)
    private LocalDateTime fecha;

    public CodigoGenerado() {
    }

    public CodigoGenerado(int idCode, Usuarios idUser, String prompt, String codigo, String lenguaje, LocalDateTime fecha) {
        this.idCode = idCode;
        this.idUser = idUser;
        this.prompt = prompt;
        this.codigo = codigo;
        this.lenguaje = lenguaje;
        this.fecha = fecha;
    }

    public int getIdCode() {
        return idCode;
    }

    public void setIdCode(int idCode) {
        this.idCode = idCode;
    }

    public Usuarios getIdUser() {
        return idUser;
    }

    public void setIdUser(Usuarios idUser) {
        this.idUser = idUser;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
