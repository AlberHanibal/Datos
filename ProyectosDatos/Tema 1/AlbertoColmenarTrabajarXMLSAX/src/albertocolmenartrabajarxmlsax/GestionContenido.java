package albertocolmenartrabajarxmlsax;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;

/**
 *
 * @author DAM2A-03
 */
public class GestionContenido extends DefaultHandler {

    public GestionContenido() {
        super();
    }

    @Override
    public void startDocument() {
        try {
            super.startDocument();
            System.out.println("Comienzo del documento");
        } catch (SAXException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void endDocument() {
        try {
            super.endDocument();
            System.out.println("Fin del documento");
        } catch (SAXException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void startElement(String ruta, String nombre, String nombreC, Attributes atrbts) {
        try {
            super.startElement(ruta, nombre, nombreC, atrbts);
            System.out.println("Inicio del elemento " + nombre);
        } catch (SAXException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void endElement(String ruta, String nombre, String nombreC) {
        try {
            super.endElement(ruta, nombre, nombreC);
            System.out.println("Fin del elemento " + nombre);
        } catch (SAXException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void characters(char[] chars, int i, int i1) {
        String car = new String(chars, i, i1);
        car = car.replaceAll("[\t\n]", "");
        System.out.println(car);
    }
}
