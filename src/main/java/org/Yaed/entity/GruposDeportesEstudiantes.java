package org.Yaed.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "gruposdeportesestudiantes")
public class GruposDeportesEstudiantes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Referencia a Estudiante (carnet es la PK)
    @ManyToOne
    @JoinColumn(name = "id_estudiante", referencedColumnName = "carnet", nullable = false)
    private Estudiante estudiante;

    // Referencia a GrupoDeporte
    @ManyToOne
    @JoinColumn(name = "grupo_id", referencedColumnName = "id", nullable = false)
    private GrupoDeporte grupoDeporte;

    public GruposDeportesEstudiantes() {
    }

    public GruposDeportesEstudiantes(Estudiante estudiante, GrupoDeporte grupoDeporte) {
        this.estudiante = estudiante;
        this.grupoDeporte = grupoDeporte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public GrupoDeporte getGrupoDeporte() {
        return grupoDeporte;
    }

    public void setGrupoDeporte(GrupoDeporte grupoDeporte) {
        this.grupoDeporte = grupoDeporte;
    }

    @Override
    public String toString() {
        return "GruposDeportesEstudiantes{" +
                "id=" + id +
                ", estudiante=" + estudiante +
                ", grupoDeporte=" + grupoDeporte +
                '}';
    }
}
