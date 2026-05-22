
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Nappe
 */
public class gestorArchivos {

    PrintWriter outputStream;
    Scanner inputStream;
    String fileName;
    File f;
    String header;
    String path;

    public gestorArchivos(String header, String path, String fileName) {

        this.header = header;
        this.fileName = fileName;
        this.path = path;

    }

    public void crearArchivoCSV() {

        f = new File(path + fileName + ".csv");

        try {
            if (!f.exists()) {
                outputStream = new PrintWriter(new FileOutputStream(path + fileName + ".csv", true));
                outputStream.println(header);
                outputStream.close();
                System.out.println("Se creó el  archivo: " + f.getName());
            } else {
                System.out.println("Ya existe el archivo: " + f.getName());
            }
        } catch (Exception e) {
            try {
                crearDirectorio(path);
                crearArchivoCSV();
            } catch (Exception ex) {
                System.out.println("Excepción del tipo: " + ex.getClass().getSimpleName());
                e.printStackTrace();
            }

        }
    }

    public void crearArchivoTXT() {

        f = new File(path + fileName + ".txt");

        try {
            if (!f.exists()) {
                outputStream = new PrintWriter(new FileOutputStream(path + fileName + ".txt", true));
                outputStream.print("");
                outputStream.close();
                System.out.println("Se creó el  archivo: " + f.getName());
            } else {
                System.out.println("Ya existe el archivo: " + f.getName());
            }
        } catch (Exception e) {
            try {
                crearDirectorio(path);
                crearArchivoTXT();
            } catch (Exception ex) {
                System.out.println("Excepción del tipo: " + ex.getClass().getSimpleName());
                e.printStackTrace();
            }

        }
    }

    private void crearDirectorio(String carpeta) {
        File folder = new File(carpeta);

        if (folder.mkdir()) {
            System.out.println("Se creó la carpeta: " + folder.getName());
        } else {
            System.out.println("Ya existe la carpeta: " + folder.getName());

        }
    }

    public void mostrarArchivo() {
        String linea;

        try {
            inputStream = new Scanner(new File(fileName));
            linea = inputStream.nextLine();
            System.out.println(linea);
            while (inputStream.hasNextLine()) {
                linea = inputStream.nextLine();
                System.out.println(linea);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Excepción del tipo :" + e.getClass().getSimpleName());
        }
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
