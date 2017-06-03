
package asistencia;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.model.*;

import alumno.*;
import asistencia.control.*;

@Entity
// @Views(@View(members="id;fecha;presente;alumno"))
@Tabs({ @Tab(properties = "alumno.nombreYApellido,fecha, presente,alumno.curso") })
public class Asistencia extends Identifiable {

	private Date fecha;

	@OnChange(AlCambiarPresente_action.class)
	private Boolean presente;

	@ManyToOne
	@NoSearch
	@ReferenceView("Simple")
	private Alumno alumno;

	public Asistencia() {

	}

	public Asistencia(Date fecha, Boolean presente, Alumno alumno) {
		super();
		this.fecha = fecha;
		this.presente = presente;
		this.alumno = alumno;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Boolean getPresente() {
		return presente;
	}

	public void setPresente(Boolean presente) {
		this.presente = presente;
	}

	@Stereotype("ETIQUETA")
	public Boolean getEstuvoPresente() {
		return this.presente;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	// public void setPresenteGuardado(Boolean presenteGuardado) {
	// this.presenteGuardado = presenteGuardado;
	// }

	@Override
	public String toString() {
		return "Asistencia [fecha=" + fecha + ", presente=" + presente + ", alumno=" + alumno + "]";
	}

}
