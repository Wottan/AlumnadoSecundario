package orientacion;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.model.*;

@Entity
public class Orientacion extends Identifiable {

	@Required
	private String descripcion;

	private int duracion;

	@OneToMany(mappedBy="orientacion" ,cascade=CascadeType.ALL)
	private List<AnioOrientacion> anios;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public List<AnioOrientacion> getAnios() {
		return anios;
	}

	public void setAnios(List<AnioOrientacion> anios) {
		this.anios = anios;
	}

}
