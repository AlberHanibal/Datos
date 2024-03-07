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
    
    /**
     * @param args the command line arguments
     */
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
