package planilla;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.model.*;

@Entity
@Views({ @View(members = "descripcion") })
public class Periodo extends Identifiable {
	//
	//
	// private Periodos periodo;
	//
	// public enum Periodos {
	// PrimerTrimestre, SegundoTrimestre, TercerTrimestre, EvaluacionDiciembre,
	// EvaluacionMarzo
	// };

	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
