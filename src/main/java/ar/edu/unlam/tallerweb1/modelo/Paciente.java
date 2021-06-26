package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "paciente_id")
public class Paciente extends Usuario{

    private String numeroAfiliado;

    public String getNumeroAfiliado() {
        return numeroAfiliado;
    }

    public void setNumeroAfiliado(String numeroAfiliado) {
        this.numeroAfiliado = numeroAfiliado;
    }
}
