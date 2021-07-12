package ar.edu.unlam.tallerweb1.modelo;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;



@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	private String email;
	private String password;
	@OneToOne(cascade = CascadeType.ALL)
	private Persona persona;

	private String rol;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Usuario)) return false;
		Usuario usuario = (Usuario) o;
		return Objects.equals(getId(), usuario.getId()) &&
				Objects.equals(getEmail(), usuario.getEmail()) &&
				Objects.equals(getPassword(), usuario.getPassword()) &&
				Objects.equals(getRol(), usuario.getRol());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getEmail(), getPassword(), getRol());
	}
}
