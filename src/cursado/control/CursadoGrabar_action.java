package cursado.control;

import java.util.*;

import org.openxava.actions.*;
import org.openxava.jpa.*;
import org.openxava.model.*;
import org.openxava.view.*;

import alumno.*;
import cursado.*;
import materia.*;
import planilla.*;

public class CursadoGrabar_action extends SaveElementInCollectionAction {

	@Override
	public void execute() throws Exception {
		System.out.println("Ingreso");
		Alumno alumno = null;
		View view = (View) getContext().get(getRequest(), getView().getViewObject());
		System.out.println(view.getValue("alumno.dni"));

		Map alumnoMap = (Map) view.getValue("alumno");
		System.out.println("AlumnoMap " + alumnoMap);
		if (alumnoMap.get("dni") != null) {
			alumno = (Alumno) MapFacade.findEntity("Alumno", alumnoMap);
			System.out.println(alumno.getDni());
		}
		super.execute();
		System.out.println(getView().getValue("id"));
		Map cursoHabilitadoMap = new HashMap();
		cursoHabilitadoMap.put("id", getView().getValue("id"));
		CursoHabilitado cursoHabilitado = (CursoHabilitado) MapFacade.findEntity("CursoHabilitado", cursoHabilitadoMap);
		System.out.println(cursoHabilitado.getCurso().getDescripcion());
		System.out.println(cursoHabilitado.getCursados().size());
		Cursado cursado = (Cursado) view.getEntity();
		crearPlanilla(cursoHabilitado, alumno);
		System.out.println(devolverCursado(cursoHabilitado, alumno).getAlumno().getNombreYApellido());
		// Map cursadoMap=(Map) view.getValue("id");
		//
		// Cursado cursado=(Cursado) MapFacade.findEntity("Cursado",cursadoMap);
		// System.out.println(cursado.getAlumno().getNombreYApellido());

	}

	private void crearPlanilla(CursoHabilitado cursoHabilitado, Alumno alumno) {
		Cursado cursado = devolverCursado(cursoHabilitado, alumno);
		MateriaPlanilla materiaPlanilla;
		if (cursado != null) {
			Planilla planilla = new Planilla();
			planilla.setMateriaPlanilla(new ArrayList<MateriaPlanilla>());
			planilla.setCursado(cursado);
			XPersistence.getManager().persist(planilla);
			for (Materia materia : cursoHabilitado.getCurso().devolverMaterias()) {
				materiaPlanilla = new MateriaPlanilla();
				materiaPlanilla.setNota(new ArrayList<Nota>());
				materiaPlanilla.setMateria(materia);
				materiaPlanilla.setPlanilla(planilla);
				materiaPlanilla.setNota(new ArrayList<Nota>());
				XPersistence.getManager().persist(materiaPlanilla);
			}

			// XPersistence.getManager().persist(planilla);
		}
	}

	private Cursado devolverCursado(CursoHabilitado cursoHabilitado, Alumno alumno) {
		// System.out.println(cursoHabilitado.getCursados().size());
		for (Cursado cursado : cursoHabilitado.getCursados()) {
			// System.out.println(cursado.getAlumno().getDni());
			// System.out.println(alumno.getDni());
			if (cursado.getAlumno().getDni().equals(alumno.getDni())) {
				System.out.println(alumno.getDni());
				return cursado;
			}
		}
		return null;
	}
}
