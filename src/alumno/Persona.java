package alumno;

import javax.persistence.*;

import org.openxava.annotations.*;

@MappedSuperclass
@Views({ @View(members = "dni;nombreYApellido;direccion;telefono;celular;mail") })
public class Persona {

	@Id
	private Long dni;

	@Required
	private String nombreYApellido;

	@Embedded
	private Direccion direccion;

	private int telefono;

	private int celular;

	private String mail;

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public String getNombreYApellido() {
		return nombreYApellido;
	}

	public void setNombreYApellido(String nombreYApellido) {
		this.nombreYApellido = nombreYApellido;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public int getCelular() {
		return celular;
	}

	public void setCelular(int celular) {
		this.celular = celular;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Stereotype("ETIQUETA")
	@LabelFormat(LabelFormatType.NO_LABEL)
	public Long getDocumento() {
		return this.dni;
	}

}
