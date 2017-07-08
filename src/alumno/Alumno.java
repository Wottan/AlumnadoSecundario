package alumno;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import cursado.*;
import curso.*;
import examen.*;
import materia.*;
import planilla.*;
import util.*;

@Entity
@Views({ @View(members = "dni;nombreYApellido;direccion;telefono;celular;mail"),
		@View(name = "Simple", members = "documento;nombreYApellido"),
		@View(name = "Simple2", members = "dni,nombreYApellido"),
		@View(name = "SimpleCursado", members = "dni;nombreYApellido") })
@Tabs({ @Tab(properties = "dni,nombreYApellido,telefono,celular,mail,curso") })
public class Alumno extends Persona {

	@OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL)
	private List<Cursado> cursados;

	@OneToMany(mappedBy = "alumno")
	private List<Examen> examenesFinales;

	@Stereotype("ETIQUETA")
	public Long getDocumento() {
		return getDni();
	}
	
	public CursoHabilitado devolverCursoHabilitadoDelAnioActual() {
		int año = Util.obtenerAnio(new Date());
		for (Cursado cursado : cursados) {
			if (cursado.getCursoHabilitado().getAnio() == año) {
				return cursado.getCursoHabilitado();
			}
		}
		return null;
	}

	public String getCurso() {
		String retorno = "Sin Curso";
		if (devolverCursoHabilitadoDelAnioActual() != null) {
			retorno = devolverCursoHabilitadoDelAnioActual().getCurso().devolverInformacion();
		}
		return retorno;
	}

	public List<MateriaPlanilla> devolverPrevias() {
		ArrayList<MateriaPlanilla> previas = new ArrayList<MateriaPlanilla>();
		for (Cursado cursado : cursados) {
			previas.addAll(cursado.getPlanilla().devolverPrevias());
		}

		for (Examen examen : examenesFinales) {
			if (examen.getAprobo()) {
				// if(previas.contains(examen.getMateriaPlanilla())){
				previas.remove(examen.getMateriaPlanilla());
				// }
			}
		}
		return previas;
	}

	public String codigosDePrevias() {
		String retorno = "'ae'";
		for (MateriaPlanilla materiaPlanilla : devolverPrevias()) {
			retorno = retorno + "'" + materiaPlanilla.getId() + "',";
		}
		if (!retorno.equals("'ae'")) {
			retorno = retorno.substring(4, retorno.length() - 1);
		}
		return retorno;
	}
}
