package restaurantealbertomongo;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import java.util.Iterator;
import java.util.Scanner;
import org.bson.Document;
/**
 *
 * @author DAM2A-03
 */
public class RestauranteAlbertoMongo {

    private static MongoClient CrearConexion() {
        MongoClient mongo = new MongoClient("localhost", 27017);
        return mongo;
    }
    
    public static void datosRestaurantes(MongoClient mongo, String database) {
        MongoDatabase db = mongo.getDatabase(database);
        MongoCollection coleccion = (MongoCollection) db.getCollection("restaurantes");
        FindIterable cursor = coleccion.find();
        Iterator ite = cursor.iterator();
        int n = 1;
        while (ite.hasNext()) {
            // cambiar esto
            Document doc = (Document) ite.next();
            System.out.println("\nDocumento " + n);
            System.out.println("Nombre " + doc.getString("nombre"));        
            System.out.println("Apellidos " + doc.getString("apellidos"));
            System.out.println("Edad " + doc.get("edad"));
            System.out.println("Aficiones " + doc.get("aficiones"));
            System.out.println("Estudios " + doc.getString("estudios"));
            System.out.println("Teléfono " + doc.getString("telefono"));
            n++;
        }
    }
    
    public static void insertarRestaurante(MongoClient mongo, String database) {
        MongoDatabase db = mongo.getDatabase(database);
        MongoCollection coleccion = (MongoCollection) db.getCollection("restaurantes");
        Document document = new Document();
        document.put("nombre", "Alberto");
        document.put("apellidos", "Colmenar");
        document.put("edad", 27);
        coleccion.insertOne(document);
    }
    
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        MongoClient mongo = CrearConexion();
        if (mongo != null) {
            System.out.format("1. Consulta de los datos de un empleado.%n"
                    + "2. Alta de un empleado.%n");     
            int opcion = Integer.parseInt(sc.nextLine());
            switch (opcion) {
                case 1: {
                    break;
                }
                case 2: {
                    break;
                }
            }
        }
    }
    
}
