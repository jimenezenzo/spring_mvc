package ar.edu.unlam.tallerweb1.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "medico_id")
public class Medico extends Usuario {

    private String matricula;

    private Boolean guardia;

    @ManyToMany(mappedBy = "medicos")
    @JsonIgnore
    private List<Especialidad> especialidades;

    @ManyToOne
    private Consultorio consultorio;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }

    public Boolean getGuardia() {
        return guardia;
    }

    public void setGuardia(Boolean guardia) {
        this.guardia = guardia;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }
}
