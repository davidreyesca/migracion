package Controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Controlador de la vista LogIn
 * @author David Reyes
 */
public class LogIn 
{
    private String Usuario;
    private String Password;
    int resultado = 0;
    int resultado2 = 0;
    /**
     * @return Usuario
     */
    public String getUsuario() 
    {
        return Usuario;
    }
    /**
     * Recibe y iguala a usuario de esta clase 
     * @param Usuario 
     */
    public void setUsuario(String Usuario) 
    {
        this.Usuario = Usuario;
    }
    /**
     * @return Password
     */
    public String getPassword() 
    {
        return Password;
    }
    /**
     * Recibe y iguala a usuario de esta clase 
     * @param Password
     */
    public void setPassword(String Password) 
    {
        this.Password = Password;
    }
    /**
     *Metodo encargado del inicio de sesión, solo verifica usuario y password.
     * @return resultado
     */
    public int InicioSesion()
    {          
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        String sSQL="SELECT * FROM usuarios WHERE NombreUsuario='" + this.Usuario + "' AND Password='" + this.Password +"'";
        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            if (rs.next()) 
            {
               resultado=1;
            }
        } catch (SQLException ex) {
            System.out.println("Error al contectar la base de datos" + ex);
        }finally
        {
            mysql.desconectar();
        }
    return resultado;
    }
    /**
     * Metodo encargado de verificar que el usuario no se encuentra bloqueado del sistema.
     * @return resultado2
     */
    public int VerificacionBloqueo()
    {
        InicioSesion();
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();

        String sSQL="SELECT * FROM usuarios WHERE NombreUsuario='" + this.Usuario + "' AND IDEstatusUsuario='1'";
        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            if (rs.next()) 
            {
               resultado2=1;
            }
        } catch (SQLException ex) {
            System.out.println("Error al contectar la base de datos" + ex);
        }finally
        {
            mysql.desconectar();
        }
    return resultado2;
    }
}
