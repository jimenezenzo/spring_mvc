package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.CitaHistoria;
import ar.edu.unlam.tallerweb1.modelo.EstadoCita;
import ar.edu.unlam.tallerweb1.modelo.datos.DatosCitaHistoria;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCitaHistoria;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioMedico;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class ServicioCitaHistoriaImpl implements ServicioCitaHistoria{

    RepositorioCitaHistoria repositorioCitaHistoria;
    RepositorioMedico repositorioMedico;
    private String filePath = "C:/files/";

    @Autowired
    public ServicioCitaHistoriaImpl(RepositorioCitaHistoria repositorioCitaHistoria, RepositorioMedico repositorioMedico) {
        this.repositorioCitaHistoria = repositorioCitaHistoria;
        this.repositorioMedico = repositorioMedico;
    }

    @Override
    public CitaHistoria citaHistoriaById(Long idCita) {
        return repositorioCitaHistoria.citaHistoriaById(idCita);
    }

    @Override
    public void updateCitaHistoria(CitaHistoria citaHistoria) {
        repositorioCitaHistoria.updateCitaHistoria(citaHistoria);
    }

    @Override
    public void observarCitaConsultorio(DatosCitaHistoria datosCitaHistoria) throws IOException {
        CitaHistoria citaHistoria = new CitaHistoria();
        citaHistoria.setObservacion(datosCitaHistoria.getObservacion());
        citaHistoria.setFechaRegistro(LocalDateTime.now());
        citaHistoria.setCita(this.repositorioMedico.obtenerCitaConsultorioId(datosCitaHistoria.getIdCita()));

        if (!datosCitaHistoria.getFile().isEmpty()){
            MultipartFile multipart = datosCitaHistoria.getFile();
            String getOriginalFilename = UUID.randomUUID() + multipart.getOriginalFilename().substring( multipart.getOriginalFilename().lastIndexOf("."));

            File path = new File(this.filePath);
            multipart.transferTo(new File(path, getOriginalFilename));

            citaHistoria.setArchivo(getOriginalFilename);
        }

        switch (datosCitaHistoria.getEstado()){
            case "observado":
                citaHistoria.setEstado(EstadoCita.OBSERVADO);
                break;
            case "finalizado":
                citaHistoria.setEstado(EstadoCita.FINALIZADO);
                break;
            case "cancelado":
                citaHistoria.setEstado(EstadoCita.CANCELADO);
                break;
        }

        this.repositorioCitaHistoria.guardarHistoria(citaHistoria);
    }

    @Override
    public void cancelarCitaPaciente(Long idCita) {
        CitaHistoria citaHistoria = new CitaHistoria();
        citaHistoria.setObservacion("Cancelado por el paciente");
        citaHistoria.setFechaRegistro(LocalDateTime.now());
        citaHistoria.setCita(this.repositorioMedico.obtenerCitaConsultorioId(idCita));
        citaHistoria.setEstado(EstadoCita.CANCELADO);

        this.repositorioCitaHistoria.guardarHistoria(citaHistoria);
    }

    @Override
    public void descargarArchivo(String file, HttpServletResponse response) throws IOException {
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file + "\"");

        InputStream is = new FileInputStream(filePath + file);

        IOUtils.copy(is, response.getOutputStream());

        response.flushBuffer();
    }
}
