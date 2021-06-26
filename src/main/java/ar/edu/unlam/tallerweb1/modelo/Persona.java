package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroDocumento;
    private String tipoDocumento;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String sexo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /*public boolean chequearPersona(Persona persona){

        boolean datosCorrectos = true;

        if (    persona.getNumeroAfiliado().equals("") ||
                persona.getNombre().equals("") ||
                persona.getApellido().equals("") ||
                persona.getEmail().equals("") ||
                persona.getTipoDocumento().equals("") ||
                persona.getNumeroDocumento().equals("")||
                persona.getSexo().equals("")

            )

        {
            datosCorrectos = false;
        }

        return datosCorrectos;

    }*/


    /*public FormularioPersona toFormularioPersona()
    {
        FormularioPersona formularioPersona = new FormularioPersona();
        formularioPersona.setNumeroAfiliado(numeroAfiliado);
        formularioPersona.setNombre(nombre);
        formularioPersona.setApellido(apellido);
        formularioPersona.setFechaNacimiento(fechaNacimiento);
        formularioPersona.setNumeroDocumento(numeroDocumento);
        formularioPersona.setTipoDocumento(tipoDocumento);
        formularioPersona.setSexo(sexo);
        formularioPersona.setMatricula(matricula);
        formularioPersona.setEmail(email);


        return formularioPersona;
    }*/


}

