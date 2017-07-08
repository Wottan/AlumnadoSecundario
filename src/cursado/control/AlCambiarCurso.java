package cursado.control;

import java.util.*;

import org.openxava.actions.*;
import org.openxava.jpa.*;
import org.openxava.model.*;

import com.gargoylesoftware.htmlunit.javascript.host.*;
import com.sun.xml.internal.bind.v2.runtime.Location;
import com.sun.xml.internal.ws.api.server.*;
import com.sun.xml.internal.ws.client.*;

import alumno.*;
import cursado.*;
import curso.*;
import materia.*;
import planilla.*;

public class AlCambiarCurso extends OnChangePropertyBaseAction implements IChainAction {

	private String nextAction = null;

	@Override
	public void execute() throws Exception {
//		if (getNewValue() != null) {
//			int anio = (Integer) getView().getValue("anio");
//			System.out.println("Año Al Cambiar Curso " + getView().getValue("anio"));
//			System.out.println(getView().getValue("curso"));
//			Map cursoMap = (Map) getView().getValue("curso");
//			Map idCurso = new HashMap();
//			idCurso.put("id", cursoMap.get("id"));
//			Curso curso = (Curso) MapFacade.findEntity("Curso", idCurso);
//			System.out.println("Curso " + curso.getOrientacion().getDescripcion());
//			CursoHabilitado cursoHabilitado = (CursoHabilitado) getView().getEntity();
//			List<Alumno> alumnos = new ArrayList<Alumno>();
//			List<Cursado> cursados = new ArrayList<Cursado>();
//			if (!curso.devolverCursadosDeAnioAnteriorHasta3Previas(anio).isEmpty()) {
//				System.out.println("Tiene 1 alumno o mas");
//				cursoHabilitado.setCursados(new ArrayList<Cursado>());
//				XPersistence.getManager().persist(cursoHabilitado);
//				for (Cursado cursado : curso.devolverCursadosDeAnioAnteriorHasta3Previas(anio)) {
//					Cursado nuevoCursado = new Cursado();
//					nuevoCursado.setAlumno(cursado.getAlumno());
//					nuevoCursado.setCursoHabilitado(cursoHabilitado);
//					System.out.println(cursado.getAlumno().getNombreYApellido());
//
//					XPersistence.getManager().persist((nuevoCursado));
//					cursados.add(nuevoCursado);
//					cursoHabilitado.getCursados().add(nuevoCursado);
//					crearPlanilla(cursoHabilitado, nuevoCursado);
//				}
//				cursoHabilitado.setCursados(cursados);
//				// for (MiDetalle detalle: detalle) {
//				// XPersistence.persiste(detalle); // Si los detalles son nuevos
//				// y
//				// se tienen que crear
//				// detalle.setPadre(entidad);
//				// }
//				getView().setModel(cursoHabilitado);
//				// getView().setValue("cursados",cursoHabilitado.getCursados());
//				// curso.devolverCursadosDeAnioAnteriorHasta3Previas(anio));
//				// getView().getSubview("cursados").refreshCollections();
//				// getView().findObject();
//				// getView().refresh();
////				nextAction = "CRUD.refresh";
//				
//			}
//		}
	}

	private void crearPlanilla(CursoHabilitado cursoHabilitado, Cursado nuevoCursado) {
		MateriaPlanilla materiaPlanilla;
		Planilla planilla = new Planilla();
		planilla.setMateriaPlanilla(new ArrayList<MateriaPlanilla>());
		planilla.setCursado(nuevoCursado);
		XPersistence.getManager().persist(planilla);
		for (Materia materia : cursoHabilitado.getCurso().devolverMaterias()) {
			materiaPlanilla = new MateriaPlanilla();
			materiaPlanilla.setMateria(materia);
			materiaPlanilla.setPlanilla(planilla);
			XPersistence.getManager().persist(materiaPlanilla);
		}
		nuevoCursado.setPlanilla(planilla);
	}

	@Override
	public String getNextAction() throws Exception {
		// TODO Auto-generated method stub
		return nextAction;
	}
}
