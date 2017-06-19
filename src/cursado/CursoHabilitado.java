package cursado;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.model.*;

import asistencia.control.*;
import curso.*;

@Entity
@Tab(properties = "anio,curso.descripcion,curso.orientacion.descripcion,curso.anioOrientacion.anio,curso.division.descripcion")
@Views({ @View(name = "Asistencia", members = "id;curso"), @View(name="SinCursado",members="anio;curso") })
public class CursoHabilitado extends Identifiable {

	@ReadOnly
	@DefaultValueCalculator(CurrentYearCalculator.class)
	private int anio;

	@Required
	@ManyToOne
	@ReferenceView("Simple")
	@NoFrame
	@SearchAction("CursoControlador.buscar")
	private Curso curso;

	// @NewAction("CursadoControlador.A�adir")
	@OneToMany(mappedBy = "cursoHabilitado", cascade = CascadeType.REMOVE)
	@ListProperties(value = "alumno.dni,alumno.nombreYApellido")
	@SaveAction("CursadoControlador.grabar")
	private List<Cursado> cursados;

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Cursado> getCursados() {
		return cursados;
	}

	public void setCursados(List<Cursado> cursados) {
		this.cursados = cursados;
	}

}
