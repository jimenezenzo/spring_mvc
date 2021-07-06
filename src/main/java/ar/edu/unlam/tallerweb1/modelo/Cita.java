package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private Medico medico;

    @OneToMany(mappedBy = "cita", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CitaHistoria> citaHistoriaList;

    private LocalDateTime fechaRegistro;

    public Cita() {
        this.citaHistoriaList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<CitaHistoria> getCitaHistoriaList() {
        return citaHistoriaList;
    }

    public void setCitaHistoriaList(List<CitaHistoria> citaHistoriaList) {
        this.citaHistoriaList = citaHistoriaList;
    }

    public CitaHistoria getUltimaHistoria(){
        return this.getCitaHistoriaList().get(this.getCitaHistoriaList().size() - 1);
    }

    public void agregarHistoria(CitaHistoria citaHistoria){
        citaHistoria.setCita(this);
        this.citaHistoriaList.add(citaHistoria);
    }

    public String getFechaRegistroFormateada(){
        DateTimeFormatter esDateFormatLargo = DateTimeFormatter
                .ofPattern("dd 'de' MMMM 'de' yyyy HH:mm")
                .withLocale(new Locale("es", "AR"));

        return this.fechaRegistro.format(esDateFormatLargo);
    }
}
