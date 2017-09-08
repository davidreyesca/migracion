package Controlador;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 * Clase encargada de la conexión a la base de datos MySQL de migración
 * @author David Reyes
 */
public class ConexionMySql
{
    private static Connection conn;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String bd = "migracion";
    private static final String user = "root";
    private static final String password = "";
    private static final String url = "jdbc:mysql://localhost:3306/" + bd;
    
//    private static final String driver = "com.mysql.jdbc.Driver";
//    private static final String bd = "migraciondb";
//    private static final String user = "adminmigracion";
//    private static final String password = "Notaria21SLPassword";
//    private static final String url = "jdbc:mysql://160.153.59.37:3306/" + bd;
    
    /**
     * Metodo encargado de la conexión
     */
    public ConexionMySql() 
    {
        conn = null;
        try 
        {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            if(conn != null)
            {
                System.out.println("Conexión establecida...");
            }
        } catch (ClassNotFoundException | SQLException e) 
        {
            System.out.println("Error al conectar " + e);
            JOptionPane.showMessageDialog(null, "La base de datos se encuentra fuera de linea, Por favor intente la operación más tarde.");
        }
    }
    /**
     * Este metodo nos retorna la conexión a la bd
     * @return conn 
     */
    public Connection getConection()
    {
        return conn;
    }   
    /**
     * Con este metodo nos desconectamos de la bd
     */
    public void desconectar()
    {
        conn = null;
        if(conn == null)
            {
                System.out.println("Conexión terminada...");
            }
    } 
}
