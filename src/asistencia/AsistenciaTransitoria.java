package asistencia;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import asistencia.control.*;

import cursado.*;

@View(members = "fecha;cursoHabilitado;asistencias")
public class AsistenciaTransitoria {

	@ManyToOne
	@ReferenceView("Asistencia")
	@OnChange(AlCambiarCursoHabiltado.class)
	private CursoHabilitado cursoHabilitado;

	@ReadOnly
	private Date fecha;

	// // @ReadOnly
	// @EditOnly
	// @ElementCollection
	// @CollectionTable(name = "Asistencia")
	//
	// @ListProperties(value =
	// "alumno.documento,alumno.nombreYApellido,estuvoPresente,presente")
	// private List<Asistencia> asistencias;

	// @ReadOnly
	@EditOnly
	@ElementCollection
	@CollectionTable(name = "Asistencia")
	@ListProperties(value = "alumno.documento,alumno.nombreYApellido,estuvoPresente,presente")
	private List<AsistenciaAlumnoTransitoria> asistencias;

	public CursoHabilitado getCursoHabilitado() {
		return cursoHabilitado;
	}

	public void setCursoHabilitado(CursoHabilitado cursoHabilitado) {
		this.cursoHabilitado = cursoHabilitado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<AsistenciaAlumnoTransitoria> getAsistencias() {
		return asistencias;
	}

	public void setAsistencias(List<AsistenciaAlumnoTransitoria> asistencias) {
		this.asistencias = asistencias;
	}

}
