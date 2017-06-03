package asistencia.control;

import java.util.*;

import org.openxava.actions.*;

public class AlIniciar extends ViewBaseAction{

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		getView().setValue("fecha", new Date());
	}

}
