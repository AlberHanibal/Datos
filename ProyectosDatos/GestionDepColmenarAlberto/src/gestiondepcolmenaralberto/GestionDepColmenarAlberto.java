/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondepcolmenaralberto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DAM2A-03
 */
public class GestionDepColmenarAlberto {

    Connection conexion;

    public GestionDepColmenarAlberto() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conexion = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "Dam-2022");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GestionDepColmenarAlberto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GestionDepColmenarAlberto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertar(String nombre, String localidad) {
        String sql = "{call GESTION_DEPT2.INSERT_DEPART(?,?)}";
        CallableStatement llamada;
        try {
            llamada = conexion.prepareCall(sql);
            llamada.setString(1, nombre);
            llamada.setString(2, localidad);
            llamada.executeUpdate();
            llamada.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestionDepColmenarAlberto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void visualizarPorNombre(String nombreDep) {
        try {
            String sql = "{CALL GESTION_DEPT2.VISUALIZAR_DATOS_DEPART(?)}";
            //preparar la llamada
            CallableStatement llamada = conexion.prepareCall(sql);
            //dar valor a los parámetros
            llamada.setString(1, nombreDep);
            enable_dbms_output(conexion, 100);
            //ejecutar el procedimiento
            llamada.execute();
            print_dbms_output(conexion);
            //cerrar flujos
            llamada.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestionDepColmenarAlberto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void enable_dbms_output(Connection conn, int buffer_size) {
        try {
            //Activa la salida dbms_output
            CallableStatement stmt = conn.prepareCall("{call sys.dbms_output.enable(?) }");
            //almacena las líneas de salida en el buffer
            stmt.setInt(1, buffer_size);
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Error al activar dbms_output! " + e.toString());
        }
    }

    public static void print_dbms_output(Connection conn) {
        try {
            //obtiene las líneas almacenadas en el buffer
            CallableStatement stmt = conn.prepareCall("{call sys.dbms_output.get_line(?,?)}");
            //el primer parámetro de salida (out) representa una línea.
            //el segundo parámetro de salida representa el estado
            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.registerOutParameter(2, Types.NUMERIC);
            String line;
            stmt.execute();
            while ((line = stmt.getString(1)) != null) {
                System.out.println(" " + line);
                stmt.execute();
            }
        } catch (Exception e) {
            System.out.println("ERROR " + e.toString());
        }
    }

}
