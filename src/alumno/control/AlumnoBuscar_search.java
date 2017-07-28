package alumno.control;

import java.util.*;

import javax.persistence.*;

import org.openxava.actions.*;
import org.openxava.jpa.*;
import org.openxava.model.*;
import org.openxava.view.*;

import alumno.*;
import curso.*;

public class AlumnoBuscar_search extends ReferenceSearchAction {

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated met hod stub
		String cursoId = (String) getPreviousView().getValue("curso.id");
		if (cursoId.equals("")) {
			throw new Exception("Seleccione el curso");
		}
		Map cursoMap= new HashMap();
		cursoMap.put("id", cursoId);
		System.out.println("Curso " + getPreviousView().getValue("curso"));
		System.out.println("Id curso " + cursoId);
		Curso curso=(Curso) MapFacade.findEntity("Curso", cursoMap);
		System.out.println("Curso Objeto " +curso.getOrientacion().getDescripcion());
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
			System.out.println("CursoHabilitadoDelAnioActual " + alumno.devolverCursoHabilitadoDelAnioActual());
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
