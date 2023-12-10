package colmenaralbertomvcgestionusuarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
                ResultSet resultado = stmt.executeQuery("SELECT * FROM usuarios WHERE nombre='" + 
                        nombre + "' and contraseña='" + contrasena + "'");
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
    
    public boolean modificarContrasena(String nombre, String contrasena) {
        boolean modificado = false;
        try {
            PreparedStatement sql = conexion.prepareStatement("UPDATE usuarios SET contraseña = ? WHERE nombre = ?");
            sql.setString(1, contrasena);
            sql.setString(2, nombre);
            sql.executeUpdate();
            modificado = true;
            sql.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return modificado;
    }
    
    public String contrasenaValida(String contrasena) {
        String errores = "";
        if (!contrasena.matches("\\w*")) {
            errores += " Debe estar formada sólo por letras, números y el carácter “_”.\n";
        }
        if (contrasena.length() < 8) {
            errores += " Debe tener 8 caracteres como mínimo.\n";
        }
        if (contrasena.matches("^\\d.*")) {
            errores += " No debe comenzar con un carácter numérico.\n";
        }
        if (!contrasena.matches(".*[A-Z]+.*")) {
            errores += " Debe contener al menos una letra en mayúscula.\n";
        }
        return errores;
    }
    
}
