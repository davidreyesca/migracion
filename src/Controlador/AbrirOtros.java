
package Controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Vista.OtrosVisualizacion;

public class AbrirOtros 
{
    OtrosVisualizacion a = new OtrosVisualizacion();
    String tipoPersonaParticipante, nombreParticipante, apPaParticipante, apMaParticipante;
    String observaciones, tipoOtro;
    String sSQL="";
    int noExpediente= AbrirExpediente.NoExpedinte;
    int IDCliente=1;
    int numeroParticipantes;
    String[][] nombresdatos;

    boolean validacionfinal=true;
    public void ObtenerDatos()
    {
        ObtenerDatosGenerales();
        ObtenerDatosParticipante();
        a.setVisible(true);
    }

    public void ObtenerDatosGenerales()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "SELECT * FROM otros WHERE IDNoExpediente = '" + AbrirExpediente.NoExpedinte + "'";        
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {    
                observaciones = rs.getString("Observaciones");
                tipoOtro = rs.getString("tipoOtro");
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
        a.jTTipoActo.setText(tipoOtro);
        a.jTAObservaciones.append(observaciones);
    }
    public void ObtenerDatosParticipante()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        int contador=0;
        sSQL= "SELECT * FROM otrosparticipante WHERE IDNoExpediente = '" + AbrirExpediente.NoExpedinte + "'";
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                contador++;
            }
            nombresdatos = new String [contador][4];
            if(contador>1)
            {
                for (int i = 0; i < contador-1; i++) 
                {              
                    AgregarParticipante();
                }
            }
            int contador2=0;
            rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                tipoPersonaParticipante = rs.getString("TipoCliente");
                nombreParticipante = rs.getString("NombreParticipante");
                apPaParticipante = rs.getString("ApPaternoParticipante");
                apMaParticipante = rs.getString("ApMaternoParticipante");
                nombresdatos[contador2][0]=tipoPersonaParticipante;
                nombresdatos[contador2][1]=nombreParticipante;
                nombresdatos[contador2][2]=apPaParticipante;
                nombresdatos[contador2][3]=apMaParticipante;
                System.out.println("Datos: " + nombresdatos[contador2][0] + " " + nombresdatos[contador2][1] + " " + nombresdatos[contador2][2] + " " + nombresdatos[contador2][3]);
                contador2++;
            }
            if(contador==1)
            {
                LlenarDatosParticipante();
            }else
            {
                LlenarDatosParticipante();
                LlenarDatosParticipantes();
            }
        } catch (SQLException ex) {
            System.out.println("Error los datos del expedientes" + ex);
        }finally
        //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
        {
            mysql.desconectar();
        }
    }

    public void LlenarDatosParticipante()
    {
        if(nombresdatos[0][0].equals("Persona Física"))
        {
            a.jRBFisicaParticipante.setSelected(true);
            a.jTNombreParticipanteFisica.setText(nombresdatos[0][1]);
            a.jTApPaParticipanteFisica.setText(nombresdatos[0][2]);
            a.jTApMaParticipanteFisica.setText(nombresdatos[0][3]);
        }else if(nombresdatos[0][0].equals("Persona Moral"))
        {
            a.jRBMoralParticipante.setSelected(true);
            a.jTNombreParticipanteMoral.setText(nombresdatos[0][1]);
            a.jLNombreFisicaParticipante.setVisible(false);
            a.jLApMaParticipanteFisica.setVisible(false);
            a.jLApPaParticipanteFisica.setVisible(false);
            a.jTNombreParticipanteFisica.setVisible(false);
            a.jTApMaParticipanteFisica.setVisible(false);
            a.jTApPaParticipanteFisica.setVisible(false);
            a.jLNombreMoralParticipante.setVisible(true);
            a.jTNombreParticipanteMoral.setVisible(true);
        }
    }
    public void LlenarDatosParticipantes()
    {
        for (int i = 1; i <nombresdatos.length ; i++) 
        {
                a.LlenarParticipantes(i-1, nombresdatos[i][0], nombresdatos[i][1], nombresdatos[i][2], nombresdatos[i][3]);
        }
    }

    public void AgregarParticipante()
    {
        a.AgregarParticipante();
    }

}
    