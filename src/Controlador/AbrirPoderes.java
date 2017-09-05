
package Controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Vista.PoderesVisualizacion;

public class AbrirPoderes 
{
    PoderesVisualizacion a = new PoderesVisualizacion();
    String tipoPersonaQuienRecibe, nombreQuienRecibe, apPaQuienRecibe, apMaQuienRecibe;
    String tipoPersonaOtorgante, nombreOtorgante, apPaOtorgante, apMaOtorgante;
    String observaciones;
    String sSQL="";
    int noExpediente= AbrirExpediente.NoExpedinte;
    int IDCliente=1;
    int numeroquienesreciben;
    int numerootorgantes;
    String[][] quienesrecibendatos;
    String[][] otorgantesdatos;
    boolean validacionfinal=true;
    public void ObtenerDatos()
    {
        ObtenerDatosGenerales();
        ObtenerDatosQuienRecibe();
        ObtenerDatosOtorgante();
        a.setVisible(true);
    }

    public void ObtenerDatosGenerales()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "SELECT * FROM poderes WHERE IDNoExpediente = '" + AbrirExpediente.NoExpedinte + "'";        
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                observaciones = rs.getString("Observaciones");
            }
            LlenarDatosGenerales();
        } catch (SQLException ex) {
            System.out.println("Error los datos del expedientes" + ex);
        }finally
        //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
        {
            mysql.desconectar();
        }
    }
    public void LlenarDatosGenerales()
    {
        a.jTAObservaciones.append(observaciones);
    }
    
    public void ObtenerDatosQuienRecibe()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        int contador=0;
        sSQL= "SELECT * FROM poderesquienrecibe WHERE IDNoExpediente = '" + AbrirExpediente.NoExpedinte + "'";
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                contador++;
            }
            quienesrecibendatos = new String [contador][4];
            if(contador>1)
            {
                for (int i = 0; i < contador-1; i++) 
                {              
                    AgregarQuienRecibe();
                }
            }
            int contador2=0;
            rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                tipoPersonaQuienRecibe = rs.getString("TipoCliente");
                nombreQuienRecibe = rs.getString("NombreQuienRecibe");
                apPaQuienRecibe = rs.getString("ApPaternoQuienRecibe");
                apMaQuienRecibe = rs.getString("ApMaternoQuienRecibe");
                quienesrecibendatos[contador2][0]=tipoPersonaQuienRecibe;
                quienesrecibendatos[contador2][1]=nombreQuienRecibe;
                quienesrecibendatos[contador2][2]=apPaQuienRecibe;
                quienesrecibendatos[contador2][3]=apMaQuienRecibe;
                System.out.println("Datos: " + quienesrecibendatos[contador2][0] + " " + quienesrecibendatos[contador2][1] + " " + quienesrecibendatos[contador2][2] + " " + quienesrecibendatos[contador2][3]);
                contador2++;
            }
            if(contador==1)
            {
                LlenarDatosQuienRecibe();
            }else
            {
                LlenarDatosQuienRecibe();
                LlenarDatosQuienesReciben();
            }
        } catch (SQLException ex) {
            System.out.println("Error los datos del expedientes" + ex);
        }finally
        //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
        {
            mysql.desconectar();
        }
    }
    public void ObtenerDatosOtorgante()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        int contador=0;
        sSQL= "SELECT * FROM poderesotorgante WHERE IDNoExpediente = '" + AbrirExpediente.NoExpedinte + "'";
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                contador++;
            }
            otorgantesdatos = new String [contador][4];
            if(contador>1)
            {
                for (int i = 0; i < contador-1; i++) 
                {              
                    AgregarOtorgante();
                }
            }
            int contador2=0;
            rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                tipoPersonaOtorgante = rs.getString("TipoCliente");
                nombreOtorgante= rs.getString("NombreOtorgante");
                apPaOtorgante = rs.getString("ApPaternoOtorgante");
                apMaOtorgante = rs.getString("ApMaternoOtorgante");
                otorgantesdatos[contador2][0]=tipoPersonaOtorgante;
                otorgantesdatos[contador2][1]=nombreOtorgante;
                otorgantesdatos[contador2][2]=apPaOtorgante;
                otorgantesdatos[contador2][3]=apMaOtorgante;
                System.out.println("Datos: " + otorgantesdatos[contador2][0] + " " + otorgantesdatos[contador2][1] + " " + otorgantesdatos[contador2][2] + " " + otorgantesdatos[contador2][3]);
                contador2++;
            }
            if(contador==1)
            {
                LlenarDatosOtorgante();
            }else
            {
                LlenarDatosOtorgante();
                LlenarDatosOtorgantes();
            }
        } catch (SQLException ex) {
            System.out.println("Error los datos del expedientes" + ex);
        }finally
        //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
        {
            mysql.desconectar();
        }
    }
        
    public void LlenarDatosQuienRecibe()
    {
        if(quienesrecibendatos[0][0].equals("Persona Física"))
        {
            a.jRBFisicaQuienRecibe.setSelected(true);
            a.jTNombreQuienRecibeFisica.setText(quienesrecibendatos[0][1]);
            a.jTApPaQuienRecibeFisica.setText(quienesrecibendatos[0][2]);
            a.jTApMaQuienRecibeFisica.setText(quienesrecibendatos[0][3]);
        }else if(quienesrecibendatos[0][0].equals("Persona Moral"))
        {
            a.jRBMoralQuienRecibe.setSelected(true);
            a.jTNombreQuienRecibeMoral.setText(quienesrecibendatos[0][1]);
            a.jLNombreFisicaQuienRecibe.setVisible(false);
            a.jLApMaQuienRecibeFisica.setVisible(false);
            a.jLApPaQuienRecibeFisica.setVisible(false);
            a.jTNombreQuienRecibeFisica.setVisible(false);
            a.jTApMaQuienRecibeFisica.setVisible(false);
            a.jTApPaQuienRecibeFisica.setVisible(false);
            a.jLNombreMoralQuienRecibe.setVisible(true);
            a.jTNombreQuienRecibeMoral.setVisible(true);
        }
    }
    public void LlenarDatosOtorgante()
    {
        if(otorgantesdatos[0][0].equals("Persona Física"))
        {
            a.jRBFisicaOtorgante.setSelected(true);
            a.jTNombreOtorganteFisica.setText(otorgantesdatos[0][1]);
            a.jTApPaOtorganteFisica.setText(otorgantesdatos[0][2]);
            a.jTApMaOtorganteFisica.setText(otorgantesdatos[0][3]);
        }else if(otorgantesdatos[0][0].equals("Persona Moral"))
        {
            a.jRBMoralOtorgante.setSelected(true);
            a.jTNombreOtorganteMoral.setText(otorgantesdatos[0][1]);
            a.jLNombreFisicaOtorgante.setVisible(false);
            a.jLApMaOtorganteFisica.setVisible(false);
            a.jLApPaOtorganteFisica.setVisible(false);
            a.jTNombreOtorganteFisica.setVisible(false);
            a.jTApMaOtorganteFisica.setVisible(false);
            a.jTApPaOtorganteFisica.setVisible(false);
            a.jLNombreMoralOtorgante.setVisible(true);
            a.jTNombreOtorganteMoral.setVisible(true);
        }
    }
    public void LlenarDatosQuienesReciben()
    {
        for (int i = 1; i <quienesrecibendatos.length ; i++) 
        {
                a.LlenarQuienesReciben(i-1, quienesrecibendatos[i][0], quienesrecibendatos[i][1], quienesrecibendatos[i][2], quienesrecibendatos[i][3]);
        }
    }
    public void LlenarDatosOtorgantes()
    {
        for (int i = 1; i <otorgantesdatos.length ; i++) 
        {
                a.LlenarOtorgantes(i-1, otorgantesdatos[i][0], otorgantesdatos[i][1], otorgantesdatos[i][2], otorgantesdatos[i][3]);
        }
    }
    public void AgregarQuienRecibe()
    {
        a.AgregarQuienRecibe();
    }
    public void AgregarOtorgante()
    {
        a.AgregarOtorgante();
    }
}
    