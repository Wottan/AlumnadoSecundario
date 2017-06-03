package cursado;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.model.*;

import alumno.*;

@Entity
// @Tabs({ @Tab(properties = "anio,anioDeCurso,division") })
@Views({ @View(members = "alumno") })
public class Cursado extends Identifiable {

	private boolean egresado;

	@Required
	@ManyToOne
	@ReferenceView("SimpleCursado")
	@SearchAction("AlumnoControlador.buscar")
	private Alumno alumno;

	@ManyToOne
	private CursoHabilitado cursoHabilitado;

	public boolean isEgresado() {
		return egresado;
	}

	public void setEgresado(boolean egresado) {
		this.egresado = egresado;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public CursoHabilitado getCursoHabilitado() {
		return cursoHabilitado;
	}

	public void setCursoHabilitado(CursoHabilitado cursoHabilitado) {
		this.cursoHabilitado = cursoHabilitado;
	}

}
