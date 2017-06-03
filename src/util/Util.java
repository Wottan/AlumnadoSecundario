package util;

import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;


public class Util {

	public static String formatoFechaHora(Date fecha) {
		SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
		return (fecha == null) ? "" : formateador.format(fecha);
	}

	public static String formatoFecha(Date fecha) {
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		return formateador.format(fecha);
	}

	public static Date StringADateHora(String fechaString) {
		SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
		Date fecha = null;
		try {
			fecha = formateador.parse(fechaString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fecha;
	}

	public static Date StringADate(String fechaString) {
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = null;
		try {
			fecha = formateador.parse(fechaString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fecha;
	}

	public static int diferenciaEntreFechas(Date fechaMayor, Date fechaMenor) {
		long diferenciaEn_ms = fechaMayor.getTime() - fechaMenor.getTime();
		long dias = diferenciaEn_ms / (1000 * 60 * 60 * 24);
		return (int) dias;
	}

	public static int devolverNumeroDeDiaDeLaSemana(Date fecha) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(fecha);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	public static List<Date> devolverFechasEnRango(Date fechaDesde, Date fechaHasta, String excepcion)
			throws Exception {
		if (fechaDesde == null || fechaHasta == null) {
			throw new Exception(excepcion);
		}
		ArrayList<Date> fechas = new ArrayList<Date>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaDesde); // setea el calendario con la fecha
		// actual.
		while (true) {
			fechas.add(calendar.getTime());
			// show(calendar); // muestra la fecha actual.
			if (calendar.getTime().equals(fechaHasta))
				break; // si las fechas son
			// iguales,
			// termina el bucle.
			calendar.add(Calendar.DATE, 1);
		}
		return fechas;
	}

	public static Date sumarRestarMinutosFecha(Date fecha, int minutos) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(fecha); // Configuramos la fecha que se recibe

		calendar.add(Calendar.MINUTE, minutos); // numero de días a añadir, o
												// restar en caso de días<0

		return calendar.getTime(); // Devuelve el objeto Date con los nuevos
									// días añadidos

	}

	public static int obtenerAnio(Date date) {
		if (null == date) {
			return 0;
		} else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
			return Integer.parseInt(dateFormat.format(date));
		}
	}

	public static String compararFechas(Date fechaMenor, Date fechaMayor) {
		// System.out.println("Parametro String Fecha 1 = " + fecha1 + "\n"
		// + "Parametro String fechaActual = " + fechaActual + "\n");
		String resultado = "";
		if (fechaMenor.before(fechaMayor)) {
			resultado = "Menor";
		} else if (fechaMayor.before(fechaMenor)) {
			resultado = "Mayor";
		} else {
			resultado = "Igual";
		}
		return resultado;
	}

	public static List eliminarRepetidos(List lista) {
		HashSet hashSet = new HashSet(lista);
		lista.clear();
		lista.addAll(hashSet);
		return lista;
	}
	
	public static boolean compararFechasPorFechaYHora(Date fechaA, Date fechaB,Time horaA,Time horaB){
		Calendar calendarioHoraNueva = Calendar.getInstance();
		calendarioHoraNueva.setTime(horaA);
		Calendar calendarioHoraPersistida = Calendar.getInstance();
		calendarioHoraPersistida.setTime(horaB);
		int horaNueva=calendarioHoraNueva.get(Calendar.HOUR);
		int minutoNueva=calendarioHoraNueva.get(Calendar.MINUTE);
		int horaPersistida=calendarioHoraPersistida.get(Calendar.HOUR);
		int minutoPersistida=calendarioHoraPersistida.get(Calendar.MINUTE);
		if(Util.formatoFecha(fechaA).equals(Util.formatoFecha(fechaB))){
			System.out.println("Fechas Iguales");
			if(horaNueva==horaPersistida){
				System.out.println("Horas Iguales");
				if(minutoNueva==minutoPersistida){
					System.out.println("Minutos Iguales");
					return true;
				}
			}
		}
		return false;
		
	}
	
	public static boolean compararHoras(Time horaA,Time horaB){
		Calendar calendarioHoraNueva = Calendar.getInstance();
		calendarioHoraNueva.setTime(horaA);
		Calendar calendarioHoraPersistida = Calendar.getInstance();
		calendarioHoraPersistida.setTime(horaB);
		int horaNueva=calendarioHoraNueva.get(Calendar.HOUR);
		int minutoNueva=calendarioHoraNueva.get(Calendar.MINUTE);
		int horaPersistida=calendarioHoraPersistida.get(Calendar.HOUR);
		int minutoPersistida=calendarioHoraPersistida.get(Calendar.MINUTE);
			System.out.println("Fechas Iguales");
			if(horaNueva==horaPersistida){
				System.out.println("Horas Iguales");
				if(minutoNueva==minutoPersistida){
					System.out.println("Minutos Iguales");
					return true;
				}
			}
		return false;
		
	}
}
