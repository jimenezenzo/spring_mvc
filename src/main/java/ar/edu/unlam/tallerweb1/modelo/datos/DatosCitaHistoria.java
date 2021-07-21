package ar.edu.unlam.tallerweb1.modelo.datos;

import org.springframework.web.multipart.MultipartFile;

public class DatosCitaHistoria {
    Long idCita;
    String observacion;
    String estado;
    MultipartFile file;

    public Long getIdCita() {
        return idCita;
    }

    public void setIdCita(Long idCita) {
        this.idCita = idCita;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
