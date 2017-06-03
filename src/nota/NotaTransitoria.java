package nota;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import cursado.*;
import materia.*;
import planilla.*;

public class NotaTransitoria {

	@ManyToOne
	private CursoHabilitado cursoHabilitado;

	@ManyToOne
	private Materia materia;

	@ManyToOne
	@DescriptionsList
	private Periodo periodo;

	@ElementCollection
	@ListProperties("alumno,nota")
	private List<AlumnoNotaTransitoria> notas;

	public CursoHabilitado getCursoHabilitado() {
		return cursoHabilitado;
	}

	public void setCursoHabilitado(CursoHabilitado cursoHabilitado) {
		this.cursoHabilitado = cursoHabilitado;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public List<AlumnoNotaTransitoria> getNotas() {
		return notas;
	}

	public void setNotas(List<AlumnoNotaTransitoria> notas) {
		this.notas = notas;
	}

}
