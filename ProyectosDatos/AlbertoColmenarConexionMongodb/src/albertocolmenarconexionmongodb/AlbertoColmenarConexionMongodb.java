package albertocolmenarconexionmongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import java.util.Iterator;
import org.bson.Document;


/**
 *
 * @author DAM2A-03
 */
public class AlbertoColmenarConexionMongodb {

    private static MongoClient CrearConexion() {
        MongoClient mongo = new MongoClient("localhost", 27017);
        return mongo;
    }
    
    public static void printDatabase(MongoClient mongo) {
        MongoIterable<String> listaBD = mongo.listDatabaseNames();
        Iterator it = listaBD.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
    
    public static void datosColeccion(MongoClient mongo, String database) {
        MongoDatabase db = mongo.getDatabase(database);
        MongoCollection coleccion = (MongoCollection) db.getCollection("datospersonas");
        FindIterable cursor = coleccion.find();
        Iterator ite = cursor.iterator();
        int n = 1;
        while (ite.hasNext()) {
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
    
    public static void insertarDocumento(MongoClient mongo, String database) {
        MongoDatabase db = mongo.getDatabase(database);
        MongoCollection coleccion = (MongoCollection) db.getCollection("datospersonas");
        Document document = new Document();
        document.put("nombre", "Alberto");
        document.put("apellidos", "Colmenar");
        document.put("edad", 27);
        coleccion.insertOne(document);
    }
    
    public static void buscarDocumento(MongoClient mongo, String database, String nombreBuscado) {
        MongoDatabase db = mongo.getDatabase(database);
        MongoCollection coleccion = (MongoCollection) db.getCollection("datospersonas");
        Document busqueda = new Document();
        busqueda.put("nombre", nombreBuscado);
        FindIterable cursor = coleccion.find(busqueda);
        Iterator ite = cursor.iterator();
        while (ite.hasNext()) {
            Document doc = (Document) ite.next();
            System.out.println("Nombre " + doc.getString("nombre"));        
            System.out.println("Apellidos " + doc.getString("apellidos"));
        }
    }
    
    public static void borrarDocumento(MongoClient mongo, String database, String nombreBuscado) {
        MongoDatabase db = mongo.getDatabase(database);
        MongoCollection coleccion = (MongoCollection) db.getCollection("datospersonas");
        Document document = new Document();
        document.put("nombre", nombreBuscado);
        coleccion.deleteOne(document);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MongoClient mongo = CrearConexion();
        if (mongo != null) {
            printDatabase(mongo);
            insertarDocumento(mongo, "datospersonas");
            //borrarDocumento(mongo, "datospersonas", "Alberto");
            datosColeccion(mongo, "datospersonas");
            buscarDocumento(mongo, "datospersonas", "Alberto");
        }
    }
    
}
