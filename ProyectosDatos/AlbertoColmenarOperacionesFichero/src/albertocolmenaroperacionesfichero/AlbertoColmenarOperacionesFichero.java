/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package albertocolmenaroperacionesfichero;

import java.io.File;

/**
 *
 * @author DAM2A-03
 */
public class AlbertoColmenarOperacionesFichero {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File f = new File("../prueba.txt");
        mostrarInformacionFichero(f);
        //listarFicheros(f);
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
    
    // crear directorio dado un nombre, comprobar si existe, crear 2 files vacios y renombrar uno
    // mkdir(), createNewFile(), en un try catch
}
