package albertocolmenartrabajarxmldom;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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
                    System.out.print("Dame el nombre del escritor: ");
                    String nombreEscritor = sc.nextLine();
                    System.out.print("Dame la fecha de nacimiento del escritor: ");
                    String nacimiento = sc.nextLine();
                    annadirEscritor(documento, nombreEscritor, nacimiento);
                    break;
                case 4:
                    System.out.println("Dame el nombre o el nacimiento del escritor");
                    String etiqueta = sc.nextLine();
                    borrarEscritor(documento, etiqueta);
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
    
    public static void annadirEscritor(Document doc, String nombre, String nacimiento) {
        try {
            Element raiz = doc.getDocumentElement();
            Element escritor = doc.createElement("escritor");
            Element nombreEscritor = doc.createElement("nombre");
            nombreEscritor.appendChild(doc.createTextNode(nombre));
            Element nacEscritor = doc.createElement("nacimiento");
            nacEscritor.appendChild(doc.createTextNode(nacimiento));
            escritor.appendChild(nombreEscritor);
            escritor.appendChild(nacEscritor);
            raiz.appendChild(escritor);
            
            Source source = new DOMSource(doc);
            Result result = new StreamResult(new File("Escritores.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        } catch (TransformerException ex) {
            Logger.getLogger(AlbertoColmenarTrabajarXMLDOM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void borrarEscritor(Document doc, String etiqueta) {
        try {
            Element raiz = doc.getDocumentElement();
            NodeList escritores = raiz.getElementsByTagName("escritor");
            for (int i = 0; i < escritores.getLength(); i++) {
                if (escritores.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    NodeList hijosEscritor = escritores.item(i).getChildNodes();
                    for (int j = 0; j < hijosEscritor.getLength(); j++) {
                        if (hijosEscritor.item(j).getTextContent().equals(etiqueta)) {
                            raiz.removeChild(escritores.item(i));
                            break;
                        }
                    }
                }
            }
            Source source = new DOMSource(doc);
            Result result = new StreamResult(new File("Escritores.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(AlbertoColmenarTrabajarXMLDOM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(AlbertoColmenarTrabajarXMLDOM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
