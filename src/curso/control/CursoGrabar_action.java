package curso.control;

import java.util.*;

import javax.ejb.*;
import javax.persistence.*;

import org.openxava.actions.*;
import org.openxava.jpa.*;
import org.openxava.model.*;
import org.openxava.util.*;

import curso.*;
import orientacion.*;

public class CursoGrabar_action extends SaveAction {

	@Override
	public void execute() throws Exception {
		Map orientacionMap = (Map) getView().getValue("orientacion");
		Map anioOrientacionMap = (Map) getView().getValue("anioOrientacion");
		Map divisionMap = (Map) getView().getValue("division");

		if (devolverCurso(orientacionMap, anioOrientacionMap, divisionMap) != null) {
			throw new Exception("El curso ya se encuentra creado");
		}
		super.execute();
	}

	public Curso devolverCurso(Map orientacionMap, Map anioOrientacionMap, Map divisionMap)
			throws ObjectNotFoundException, SystemException, FinderException {
		Orientacion orientacion = (Orientacion) MapFacade.findEntity("Orientacion", orientacionMap);
		AnioOrientacion anioOrientacion = (AnioOrientacion) MapFacade.findEntity("AnioOrientacion", anioOrientacionMap);
		Division division = (Division) MapFacade.findEntity("Division", divisionMap);
		String sql = "Select c From Curso as c where c.orientacion = ?1 and c.anioOrientacion= ?2 and c.division= ?3";
		Query query = XPersistence.getManager().createQuery(sql).setParameter(1, orientacion)
				.setParameter(2, anioOrientacion).setParameter(3, division);
		try {
			return (Curso) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

}
