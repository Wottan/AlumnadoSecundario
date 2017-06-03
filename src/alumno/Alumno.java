package alumno;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import cursado.*;
import util.*;

@Entity
@Views({ @View(members = "dni;nombreYApellido;direccion;telefono;celular;mail"),
		@View(name = "Simple", members = "documento;nombreYApellido"),
		@View(name = "SimpleCursado", members = "dni;nombreYApellido") })
@Tabs({ @Tab(properties = "dni,nombreYApellido,telefono,celular,mail,curso") })
public class Alumno extends Persona {

	@OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL)
	private List<Cursado> cursados;

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
}
