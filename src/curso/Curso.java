package curso;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.model.*;

import cursado.*;
import materia.*;
import orientacion.*;
import util.*;

@Entity
@Tabs({ @Tab(properties = "division.descripcion,anioOrientacion.anio") })
@Views({ @View(name = "Simple", members = "division;orientacion;anio"),
		@View(members = "orientacion;anioOrientacion;division") })
public class Curso extends Identifiable {

	@Required
	@ManyToOne
	@DescriptionsList
	private Division division;

	@OneToMany(mappedBy = "curso")
	private List<CursoHabilitado> cursosHabilitados;

	@Required
	@ManyToOne
	@DescriptionsList
	private Orientacion orientacion;

	@Required
	@ManyToOne
	@DescriptionsList(descriptionProperties = "anio", depends = "orientacion", condition = "${orientacion.id}=?")
	private AnioOrientacion anioOrientacion;

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	public List<CursoHabilitado> getCursosHabilitados() {
		return cursosHabilitados;
	}

	public void setCursosHabilitados(List<CursoHabilitado> cursosHabilitados) {
		this.cursosHabilitados = cursosHabilitados;
	}

	public boolean tieneCursoHabilitadoEnAnioActual() {
		int año = Util.obtenerAnio(new Date());
		for (CursoHabilitado cursoHabilitado : cursosHabilitados) {
			if (cursoHabilitado.getAnio() == año) {
				return true;
			}
		}
		return false;
	}

	public boolean anioMayorEnCursoHabilitado(int anio) {
		for (CursoHabilitado cursoHabilitado : cursosHabilitados) {
			if (anio <= cursoHabilitado.getAnio()) {
				return true;
			}
		}
		return false;
	}

	public int getAnio() {
		return this.anioOrientacion.getAnio();
	}

	public Orientacion getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(Orientacion orientacion) {
		this.orientacion = orientacion;
	}

	public AnioOrientacion getAnioOrientacion() {
		return anioOrientacion;
	}

	public void setAnioOrientacion(AnioOrientacion anioOrientacion) {
		this.anioOrientacion = anioOrientacion;
	}

	public String devolverInformacion() {
		return this.descripcion + " " + this.anioOrientacion.getAnio() + " " + this.division.getDescripcion();
	}

	public List<Materia> devolverMaterias() {
		List<Materia> retorno = new ArrayList<Materia>();
		for (MateriaPorAnio materiaPorAnio : anioOrientacion.getMaterias()) {
			retorno.add(materiaPorAnio.getMateria());
		}
		return retorno;
	}

	public List<Cursado> devolverCursadosDeAnioAnteriorHasta3Previas(int anio) {
		ArrayList<Cursado> retorno = new ArrayList<Cursado>();
		for (CursoHabilitado cursoHabilitado : cursosHabilitados) {
			if (cursoHabilitado.getAnio() == anio - 1) {
				retorno.addAll(cursoHabilitado.devolverAlumnosQueTenganHasta3Previas());
			}
		}
		return retorno;
	}
}
