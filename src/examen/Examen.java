package examen;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.model.*;

import alumno.*;
import examen.control.*;
import materia.*;
import planilla.*;

@Entity
@Tabs({ @Tab(properties = "fecha,alumno.dni,alumno.nombreYApellido,calificacion,materiaPlanilla.materia.descripcion,presente") })
public class Examen extends Identifiable {

	@Required
	private Date fecha;

	@Required
	@ManyToOne
	@ReferenceView("Simple2")
	private Alumno alumno;

	@Required
	@ManyToOne
	@SearchAction("ExamenControlador.buscarMateria")
	@ReferenceView("Examen")
	private MateriaPlanilla materiaPlanilla;

	@OnChange(AlCambiarCalificacionExamen.class)
	private Double calificacion;

	private Boolean presente;

	private Boolean aprobo;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public MateriaPlanilla getMateriaPlanilla() {
		return materiaPlanilla;
	}

	public void setMateriaPlanilla(MateriaPlanilla materiaPlanilla) {
		this.materiaPlanilla = materiaPlanilla;
	}

	public Double getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Double calificacion) {
		this.calificacion = calificacion;
	}

	public Boolean getPresente() {
		return presente;
	}

	public void setPresente(Boolean presente) {
		this.presente = presente;
	}

	public Boolean getAprobo() {
		return aprobo;
	}

	public void setAprobo(Boolean aprobo) {
		this.aprobo = aprobo;
	}

}
