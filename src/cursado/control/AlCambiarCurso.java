package cursado.control;

import java.util.*;


import org.openxava.actions.*;
import org.openxava.model.*;

import cursado.*;
import curso.*;

public class AlCambiarCurso extends OnChangePropertyBaseAction {

	@Override
	public void execute() throws Exception {
		if (getNewValue() != null) {
			int anio = (Integer) getView().getValue("anio");
			System.out.println("Año Al Cambiar Curso " + getView().getValue("anio"));
			System.out.println(getView().getValue("curso"));
			Map cursoMap = (Map) getView().getValue("curso");
			Map idCurso = new HashMap();
			idCurso.put("id", cursoMap.get("id"));
			Curso curso = (Curso) MapFacade.findEntity("Curso", idCurso);
			System.out.println("Curso " + curso.getDescripcion());
			for (Cursado cursado : curso.devolverCursadosDeAnioAnteriorHasta3Previas(anio)) {
				System.out.println(cursado.getAlumno().getNombreYApellido());
			}
			getView().setValue("cursados", curso.devolverCursadosDeAnioAnteriorHasta3Previas(anio));
			getView().refreshCollections();
		}
	}
}
