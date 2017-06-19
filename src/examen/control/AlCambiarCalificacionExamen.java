package examen.control;

import org.openxava.actions.*;

public class AlCambiarCalificacionExamen extends OnChangePropertyBaseAction {

	@Override
	public void execute() throws Exception {
		Double calificacion = (Double) getNewValue();
		if (calificacion != null) {
			if (calificacion >= 1 && calificacion <= 10) {
				getView().setValue("presente", true);
				if (calificacion >= 6) {
					getView().setValue("aprobo", true);
				}else{
					getView().setValue("aprobo", false);
				}
			} else {
				getView().setValue("calificacion", null);
				throw new Exception("Ingrese una calificacion correcta");
			}
		}else{
			getView().setValue("presente", false);
		}
	}

}
