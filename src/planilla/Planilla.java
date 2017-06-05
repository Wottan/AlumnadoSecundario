package planilla;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.model.*;

import cursado.*;

@Entity
@Tabs({@Tab(properties="cursado.alumno.dni,cursado.alumno.nombreYApellido")})
public class Planilla extends Identifiable {

	@OneToMany(mappedBy="planilla")
	private List<MateriaPlanilla> materiaPlanilla;

	@ManyToOne
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

}
