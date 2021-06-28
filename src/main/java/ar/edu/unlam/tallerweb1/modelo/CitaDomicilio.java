package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "citaDomicilio_id")
public class CitaDomicilio extends Cita{

    private Float latitud;

    private Float longitud;

    private String sintomas;
}
