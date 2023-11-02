package albertocolmenaroperacionesconnio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author DAM2A-03
 */
public class AlbertoColmenarOperacionesConNio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Path ruta1 = null;
        Path ruta2 = null;
        Scanner sc = new Scanner(System.in);
        File fichero = null;
        System.out.format("1. Copiar fichero.%n"
                + "2. Mover fichero.%n"
                + "3. Mostrar fichero.%n"
                + "4. Mostrar información del fichero.%n"
                + "¿Qué quieres hacer? ");
        int opcion = Integer.parseInt(sc.nextLine());
        switch (opcion) {
            case 1:
                System.out.print("Nombre del fichero. ");
                ruta1 = Paths.get(sc.nextLine());
                System.out.print("Ruta destino. " );
                ruta2 = Paths.get(sc.nextLine());
                copiarFichero(ruta1, ruta2);
            break;
            case 2:
                System.out.print("Nombre del fichero. ");
                ruta1 = Paths.get(sc.nextLine());
                System.out.print("Ruta destino. " );
                ruta2 = Paths.get(sc.nextLine());
                moverFichero(ruta1, ruta2);
            break;
            case 3:
                System.out.print("Nombre del fichero. ");
                ruta1 = Paths.get(sc.nextLine());
                mostrarFichero(ruta1);
            break;
            case 4:
                System.out.print("Nombre del fichero. ");
                ruta1 = Paths.get(sc.nextLine());
                mostrarInformacionFichero(ruta1);
            break;
        }
        
    }
    
    // Clase Paths: para crear las rutas y método get() dado la ruta como string.
    // Clase Files: método exists() para comprobar que existe el fichero y copy() para copiarlo en la otra ruta. 
    // dado 2 variables Path, una con el Path del fichero que quieres copiar 
    //y otra con el Path donde lo quieres copiar.
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
