package albertocolmenaralumnoficherosdetexto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author DAM2A-03
 */
public class AlbertoColmenarAlumnoFicherosDeTexto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File fichero = new File("../prueba.txt");
        //leerFichero(fichero);
        escribirProvincias();
    }
    
    public static void leerFichero(File fichero) {
        try {
            if (fichero.exists()) {
                BufferedReader texto = new BufferedReader(new FileReader(fichero));
                String linea;
                while((linea = texto.readLine()) != null) {
                    System.out.println(linea);
                }
                texto.close();
            } else {
                System.out.format("El fichero %s no existe%n", fichero.getName());
            }
        } catch(IOException ex) {
            System.out.println("Error al leer el fichero" + ex);
        }
    }
    
    public static void escribirProvincias() {
        PrintWriter texto = null;
        try {
            String prov[]={"Albacete", "Avila", "Alicante", "Badajoz", "Barcelona", "Bilbao","Caceres", "Cádiz", "Corboba", "Huelva", "Sevilla", "Soria", "Toledo", "Valencia", "Zamora", "Zaragoza"};
            File ficheroProvincias = new File("provincias.txt");
            texto = new PrintWriter(ficheroProvincias);
            for(String provincia: prov) {
                texto.print(provincia + " ");
            }
        } catch(IOException ex) {
            System.out.println("Error con el fichero" + ex);
        } finally {
            texto.close();
        }
    }
    
    public static void encontrarProvincia() {
        
    }
}
