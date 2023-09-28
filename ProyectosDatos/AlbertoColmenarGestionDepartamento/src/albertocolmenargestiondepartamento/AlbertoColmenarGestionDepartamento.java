package albertocolmenargestiondepartamento;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author DAM2A-03
 */
public class AlbertoColmenarGestionDepartamento {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        File fichero = new File("departamentos.dat");
        System.out.format("1. Escribir fichero departamentos.%n"
                + "2. Leer fichero departamentos.%n"
                + "3. Buscar departamento dado número de departamento.%n");
        int opcion = Integer.parseInt(sc.nextLine());
        switch(opcion) {
            case 1:
                boolean sobreescribir = false;
                String quiereSobreescribir;
                if (fichero.exists()) {
                    System.out.print ("Ya existe el fichero, ¿desea sobreescribirlo? (s/n) ");
                    quiereSobreescribir = sc.nextLine();
                    sobreescribir = quiereSobreescribir.equals("s");
                }
                if ((!fichero.exists() || sobreescribir)) {
                    crearDepartamentos(fichero);
                }
            break;
            
            case 2:
                if (fichero.exists()) {
                    leerFicheroDepartamento(fichero);
                } else {
                    System.out.println("No existe el fichero");
                }
            break;
            
            case 3:
                if (fichero.exists()) {
                    System.out.print("Número de departamento a buscar: ");
                    int numDepart = Integer.parseInt(sc.nextLine());
                    buscarDepartamento(fichero, numDepart);
                } else {
                    System.out.println("No existe el fichero");
                }
            break;
        }
    }
    
    public static void crearDepartamentos(File fichero) {
        try {
            Scanner sc = new Scanner(System.in);
            DataOutputStream salida = new DataOutputStream( new BufferedOutputStream( new FileOutputStream(fichero)));
            String nuevoDepartamento = "s";
            while (nuevoDepartamento.equals("s")) {
                System.out.print("Número de departamento: ");
                salida.writeInt(Integer.parseInt(sc.nextLine()));
                System.out.print("Nombre del departamento: ");
                salida.writeUTF(sc.nextLine());
                System.out.print("Localidad donde se encuentra el departamento: ");
                salida.writeUTF(sc.nextLine());
                System.out.print("Presupuesto del departamento: ");
                salida.writeDouble(Double.parseDouble(sc.nextLine()));
                System.out.print("¿Quiere escribir otro departamento? (s/n) ");
                nuevoDepartamento = sc.nextLine();
            }
            salida.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("No existe el fichero " + ex.getMessage());
        } catch(IOException ex) {
            System.out.println("Error con el fichero " + ex.getMessage());
        }        
    }
    
    public static void leerFicheroDepartamento(File fichero) {
        int numDepart;
        String nombreDepart, localidadDepart;
        double presupuestoDepart;
        
        try {
            DataInputStream lector = new DataInputStream( new BufferedInputStream( new FileInputStream(fichero)));
            while(true) {
                numDepart = lector.readInt();
                nombreDepart = lector.readUTF();
                localidadDepart = lector.readUTF();
                presupuestoDepart = lector.readDouble();
                System.out.format("Información: %d, %s, %s, %.0f%n", numDepart, nombreDepart, localidadDepart, presupuestoDepart);
            }
        } catch (EOFException ex) {
            
        } catch (FileNotFoundException ex) {
            System.out.println("No existe el fichero " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al leer el fichero" + ex.getMessage());
        }   
    }
    
    public static void buscarDepartamento(File fichero, int departamento) {
        int numDepart;
        String nombreDepart, localidadDepart;
        double presupuestoDepart;
        boolean encontrado = false;
        try {
            DataInputStream lector = new DataInputStream( new BufferedInputStream( new FileInputStream(fichero)));
            while(!encontrado) {
                numDepart = lector.readInt();
                nombreDepart = lector.readUTF();
                localidadDepart = lector.readUTF();
                presupuestoDepart = lector.readDouble();
                encontrado = departamento == numDepart;
                if (encontrado) {
                    System.out.format("Información: %d, %s, %s, %.0f%n", numDepart, nombreDepart, localidadDepart, presupuestoDepart);
                }
            }
        } catch (EOFException ex) {
            
        } catch (FileNotFoundException ex) {
            System.out.println("No existe el fichero " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al leer el fichero" + ex.getMessage());
        }
    }
    
}
