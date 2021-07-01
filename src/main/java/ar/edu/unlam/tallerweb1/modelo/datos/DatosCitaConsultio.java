package ar.edu.unlam.tallerweb1.modelo.datos;

import javax.validation.constraints.NotNull;

public class DatosCitaConsultio {
    private String paciente;
    @NotNull(message = "Tenes que seleccionar un medico")
    private int medico;
    @NotNull(message = "Tenes que seleccionar una especialidad")
    private int especialidad;
    @NotNull(message = "Tenes que ingresar la hora de la cita")
    private String hora;
    private int tipoCita;
    @NotNull(message = "Tenes que seleccionar una fecha")
    private String fecha;

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setMedico(int medico) {
        this.medico = medico;
    }

    public int getMedico() {
        return medico;
    }

    public void setEspecialidad(int especialidad) {
        this.especialidad = especialidad;
    }

    public int getEspecialidad() {
        return especialidad;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getHora() {
        return hora;
    }

    public void setTipoCita(int tipoCita) {
        this.tipoCita = tipoCita;
    }

    public int getTipoCita() {
        return tipoCita;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }
}
