package colmenaralbertovuelos;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DAM2A-03
 */
public class ColmenarAlbertoMVCVuelos {

    public static Connection conexion;
    
    public ColmenarAlbertoMVCVuelos() {
    }
    
    public void getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/gestionvuelos", "root", "");
            System.out.println("Conexion realizada");
        } catch (ClassNotFoundException ex) {
            System.err.println("Error " + ex.getMessage());
        } catch (SQLException ex) {
            System.err.println("Error de conexión " + ex.getMessage());
        }
    }
    
    public String informaBD() {
        String datos = "";
        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            datos = String.format("NOMBRE: %s%n"
                    + "DRIVER: %s%n"
                    + "URL: %s%n"
                    + "USUARIO: %s%n", 
                    dbmd.getDatabaseProductName(), dbmd.getDriverName(), dbmd.getURL(), dbmd.getUserName());
            ResultSet tablas = dbmd.getTables("gestionvuelos", "gestionvuelos", null, null);
            while (tablas.next()) {
                datos += "TABLA: " + tablas.getString("TABLE_NAME") + "\n";
            }
        } catch (SQLException ex) {
            System.err.println("Error " + ex.getMessage());
        }
        return datos;
    }
    
    public String informaTabla(String nombreTabla) {
        String datos = "";
        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet tabla = dbmd.getTables("gestionvuelos", "gestionvuelos", nombreTabla, null);
            
            // recorrer el resultset
            datos = String.format("CATALOGO: %s%n" +
                                "ESQUEMA: %s%n" +
                                "NOMBRE TABLA: %s%n" +
                                "TIPO: %s%n", "");
             
            
            
        } catch (SQLException ex) {
            System.err.println("Error " + ex.getMessage());
        }
        return datos;
    }
    
}