
package Controlador;

import java.awt.HeadlessException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;


public class CrearCaso 
{
    static String sSQL="";
    static private int NoExpediente;
    static private int IDCliente=1;
    static private int IDTipoExpediente;
    static private boolean validacionFinal=false;
    static private String NombreArchivos[];
    static private String URLsArchivos[];

    
    public static void GuardarTablaCasosCliente(int TipoCaso) 
    {
        IDTipoExpediente = TipoCaso;
        NoExpediente=BDdocumentos.getNoExpediente();
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "INSERT INTO casoscliente(IDCliente , IDNoExpediente, IDTipoExpediente) VALUES (?, ?, ?)";        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, IDCliente);
            pst.setInt(2, NoExpediente);
            pst.setInt(3, IDTipoExpediente);
            int validacion = pst.executeUpdate();
            if (validacion>0) 
            {
                System.out.println("Se han insertado los datos correctamente");
                validacionFinal=true;
                mysql.desconectar();
                GuardarTablaDocumentos();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Hubo un error al insertar los datos en la Tabla CASOS DEL CLIENTE.");
                validacionFinal=false;
            }
        } catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! " + e);
        }
        finally
        {
            mysql.desconectar();
        }
    }
    public static String fechaAtcual()
    {
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
        return formatoFecha.format(fecha);
    }
    
    public static void GuardarTablaDocumentos() 
    {   
        NombreArchivos=BDdocumentos.getNombresArchivos();
        URLsArchivos=BDdocumentos.getCarpetaServidor();
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "INSERT INTO documentos(IDNoExpediente , NombreArchivo, FechaCreacion, Ruta) VALUES (?, ?, ?, ?)";        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, NoExpediente);
                for(int i=0; i<NombreArchivos.length; i++)
                {
                        
                                    pst.setInt(1, NoExpediente);
                                    pst.setString(2, NombreArchivos[i]);
                                    pst.setString(3, fechaAtcual());
                                    pst.setString(4, URLsArchivos[i]);                                   
                                    int validacion = pst.executeUpdate();                                    
                                    if (validacion>0) 
                                    {
                                        System.out.println("validacion: " + validacion);
                                        validacionFinal=true;
                                    }
                                    else
                                    {
                                        JOptionPane.showMessageDialog(null, "Hubo un error al insertar los datos en la Tabla VENDEDORES Extras.");
                                        validacionFinal=false;
                                    }                                   
                }
            if (validacionFinal==true) 
            {
                System.out.println("Hemos insertado las 2 TABLAS correctamente!");
                mysql.desconectar();
                GuardarTablaBitacora();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Hubo un error al insertar los datos en la Tabla DOCUMENTOS.");
            }
        } catch (HeadlessException | UnknownHostException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! " + e);
        }
        finally
        {
            mysql.desconectar();
        }
    }
    
    public static void GuardarTablaBitacora() throws UnknownHostException 
    {
        int IDUsuario = DatosSesionIniciada.getIDUsuario();
        NoExpediente=BDdocumentos.getNoExpediente();
        int IDEstatusExpediente = 2;
        InetAddress localHost = InetAddress.getLocalHost();
        String IP = localHost.getHostAddress();
        String Mensaje = "Se creo el expediente " + NoExpediente + ", y se adjuntaron " + NombreArchivos.length + " archivo(s)";
        
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "INSERT INTO bitacora(IDUsuario, IDNoExpediente, IDEstatusExpediente, FechaOrigen, HostOrigen, ActividadRealizada, IDActividadRealizada) VALUES (?, ?, ?, ?, ?, ?, ?)";        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setInt(1, IDUsuario);
            pst.setInt(2, NoExpediente);
            pst.setInt(3, IDEstatusExpediente);
            pst.setString(4, fechaAtcual());
            pst.setString(5, IP);
            pst.setString(6, Mensaje);
            pst.setInt(7, 1);
            int validacion = pst.executeUpdate();
            if (validacion>0) 
            {
                System.out.println("Se han insertado los datos correctamente");
                validacionFinal=true;
                mysql.desconectar();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Hubo un error al insertar los datos en la Tabla BITACORA.");
                validacionFinal=false;
            }
        } catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! " + e);
        }
        finally
        {
            mysql.desconectar();
        }
    }
}