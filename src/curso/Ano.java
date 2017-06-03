package curso;

import javax.persistence.Entity;

import org.openxava.annotations.*;
import org.openxava.model.*;

@Entity
public class Ano extends Identifiable {

	@Required
	private String anio;

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

}
