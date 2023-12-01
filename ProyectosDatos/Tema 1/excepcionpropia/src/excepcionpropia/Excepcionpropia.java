package excepcionpropia;

import java.util.Scanner;

/**
 *
 * @author DAM2A-03
 */
class ExcepionIntervalo extends Exception {
    
    public ExcepionIntervalo(String msg) {
            super(msg);
    }
}
public class Excepcionpropia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        int numerador, denominador, cociente;
        try {
            System.out.println("Introduce un valor para el numerador");
            numerador = Integer.parseInt(sc.nextLine());
            System.out.println("Introduce un valor para el denominador");
            denominador = Integer.parseInt(sc.nextLine());
            rango(numerador, denominador);
            cociente = numerador / denominador;
            System.out.println("El resultado de la división es " + cociente); 
        } catch (NumberFormatException ex) {
            System.out.println("Se han introducido caracteres no numéricos");
        } catch (ArithmeticException ex) {
            System.out.println("División entre cero");
        } catch (ExcepionIntervalo ex) {
            System.out.println(ex.getMessage());
        } 
    }
    
    static void rango(int num, int den) throws ExcepionIntervalo {
        if ((num > 100) || (den < 0)) {
            throw new ExcepionIntervalo("Números fuera de rango");
        }
    }
    
}
