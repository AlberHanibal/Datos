package albertocolmenarpruebagson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DAM2A-03
 */
public class AlbertoColmenarPruebaGson {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            Gson gson = new Gson();
            Alumno alumno = new Alumno("Alberto", 987);
            String datosjson = gson.toJson(alumno);
            //System.out.println(datosjson);
            
            Alumno nuevoAlumno = gson.fromJson(new FileReader("prueba1.json"), Alumno.class);
            //System.out.println(nuevoAlumno);
            
            String jsonAlumnos = mostrarAlumnosJson();
            PrintWriter ficheroJsonAlumnos = new PrintWriter(new FileWriter("listaAlumnos.json"));
            ficheroJsonAlumnos.print(jsonAlumnos);
            ficheroJsonAlumnos.close();
            File ficheroJson = new File("listaAlumnos.json");
            mostrarFicheroJson(ficheroJson);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AlbertoColmenarPruebaGson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AlbertoColmenarPruebaGson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String mostrarAlumnosJson() {
        Gson gson = new Gson();
        ArrayList<Alumno> listaAlumnos = new ArrayList();
        listaAlumnos.add(new Alumno("Miriam", 23));
        listaAlumnos.add(new Alumno("Luis", 12));
        listaAlumnos.add(new Alumno("Lucía", 75));
        listaAlumnos.add(new Alumno("Julian", 51));
        return gson.toJson(listaAlumnos);
    }
    
    public static void mostrarFicheroJson(File ficheroJson) {
        try {
            Gson gson = new Gson();
            Type tipoListaCliente = new TypeToken<List<Alumno>>(){}.getType();
            List<Alumno> listaAlumno = gson.fromJson(new FileReader(ficheroJson), tipoListaCliente);
            for (Alumno alumno : listaAlumno) {
                System.out.println(alumno);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AlbertoColmenarPruebaGson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
