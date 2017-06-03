package planilla;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.model.*;

import materia.*;

@Entity
public class MateriaPlanilla extends Identifiable {

	@ElementCollection
	@ListProperties("periodo,nota")
	private List<Nota> nota;

	@ManyToOne
	private Materia materia;

	@ManyToOne
	private Planilla planilla;

	public List<Nota> getNota() {
		return nota;
	}

	public void setNota(List<Nota> nota) {
		this.nota = nota;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Planilla getPlanilla() {
		return planilla;
	}

	public void setPlanilla(Planilla planilla) {
		this.planilla = planilla;
	}

}
