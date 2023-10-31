package albertocolmenargestionpersonas;

import com.thoughtworks.xstream.XStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author DAM2A-03
 */
public class AlbertoColmenarGestionPersonas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File fichero = new File("datosPersonas.obj");
        Scanner sc = new Scanner(System.in);
        String opcion = "";
        if (!fichero.exists()) {
            crearFicheroPersonas(fichero);
        } else {
            System.out.print("El fichero ya existe, ¿desea sobreescribirlo? (s/n) ");
            opcion = sc.nextLine();
            if (opcion.equals("s")) {
                crearFicheroPersonas(fichero);
            }
        }
        ArrayList<Persona> lista = new ArrayList<>();
        volcadoALista(fichero, lista);
        listadoCompleto(lista);
        crearXML(fichero);
        crearHTML();
        int menu;
        do {
            System.out.format("1. Consultas.%n"
                    + "2. Mantenimiento.%n"
                    + "3. Salida.%n");
            menu = Integer.parseInt(sc.nextLine());
            int submenu;
            switch (menu) {
                case 1:
                    System.out.format("____________________________%n"
                            + "Consultas.%n"
                            + "     1. Mostrar los datos de una persona dado su DNI.%n"
                            + "     2. Mostrar los datos de la Persona con mayor edad en el fichero.%n"
                            + "     3. Listado de Personas comprendidas en un rango de edades.%n"
                            + "     4. Media de edad en el fichero.%n"
                            + "     5. Salir del submenú.%n");
                    submenu = Integer.parseInt(sc.nextLine());
                    switch (submenu) {
                        case 1:
                            System.out.print("Dame un dni: ");
                            String dni = sc.nextLine();
                            System.out.println(mostrarDatosDni(fichero, dni));
                            break;
                        case 2:
                            System.out.println(mostrarPersonaVieja(fichero));
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                    }
                    break;
                case 2:
                    System.out.format("____________________________%n"
                            + "Mantenimiento.%n"
                            + "     1. Alta de una persona.%n"
                            + "     2. Baja de una persona.%n"
                            + "     3. Salir del submenú.%n");
                    submenu = Integer.parseInt(sc.nextLine());
                    switch (submenu) {
                        case 1:

                            break;
                        case 2:
                            break;
                    }
                    break;
            }
        } while (menu != 3);

    }

    private static void crearFicheroPersonas(File fichero) {
        String dnis[] = {"83487622", "13481112", "22356622", "33455614", "77733182", "83765629", "22487611", "56787682", "80087600", "53077609"};
        String nombres[] = {"Ana", "Javier", "Luisa", "Tomás", "Julio", "Pedro", "Rocio", "German", "Maria", "Serafín"};
        int edades[] = {17, 22, 19, 15, 20, 26, 25, 19, 17, 20};
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(fichero));
            for (int i = 0; i < dnis.length; i++) {
                oos.writeObject(new Persona(dnis[i], nombres[i], edades[i]));
            }
            oos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el fichero" + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error en el fichero" + ex.getMessage());
        }
    }

    private static String mostrarDatosDni(File fichero, String dni) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(fichero));
            Persona persona = null;
            boolean encontrado = false;
            while (true && !encontrado) {
                persona = (Persona) ois.readObject();
                encontrado = persona.getDni().equals(dni);
            }
            if (encontrado) {
                return persona.toString();
                //falta sacarlo
            }
        } catch (EOFException ex) {
            System.out.println("Se ha leído el fichero" + ex.getMessage());
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el fichero" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error con la clase persona" + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error en el fichero" + ex.getMessage());
        }
        return "-1";
    }

    // sin terminar
    private static String mostrarPersonaVieja(File fichero) {
        ArrayList<Persona> lista = new ArrayList<>();
        volcadoALista(fichero, lista);
        Persona personaVieja = lista.get(0);
        for (Persona persona : lista) {
            if (persona.getEdad() > personaVieja.getEdad()) {
                personaVieja = persona;
            }
        }
        return personaVieja.toString();
    }
   
    
    public static void volcadoALista(File f, ArrayList<Persona> lista) {
        try {
            ObjectInputStream ois;
            Persona p;
            ois = new ObjectInputStream(new FileInputStream(f));
            while (true) {
                p = (Persona) ois.readObject();
                lista.add(p);
            }
        } catch (EOFException eof){        
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static void volcadoAFichero(File f, ArrayList<Persona> lista) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(f));
            for (Persona persona : lista) {
                oos.writeObject(persona);
            }
            oos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AlbertoColmenarGestionPersonas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AlbertoColmenarGestionPersonas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void crearXML(File fichero) {
        XStream xstream = new XStream();
        ArrayList<Persona> lista = new ArrayList<>();
        volcadoALista(fichero, lista);
        try {
            xstream.alias("Datos", Persona.class);
            xstream.alias("ListadoPersonas", List.class);
            xstream.toXML(lista, new FileOutputStream("Personas.xml"));
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static void crearHTML() {
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(new File("Personas.html"));
            Source estilos = new StreamSource("personas.xsl");
            Source datos = new StreamSource("personas.xml");
            Result result = new StreamResult(os);
            Transformer transformer = TransformerFactory.newInstance().newTransformer(estilos);
            transformer.transform(datos, result);
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (TransformerConfigurationException ex) {
            System.err.println(ex.getMessage());
        } catch (TransformerException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static void listadoCompleto(ArrayList<Persona> lista) {
        for (Persona persona : lista) {
                System.out.println(persona);
            }
    }
}

