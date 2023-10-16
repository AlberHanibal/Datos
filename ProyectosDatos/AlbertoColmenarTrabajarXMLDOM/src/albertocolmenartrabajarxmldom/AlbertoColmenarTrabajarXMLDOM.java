package albertocolmenartrabajarxmldom;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author DAM2A-03
 */
public class AlbertoColmenarTrabajarXMLDOM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            File fichero = new File("Escritores.xml");
            Document documento = builder.parse(fichero);

            Scanner sc = new Scanner(System.in);
            System.out.format("1. Listado de todos los escritores.%n"
                    + "2. Datos de un escritor.%n"
                    + "3. Annadir un escritor.%n"
                    + "4. Borrar un escritor.%n");
            int opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    listadoCompleto(documento);
                    break;
                case 2:
                    System.out.print("Dame el nombre del escritor: ");
                    String nombre = sc.nextLine();
                    informacionEscritor(documento, nombre);
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }

        } catch (IOException | NumberFormatException | ParserConfigurationException | SAXException e) {
            System.err.println("Error de procesamiento: " + e.getMessage());
        }
    }

    public static void listadoCompleto(Document doc) {
        Element raiz = doc.getDocumentElement();
        NodeList escritores = raiz.getElementsByTagName("escritor");
        System.out.format("Hay %d escritores: %n", escritores.getLength());
        for (int i = 0; i < escritores.getLength(); i++) {
            System.out.println("------------------------");
            if (escritores.item(i).getNodeType() == Node.ELEMENT_NODE) {
                NodeList hijosEscritor = escritores.item(i).getChildNodes();
                for (int j = 0; j < hijosEscritor.getLength(); j++) {
                    if (hijosEscritor.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        System.out.format("%s: %s.%n", hijosEscritor.item(j).getNodeName(), hijosEscritor.item(j).getTextContent());
                    }
                }
            }
        }
    }

    public static void informacionEscritor(Document doc, String nombre) {
        Element raiz = doc.getDocumentElement();
        NodeList nombres = raiz.getElementsByTagName("nombre");
        Node escritor = null;
        boolean encontrado = false;
        for (int i = 0; i < nombres.getLength(); i++) {
            if (nombres.item(i).getTextContent().equals(nombre)) {
                escritor = nombres.item(i).getParentNode();
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            NodeList hijosEscritor = escritor.getChildNodes();
            for (int i = 0; i < hijosEscritor.getLength(); i++) {
                if (hijosEscritor.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    System.out.format("%s: %s.%n", hijosEscritor.item(i).getNodeName(), hijosEscritor.item(i).getTextContent());
                }
            }
        }

    }

}
