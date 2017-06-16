package planilla;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.model.*;

import materia.*;

@Entity
@Views({ @View(members = "materia;notas"),
		 @View(name="Examen", members="id;materia")	})
public class MateriaPlanilla extends Identifiable {

	@ManyToOne
	@NoFrame
	@NoCreate
	@NoSearch
	@NoModify
	@ReadOnly
	private Materia materia;

	@ManyToOne
	private Planilla planilla;

	@ElementCollection
	@ListProperties("periodo.id,periodo.descripcion,nota")
	private List<Nota> notas;

	
	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Planilla getPlanilla() {
		return planilla;
	}

	public void setPlanilla(Planilla planilla) {
		this.planilla = planilla;
	}

	public Double getPromedio(){
		Double retorno=0.0;
		int cantidadDeNotas=notas.size();
		for (Nota nota : notas) {
			if(nota.getNota()!=null){
				retorno=retorno+nota.getNota();
			}
		}
		if(cantidadDeNotas!=0){
			retorno=retorno/cantidadDeNotas;
		}
		return retorno;
	}
	
	
}
