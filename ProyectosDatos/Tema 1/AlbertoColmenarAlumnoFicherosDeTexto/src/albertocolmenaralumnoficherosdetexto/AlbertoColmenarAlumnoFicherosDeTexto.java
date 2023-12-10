package albertocolmenaralumnoficherosdetexto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author DAM2A-03
 */
public class AlbertoColmenarAlumnoFicherosDeTexto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        File fichero = null;
        System.out.format("1. Leer fichero.%n"
                + "2. Crear fichero provincias.%n"
                + "3. Buscar provincias en el fichero provincias.txt.%n"
                + "4. Crear alfabeto.%n"
                + "¿Qué quieres hacer? ");
        int opcion = Integer.parseInt(sc.nextLine());
        switch (opcion) {
            case 1:
                System.out.println("Nombre del fichero.");
                fichero = new File(sc.nextLine());
                leerFichero(fichero);
            break;
            case 2:
                escribirProvincias();
                System.out.println("Fichero provincias.txt creado");
            break;
            case 3:
                System.out.print("Nombre de la provincia. ");
                fichero = new File("provincias.txt");
                encontrarProvincia(fichero, sc.nextLine());
            break;
            case 4:
                escribirAlfabeto();
                System.out.println("Fichero alfabeto.txt creado");
            break;
        }       
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
    
    // Crea el fichero provincias.txt con la lista de provincias del propio método.
    public static void escribirProvincias() {
        PrintWriter texto = null;
        try {
            String prov[]={"Albacete", "Avila", "Alicante", "Badajoz", "Barcelona", 
                "Bilbao","Caceres", "Cádiz", "Corboba", "Huelva", "Sevilla", "Soria", 
                "Toledo", "Valencia", "Zamora", "Zaragoza"};
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
    // Búsqueda en el fichero de la provincia pasada por parámetro
    public static void encontrarProvincia(File fichero, String provincia) {
        try {
            Scanner sc = new Scanner(fichero);
        boolean encontrado = false;
        while (sc.hasNext() && !encontrado) {
            encontrado = sc.next().equals(provincia);
        }
        System.out.format("¿Se ha encontrado la provincia %s? %b%n", provincia, encontrado);
        } catch (IOException ex) {
            System.out.println("Error con el fichero" + ex);
        }
    }
    
    public static void escribirAlfabeto() {
        PrintWriter texto = null;
        try {
            File ficheroAlfabeto = new File("alfabeto.txt");
            texto = new PrintWriter(ficheroAlfabeto);
            for (char c = 'A'; c <= 'Z'; ++c) {
                texto.println(c + ", " + Character.toLowerCase(c));
            }
        } catch(IOException ex) {
            System.out.println("Error con el fichero" + ex);
        } finally {
            texto.close();
        }
    }
}
