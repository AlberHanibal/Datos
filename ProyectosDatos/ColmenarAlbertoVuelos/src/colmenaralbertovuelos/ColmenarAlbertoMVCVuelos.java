package colmenaralbertovuelos;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DAM2A-03
 */
public class ColmenarAlbertoMVCVuelos {

    public static Connection conexion;
    public static String nombreBD;

    public ColmenarAlbertoMVCVuelos() {
    }

    public void getConnection() {
        Scanner in = new Scanner(System.in);
        String bd = "";

        while ((!bd.equals("mySQL")) && !bd.equals("Oracle")) {
            System.out.println("¿Cómo quieres conectarte, con mySQL u Oracle?");
            bd = in.nextLine();
        }

        if (bd.equals("mySQL")) {
            try {
                nombreBD = "gestionvuelos";
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/gestionvuelos", "root", "");
                System.out.println("Conexion realizada");
            } catch (ClassNotFoundException ex) {
                System.err.println("Error " + ex.getMessage());
            } catch (SQLException ex) {
                System.err.println("Error de conexión " + ex.getMessage());
            }
        } else if (bd.equals("Oracle")) {
            nombreBD = "SYSTEM";
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                conexion = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "Dam-2022");
                System.out.println("Conexion realizada");
            } catch (ClassNotFoundException ex) {
                System.err.println("Error " + ex.getMessage());
            } catch (SQLException ex) {
                System.err.println("Error de conexión " + ex.getMessage());
            }
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
            ResultSet tablas = dbmd.getTables(nombreBD, nombreBD, null, null);
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
            ResultSet tabla = dbmd.getTables(nombreBD, nombreBD, nombreTabla, null);
            // no entra
            while (tabla.next()) {

                datos += String.format("CATALOGO: %s%n"
                        + "ESQUEMA: %s%n"
                        + "NOMBRE TABLA: %s%n"
                        + "TIPO: %s%n",
                        tabla.getString("TABLE_CAT"),
                        tabla.getString("TABLE_SCHEM"),
                        tabla.getString("TABLE_NAME"),
                        tabla.getString("TABLE_TYPE"));
            }
        } catch (SQLException ex) {
            System.err.println("Error " + ex.getMessage());
        }
        return datos;
    }

    public String informaColumnas(String nombreTabla) {
        String datos = "";
        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet columnas = dbmd.getColumns(null, nombreBD, nombreTabla, null);

            while (columnas.next()) {
                datos += String.format("NOMBRE: %s%n"
                        + "TIPO: %s%n"
                        + "TAMAÑO: %s%n"
                        + "ES NULA: %s%n%n",
                        columnas.getString("COLUMN_NAME"),
                        columnas.getString("TYPE_NAME"),
                        columnas.getString("COLUMN_SIZE"),
                        columnas.getString("IS_NULLABLE"));
            }
        } catch (SQLException ex) {
            System.err.println("Error " + ex.getMessage());
        }
        return datos;
    }

    public DefaultTableModel generarTablaVuelos() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            Statement stmt = conexion.createStatement();
            ResultSet consulta = stmt.executeQuery("SELECT cod_vuelo,"
                    + " procedencia, destino FROM vuelos");
            tabla.addColumn("COD_VUELO");
            tabla.addColumn("PROCEDENCIA");
            tabla.addColumn("DESTINO");
            Object datos[] = new Object[3];

            while (consulta.next()) {
                for (int i = 0; i < 3; i++) {
                    datos[i] = consulta.getObject(i + 1);
                }
                tabla.addRow(datos);
            }
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return tabla;
    }

    public ArrayList obtenerListaCodigo() {
        ArrayList<String> lista = new ArrayList();
        String cod;
        try {
            Statement stmt = conexion.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT COD_VUELO FROM vuelos");
            while (resultado.next()) {
                cod = resultado.getString("COD_VUELO");
                lista.add(cod);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return lista;
    }

    public DefaultTableModel generarTablaPasajeros(String cod_vuelo) {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            Statement stmt = conexion.createStatement();
            ResultSet consulta = stmt.executeQuery("SELECT NUM,"
                    + " TIPO_PLAZA, FUMADOR FROM pasajeros WHERE COD_VUELO LIKE '" + cod_vuelo + "'");
            tabla.addColumn("NUMERO");
            tabla.addColumn("TIPO_PLAZA");
            tabla.addColumn("FUMADOR");
            Object datos[] = new Object[3];

            while (consulta.next()) {
                for (int i = 0; i < 3; i++) {
                    datos[i] = consulta.getObject(i + 1);
                }
                tabla.addRow(datos);
            }
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return tabla;
    }
}
