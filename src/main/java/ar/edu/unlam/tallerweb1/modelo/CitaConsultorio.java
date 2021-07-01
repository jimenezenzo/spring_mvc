package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Entity
@PrimaryKeyJoinColumn(name = "citaConsultorio_id")
public class CitaConsultorio extends Cita{

    private LocalDate fecha;

    private LocalTime hora;

    @ManyToOne
    private Especialidad especialidad;

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public String fechaFormateada(){
        DateTimeFormatter esDateFormatLargo = DateTimeFormatter
                .ofPattern("EEEE, dd 'de' MMMM 'de' yyyy")
                .withLocale(new Locale("es", "AR"));

        return fecha.format(esDateFormatLargo);
    }
}
