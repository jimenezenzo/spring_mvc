package ar.edu.unlam.tallerweb1.modelo.datos;

import ar.edu.unlam.tallerweb1.modelo.Medico;

import javax.validation.constraints.NotNull;

public class DatosCitaDomicilio {

    @NotNull
    private String sintomas;
    private boolean urgente;
    private String emailPaciente;
    private Float latitud;
    private Float longitud;
    private Medico medico;
    private Long demora;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Long getDemora() {
        return demora;
    }

    public void setDemora(Long demora) {
        this.demora = demora;
    }

    public Float getLatitud() {
        return latitud;
    }

    public void setLatitud(Float latitud) {
        this.latitud = latitud;
    }

    public Float getLongitud() {
        return longitud;
    }

    public void setLongitud(Float longitud) {
        this.longitud = longitud;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public boolean isUrgente() {
        return urgente;
    }

    public void setUrgente(boolean urgente) {
        this.urgente = urgente;
    }

    public String getEmailPaciente() {
        return emailPaciente;
    }

    public void setEmailPaciente(String emailPaciente) {
        this.emailPaciente = emailPaciente;
    }
}
