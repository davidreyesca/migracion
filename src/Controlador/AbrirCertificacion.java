
package Controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Vista.CertificacionesVisualizacion;

public class AbrirCertificacion 
{
    CertificacionesVisualizacion a = new CertificacionesVisualizacion();
    String tipoPersonaNombre, nombreNombre, apPaNombre, apMaNombre;
    String observaciones;
    String sSQL="";
    int noExpediente= AbrirExpediente.NoExpedinte;
    int IDCliente=1;
    int numeroNombres;
    String[][] nombresdatos;

    boolean validacionfinal=true;
    public void ObtenerDatos()
    {
        ObtenerDatosGenerales();
        ObtenerDatosNombre();
        EscondresEdicion();
        a.setVisible(true);
    }
    public void EscondresEdicion()
    {
        if(AbrirExpediente.getEditar()==0)
        {
            //No se habilito la edicion
            a.jBTerminar.setVisible(false);
            a.jBHabilitarEdicion.setVisible(true);
        }
        else
        {
            a.jBTerminar.setVisible(true);
            a.jBHabilitarEdicion.setVisible(false);
        }
    }
    
    public void ObtenerDatosGenerales()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "SELECT * FROM certificaciones WHERE IDNoExpediente = '" + AbrirExpediente.NoExpedinte + "'";        
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
    public void ObtenerDatosNombre()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        int contador=0;
        sSQL= "SELECT * FROM certificacionesnombres WHERE IDNoExpediente = '" + AbrirExpediente.NoExpedinte + "'";
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
                    AgregarNombre();
                }
            }
            int contador2=0;
            rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                tipoPersonaNombre = rs.getString("TipoCliente");
                nombreNombre = rs.getString("NombreNombre");
                apPaNombre = rs.getString("ApPaternoNombre");
                apMaNombre = rs.getString("ApMaternoNombre");
                nombresdatos[contador2][0]=tipoPersonaNombre;
                nombresdatos[contador2][1]=nombreNombre;
                nombresdatos[contador2][2]=apPaNombre;
                nombresdatos[contador2][3]=apMaNombre;
                System.out.println("Datos: " + nombresdatos[contador2][0] + " " + nombresdatos[contador2][1] + " " + nombresdatos[contador2][2] + " " + nombresdatos[contador2][3]);
                contador2++;
            }
            if(contador==1)
            {
                LlenarDatosNombre();
            }else
            {
                LlenarDatosNombre();
                LlenarDatosNombres();
            }
        } catch (SQLException ex) {
            System.out.println("Error los datos del expedientes" + ex);
        }finally
        //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
        {
            mysql.desconectar();
        }
    }

    public void LlenarDatosNombre()
    {
        if(nombresdatos[0][0].equals("Persona Física"))
        {
            a.jRBFisicaNombre.setSelected(true);
            a.jTNombreNombreFisica.setText(nombresdatos[0][1]);
            a.jTApPaNombreFisica.setText(nombresdatos[0][2]);
            a.jTApMaNombreFisica.setText(nombresdatos[0][3]);
        }else if(nombresdatos[0][0].equals("Persona Moral"))
        {
            a.jRBMoralNombre.setSelected(true);
            a.jTNombreNombreMoral.setText(nombresdatos[0][1]);
            a.jLNombreFisicaNombre.setVisible(false);
            a.jLApMaNombreFisica.setVisible(false);
            a.jLApPaNombreFisica.setVisible(false);
            a.jTNombreNombreFisica.setVisible(false);
            a.jTApMaNombreFisica.setVisible(false);
            a.jTApPaNombreFisica.setVisible(false);
            a.jLNombreMoralNombre.setVisible(true);
            a.jTNombreNombreMoral.setVisible(true);
        }
    }
    public void LlenarDatosNombres()
    {
        for (int i = 1; i <nombresdatos.length ; i++) 
        {
                a.LlenarNombres(i-1, nombresdatos[i][0], nombresdatos[i][1], nombresdatos[i][2], nombresdatos[i][3]);
        }
    }

    public void AgregarNombre()
    {
        a.AgregarNombre();
    }

}
    