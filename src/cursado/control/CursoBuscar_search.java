package cursado.control;

import java.util.*;

import javax.persistence.*;

import org.openxava.actions.*;
import org.openxava.jpa.*;

import curso.*;

public class CursoBuscar_search extends ReferenceSearchAction {

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		int anio = getView().getValueInt("anio");
		System.out.println("Año " + anio);
		super.execute();
		System.out.println(codigosDeCursos(anio));
		getTab().setPropertiesNames("orientacion.descripcion,division.descripcion,anioOrientacion.anio");
		// getTab().setBaseCondition("${id} not in (" + codigosDeCursos() +
		// ")");
		System.out.println(codigosDeCursos(anio));
		getTab().setBaseCondition("${id} not in (" + codigosDeCursos(anio) + ")");
	}

	private List<Curso> devolverCursos() {
		String sql = "Select c From Curso as c ";
		Query query = XPersistence.getManager().createQuery(sql);
		return query.getResultList();
	}

	// private String codigosDeCursos() {
	// String retorno = "'ae'";
	// for (Curso curso : devolverCursos()) {
	// if (curso.tieneCursoHabilitadoEnAnioActual()) {
	// retorno = retorno + "'" + curso.getId() + "',";
	// }
	// }
	// if (!retorno.equals("'ae'")) {
	// retorno = retorno.substring(4, retorno.length() - 1);
	// }
	// return retorno;
	// }

	private String codigosDeCursos(int anio) {
		String retorno = "'ae'";
		for (Curso curso : devolverCursos()) {
			if (curso.anioMayorEnCursoHabilitado(anio)) {
				retorno = retorno + "'" + curso.getId() + "',";
			}
		}
		if (!retorno.equals("'ae'")) {
			retorno = retorno.substring(4, retorno.length() - 1);
		}
		return retorno;
	}

}
