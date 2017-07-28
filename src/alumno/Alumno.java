package alumno;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import cursado.*;
import curso.*;
import examen.*;
import materia.*;
import planilla.*;
import util.*;

@Entity
@Views({ @View(members = "dni;nombreYApellido;direccion;telefono;celular;mail"),
		@View(name = "Simple", members = "documento;nombreYApellido"),
		@View(name = "Simple2", members = "dni,nombreYApellido"),
		@View(name = "SimpleCursado", members = "dni;nombreYApellido") })
@Tabs({ @Tab(properties = "dni,nombreYApellido,telefono,celular,mail,curso") })
public class Alumno extends Persona {

	@OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL)
	private List<Cursado> cursados;

	@OneToMany(mappedBy = "alumno")
	private List<Examen> examenesFinales;

	@Stereotype("ETIQUETA")
	public Long getDocumento() {
		return getDni();
	}

	public CursoHabilitado devolverCursoHabilitadoDelAnioActual() {
		int año = 2018/* Util.obtenerAnio(new Date()) */;
		System.out.println("Anio " + año);
		System.out.println("Cursado size " + cursados.size());
		for (Cursado cursado : cursados) {
			System.out.println("Alumno " + cursado.getAlumno().getNombreYApellido());
			System.out.println("Anio cursoHabilitado " + cursado.getCursoHabilitado().getAnio());
			if (cursado.getCursoHabilitado().getAnio() == año) {
				return cursado.getCursoHabilitado();
			}
		}
		return null;
	}

	public boolean sePuedeInscribir(Curso curso) {
		if (devolverPrevias().size() <= 3) {
			if (curso.getAnio() > 2) {
				if (estuvoElAnioAnteriorEnUnCursoDeLaMismaOrientacion(curso)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean estuvoElAnioAnteriorEnUnCursoDeLaMismaOrientacion(Curso curso) {
		int anio = Util.obtenerAnio(new Date()) - 1;
		for (Cursado cursado : cursados) {
			if (cursado.getCursoHabilitado().getAnio() == anio) {
				if (cursado.getCursoHabilitado().getCurso().getOrientacion().getId() == curso.getOrientacion()
						.getId()) {
					return true;
				} 
			}
		}
		return false;
	}

	public boolean estaCursandoPrimero() {
		int anio = Util.obtenerAnio(new Date()) - 1;
		for (Cursado cursado : cursados) {
			if (cursado.getCursoHabilitado().getAnio() == anio) {
				if (cursado.getCursoHabilitado().getCurso().getAnio() == 1) {
					return true;
				}
			}
		}
		return false;
	}

	public String getCurso() {
		String retorno = "Sin Curso";
		if (devolverCursoHabilitadoDelAnioActual() != null) {
			retorno = devolverCursoHabilitadoDelAnioActual().getCurso().devolverInformacion();
		}
		return retorno;
	}

	public List<MateriaPlanilla> devolverPrevias() {
		ArrayList<MateriaPlanilla> previas = new ArrayList<MateriaPlanilla>();
		for (Cursado cursado : cursados) {
			previas.addAll(cursado.getPlanilla().devolverPrevias());
		}

		for (Examen examen : examenesFinales) {
			if (examen.getAprobo()) {
				previas.remove(examen.getMateriaPlanilla());
			}
		}
		return previas;
	}

	public String codigosDePrevias() {
		String retorno = "'ae'";
		for (MateriaPlanilla materiaPlanilla : devolverPrevias()) {
			retorno = retorno + "'" + materiaPlanilla.getId() + "',";
		}
		if (!retorno.equals("'ae'")) {
			retorno = retorno.substring(4, retorno.length() - 1);
		}
		return retorno;
	}
}
