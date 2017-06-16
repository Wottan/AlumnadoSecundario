package examen.control;

import java.util.*;

import org.openxava.actions.*;
import org.openxava.model.*;

import alumno.*;

public class ExamenBuscar_search extends ReferenceSearchAction {

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		Map alumno = (Map) getView().getValue("alumno");
		System.out.println(alumno.get("dni"));
		Long dni = (Long) alumno.get("dni");
		System.out.println(dni);
		Map alumnoMap = new HashMap();
		alumnoMap.put("dni", dni);
		if (dni == null) {
			throw new Exception("Agregue el alumno");
		}
		Alumno alumnoConPrevias = (Alumno) MapFacade.findEntity("Alumno", alumnoMap);
		super.execute();
		// System.out.println(codigosDeCursos());
		getTab().setPropertiesNames("materia.descripcion,planilla.cursado.cursoHabilitado.curso.anioOrientacion.anio");
		getTab().setBaseCondition("${id} in (" + alumnoConPrevias.codigosDePrevias() + ")");
	}
}
