package albertocolmenartrabajarxmlsax;

import java.io.IOException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author DAM2A-03
 */
public class AlbertoColmenarTrabajarXMLSAX {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            XMLReader lectorXML = XMLReaderFactory.createXMLReader();
            GestionContenido gestor = new GestionContenido();
            lectorXML.setContentHandler(gestor);
            InputSource ficheroXML = new InputSource("Desayunos.xml");
            lectorXML.parse(ficheroXML);
        } catch (SAXException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
