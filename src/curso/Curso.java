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
@Tabs({ @Tab(properties = "descripcion,division.descripcion,anioOrientacion.anio") })
@Views({ @View(name = "Simple", members = "descripcion;division;orientacion;anio"),
		@View(members = "descripcion;orientacion;anioOrientacion;division") })
public class Curso extends Identifiable {

	@Required
	private String descripcion;

	@ManyToOne
	@DescriptionsList
	private Division division;

	@OneToMany(mappedBy = "curso")
	private List<CursoHabilitado> cursosHabilitados;

	@ManyToOne
	@DescriptionsList
	private Orientacion orientacion;

	@ManyToOne
	@DescriptionsList(descriptionProperties = "anio", depends = "orientacion", condition = "${orientacion.id}=?")
	private AnioOrientacion anioOrientacion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

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
	
	public List<Materia> devolverMaterias(){
		List<Materia>retorno= new ArrayList<Materia>();
		for (MateriaPorAnio materiaPorAnio : anioOrientacion.getMaterias()) {
			retorno.add(materiaPorAnio.getMateria());
		}
		return retorno;
	}
}
