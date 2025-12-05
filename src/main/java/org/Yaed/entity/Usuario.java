package org.Yaed.entity;

import jakarta.persistence.*;
import org.checkerframework.checker.units.qual.C;

@Entity
public class Usuario {
    @Id
    @Column (name = "usuario")
    private String usuario ;

    @Column (name = "correo")
    private String correo ;

    @Column (name = "nombres")
    private String nombres ;

    @Column (name = "apellidos")
    private String apellidos ;

    @Column (name = "celular")
    private String celular ;

    @Column (name = "edad")
    private int edad ;

    @Column(name = "password")
    private String password ;

    @ManyToOne
    @JoinColumn(name = "tipo_usuario")
    private TipoUsuario tipoUsuario;

    public Usuario() {
    }

    public Usuario(String correo, String nombres, String apellidos, String celular, int edad,String password, TipoUsuario tipoUsuario) {
        this.correo = correo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.celular = celular;
        this.edad = edad;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "usuario='" + usuario + '\'' +
                ", correo='" + correo + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", celular='" + celular + '\'' +
                ", edad=" + edad +
                ", password='" + password + '\'' +
                ", tipoUsuario=" + tipoUsuario +
                '}';
    }
}
