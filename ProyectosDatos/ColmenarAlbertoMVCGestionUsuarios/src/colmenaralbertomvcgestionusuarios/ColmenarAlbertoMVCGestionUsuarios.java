package colmenaralbertomvcgestionusuarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DAM2A-03
 */
public class ColmenarAlbertoMVCGestionUsuarios {

    public static Connection conexion;

    public ColmenarAlbertoMVCGestionUsuarios() {
    }
   
    public void getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/gestionusuarios2", "root", "");
            System.out.println("Conexion realizada");
        } catch (ClassNotFoundException ex) {
            System.err.println("Error " + ex.getMessage());
        } catch (SQLException ex) {
            System.err.println("Error de conexión " + ex.getMessage());
        }
    }
    
}
