package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class CitaHistoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String observacion;

    private Enum<EstadoCita> estado;

    private String archivo;

    @ManyToOne()
    @JoinColumn(name = "cita_id")
    private Cita cita;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Enum<EstadoCita> getEstado() {
        return estado;
    }

    public void setEstado(Enum<EstadoCita> estado) {
        this.estado = estado;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
}
