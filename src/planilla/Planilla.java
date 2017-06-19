package planilla;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.model.*;

import cursado.*;

@Entity
@Views({ @View(members = "Alumno[cursado];materiaPlanilla") })
@Tabs({ @Tab(properties = "cursado.alumno.dni,cursado.alumno.nombreYApellido") })
public class Planilla extends Identifiable {

	@OneToMany(mappedBy = "planilla")
	@NoCreate
	@RemoveAction("")
	@RemoveSelectedAction("")
	@ListProperties("materia.descripcion,promedio")
	private List<MateriaPlanilla> materiaPlanilla;

	@OneToOne
	@ReadOnly
	@ReferenceView("ConCurso")
	@NoFrame
	private Cursado cursado;

	public List<MateriaPlanilla> getMateriaPlanilla() {
		return materiaPlanilla;
	}

	public void setMateriaPlanilla(List<MateriaPlanilla> materiaPlanilla) {
		this.materiaPlanilla = materiaPlanilla;
	}

	public Cursado getCursado() {
		return cursado;
	}

	public void setCursado(Cursado cursado) {
		this.cursado = cursado;
	}

	public List<MateriaPlanilla> devolverPrevias() {
		ArrayList<MateriaPlanilla> previas = new ArrayList<MateriaPlanilla>();
		for (MateriaPlanilla materiaPlanilla : materiaPlanilla) {
			if (materiaPlanilla.getNotas().size() >= 3) {
				if (materiaPlanilla.getPromedio() <= 6) {
					previas.add(materiaPlanilla);
				}
			}
		}
		return previas;
	}
}
