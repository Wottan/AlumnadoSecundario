package planilla;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.model.*;

import materia.*;

@Entity
@Views({ @View(members = "materia;notas") })
public class MateriaPlanilla extends Identifiable {

	@ManyToOne
	private Materia materia;

	@ManyToOne
	private Planilla planilla;

	@ElementCollection
	@ListProperties("periodo.id,periodo.descripcion,nota")
	private List<Nota> notas;

	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
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
