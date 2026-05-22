
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Nappe
 */
public class guardarInformacionCSV {

    public guardarInformacionCSV(consumoMensual[] cm, String filename) {

        agregarRegistro(cm, filename);

    }

    public void agregarRegistro(consumoMensual[] cm, String filename) {
        PrintWriter outputStream;
        for (int i = 0; i < cm.length; i++) {
            try {
                verificarConsumo(cm[i]);
                try {
                    String renglon = cm[i].transformarRenglon();
                    outputStream = new PrintWriter(new FileOutputStream(filename, true));
                    outputStream.println(renglon);
                    outputStream.close();

                } catch (FileNotFoundException e) {
                    System.out.println("Excepcion tipo :" + e.getClass().getSimpleName());

                }
            } catch (consumoAnteriorMayorException e) {
            }

        }
    }

    public void verificarConsumo(consumoMensual cm) throws consumoAnteriorMayorException {
        if (cm.getLecturaActual() < cm.getLecturaAnterior()) {
            throw new consumoAnteriorMayorException("El consumo del mes actual del apartamento " + cm.getNumeroDepartamento() + " es inferior al consumo del mes anterior. (" + cm.getLecturaActual() + " / " + cm.getLecturaAnterior() + ")");
        }

    }

}
