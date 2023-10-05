package albertocolmenargestionempleados;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 *
 * @author DAM2A-03
 */
public class AlbertoColmenarGestionEmpleados {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        File fichero = new File("empleados.dat");
        Scanner sc = new Scanner(System.in);
        if (!fichero.exists()) {
            crearFicheroEmpleados(fichero);
        } else {
            System.out.println("El fichero ya existe. ¿Desea sobreescribirlo? (s/n)");
            if (sc.nextLine().equals("s")) {
                crearFicheroEmpleados(fichero);
            }
        }
        
        System.out.format("1. Consulta de los datos de un empleado.%n"
                + "2. Alta de un empleado.%n");     
        int opcion = Integer.parseInt(sc.nextLine());
        switch (opcion) {
            case 1: {
                System.out.println("Número de empleado: ");
                int numEmpleado = Integer.parseInt(sc.nextLine());
                visualizarEmpleado(fichero, numEmpleado);
                break;
            }
            case 2: {
                System.out.print("Número de departamento: ");
                int numDepart = Integer.parseInt(sc.nextLine());
                System.out.print("Salario: ");
                double salario = Double.parseDouble(sc.nextLine());
                altaEmpleado(fichero, numDepart, salario);
                break;
            }
        }
    }
    
    public static void crearFicheroEmpleados(File fichero) {
        Scanner sc = new Scanner(System.in);
        RandomAccessFile raf = null;
        String respuesta = "s";
        int numEmpleado = 1;
        
        try {
            raf = new RandomAccessFile(fichero, "rw");
            do {
                raf.writeInt(numEmpleado);
                System.out.print("Número de departamento: ");
                raf.writeInt(Integer.parseInt(sc.nextLine()));
                System.out.print("Salario: ");
                raf.writeDouble(Double.parseDouble(sc.nextLine()));
                System.out.println("Se ha añadido el empleado nuevo. ¿Desea añadir otro? (s/n)");
                respuesta = sc.nextLine();
                numEmpleado++;
            } while (respuesta.equals("s"));
            raf.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontró el fichero " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir " + ex.getMessage());
        }
    }
    
    public static void altaEmpleado(File fichero, int numDepart, double salario) {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(fichero, "rw");
            int numEmpleado = (int) ((raf.length() / 16) + 1);
            raf.writeInt(numEmpleado);
            raf.writeInt(numDepart);
            raf.writeDouble(salario);
            raf.close();
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontró el fichero " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir " + ex.getMessage());
        }   
    }
    
    public static void visualizarEmpleado(File fichero, int numEmpleado) {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(fichero, "r");
            int posicion = (numEmpleado - 1) * 16;
            if (posicion < raf.length()) {
                raf.seek(posicion);
                int numEmp = raf.readInt();
                int numDepart = raf.readInt();
                double salario = raf.readDouble();
                System.out.format("El empleado %d está en el departamento %d y cobra %.0f€%n", numEmpleado, numDepart, salario);
            } else {
                System.out.println("El empleado no existe.");
            }
        } catch (EOFException ex) {
            System.out.println("Se ha completado la lectura del fichero " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontró el fichero " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al leer " + ex.getMessage());
        }
    }
    
}
