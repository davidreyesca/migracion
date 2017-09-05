
package Controlador;

import Vista.TestamentosVisualizacion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AbrirTestamentos
{
    TestamentosVisualizacion a = new TestamentosVisualizacion();
    String tipoPersonaOtorgante, nombreOtorgante, apPaOtorgante, apMaOtorgante;
    String observaciones;
    String sSQL="";
    int noExpediente= AbrirExpediente.NoExpedinte;
    int IDCliente=1, folio;
    int numerootorgantes;
    String[][] otorgantesdatos;
    boolean validacionfinal=true;
    public void ObtenerDatos()
    {
        ObtenerDatosGenerales();
        ObtenerDatosOtorgante();
        a.setVisible(true);
    }

    public void ObtenerDatosGenerales()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "SELECT * FROM testamentos WHERE IDNoExpediente = '" + AbrirExpediente.NoExpedinte + "'";        
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                folio= rs.getInt("Folio");
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
        a.jTFolio.setText(String.valueOf(folio));
        a.jTAObservaciones.append(observaciones);
    }
    
    public void ObtenerDatosOtorgante()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        int contador=0;
        sSQL= "SELECT * FROM testamentosotorgante WHERE IDNoExpediente = '" + AbrirExpediente.NoExpedinte + "'";
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
                nombreOtorgante = rs.getString("NombreOtorgante");
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
    public void LlenarDatosOtorgantes()
    {
        for (int i = 1; i <otorgantesdatos.length ; i++) 
        {
                a.LlenarOtorgantes(i-1, otorgantesdatos[i][0], otorgantesdatos[i][1], otorgantesdatos[i][2], otorgantesdatos[i][3]);
        }
    }

    public void AgregarOtorgante()
    {
        a.AgregarOtorgante();
    }

}
    