package cursado.control;

import org.openxava.actions.*;

public class AnadirAlumnosCursado_action extends GoAddElementsToCollectionAction {

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		super.execute();
		getTab().setDefaultPropertiesNames("alumno,anio");
	}

	
}
