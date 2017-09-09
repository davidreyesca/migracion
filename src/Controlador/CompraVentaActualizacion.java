package Controlador;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;


public class CompraVentaActualizacion {

    public CompraVentaActualizacion() 
    {
    }
    String tipoPersonaComprador, nombreFisicaComprador, apPaFisicaComprador, apMaFisicaComprador, nombreMoralComprador;
    String tipoPersonaVendedor, nombreFisicaVendedor, apPaFisicaVendedor, apMaFisicaVendedor, nombreMoralVendedor;
    String tipoacto, calle, noexterior, nointerior, colonia, estado, municipio, observaciones;
    Date fecha;
    String sSQL="";
    int noExpediente= AbrirExpediente.getNoExpedinte();
    int IDCliente=1;
    int folioReal, instrumento, tomo, IDTipoCompraventa;
    int numerocompradores;
    int numerovendedores;
    String[][] compradoresdatos;
    String[][] vendedoresdatos;
    boolean validacionfinal=true;
    

    public void getCompradores(String[][] compradores)
    {
        this.compradoresdatos = compradores;
    }
    public void getVendedores(String[][] vendedores)
    {
        this.vendedoresdatos = vendedores;
    }   
    public void getFolioReal(int folio)
    {
        this.folioReal = folio;
    }
    public void getIDTipoCompraventa(int IDTipoCompraventa)
    {
        this.IDTipoCompraventa = IDTipoCompraventa;
    }
    public void getInstrumento(int instrumento)
    {
        this.instrumento = instrumento;
    }
    public void getTomo(int tomo)
    {
        this.tomo = tomo;
    }
    public void getFecha(Date fecha)
    {
        this.fecha = fecha;
    }
        public void getTipoActo(String tipoacto)
    {
        this.tipoacto = tipoacto;
    }
        public void getCalle(String calle)
    {
        this.calle = calle;
    }    
        public void getNoExteriror(String noexterior)
    {
        this.noexterior = noexterior;
    }    
        public void getNoInterior(String nointerior)
    {
        this.nointerior = nointerior;
    }    
        public void getColonia(String colonia)
    {
        this.colonia = colonia;
    }    
        public void getEstado(String estado)
    {
        this.estado = estado;
    }    
        public void getMunicipio(String municipio)
    {
        this.municipio = municipio;
    }    
        public void getObservaciones(String observaciones)
    {
        this.observaciones = observaciones;
    }
    public void getNumeroCompradores(int numerocompradores)
    {
            this.numerocompradores = numerocompradores;
    }
    public void getNumeroVendedores(int numerovendedores)
    {
            this.numerovendedores = numerovendedores;
    }
    public void guardarTablaCompraVenta()
    {
        String mensaje ="Los datos de la tabla COMPRA-VENTA se han guardado correctamente";
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "UPDATE compraventa SET IDTipoCompraVenta=?, FolioReal=?,Instrumento=?, Tomo=?, Fecha=?, TipoActo=?, Calle=?, NoExterior=?, NoInterior=?, Colonia=?, Estado=?, Municipio=?, Observaciones=? WHERE IDNoExpediente = '" + AbrirExpediente.getNoExpedinte() + "'";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, IDTipoCompraventa);
            pst.setInt(2, folioReal);
            pst.setInt(3, instrumento);
            pst.setInt(4, tomo);
            pst.setDate(5, new java.sql.Date(fecha.getTime()));
            pst.setString(6, tipoacto);
            pst.setString(7, calle);
            pst.setString(8, noexterior);
            pst.setString(9, nointerior);
            pst.setString(10, colonia);
            pst.setString(11, estado);
            pst.setString(12, municipio);
            pst.setString(13, observaciones);
            int validacion = pst.executeUpdate();
            if (validacion>0) {
                System.out.println(mensaje);
                validacionfinal = true;
                mysql.desconectar();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Hubo un error al ACTUALIZAR los datos.");
                validacionfinal = false;
            }
        } catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! " + e);
            validacionfinal = false;
        }
    }
    public void EliminarTablaCompradores()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "DELETE FROM compraventacompradores WHERE IDNoExpediente = '" + AbrirExpediente.getNoExpedinte() + "'";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            int validacion = pst.executeUpdate();
            if (validacion>0) {
                System.out.println("Eliminado con exito!");  
                validacionfinal = true;
                mysql.desconectar();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Hubo un error al ACTUALIZAR los datos.");
                validacionfinal = false;
            }
        } catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! " + e);
            validacionfinal = false;
        }
    }
    public void guardarTablaCompradores()
    {
        System.out.println("Entre a compradores :D");
        if(Vista.CompraVentaVisualizacion.jRBFisicaComprador.isSelected()==true)
        {
            tipoPersonaComprador=Vista.CompraVentaVisualizacion.jRBFisicaComprador.getText();
            nombreFisicaComprador= Vista.CompraVentaVisualizacion.jTNombreCompradorFisica.getText();
            apPaFisicaComprador= Vista.CompraVentaVisualizacion.jTApPaCompradorFisica.getText();
            apMaFisicaComprador=Vista.CompraVentaVisualizacion.jTApMaCompradorFisica.getText();
        }
        else if(Vista.CompraVentaVisualizacion.jRBMoralComprador.isSelected())
        {
            tipoPersonaComprador = Vista.CompraVentaVisualizacion.jRBMoralComprador.getText();
            nombreMoralComprador = Vista.CompraVentaVisualizacion.jTNombreCompradorMoral.getText();
        }
        String mensaje ="Los datos de la tabla COMPRADORES se han guardador correctamente";
        //------------------------------------------------------------------------------------        
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        System.out.println("Llegue a primera base ;) ");
        sSQL= "INSERT INTO compraventacompradores(IDNoExpediente , TipoCliente, NombreComprador, ApPaternoComprador, ApMaternoComprador) VALUES (?, ?, ?, ?, ?)";        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, noExpediente);
            pst.setString(2, tipoPersonaComprador);
            if("Persona Física".equals(tipoPersonaComprador))
            {
                pst.setString(3, nombreFisicaComprador);
                pst.setString(4, apPaFisicaComprador);
                pst.setString(5, apMaFisicaComprador);
            }
            if("Persona Moral".equals(tipoPersonaComprador))
            {
                pst.setString(3, nombreMoralComprador);
                pst.setString(4, "");
                pst.setString(5, "");
            }
            int validacion = pst.executeUpdate();
            if(numerocompradores>0)
            {
                for(int i=0; i<numerocompradores; i++)
                {
                        
                                    pst.setInt(1, noExpediente);
                                    pst.setString(2, compradoresdatos[i][0]);
                                    System.out.println("Soy " + compradoresdatos[i][0]);
                                    String uno=compradoresdatos[i][1];
                                    String dos=compradoresdatos[i][2];
                                    String tres=compradoresdatos[i][3];
                                    if("Persona Física".equals(compradoresdatos[i][0]))
                                    {
                                        pst.setString(3, uno);
                                        pst.setString(4, dos);
                                        pst.setString(5, tres);
                                    }
                                    if("Persona Moral".equals(compradoresdatos[i][0]))
                                    {
                                        pst.setString(3, uno);
                                        pst.setString(4, "");
                                        pst.setString(5, "");
                                    }
                                    int validacion2 = pst.executeUpdate();
                                    
                                    if (validacion2>0) {
                                        System.out.println("validacion: " + validacion2);
                                        validacionfinal = true;
                                    }
                                    else
                                    {
                                        JOptionPane.showMessageDialog(null, "Hubo un error al insertar los datos en la Tabla COMPRADORES Extras.");
                                        validacionfinal = false;
                                    }                                   
                }
            }        
            if (validacion>0) {
                System.out.println(mensaje);
                validacionfinal = true;
                mysql.desconectar();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Hubo un error al insertar los datos en la Tabla COMPRADORES.");
                validacionfinal = false;
            }
        } catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! " + e);
            validacionfinal = false;
        }     
    }
    public void EliminarTablaVendedores()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "DELETE FROM compraventavendedores WHERE IDNoExpediente = '" + AbrirExpediente.getNoExpedinte() + "'";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            int validacion = pst.executeUpdate();
            if (validacion>0) {
                System.out.println("Eliminado con exito!");           
                validacionfinal = true;
                mysql.desconectar();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Hubo un error al ACTUALIZAR los datos.");
                validacionfinal = false;
            }
        } catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! " + e);
            validacionfinal = false;
        }
    }
    public void guardarTablaVendedores()
    {
        if(Vista.CompraVentaVisualizacion.jRBFisicaVendedor.isSelected()==true)
        {
            tipoPersonaVendedor = Vista.CompraVentaVisualizacion.jRBFisicaVendedor.getText();
            nombreFisicaVendedor = Vista.CompraVentaVisualizacion.jTNombreVendedorFisica.getText();
            apPaFisicaVendedor = Vista.CompraVentaVisualizacion.jTApPaVendedorFisica.getText();
            apMaFisicaVendedor = Vista.CompraVentaVisualizacion.jTApMaVendedorFisica.getText();
        }
        else if(Vista.CompraVentaVisualizacion.jRBMoralVendedor.isSelected())
        {
            tipoPersonaVendedor = Vista.CompraVentaVisualizacion.jRBMoralVendedor.getText();
            nombreMoralVendedor = Vista.CompraVentaVisualizacion.jTNombreVendedorMoral.getText();
        } 
        String mensaje ="Los datos de la tabla VENDEDORES se han guardador correctamente";
        //------------------------------------------------------------------------------------        
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "INSERT INTO compraventavendedores(IDNoExpediente , TipoCliente, NombreVendedor, ApPaternoVendedor, ApMaternoVendedor) VALUES (?, ?, ?, ?, ?)";        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, noExpediente);
            System.out.println("1 " + noExpediente);
            pst.setString(2, tipoPersonaVendedor);
            System.out.println("2 " + tipoPersonaVendedor);
            if("Persona Física".equals(tipoPersonaVendedor))
            {
                pst.setString(3, nombreFisicaVendedor);
                System.out.println("3 " + nombreFisicaVendedor);
                pst.setString(4, apPaFisicaVendedor);
                System.out.println("4 " + apPaFisicaVendedor);
                pst.setString(5, apMaFisicaVendedor);
                System.out.println("5 " + apMaFisicaVendedor);
            }
            if("Persona Moral".equals(tipoPersonaVendedor))
            {
                pst.setString(3, nombreMoralVendedor);
                System.out.println("3 " + nombreMoralVendedor);
                pst.setString(4, "");
                pst.setString(5, "");
            }
            int validacion = pst.executeUpdate();
            if(numerovendedores>0)
            {
                for(int i=0; i<numerovendedores; i++)
                {
                        
                                    pst.setInt(1, noExpediente);
                                    pst.setString(2, vendedoresdatos[i][0]);
                                    System.out.println("Soy " + vendedoresdatos[i][0]);
                                    String uno=vendedoresdatos[i][1];
                                    String dos=vendedoresdatos[i][2];
                                    String tres=vendedoresdatos[i][3];
                                    if("Persona Física".equals(vendedoresdatos[i][0]))
                                    {
                                        pst.setString(3, uno);
                                        pst.setString(4, dos);
                                        pst.setString(5, tres);
                                    }
                                    if("Persona Moral".equals(vendedoresdatos[i][0]))
                                    {
                                        pst.setString(3, uno);
                                        pst.setString(4, "");
                                        pst.setString(5, "");
                                    }
                                    int validacion2 = pst.executeUpdate();
                                    
                                    if (validacion2>0) {
                                        System.out.println("validacion: " + validacion2);
                                        validacionfinal = true;
                                    }
                                    else
                                    {
                                        JOptionPane.showMessageDialog(null, "Hubo un error al insertar los datos en la Tabla VENDEDORES Extras.");
                                        validacionfinal = false;
                                    }                                   
                }
            } 
            if (validacion>0) {
                System.out.println(mensaje);
                validacionfinal = true;
                mysql.desconectar();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Hubo un error al insertar los datos en la Tabla VENDEDORES.");
                validacionfinal = false;
            }
        } catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! en VENDEDORES " + e);
            validacionfinal = false;
        }     
    }
    public void capturaFinal()
    {
        guardarTablaCompraVenta();
        EliminarTablaCompradores();
        EliminarTablaVendedores();
        guardarTablaCompradores();
        guardarTablaVendedores();
        if(validacionfinal == true)
        {
            JOptionPane.showMessageDialog(null, "¡Se han actualizado todos los datos correctamente!");
        }else
        {
            JOptionPane.showMessageDialog(null, "NO se han podido actualizar TODAS las OPERACIONES");
        }
    }
}
