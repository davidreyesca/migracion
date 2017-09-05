package Controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ObtenerValoresUsuarios 
{
    public static String ObtenerTipoUsuario(int tipousuario)
    {
        String tipo="";
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        String sSQL="SELECT * FROM tipousuario WHERE IDTipoUsuario ='" + tipousuario + "'";
        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                    tipo = rs.getString("TipoUsuario");
            }
        } catch (SQLException ex) {
            System.out.println("Error al contectar la base de datos" + ex);
        }finally
        {
            mysql.desconectar();
        }
        return tipo;
    }
    
    public static String ObtenerEstatus(int estatus)
    {
        String estatususuario="";
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        String sSQL="SELECT * FROM estatususuario WHERE IDEstatusUsuario ='" + estatus + "'";
        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                    estatususuario = rs.getString("EstatusUsuario");
            }
        } catch (SQLException ex) {
            System.out.println("Error al contectar la base de datos" + ex);
        }finally
        {
            mysql.desconectar();
        }
        return estatususuario;
    }
    
    public static String ObtenerArea(int Area)
    {
        String area="";
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        String sSQL="SELECT * FROM area WHERE IDArea ='" + Area + "'";
        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                    area = rs.getString("NombreArea");
            }
        } catch (SQLException ex) {
            System.out.println("Error al contectar la base de datos" + ex);
        }finally
        {
            mysql.desconectar();
        }
        return area;
    }
}
