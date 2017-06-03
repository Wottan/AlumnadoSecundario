package asistencia.control;

import java.util.*;

import javax.persistence.*;

import org.openxava.actions.*;
import org.openxava.jpa.*;
import org.openxava.model.*;

import alumno.*;
import asistencia.*;

public class AsistenciaAlumnoGrabar_action extends ViewBaseAction {

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		Map cursoHabilitado = (Map) getView().getValue("cursoHabilitado");
		if (cursoHabilitado.get("id").equals("")) {
			addWarning("Agregue el curso habilitado", 1);
			return;
		}
		Date fecha = (Date) getView().getValue("fecha");
		Asistencia asistencia;
		Boolean presente;
		Alumno alumno;
		Long dni;
		Collection alumnos = (Collection) getView().getValue("asistencias");
		for (Object object : alumnos) {
			Map asistenciaMap = (Map) object;
			Map alumnoMap = (Map) asistenciaMap.get("alumno");
			Map dniAlu = new HashMap();
			dni = (Long) alumnoMap.get("documento");
			dniAlu.put("dni", dni);
			alumno = (Alumno) MapFacade.findEntity("Alumno", dniAlu);
			System.out.println(alumno.getNombreYApellido());
			presente = (Boolean) asistenciaMap.get("presente");
			System.out.println(presente);
			asistencia = devolverAsistenciaPorAlumno(alumno);
			if (asistencia != null) {
				asistencia.setPresente(presente);
			} else {
				XPersistence.getManager().persist(new Asistencia(fecha, presente, alumno));
			}
		}
		System.out.println(alumnos);
		System.out.println(getView().getValue("cursoHabilitado"));
		System.out.println("Ingreso");
		getView().setValue("cursoHabilitado", null);
		getView().setValue("cursoHabilitado.curso", null);
		getView().setValue("cursoHabilitado.curso.anio", null);
		getView().setValue("cursoHabilitado.curso.division", null);
		getView().setValue("asistencias", null);
		getView().refreshCollections();
		addMessage("Asistencia Generada Correctamente", 1);
	}

	private Asistencia devolverAsistenciaPorAlumno(Alumno alumno) {
		try {
			String sql = "Select a From Asistencia as a where a.fecha = ?1 and a.alumno= ?2";
			Query query = XPersistence.getManager().createQuery(sql).setParameter(1, new Date(), TemporalType.DATE)
					.setParameter(2, alumno);
			return (Asistencia) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

}
