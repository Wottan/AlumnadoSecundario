package materia.control;

import java.util.*;

import org.openxava.actions.*;

public class MateriaBuscar_search extends ReferenceSearchAction {

	@Override
	public void execute() throws Exception {
		Collection materias = (Collection) getView().getValue("materias");
		super.execute();
		getTab().setBaseCondition("${id} not in (" + devolverCodigosDeMaterias(materias) + ")");

	}

	public String devolverCodigosDeMaterias(Collection materias) {
		String retorno = "'na'";
		String codigo;

		for (Object object : materias) {
			Map materiasMap = (Map) object;
			Map materiaMap = (Map) materiasMap.get("materia");
			System.out.println(materiaMap);
			codigo = (String) materiaMap.get("id");
			retorno = retorno + "'" + codigo + "',";
		}

		if (retorno != "'na'") {
			retorno = retorno.substring(4, retorno.length() - 1);
		}
		return retorno;
	}

}
