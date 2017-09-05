
package Controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Vista.AperturaCreditoVisualizacion;

public class AbrirAperturaCredito 
{
    AperturaCreditoVisualizacion a = new AperturaCreditoVisualizacion();
    String tipoPersonaAcreditado, nombreAcreditado, apPaAcreditado, apMaAcreditado;
    String tipoPersonaAcreditante, nombreAcreditante, apPaAcreditante, apMaAcreditante;
    String observaciones;
    String sSQL="";
    int noExpediente= AbrirExpediente.NoExpedinte;
    int IDCliente=1;
    int numeroacreditados;
    int numeroacreditantes;
    String[][] acreditadosdatos;
    String[][] acreditantesdatos;
    boolean validacionfinal=true;
    public void ObtenerDatos()
    {
        ObtenerDatosGenerales();
        ObtenerDatosAcreditado();
        ObtenerDatosAcreditante();
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
        sSQL= "SELECT aperturacredito WHERE IDNoExpediente = '" + AbrirExpediente.NoExpedinte + "'";        
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
    public void ObtenerDatosAcreditado()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        int contador=0;
        sSQL= "SELECT * FROM aperturacreditoacreditado WHERE IDNoExpediente = '" + AbrirExpediente.NoExpedinte + "'";
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                contador++;
            }
            acreditadosdatos = new String [contador][4];
            if(contador>1)
            {
                for (int i = 0; i < contador-1; i++) 
                {              
                    AgregarAcreditado();
                }
            }
            int contador2=0;
            rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                tipoPersonaAcreditado = rs.getString("TipoCliente");
                nombreAcreditado = rs.getString("NombreAcreditado");
                apPaAcreditado = rs.getString("ApPaternoAcreditado");
                apMaAcreditado = rs.getString("ApMaternoAcreditado");
                acreditadosdatos[contador2][0]=tipoPersonaAcreditado;
                acreditadosdatos[contador2][1]=nombreAcreditado;
                acreditadosdatos[contador2][2]=apPaAcreditado;
                acreditadosdatos[contador2][3]=apMaAcreditado;
                System.out.println("Datos: " + acreditadosdatos[contador2][0] + " " + acreditadosdatos[contador2][1] + " " + acreditadosdatos[contador2][2] + " " + acreditadosdatos[contador2][3]);
                contador2++;
            }
            if(contador==1)
            {
                LlenarDatosAcreditado();
            }else
            {
                LlenarDatosAcreditado();
                LlenarDatosAcreditados();
            }
        } catch (SQLException ex) {
            System.out.println("Error los datos del expedientes" + ex);
        }finally
        //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
        {
            mysql.desconectar();
        }
    }
        public void ObtenerDatosAcreditante()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        int contador=0;
        sSQL= "SELECT * FROM aperturacreditoacreditante WHERE IDNoExpediente = '" + AbrirExpediente.NoExpedinte + "'";
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                contador++;
            }
            acreditantesdatos = new String [contador][4];
            if(contador>1)
            {
                for (int i = 0; i < contador-1; i++) 
                {              
                    AgregarAcreditante();
                }
            }
            int contador2=0;
            rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                tipoPersonaAcreditante = rs.getString("TipoCliente");
                nombreAcreditante= rs.getString("NombreAcreditante");
                apPaAcreditante = rs.getString("ApPaternoAcreditante");
                apMaAcreditante = rs.getString("ApMaternoAcreditante");
                acreditantesdatos[contador2][0]=tipoPersonaAcreditante;
                acreditantesdatos[contador2][1]=nombreAcreditante;
                acreditantesdatos[contador2][2]=apPaAcreditante;
                acreditantesdatos[contador2][3]=apMaAcreditante;
                System.out.println("Datos: " + acreditantesdatos[contador2][0] + " " + acreditantesdatos[contador2][1] + " " + acreditantesdatos[contador2][2] + " " + acreditantesdatos[contador2][3]);
                contador2++;
            }
            if(contador==1)
            {
                LlenarDatosAcreditante();
            }else
            {
                LlenarDatosAcreditante();
                LlenarDatosAcreditantes();
            }
        } catch (SQLException ex) {
            System.out.println("Error los datos del expedientes" + ex);
        }finally
        //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
        {
            mysql.desconectar();
        }
    }
        
    public void LlenarDatosAcreditado()
    {
        if(acreditadosdatos[0][0].equals("Persona Física"))
        {
            a.jRBFisicaAcreditado.setSelected(true);
            a.jTNombreAcreditadoFisica.setText(acreditadosdatos[0][1]);
            a.jTApPaAcreditadoFisica.setText(acreditadosdatos[0][2]);
            a.jTApMaAcreditadoFisica.setText(acreditadosdatos[0][3]);
        }else if(acreditadosdatos[0][0].equals("Persona Moral"))
        {
            a.jRBMoralAcreditado.setSelected(true);
            a.jTNombreAcreditadoMoral.setText(acreditadosdatos[0][1]);
            a.jLNombreFisicaAcreditado.setVisible(false);
            a.jLApMaAcreditadoFisica.setVisible(false);
            a.jLApPaAcreditadoFisica.setVisible(false);
            a.jTNombreAcreditadoFisica.setVisible(false);
            a.jTApMaAcreditadoFisica.setVisible(false);
            a.jTApPaAcreditadoFisica.setVisible(false);
            a.jLNombreMoralAcreditado.setVisible(true);
            a.jTNombreAcreditadoMoral.setVisible(true);
        }
    }
    public void LlenarDatosAcreditante()
    {
        if(acreditantesdatos[0][0].equals("Persona Física"))
        {
            a.jRBFisicaAcreditante.setSelected(true);
            a.jTNombreAcreditanteFisica.setText(acreditantesdatos[0][1]);
            a.jTApPaAcreditanteFisica.setText(acreditantesdatos[0][2]);
            a.jTApMaAcreditanteFisica.setText(acreditantesdatos[0][3]);
        }else if(acreditantesdatos[0][0].equals("Persona Moral"))
        {
            a.jRBMoralAcreditante.setSelected(true);
            a.jTNombreAcreditanteMoral.setText(acreditantesdatos[0][1]);
            a.jLNombreFisicaAcreditante.setVisible(false);
            a.jLApMaAcreditanteFisica.setVisible(false);
            a.jLApPaAcreditanteFisica.setVisible(false);
            a.jTNombreAcreditanteFisica.setVisible(false);
            a.jTApMaAcreditanteFisica.setVisible(false);
            a.jTApPaAcreditanteFisica.setVisible(false);
            a.jLNombreMoralAcreditante.setVisible(true);
            a.jTNombreAcreditanteMoral.setVisible(true);
        }
    }
    public void LlenarDatosAcreditados()
    {
        for (int i = 1; i <acreditadosdatos.length ; i++) 
        {
                a.LlenarAcreditados(i-1, acreditadosdatos[i][0], acreditadosdatos[i][1], acreditadosdatos[i][2], acreditadosdatos[i][3]);
        }
    }
    public void LlenarDatosAcreditantes()
    {
        for (int i = 1; i <acreditantesdatos.length ; i++) 
        {
                a.LlenarAcreditantes(i-1, acreditantesdatos[i][0], acreditantesdatos[i][1], acreditantesdatos[i][2], acreditantesdatos[i][3]);
        }
    }
    public void AgregarAcreditado()
    {
        a.AgregarAcreditado();
    }
    public void AgregarAcreditante()
    {
        a.AgregarAcreditante();
    }
}
    