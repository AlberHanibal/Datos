package colmenaralbertomvcgestionusuarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public boolean buscarUsuario(String nombre, String contrasena) {
        boolean encontrado = false;
        if ((!nombre.isEmpty()) || contrasena.isEmpty()) {
            try {
                Statement stmt = (Statement) conexion.createStatement();
                ResultSet resultado = stmt.executeQuery("SELECT * FROM usuarios WHERE nombre='" + nombre + "' and contraseña='" + contrasena + "'");
                if (resultado.next()) {
                    encontrado = true;
                }
                resultado.close();
                stmt.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }        
        return encontrado;
    }
    
    public boolean annadirUsuario(String nombre, String contrasena) {
        boolean insertado = false;
        try {
            PreparedStatement sql = conexion.prepareStatement("INSERT INTO usuarios (nombre, contraseña) VALUES (?, ?)");
            sql.setString(1, nombre);
            sql.setString(2, contrasena);
            sql.executeUpdate();
            insertado = true;
            sql.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return insertado;
    }
    
    public boolean contrasenaValida(String contrasena) {
        boolean valida = false;
        
        
        return valida;
    }
    
}
