/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package albertocolmenaroperacionesfichero;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author DAM2A-03
 */
public class AlbertoColmenarOperacionesFichero {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File fichero = null;
        System.out.format("1. Mostrar directorio.%n"
                + "2. Mostrar información del fichero.%n"
                + "3. Crear directorio y 2 archivos.%n"
                + "4. Borrar directorio.%n"
                + "5. Borrar directorio recursivo.%n"
                + "¿Qué quieres hacer? ");
        int opcion = Integer.parseInt(sc.nextLine());
        switch (opcion) {
            case 1:
                System.out.println("Nombre del directorio.");
                fichero = new File(sc.nextLine());
                listarFicheros(fichero);
            break;
            case 2:
                System.out.println("Nombre del fichero.");
                fichero = new File(sc.nextLine());
                mostrarInformacionFichero(fichero);
            break;
            case 3:
                System.out.println("Ruta del directorio.");
                crearDirectorio(sc.nextLine());
            break;
            case 4:
                System.out.println("Nombre del directorio.");
                fichero = new File(sc.nextLine());
                borrarDirectorio(fichero);
            break;
            case 5:
                System.out.println("Nombre del directorio.");
                fichero = new File(sc.nextLine());
                borrarDirectorioRecursivo(fichero);
            break;
        }
        //mostrarInformacionFichero(f);
        //listarFicheros(f);
        //borrarDirectorio(f);
        //borrarDirectorioRecursivo(f);
    }
    
    public static void listarFicheros(File f) {
        if (f.exists() && f.isDirectory()) {
            String[] ficheros = f.list();
            for(int x=0; x<ficheros.length; x++) {
                System.out.format("%d: %s%n", x + 1, ficheros[x]);
            }
        } else {
            System.out.println("No es un directorio o no existe.");
        }
    }
    
    public static void mostrarInformacionFichero(File f) {
        if (f.exists()) {
            String nombre = f.getName();
            String rutaRelativa = f.getPath();
            String rutaAbsoluta = f.getAbsolutePath();
            boolean permisoLectura = f.canRead();
            boolean permisoEscritura = f.canWrite();
            float tamanno = f.length();
            boolean esDirectorio = f.isDirectory();
            boolean esFichero = f.isFile();
            System.out.format("Información del fichero %s%n"
                    + "ruta relativa: %s%n"
                    + "ruta absoluta: %s%n"
                    + "¿tiene permiso de lectura? %b%n"
                    + "¿tiene permiso de escritura? %b%n"
                    + "tamaño: %.0fbytes%n"
                    + "¿es un directorio? %b%n"
                    + "¿es un fichero? %b%n"
                    , nombre, rutaRelativa, rutaAbsoluta, permisoLectura, permisoEscritura, tamanno, esDirectorio, esFichero);
        } else {
            System.out.println("El fichero no existe");
        }
    }
    
    public static void crearDirectorio(String nombreDir) {
        try {
        File dir = new File(nombreDir);
        if (!dir.exists()){
            dir.mkdir();
            File fichero1 = new File(dir, "fichero1.txt");
            fichero1.createNewFile();
            File fichero2 = new File(dir, "fichero2.txt");
            fichero2.createNewFile();
            File fichero3 = new File(dir, "ficheroRenombrado.txt");
            fichero1.renameTo(fichero3);
        }    
        } catch (IOException ex) {
            System.out.println("Error al crear el fichero/directorio");
        }
    }
    
    public static void borrarDirectorio(File dir) {
        if (dir.exists()) {
            File[] ficheros = dir.listFiles();
            for (int x = 0; x < ficheros.length; x++) {
                ficheros[x].delete();
            }
            dir.delete();
        }
        System.out.format("El directorio %s ha sido borrado%n", dir.getName());
    }
    
    public static void borrarDirectorioRecursivo(File dir) {
        if (dir.isDirectory()) {
            File[] ficheros = dir.listFiles();
            for (int x = 0; x < ficheros.length; x++) {
                if (ficheros[x].isDirectory()) {
                    borrarDirectorioRecursivo(ficheros[x]);
                }
                ficheros[x].delete();
            }
            dir.delete();
        }
        System.out.format("El directorio %s ha sido borrado%n", dir.getName());
    }
}
