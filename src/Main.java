
/**
 *
 * @author Nappe
 */
public class Main {

    public static void main(String[] args) {
        consumoMensual[] cm = new consumoMensual[10];

        cm[0] = new consumoMensual("A101", "Laura Martínez", "Marzo", 120, 138, 18.50f);
        cm[1] = new consumoMensual("A102", "Jorge Sánchez", "Marzo", 95, 111, 18.50f);
        cm[2] = new consumoMensual("B201", "Patricia Gómez", "Marzo", 210, 245, 18.50f);
        cm[3] = new consumoMensual("B202", "Ricardo Torres", "Marzo", 180, 190, 18.50f);
        cm[4] = new consumoMensual("C301", "Estela Estrella", "Marzo", 130, 150, 18.50f);
        cm[5] = new consumoMensual("C302", "Antonio Alegría", "Marzo", 122, 128, 18.50f);
        cm[6] = new consumoMensual("D401", "Teófila Taracena", "Marzo", 125, 140, 18.50f);
        cm[7] = new consumoMensual("D402", "Jimeno Jaramillo", "Marzo", 130, 145, 18.50f);
        cm[8] = new consumoMensual("E501", "Rosalaura Reynosa", "Marzo", 99, 98, 18.50f);
        cm[9] = new consumoMensual("E502", "Melquizedeb Mujica", "Marzo", 100, 108, 18.50f);
        String rutarelativa = "c:\\users\\nappe\\documents\\gestorAguaProyectoU6\\";
        String nombre = "consumos_agua";
        gestorArchivos ga = new gestorArchivos("DEPTO,RESPONSABLE,MES,LECTURA_ANTERIOR,LECTURA_ACTUAL,TARIFA_M3", rutarelativa, nombre);
        ga.crearArchivoCSV();
        ga.setFileName("reporte_consumos_general");
        ga.crearArchivoTXT();
        ga.setFileName("reporte_consumos_altos");
        ga.crearArchivoTXT();
        ga.setFileName("reporte_resumen_agua");
        ga.crearArchivoTXT();

        guardarInformacionCSV gi = new guardarInformacionCSV(cm, rutarelativa + nombre + ".csv");

        generarReportes.generarDatosGenerales(rutarelativa + nombre + ".csv", rutarelativa + "reporte_consumos_general.txt");

        generarReportes.generarConsumosAltos(rutarelativa + nombre + ".csv", rutarelativa + "reporte_consumos_altos.txt");
        
         generarReportes.generarResumenAgua(rutarelativa + nombre + ".csv", rutarelativa + "reporte_resumen_agua.txt");

    }
}
