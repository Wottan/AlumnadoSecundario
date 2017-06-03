 package asistencia.control;

import java.util.*;

import javax.persistence.*;

import org.openxava.actions.*;
import org.openxava.jpa.*;
import org.openxava.model.*;

import alumno.*;
import asistencia.*;
import cursado.*;

public class AlCambiarCursoHabiltado extends OnChangePropertyBaseAction {

	@Override
	public void execute() throws Exception {
		String valor = (String) getNewValue(); // 2
		if (valor == null)
			return;
		System.out.println(valor);
		Boolean presente = null;
		Asistencia asistencia;
		Map clave = new HashMap();
		clave.put("id", valor);
		CursoHabilitado cursoHabilitado = (CursoHabilitado) MapFacade.findEntity("CursoHabilitado", clave);
		System.out.println(cursoHabilitado.getId());
		System.out.println(cursoHabilitado.getCursados().size());
		Collection cur = new ArrayList();
		for (Cursado cursado : cursoHabilitado.getCursados()) {
			System.out.println(cursado.getAlumno().getDocumento());
			Map cursados = new HashMap();
			Map alumnoMap = new HashMap();
			alumnoMap.put("documento", cursado.getAlumno().getDocumento());
			alumnoMap.put("nombreYApellido", cursado.getAlumno().getNombreYApellido());
			asistencia = devolverAsistenciaPorAlumno(cursado.getAlumno());
			if (asistencia != null) {
				presente = asistencia.getPresente();
				System.out.println(asistencia.toString());
			} else {
				System.out.println("No hay asistencia");
			}
			cursados.put("alumno", alumnoMap);
			cursados.put("estuvoPresente", presente);
			cur.add(cursados);

		}
		getView().setValue("asistencias", cur);
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
