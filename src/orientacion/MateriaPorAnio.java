package orientacion;

import javax.persistence.*;

import org.openxava.annotations.*;

import materia.*;

@Embeddable
public class MateriaPorAnio {

	@ManyToOne
	@ReferenceView("Simple")
	@SearchAction("MateriaControlador.buscar")
	private Materia materia;

	private int horasCatedra;

	private String formacion;

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public int getHorasCatedra() {
		return horasCatedra;
	}

	public void setHorasCatedra(int horasCatedra) {
		this.horasCatedra = horasCatedra;
	}

	public String getFormacion() {
		return formacion;
	}

	public void setFormacion(String formacion) {
		this.formacion = formacion;
	}

}
