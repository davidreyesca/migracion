
package Controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Vista.DonacionVisualizacion;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AbrirDonacion 
{
    DonacionVisualizacion a = new DonacionVisualizacion();
    String tipoPersonaDonatario, nombreDonatario, apPaDonatario, apMaDonatario;
    String tipoPersonaDonante, nombreDonante, apPaDonante, apMaDonante;
    String fecha, tipoacto, calle, noexterior, nointerior, colonia, estado, municipio, observaciones;
    String sSQL="";
    int noExpediente= AbrirExpediente.NoExpedinte;
    int IDCliente=1;
    int folioReal, instrumento, tomo;
    int numerodonatarios;
    int numerodonantes;
    String[][] donatariosdatos;
    String[][] donantesdatos;
    boolean validacionfinal=true;
    public void ObtenerDatos()
    {
        ObtenerDatosGenerales();
        ObtenerDatosDonatario();
        ObtenerDatosDonante();
        a.setVisible(true);
    }

    public void ObtenerDatosGenerales()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "SELECT * FROM donacion WHERE IDNoExpediente = '" + AbrirExpediente.NoExpedinte + "'";        
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {    
                folioReal = rs.getInt("FolioReal");
                instrumento = rs.getInt("Instrumento");
                tomo = rs.getInt("Tomo");
                fecha = rs.getString("Fecha");
                tipoacto = rs.getString("TipoActo");
                calle = rs.getString("Calle");
                noexterior = rs.getString("NoExterior");
                nointerior = rs.getString("NoInterior");
                colonia = rs.getString("Colonia");
                estado = rs.getString("Estado");
                municipio = rs.getString("Municipio");
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
        a.jTFolioReal.setText(String.valueOf(folioReal));        
        a.jTInstrumento.setText(String.valueOf(instrumento));
        a.jTTomo.setText(String.valueOf(tomo));
        a.jTTipoActo.setText(tipoacto);
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/mm/yyyy");
        Date Fecha = null;
        try 
        {
        Fecha = formatoDeFecha.parse(fecha);
        } catch (ParseException ex)
        {
        }
        a.jDCFecha.setDate(Fecha);
        a.jTCalle.setText(calle);
        a.jTNoExterior.setText(noexterior);
        a.jTNoInterior.setText(nointerior);
        a.jTColonia.setText(colonia);
        a.jCBEstadoRepublica.setSelectedItem(estado);
        a.jCBMunicipio.setSelectedItem(municipio);
        a.jTAObservaciones.append(observaciones);
    }
    public void ObtenerDatosDonatario()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        int contador=0;
        sSQL= "SELECT * FROM donaciondonatarios WHERE IDNoExpediente = '" + AbrirExpediente.NoExpedinte + "'";
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                contador++;
            }
            donatariosdatos = new String [contador][4];
            if(contador>1)
            {
                for (int i = 0; i < contador-1; i++) 
                {              
                    AgregarDonatario();
                }
            }
            int contador2=0;
            rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                tipoPersonaDonatario = rs.getString("TipoCliente");
                nombreDonatario = rs.getString("NombreDonatario");
                apPaDonatario = rs.getString("ApPaternoDonatario");
                apMaDonatario = rs.getString("ApMaternoDonatario");
                donatariosdatos[contador2][0]=tipoPersonaDonatario;
                donatariosdatos[contador2][1]=nombreDonatario;
                donatariosdatos[contador2][2]=apPaDonatario;
                donatariosdatos[contador2][3]=apMaDonatario;
                System.out.println("Datos: " + donatariosdatos[contador2][0] + " " + donatariosdatos[contador2][1] + " " + donatariosdatos[contador2][2] + " " + donatariosdatos[contador2][3]);
                contador2++;
            }
            if(contador==1)
            {
                LlenarDatosDonatario();
            }else
            {
                LlenarDatosDonatario();
                LlenarDatosDonatarios();
            }
        } catch (SQLException ex) {
            System.out.println("Error los datos del expedientes" + ex);
        }finally
        //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
        {
            mysql.desconectar();
        }
    }
        public void ObtenerDatosDonante()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        int contador=0;
        sSQL= "SELECT * FROM donaciondonantes WHERE IDNoExpediente = '" + AbrirExpediente.NoExpedinte + "'";
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                contador++;
            }
            donantesdatos = new String [contador][4];
            if(contador>1)
            {
                for (int i = 0; i < contador-1; i++) 
                {              
                    AgregarDonante();
                }
            }
            int contador2=0;
            rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                tipoPersonaDonante = rs.getString("TipoCliente");
                nombreDonante= rs.getString("NombreDonante");
                apPaDonante = rs.getString("ApPaternoDonante");
                apMaDonante = rs.getString("ApMaternoDonante");
                donantesdatos[contador2][0]=tipoPersonaDonante;
                donantesdatos[contador2][1]=nombreDonante;
                donantesdatos[contador2][2]=apPaDonante;
                donantesdatos[contador2][3]=apMaDonante;
                System.out.println("Datos: " + donantesdatos[contador2][0] + " " + donantesdatos[contador2][1] + " " + donantesdatos[contador2][2] + " " + donantesdatos[contador2][3]);
                contador2++;
            }
            if(contador==1)
            {
                LlenarDatosDonante();
            }else
            {
                LlenarDatosDonante();
                LlenarDatosDonantes();
            }
        } catch (SQLException ex) {
            System.out.println("Error los datos del expedientes" + ex);
        }finally
        //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
        {
            mysql.desconectar();
        }
    }
        
    public void LlenarDatosDonatario()
    {
        if(donatariosdatos[0][0].equals("Persona Física"))
        {
            a.jRBFisicaDonatario.setSelected(true);
            a.jTNombreDonatarioFisica.setText(donatariosdatos[0][1]);
            a.jTApPaDonatarioFisica.setText(donatariosdatos[0][2]);
            a.jTApMaDonatarioFisica.setText(donatariosdatos[0][3]);
        }else if(donatariosdatos[0][0].equals("Persona Moral"))
        {
            a.jRBMoralDonatario.setSelected(true);
            a.jTNombreDonatarioMoral.setText(donatariosdatos[0][1]);
            a.jLNombreFisicaDonatario.setVisible(false);
            a.jLApMaDonatarioFisica.setVisible(false);
            a.jLApPaDonatarioFisica.setVisible(false);
            a.jTNombreDonatarioFisica.setVisible(false);
            a.jTApMaDonatarioFisica.setVisible(false);
            a.jTApPaDonatarioFisica.setVisible(false);
            a.jLNombreMoralDonatario.setVisible(true);
            a.jTNombreDonatarioMoral.setVisible(true);
        }
    }
    public void LlenarDatosDonante()
    {
        if(donantesdatos[0][0].equals("Persona Física"))
        {
            a.jRBFisicaDonante.setSelected(true);
            a.jTNombreDonanteFisica.setText(donantesdatos[0][1]);
            a.jTApPaDonanteFisica.setText(donantesdatos[0][2]);
            a.jTApMaDonanteFisica.setText(donantesdatos[0][3]);
        }else if(donantesdatos[0][0].equals("Persona Moral"))
        {
            a.jRBMoralDonante.setSelected(true);
            a.jTNombreDonanteMoral.setText(donantesdatos[0][1]);
            a.jLNombreFisicaDonante.setVisible(false);
            a.jLApMaDonanteFisica.setVisible(false);
            a.jLApPaDonanteFisica.setVisible(false);
            a.jTNombreDonanteFisica.setVisible(false);
            a.jTApMaDonanteFisica.setVisible(false);
            a.jTApPaDonanteFisica.setVisible(false);
            a.jLNombreMoralDonante.setVisible(true);
            a.jTNombreDonanteMoral.setVisible(true);
        }
    }
    public void LlenarDatosDonatarios()
    {
        for (int i = 1; i <donatariosdatos.length ; i++) 
        {
                a.LlenarDonatarios(i-1, donatariosdatos[i][0], donatariosdatos[i][1], donatariosdatos[i][2], donatariosdatos[i][3]);
        }
    }
    public void LlenarDatosDonantes()
    {
        for (int i = 1; i <donantesdatos.length ; i++) 
        {
                a.LlenarDonantes(i-1, donantesdatos[i][0], donantesdatos[i][1], donantesdatos[i][2], donantesdatos[i][3]);
        }
    }
    public void AgregarDonatario()
    {
        a.AgregarDonatario();
    }
    public void AgregarDonante()
    {
        a.AgregarDonante();
    }
}
    