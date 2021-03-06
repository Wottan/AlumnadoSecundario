package planilla;

import javax.persistence.*;

import org.openxava.annotations.*;

@Embeddable
public class Nota {

	@Required
	private Double nota;

	@Required
	@ManyToOne
	@ReferenceView("Simple")
	private Periodo periodo;

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

}
