package asistencia;

import javax.persistence.*;

import org.openxava.annotations.*;

import alumno.*;

@Embeddable
public class AsistenciaAlumnoTransitoria {

	@ManyToOne
	@NoSearch
	@ReferenceView("Simple")
	private Alumno alumno;

	private boolean presente;

	public boolean isPresente() {
		return presente;
	}

	public void setPresente(boolean presente) {
		this.presente = presente;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	@Stereotype("ETIQUETA")
	public Boolean getEstuvoPresente() {
		return this.presente;
	}
}
