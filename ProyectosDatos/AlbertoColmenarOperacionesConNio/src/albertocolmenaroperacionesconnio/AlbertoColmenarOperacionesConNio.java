package albertocolmenaroperacionesconnio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author DAM2A-03
 */
public class AlbertoColmenarOperacionesConNio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Path ruta1 = Paths.get("C:/Users/DAM2A-03/Documents/Datos/ProyectosDatos/prueba.txt");
        Path ruta2 = Paths.get("C:/Users/DAM2A-03/Documents/Datos/ProyectosDatos/qwe/prueba2.txt");
        //copiarFichero(ruta1, ruta2);
        //moverFichero(ruta1, ruta2);
        //mostrarFichero(ruta1);
        mostrarInformacionFichero(ruta1);
        
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
    
    public static void moverFichero(Path fichero, Path destino) {
        try {
            if (Files.exists(fichero)) {
                Files.move(fichero, destino);
            } else {
                System.out.format("El fichero %s no existe", fichero.getFileName());
            }
        } catch(IOException ex) {
            System.out.println("Error al mover el fichero" + ex);
        }
    }
    
    public static void mostrarFichero(Path fichero) {
        try {
            if (Files.exists(fichero)) {
                List<String> lineas = Files.readAllLines(fichero);
                for(String linea : lineas) {
                    System.out.println(linea);
                }
            } else {
                System.out.format("El fichero %s no existe", fichero.getFileName());
            }
        }
        catch(IOException ex) {
            System.out.println("Error en el fichero" + ex);
        }
    }
        
    public static void mostrarInformacionFichero(Path fichero) {
        try {
            if (Files.exists(fichero)) {
                String propietario = Files.getOwner(fichero).toString();
                boolean permisoLectura = Files.isReadable(fichero);
                boolean esEjecutable = Files.isExecutable(fichero);
                boolean esOculto = Files.isHidden(fichero);
                System.out.format("El propietario es %s%n"
                        + "¿Tiene permiso de lectura? %b%n"
                        + "¿Es ejecutable? %b%n"
                        + "¿Es oculto? %b%n", propietario, permisoLectura, esEjecutable, esOculto);
            } else {
                System.out.format("El fichero %s no existe", fichero.getFileName());
            }
        }
        catch(IOException ex) {
            System.out.println("Error en el fichero" + ex);
        }
    }
}
