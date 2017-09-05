package Controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Vista.CompraVentaVisualizacion;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AbrirCompraVenta 
{
    CompraVentaVisualizacion a = new CompraVentaVisualizacion();
    String tipoPersonaComprador, nombreComprador, apPaComprador, apMaComprador;
    String tipoPersonaVendedor, nombreVendedor, apPaVendedor, apMaVendedor;
    String fecha, tipoacto, calle, noexterior, nointerior, colonia, estado, municipio, observaciones, TipoCompraventa;
    String sSQL="";
    int noExpediente= AbrirExpediente.NoExpedinte;
    int IDCliente=1;
    int folioReal, instrumento, tomo;
    int numerocompradores;
    int numerovendedores;
    String[][] compradoresdatos;
    String[][] vendedoresdatos;
    boolean validacionfinal=true;
    public void ObtenerDatos()
    {
        ObtenerDatosGenerales();
        ObtenerDatosComprador();
        ObtenerDatosVendedor();
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
        sSQL= "SELECT compraventa.*, tipocompraventa.TipoCompraVenta FROM compraventa LEFT JOIN tipocompraventa ON compraventa.IDTipoCompraVenta = tipocompraventa.IDTipoCompraVenta WHERE compraventa.IDNoExpediente = '" + AbrirExpediente.NoExpedinte + "'";        
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {    
                TipoCompraventa = rs.getString("TipoCompraVenta");
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
        a.jCBTipoCompraVenta.setSelectedItem(TipoCompraventa);       
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
    public void ObtenerDatosComprador()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        int contador=0;
        sSQL= "SELECT * FROM compraventacompradores WHERE IDNoExpediente = '" + AbrirExpediente.NoExpedinte + "'";
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                contador++;
            }
            compradoresdatos = new String [contador][4];
            if(contador>1)
            {
                for (int i = 0; i < contador-1; i++) 
                {              
                    AgregarComprador();
                }
            }
            int contador2=0;
            rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                tipoPersonaComprador = rs.getString("TipoCliente");
                nombreComprador = rs.getString("NombreComprador");
                apPaComprador = rs.getString("ApPaternoComprador");
                apMaComprador = rs.getString("ApMaternoComprador");
                compradoresdatos[contador2][0]=tipoPersonaComprador;
                compradoresdatos[contador2][1]=nombreComprador;
                compradoresdatos[contador2][2]=apPaComprador;
                compradoresdatos[contador2][3]=apMaComprador;
                System.out.println("Datos: " + compradoresdatos[contador2][0] + " " + compradoresdatos[contador2][1] + " " + compradoresdatos[contador2][2] + " " + compradoresdatos[contador2][3]);
                contador2++;
            }
            if(contador==1)
            {
                LlenarDatosComprador();
            }else
            {
                LlenarDatosComprador();
                LlenarDatosCompradores();
            }
        } catch (SQLException ex) {
            System.out.println("Error los datos del expedientes" + ex);
        }finally
        //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
        {
            mysql.desconectar();
        }
    }
        public void ObtenerDatosVendedor()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        int contador=0;
        sSQL= "SELECT * FROM compraventavendedores WHERE IDNoExpediente = '" + AbrirExpediente.NoExpedinte + "'";
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                contador++;
            }
            vendedoresdatos = new String [contador][4];
            if(contador>1)
            {
                for (int i = 0; i < contador-1; i++) 
                {              
                    AgregarVendedor();
                }
            }
            int contador2=0;
            rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                tipoPersonaVendedor = rs.getString("TipoCliente");
                nombreVendedor= rs.getString("NombreVendedor");
                apPaVendedor = rs.getString("ApPaternoVendedor");
                apMaVendedor = rs.getString("ApMaternoVendedor");
                vendedoresdatos[contador2][0]=tipoPersonaVendedor;
                vendedoresdatos[contador2][1]=nombreVendedor;
                vendedoresdatos[contador2][2]=apPaVendedor;
                vendedoresdatos[contador2][3]=apMaVendedor;
                System.out.println("Datos: " + vendedoresdatos[contador2][0] + " " + vendedoresdatos[contador2][1] + " " + vendedoresdatos[contador2][2] + " " + vendedoresdatos[contador2][3]);
                contador2++;
            }
            if(contador==1)
            {
                LlenarDatosVendedor();
            }else
            {
                LlenarDatosVendedor();
                LlenarDatosVendedores();
            }
        } catch (SQLException ex) {
            System.out.println("Error los datos del expedientes" + ex);
        }finally
        //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
        {
            mysql.desconectar();
        }
    }
        
    public void LlenarDatosComprador()
    {
        if(compradoresdatos[0][0].equals("Persona Física"))
        {
            a.jRBFisicaComprador.setSelected(true);
            a.jTNombreCompradorFisica.setText(compradoresdatos[0][1]);
            a.jTApPaCompradorFisica.setText(compradoresdatos[0][2]);
            a.jTApMaCompradorFisica.setText(compradoresdatos[0][3]);
        }else if(compradoresdatos[0][0].equals("Persona Moral"))
        {
            a.jRBMoralComprador.setSelected(true);
            a.jTNombreCompradorMoral.setText(compradoresdatos[0][1]);
            a.jLNombreFisicaComprador.setVisible(false);
            a.jLApMaCompradorFisica.setVisible(false);
            a.jLApPaCompradorFisica.setVisible(false);
            a.jTNombreCompradorFisica.setVisible(false);
            a.jTApMaCompradorFisica.setVisible(false);
            a.jTApPaCompradorFisica.setVisible(false);
            a.jLNombreMoralComprador.setVisible(true);
            a.jTNombreCompradorMoral.setVisible(true);
        }
    }
    public void LlenarDatosVendedor()
    {
        if(vendedoresdatos[0][0].equals("Persona Física"))
        {
            a.jRBFisicaVendedor.setSelected(true);
            a.jTNombreVendedorFisica.setText(vendedoresdatos[0][1]);
            a.jTApPaVendedorFisica.setText(vendedoresdatos[0][2]);
            a.jTApMaVendedorFisica.setText(vendedoresdatos[0][3]);
        }else if(vendedoresdatos[0][0].equals("Persona Moral"))
        {
            a.jRBMoralVendedor.setSelected(true);
            a.jTNombreVendedorMoral.setText(vendedoresdatos[0][1]);
            a.jLNombreFisicaVendedor.setVisible(false);
            a.jLApMaVendedorFisica.setVisible(false);
            a.jLApPaVendedorFisica.setVisible(false);
            a.jTNombreVendedorFisica.setVisible(false);
            a.jTApMaVendedorFisica.setVisible(false);
            a.jTApPaVendedorFisica.setVisible(false);
            a.jLNombreMoralVendedor.setVisible(true);
            a.jTNombreVendedorMoral.setVisible(true);
        }
    }
    public void LlenarDatosCompradores()
    {
        for (int i = 1; i <compradoresdatos.length ; i++) 
        {
                a.LlenarCompradores(i-1, compradoresdatos[i][0], compradoresdatos[i][1], compradoresdatos[i][2], compradoresdatos[i][3]);
        }
    }
    public void LlenarDatosVendedores()
    {
        for (int i = 1; i <vendedoresdatos.length ; i++) 
        {
                a.LlenarVendedores(i-1, vendedoresdatos[i][0], vendedoresdatos[i][1], vendedoresdatos[i][2], vendedoresdatos[i][3]);
        }
    }
    public void AgregarComprador()
    {
        a.AgregarComprador();
    }
    public void AgregarVendedor()
    {
        a.AgregarVendedor();
    }
}