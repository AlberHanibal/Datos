package albertocolmenarleerxml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.InputSource;
import org.w3c.dom.Document;

/**
 *
 * @author DAM2A-03
 */
public class AlbertoColmenarLeerXML {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(new InputSource("Agenda.xml"));
            AgendaScanner scanner = new AgendaScanner(document);
            scanner.visitDocument();
        } catch(Exception e) {
            System.out.println("Error de procesamiento: " + e.getMessage());
        }
    }
    
}
