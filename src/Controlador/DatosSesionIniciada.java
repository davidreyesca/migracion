package Controlador;

import Vista.Principal;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
Clase encargada de guardar todos los datos del usuario del inicio de sesisón, para validaciones de paneles a mostrar y bitacoras
*/
public class DatosSesionIniciada 
{
    static private int IDUsuario, IDArea, IDEstatusUsuario, IDTipoUsuario, Permisos;
    static private String NombreUsuario, Nombre, ApPaternoUsuario, ApMaternoUsuario;
    
    
    public static int getIDUsuario() {
        return IDUsuario;
    }

    public static void setIDUsuario(int IDUsuario) {
        DatosSesionIniciada.IDUsuario = IDUsuario;
    }

    public static int getIDArea() {
        return IDArea;
    }

    public static void setIDArea(int IDArea) {
        DatosSesionIniciada.IDArea = IDArea;
    }

    public static int getIDEstatusUsuario() {
        return IDEstatusUsuario;
    }

    public static void setIDEstatusUsuario(int IDEstatusUsuario) {
        DatosSesionIniciada.IDEstatusUsuario = IDEstatusUsuario;
    }

    public static int getIDTipoUsuario() {
        return IDTipoUsuario;
    }

    public static void setIDTipoUsuario(int IDTipoUsuario) {
        DatosSesionIniciada.IDTipoUsuario = IDTipoUsuario;
    }

    public static int getPermisos() {
        return Permisos;
    }

    public static void setPermisos(int Permisos) {
        DatosSesionIniciada.Permisos = Permisos;
    }

    public static String getNombreUsuario() {
        return NombreUsuario;
    }

    public static void setNombreUsuario(String NombreUsuario) {
        DatosSesionIniciada.NombreUsuario = NombreUsuario;
    }

    public static String getNombre() {
        return Nombre;
    }

    public static void setNombre(String Nombre) {
        DatosSesionIniciada.Nombre = Nombre;
    }

    public static String getApPaternoUsuario() {
        return ApPaternoUsuario;
    }

    public static void setApPaternoUsuario(String ApPaternoUsuario) {
        DatosSesionIniciada.ApPaternoUsuario = ApPaternoUsuario;
    }

    public static String getApMaternoUsuario() {
        return ApMaternoUsuario;
    }

    public static void setApMaternoUsuario(String ApMaternoUsuario) {
        DatosSesionIniciada.ApMaternoUsuario = ApMaternoUsuario;
    }
    
    //Crea un objeto del Frame Princial, creando la interfaz de usuario.
    public static void Inicio() 
    {
        Principal m = new Principal();
        m.setExtendedState(MAXIMIZED_BOTH);
        m.setVisible(true);
        m.jUsuario.setText("¡Bienvenido! " + Nombre);
    }

    //Descarga de la base de datos los campos necesarios para que el sistema funcione correctamente durante la sesión activa
    public static void GuardarDatos()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        String sSQL="SELECT * FROM usuarios WHERE NombreUsuario=" + "'" + NombreUsuario +"'";
        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {                
                IDUsuario= rs.getInt(1);
                IDArea= rs.getInt(2);
                IDEstatusUsuario= rs.getInt(5);
                IDTipoUsuario= rs.getInt(6);
                Permisos= rs.getInt(7);
                Nombre=rs.getString(8);
                ApPaternoUsuario=rs.getString(9);
                ApMaternoUsuario=rs.getString(10);
            }
        } catch (SQLException ex) {
            System.out.println("Error al contectar la base de datos" + ex);
        }finally
        //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
        {
            Inicio();
            mysql.desconectar();
        }
    }
}
