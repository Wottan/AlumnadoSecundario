package alumno.control;

import java.util.*;

import javax.persistence.*;

import org.openxava.actions.*;
import org.openxava.jpa.*;

import alumno.*;

public class AlumnoBuscar_search extends ReferenceSearchAction {

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub

		super.execute();
		System.out.println(codigosDeCursos());
		// getTab().setPropertiesNames("descripcion,division.descripcion,anio.anio");
		getTab().setBaseCondition("${id} not in (" + codigosDeCursos() + ")");
	}

	private List<Alumno> devolverAlumnos() {
		String sql = "Select a From Alumno as a";
		Query query = XPersistence.getManager().createQuery(sql);
		return query.getResultList();
	}

	private String codigosDeCursos() {
		String retorno = "99";
		for (Alumno alumno : devolverAlumnos()) {
			if (alumno.devolverCursoHabilitadoDelAnioActual() != null) {
				retorno = retorno + alumno.getDni() + ",";
			}
		}
		if (!retorno.equals("99")) {
			retorno = retorno.substring(2, retorno.length() - 1);
		}
		return retorno;
	}

}
