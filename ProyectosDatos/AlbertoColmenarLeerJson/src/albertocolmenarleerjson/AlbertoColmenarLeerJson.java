package albertocolmenarleerjson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author DAM2A-03
 */
public class AlbertoColmenarLeerJson {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("prueba2.json"));
            JSONObject objeto = (JSONObject) obj;
            leerJson(objeto);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AlbertoColmenarLeerJson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AlbertoColmenarLeerJson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AlbertoColmenarLeerJson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void leerJson(JSONObject jsonObject) {
        Set<String> claves = new HashSet<String>();
        claves = jsonObject.keySet();
        for (String c : claves) {
            System.out.print("\n" + c + ":");
            if (jsonObject.get(c).getClass().getName().equals("org.json.simple.JSONArray")) {
                JSONArray personas = (JSONArray) jsonObject.get(c);
                for (int i = 0; i < personas.size(); i++) {
                    // comprobar si es array
                    if (personas.get(i).getClass().getName().equals("org.json.simple.JSONObject")) {
                        leerJson((JSONObject) personas.get(i));
                    } else {
                        System.out.print(personas.get(i) + ",");
                    }
                }
            } else if (jsonObject.get(c).getClass().getName().equals("org.json.simple.JSONObject")) {
                leerJson((JSONObject) jsonObject.get(c));
            } else {
                System.out.print(jsonObject.get(c));
            }
        }
    }
    
    public static void leerJsonConociendo() {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("prueba2.json"));
            JSONObject objeto = (JSONObject) obj;
            JSONArray personas = (JSONArray) objeto.get("datos");
            Iterator<JSONObject> it = personas.iterator();
            while (it.hasNext()) {
                JSONObject jsonObject = (JSONObject) it.next();
                String texto = (String) jsonObject.get("nombre");
                System.out.println(texto);
                texto = (String) jsonObject.get("apellidos");
                System.out.println(texto);
                JSONArray tags = (JSONArray) jsonObject.get("tel");
                Iterator<String> iterator = tags.iterator();
                while (iterator.hasNext()) {
                    System.out.println(iterator.next());
                }
                texto = (String) jsonObject.get("email");
                System.out.println(texto);
                long edad = (long) jsonObject.get("edad");
                System.out.println(edad);
                JSONObject dir = (JSONObject) jsonObject.get("direccion");
                System.out.println(dir);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AlbertoColmenarLeerJson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AlbertoColmenarLeerJson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AlbertoColmenarLeerJson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
