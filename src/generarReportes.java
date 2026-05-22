
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

            outputStream = new PrintWriter(new FileOutputStream(fileName, true));

            outputStream.println("=====================");
            for (int i = 1; i < lineas.length; i++) {
                outputStream.println(lineas[i]);
            }

            outputStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("Excepción tipo :" + e.getClass().getSimpleName());

        }

    }

    public static ArrayList<String[]> leerTextoCSV(String filename) {
        String linea;
        File archivo = new File(filename);
        ArrayList<String[]> a = new ArrayList();

        try {
            FileReader leer = new FileReader(archivo);
            BufferedReader buffer = new BufferedReader(leer);
            linea = buffer.readLine();
            while (linea != null) {
                a.add(linea.split(","));

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
        ArrayList<String[]> datosCSV = leerTextoCSV(archivoOrigen);
        String lineas[] = new String[datosCSV.size()];
        for (int i = 1; i < datosCSV.size(); i++) {
            String renglon = "Departamento: " + datosCSV.get(i)[0]
                    + ", Mes: " + datosCSV.get(i)[2]
                    + ", Consumo: " + (Float.parseFloat(datosCSV.get(i)[4]) - Float.parseFloat(datosCSV.get(i)[3]))
                    + ", Importe a pagar: " + (Float.parseFloat(datosCSV.get(i)[4]) - Float.parseFloat(datosCSV.get(i)[3])) * Float.parseFloat(datosCSV.get(i)[5])
                    + "\n";

            lineas[i] = renglon;

        }

        construirReporte(lineas, archivoDestino);
    }

    public static void generarConsumosAltos(String archivoOrigen, String archivoDestino) {
        ArrayList<String[]> datosCSV = leerTextoCSV(archivoOrigen);
        String lineas[] = new String[2];
        lineas[1] = "";
        for (int i = 1; i < datosCSV.size(); i++) {
            String renglon;
            float consumom3 = (Float.parseFloat(datosCSV.get(i)[4]) - Float.parseFloat(datosCSV.get(i)[3]));

            if (consumom3 > 25) {
                renglon = "El apartamento " + (datosCSV.get(i)[0]) + " tiene un consumo alto de agua (" + consumom3 + " metros cúbicos)\n";
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

        ArrayList<String[]> datosCSV = leerTextoCSV(archivoOrigen);
        String lineas[] = new String[2];
        float totalAPagar = 0f;
        float promedio = 0f;
        float totalConsumo = 0f;
        float mayorConsumoComparar = 0f;
        String mayorConsumo = "";
        String renglon = "";
        for (int i = 1; i < datosCSV.size(); i++) {
            totalAPagar += (Float.parseFloat(datosCSV.get(i)[4]) - Float.parseFloat(datosCSV.get(i)[3])) * Float.parseFloat(datosCSV.get(i)[5]);
            totalConsumo += (Float.parseFloat(datosCSV.get(i)[4]) - Float.parseFloat(datosCSV.get(i)[3]));
            promedio = totalAPagar / datosCSV.size();

            if ((Float.parseFloat(datosCSV.get(i)[4]) - Float.parseFloat(datosCSV.get(i)[3])) > mayorConsumoComparar) {
                mayorConsumo = (datosCSV.get(i)[0]);
                mayorConsumoComparar = (Float.parseFloat(datosCSV.get(i)[4]) - Float.parseFloat(datosCSV.get(i)[3]));

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
