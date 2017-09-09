package Controlador;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author David Reyes
 */
public class DonacionActualizacion 
{
    String tipoPersonaDonatario, nombreFisicaDonatario, apPaFisicaDonatario, apMaFisicaDonatario, nombreMoralDonatario;
    String tipoPersonaDonante, nombreFisicaDonante, apPaFisicaDonante, apMaFisicaDonante, nombreMoralDonante;
    String tipoacto, calle, noexterior, nointerior, colonia, estado, municipio, observaciones;
    Date fecha;
    String sSQL="";
    int noExpediente= AbrirExpediente.getNoExpedinte();
    int IDCliente=1;
    int folioReal, instrumento, tomo;
    int numerodonatarios;
    int numerodonantes;
    String[][] donatariosdatos;
    String[][] donantesdatos;
    boolean validacionfinal=true;
    

     
    public void getDonatarios(String[][] donatarios)
    {
        this.donatariosdatos = donatarios;
    }  
    public void getDonantes(String[][] donantes)
    {
        this.donantesdatos = donantes;
    }    
    public void getFolioReal(int folio)
    {
        this.folioReal = folio;
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
    public void getNumeroDonatarios(int numerodonatarios)
    {
            this.numerodonatarios = numerodonatarios;
    }
    public void getNumeroDonantes(int numerodonantes)
    {
            this.numerodonantes = numerodonantes;
    }
    public void guardarTablaDonacion()
    {
        String mensaje ="Los datos de la tabla DONACIÓN se han actualizado correctamente";
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "UPDATE donacion SET FolioReal=?, Instrumento=?, Tomo=?, Fecha=?, TipoActo=?, Calle=?, NoExterior=?, NoInterior=?, Colonia=?, Estado=?, Municipio=?, Observaciones=? WHERE IDNoExpediente = '" + AbrirExpediente.getNoExpedinte() + "'";       
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, folioReal);
            pst.setInt(2, instrumento);
            pst.setInt(3, tomo);
            pst.setDate(4, new java.sql.Date(fecha.getTime()));
            pst.setString(5, tipoacto);
            pst.setString(6, calle);
            pst.setString(7, noexterior);
            pst.setString(8, nointerior);
            pst.setString(9, colonia);
            pst.setString(10, estado);
            pst.setString(11, municipio);
            pst.setString(12, observaciones);
            int validacion = pst.executeUpdate();
            if (validacion>0) {
                System.out.println(mensaje);
                validacionfinal = true;
                mysql.desconectar();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Hubo un error al actualizar los datos.");
                validacionfinal = false;
            }
        } catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! en actualización de la tabla DONACIÓN " + e);
            validacionfinal = false;
        }
    }
    public void guardarTablaDonatarios()
    {
        String mensaje ="Los datos de la tabla DONATARIOS se han actualizado correctamente";
        if(Vista.DonacionVisualizacion.jRBFisicaDonatario.isSelected()==true)
        {
            tipoPersonaDonatario = Vista.DonacionVisualizacion.jRBFisicaDonatario.getText();
            nombreFisicaDonatario = Vista.DonacionVisualizacion.jTNombreDonatarioFisica.getText();
            apPaFisicaDonatario = Vista.DonacionVisualizacion.jTApPaDonatarioFisica.getText();
            apMaFisicaDonatario = Vista.DonacionVisualizacion.jTApMaDonatarioFisica.getText();
        }
        else if (Vista.DonacionVisualizacion.jRBMoralDonatario.isSelected()==true)
        {
            tipoPersonaDonatario = Vista.DonacionVisualizacion.jRBMoralDonatario.getText();
            nombreMoralDonatario =Vista.DonacionVisualizacion.jTNombreDonatarioMoral.getText();
        }
        //------------------------------------------------------------------------------------        
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "INSERT INTO donaciondonatarios(IDNoExpediente , TipoCliente, NombreDonatario, ApPaternoDonatario, ApMaternoDonatario) VALUES (?, ?, ?, ?, ?)";        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, noExpediente);
            pst.setString(2, tipoPersonaDonatario);
            if("Persona Física".equals(tipoPersonaDonatario))
            {
                pst.setString(3, nombreFisicaDonatario);
                pst.setString(4, apPaFisicaDonatario);
                pst.setString(5, apMaFisicaDonatario);
            }
            if("Persona Moral".equals(tipoPersonaDonatario))
            {
                pst.setString(3, nombreMoralDonatario);
                pst.setString(4, "");
                pst.setString(5, "");
            }
            int validacion = pst.executeUpdate();
            if(numerodonatarios>0)
            {
                for(int i=0; i<numerodonatarios; i++)
                {
                        
                                    pst.setInt(1, noExpediente);
                                    pst.setString(2, donatariosdatos[i][0]);
                                    System.out.println("Soy " + donatariosdatos[i][0]);
                                    String uno=donatariosdatos[i][1];
                                    String dos=donatariosdatos[i][2];
                                    String tres=donatariosdatos[i][3];
                                    if("Persona Física".equals(donatariosdatos[i][0]))
                                    {
                                        pst.setString(3, uno);
                                        pst.setString(4, dos);
                                        pst.setString(5, tres);
                                    }
                                    if("Persona Moral".equals(donatariosdatos[i][0]))
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
                                        JOptionPane.showMessageDialog(null, "Hubo un error al actualizar los datos en la Tabla DONATARIOS Extras.");
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
                JOptionPane.showMessageDialog(null, "Hubo un error al actualizar los datos en la Tabla DONATARIOS.");
                validacionfinal = false;
            }
        } catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! en DONATARIOS " + e);
            validacionfinal = false;
        }     
    }
    public void EliminarTablaDonatarios()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "DELETE FROM donaciondonatarios WHERE IDNoExpediente = '" + AbrirExpediente.getNoExpedinte() + "'";
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
                JOptionPane.showMessageDialog(null, "Hubo un error al Eliminar los datos.");
                validacionfinal = false;
            }
        } catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! " + e);
            validacionfinal = false;
        }
    }
    public void guardarTablaDonantes()
    {
        String mensaje ="Los datos de la tabla DONANTES se han Actualizado correctamente";
        if(Vista.DonacionVisualizacion.jRBFisicaDonante.isSelected()==true)
        {
            tipoPersonaDonante = Vista.DonacionVisualizacion.jRBFisicaDonante.getText();
            nombreFisicaDonante = Vista.DonacionVisualizacion.jTNombreDonanteFisica.getText();
            apPaFisicaDonante = Vista.DonacionVisualizacion.jTApPaDonanteFisica.getText();
            apMaFisicaDonante = Vista.DonacionVisualizacion.jTApMaDonanteFisica.getText();
        }
        else if(Vista.DonacionVisualizacion.jRBMoralDonante.isSelected()==true)
        {
            tipoPersonaDonante = Vista.DonacionVisualizacion.jRBMoralDonante.getText();
            nombreMoralDonante = Vista.DonacionVisualizacion.jTNombreDonanteMoral.getText();
        }
        //------------------------------------------------------------------------------------        
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "INSERT INTO donaciondonantes(IDNoExpediente , TipoCliente, NombreDonante, ApPaternoDonante, ApMaternoDonante) VALUES (?, ?, ?, ?, ?)";        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, noExpediente);
            pst.setString(2, tipoPersonaDonante);
            if("Persona Física".equals(tipoPersonaDonante))
            {
                pst.setString(3, nombreFisicaDonante);
                pst.setString(4, apPaFisicaDonante);
                pst.setString(5, apMaFisicaDonante);
            }
            if("Persona Moral".equals(tipoPersonaDonante))
            {
                pst.setString(3, nombreMoralDonante);
                pst.setString(4, "");
                pst.setString(5, "");
            }
            int validacion = pst.executeUpdate();
            if(numerodonantes>0)
            {
                for(int i=0; i<numerodonantes; i++)
                {
                        
                                    pst.setInt(1, noExpediente);
                                    pst.setString(2, donantesdatos[i][0]);
                                    System.out.println("Soy " + donantesdatos[i][0]);
                                    String uno=donantesdatos[i][1];
                                    String dos=donantesdatos[i][2];
                                    String tres=donantesdatos[i][3];
                                    if("Persona Física".equals(donantesdatos[i][0]))
                                    {
                                        pst.setString(3, uno);
                                        pst.setString(4, dos);
                                        pst.setString(5, tres);
                                    }
                                    if("Persona Moral".equals(donantesdatos[i][0]))
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
                                        JOptionPane.showMessageDialog(null, "Hubo un error al actualizar los datos en la Tabla DONANTES Extras.");
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
                JOptionPane.showMessageDialog(null, "Hubo un error al actualizar los datos en la Tabla DONANTES.");
                validacionfinal = false;
            }
        } catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! en DONANTES " + e);
            validacionfinal = false;
        }     
    }
    public void EliminarTablaDonantes()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "DELETE FROM donaciondonantes WHERE IDNoExpediente = '" + AbrirExpediente.getNoExpedinte() + "'";
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
                JOptionPane.showMessageDialog(null, "Hubo un error al Eliminar los datos.");
                validacionfinal = false;
            }
        } catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! " + e);
            validacionfinal = false;
        }
    }
    public void capturaFinal()
    {
        guardarTablaDonacion();
        EliminarTablaDonatarios();
        EliminarTablaDonantes();
        guardarTablaDonatarios();
        guardarTablaDonantes();
        if(validacionfinal == true)
        {
            JOptionPane.showMessageDialog(null, "¡Se han Actualizado todos los datos correctamente!");
        }else
        {
            JOptionPane.showMessageDialog(null, "NO se han podido completa TODAS las OPERACIONES");
        }
    }
}
