package restaurantealbertomongo;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import java.util.Iterator;
import java.util.Scanner;
import org.bson.Document;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.UpdateResult;

/**
 *
 * @author DAM2A-03
 */
public class RestauranteAlbertoMongo {

    private static MongoClient mongo = null;
    
    private static MongoClient CrearConexion() {
        mongo = new MongoClient("localhost", 27017);
        return mongo;
    }
    
    public static void datosRestaurantes(String database) {
        MongoDatabase db = mongo.getDatabase(database);
        MongoCollection coleccion = (MongoCollection) db.getCollection("restaurantes");
        FindIterable cursor = coleccion.find();
        Iterator ite = cursor.iterator();
        while (ite.hasNext()) {
            Document doc = (Document) ite.next();
            System.out.println("Nombre: " + doc.getString("nombre"));        
            System.out.println("Propietario: " + doc.getString("propietario"));
            System.out.println("Dirección: " + doc.get("direccion"));
            System.out.println("Especialidad: " + doc.getString("especialidad"));
            System.out.println("Teléfonos: " + doc.get("telefonos"));
        }
    }
    
    public static void insertarRestaurante(String database) {
        MongoDatabase db = mongo.getDatabase(database);
        MongoCollection coleccion = (MongoCollection) db.getCollection("restaurantes");
        Document document = new Document();
        Scanner sc = new Scanner(System.in);
        String input = "";
        boolean nombreValido = false;
        do {
            System.out.println("Nombre del restaurante: ");
            input = sc.nextLine();
            nombreValido = buscarRestaurante("AlbertoColmenarBD", input);
            if (!nombreValido) {
                System.out.println("Tienes que introducir otro nombre de restaurante. Nombre duplicado."); 
            }
        } while (!nombreValido);
        document.put("nombre", input);
        System.out.println("Nombre del propietario: ");
        input = sc.nextLine();
        document.put("propietario", input);
        System.out.println("Especialidad del restaurante: ");
        input = sc.nextLine();
        document.put("especialidad", input);
        coleccion.insertOne(document);
    }
    
    public static boolean buscarRestaurante(String database, String nombreBuscado) {
        MongoDatabase db = mongo.getDatabase(database);
        MongoCollection coleccion = (MongoCollection) db.getCollection("restaurantes");
        Document busqueda = new Document();
        busqueda.put("nombre", nombreBuscado);
        FindIterable cursor = coleccion.find(busqueda);
        Iterator ite = cursor.iterator();
        return ite.hasNext();
    }
    
    public static void borrarRestaurante(String database) {
        MongoDatabase db = mongo.getDatabase(database);
        MongoCollection coleccion = (MongoCollection) db.getCollection("restaurantes");
        Document document = new Document();
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre del propietario: ");
        String propietarioABorrar = sc.nextLine();
        document.put("propietario", propietarioABorrar);
        coleccion.deleteMany(document);
    }
    
    public static void modificarRestaurante(String database) {
        MongoDatabase db = mongo.getDatabase(database);
        MongoCollection coleccion = (MongoCollection) db.getCollection("restaurantes");
        Document document = new Document();
        Scanner sc = new Scanner(System.in);
        Bson filter;
        Bson update;
        String nombre = "";
        boolean nombreValido = false;
        do {
            System.out.println("Nombre del restaurante a modificar: ");
            nombre = sc.nextLine();
            nombreValido = buscarRestaurante("AlbertoColmenarBD", nombre);
            if (!nombreValido) {
                System.out.println("Ese restaurante no existe."); 
            }
        } while (!nombreValido);
        System.out.println("Nuevo nombre del restaurante: ");
        filter = eq("nombre", nombre);
        String nuevoNombre = sc.nextLine();
        update = set("nombre", nuevoNombre);
        coleccion.updateOne(filter, update);
        filter = eq("nombre", nuevoNombre);
        System.out.println("Nombre del propietario: ");
        String propietario = sc.nextLine();
        update = set("propietario", propietario);
        coleccion.updateOne(filter, update);
        System.out.println("Especialidad del restaurante: ");
        String especialidad = sc.nextLine();
        update = set("especialidad", especialidad);
        coleccion.updateOne(filter, update);
    }
    
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        mongo = CrearConexion();
        if (mongo != null) {
            System.out.format("1. Datos de los restaurantes.%n"
                    + "2. Insertar restaurante.%n"
                    + "3. Borrar restaurantes del propietario.%n"
                    + "4. Modificar restaurante.%n");        
            int opcion = Integer.parseInt(sc.nextLine());
            switch (opcion) {
                case 1: {
                    datosRestaurantes("AlbertoColmenarBD");
                    break;
                }
                case 2: {
                    insertarRestaurante("AlbertoColmenarBD");
                    break;
                }
                case 3: {
                    borrarRestaurante("AlbertoColmenarBD");
                    break;
                }
                case 4: {
                    modificarRestaurante("AlbertoColmenarBD");
                    break;
                }
            }
        }
    }
    
}
