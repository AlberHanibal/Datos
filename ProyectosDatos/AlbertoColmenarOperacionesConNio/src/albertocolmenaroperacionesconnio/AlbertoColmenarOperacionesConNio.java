package albertocolmenaroperacionesconnio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author DAM2A-03
 */
public class AlbertoColmenarOperacionesConNio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File ruta1 = new File("../prueba.txt");
        File ruta2 = new File("../pruebaACopiar.txt");
        copiarFichero(ruta1.toPath(), ruta2.toPath());
        
    }
    public static void copiarFichero(Path ficheroACopiar, Path ficheroCopia) {
        try {
            if (Files.exists(ficheroACopiar)) {
                Files.copy(ficheroACopiar, ficheroCopia);
            } else {
                System.out.format("El fichero %s no existe", ficheroACopiar.getFileName());
            }
        } catch(IOException ex) {
            System.out.println("Error al copiar los ficheros" + ex);
        }
    }
}
