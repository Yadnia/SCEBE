package org.Yaed.entity;


import jakarta.persistence.*;

@Entity
public class ActividadDeporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "tipo_id")
    private TipoActividadDeporte tipo;


    public ActividadDeporte() {
    }

    public ActividadDeporte(String nombre, String descripcion, TipoActividadDeporte tipo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoActividadDeporte getTipo() {
        return tipo;
    }

    public void setTipo(TipoActividadDeporte tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "ActividadDeporte{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}
