
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Nappe
 */
public class generarReportes {

    public static void construirReporte(String[] lineas, String fileName) {
        PrintWriter outputStream;

        try {

            outputStream = new PrintWriter(new FileOutputStream(fileName, false));

            outputStream.println("=====================");
            for (int i = 1; i < lineas.length; i++) {
                outputStream.println(lineas[i]);
            }

            outputStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("Excepción tipo :" + e.getClass().getSimpleName());

        }

    }

    public static String[] leerTextoCSV(String filename) {
        String linea;
        int tamanoArreglo = 0;
        File archivo = new File(filename);
        String[] a = null;

        try {
            FileReader leer = new FileReader(archivo);
            BufferedReader buffer = new BufferedReader(leer);
            linea = buffer.readLine();
            while (linea != null) {
                tamanoArreglo++;
            }

            a = new String[tamanoArreglo];

            for (int i = 0; i < tamanoArreglo; i++) {
                a[i] = linea = buffer.readLine();

                linea = buffer.readLine();
            }

            buffer.close();

        } catch (IOException e) {
            System.out.println("Excepcion Tipo :" + e.getClass().getSimpleName());
            e.printStackTrace();
        }

        return a;

    }

    public static void generarDatosGenerales(String archivoOrigen, String archivoDestino) {
        String[] datosCSV = leerTextoCSV(archivoOrigen);
        String lineas[] = null;
        for (int i = 1; i < datosCSV.length; i++) {
            lineas = datosCSV[i].split(",");
            String renglon = "Departamento: " + lineas[0]
                    + ", Mes: " + lineas[2]
                    + ", Consumo: " + (Float.parseFloat(lineas[4]) - Float.parseFloat(lineas[3]))
                    + ", Importe a pagar: " + (Float.parseFloat(lineas[4]) - Float.parseFloat(lineas[3])) * Float.parseFloat(lineas[5])
                    + "\n";

            lineas[i] = renglon;

        }

        construirReporte(lineas, archivoDestino);
    }

    public static void generarConsumosAltos(String archivoOrigen, String archivoDestino) {
        String[] datosCSV = leerTextoCSV(archivoOrigen);
        String lineas[] = new String[2];
        lineas[1] = "";
        for (int i = 1; i < datosCSV.length; i++) {
            String renglon;
            float consumom3 = (Float.parseFloat(lineas[4]) - Float.parseFloat(lineas[3]));

            if (consumom3 > 25) {
                renglon = "El apartamento " + (lineas[0]) + " tiene un consumo alto de agua (" + consumom3 + " metros cúbicos)\n";
                lineas[1] += renglon;
            }

        }

        if (lineas[1].length() < 5) {
            lineas[1] = "No hubo apartamentos con consumo alto este mes.";
        }

        lineas[0] = "";

        construirReporte(lineas, archivoDestino);

    }

    public static void generarResumenAgua(String archivoOrigen, String archivoDestino) {

        String[] datosCSV = leerTextoCSV(archivoOrigen);
        String lineas[] = new String[2];
        float totalAPagar = 0f;
        float promedio = 0f;
        float totalConsumo = 0f;
        float mayorConsumoComparar = 0f;
        String mayorConsumo = "";
        String renglon = "";
        for (int i = 1; i < datosCSV.length; i++) {
            totalAPagar += (Float.parseFloat(datosCSV[4]) - Float.parseFloat(datosCSV[3])) * Float.parseFloat(datosCSV[5]);
            totalConsumo += (Float.parseFloat(datosCSV[4]) - Float.parseFloat(datosCSV[3]));
            promedio = totalAPagar / datosCSV.length;

            if ((Float.parseFloat(datosCSV[4]) - Float.parseFloat(datosCSV[3])) > mayorConsumoComparar) {
                mayorConsumo = (datosCSV[0]);
                mayorConsumoComparar = (Float.parseFloat(datosCSV[4]) - Float.parseFloat(datosCSV[3]));

            }
        }

        renglon = "Total de consumo: " + totalConsumo + "\n"
                + "Total a pagar: " + totalAPagar + "\n"
                + "Promedio de pago por apartamento: " + promedio + "\n"
                + "Mayor consumo: " + mayorConsumoComparar + ", del apartamento " + mayorConsumo;

        lineas[1] = renglon;

        construirReporte(lineas, archivoDestino);

    }

}
