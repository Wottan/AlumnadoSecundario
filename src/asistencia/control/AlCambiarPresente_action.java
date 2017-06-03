package asistencia.control;

import org.openxava.actions.*;

public class AlCambiarPresente_action extends OnChangePropertyBaseAction {

	@Override
	public void execute() throws Exception {
		System.out.println(getView().getValue("fecha"));

	}

}
