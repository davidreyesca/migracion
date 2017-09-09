package Controlador;

import static Vista.Donacion.*;
import Vista.EleccionTipoExpediente;
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
public class Donacion 
{
    String tipoPersonaDonatario, nombreFisicaDonatario, apPaFisicaDonatario, apMaFisicaDonatario, nombreMoralDonatario;
    String tipoPersonaDonante, nombreFisicaDonante, apPaFisicaDonante, apMaFisicaDonante, nombreMoralDonante;
    String tipoacto, calle, noexterior, nointerior, colonia, estado, municipio, observaciones;
    Date fecha;
    String sSQL="";
    int noExpediente= BDdocumentos.getNoExpediente();
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
        String mensaje ="Los datos de la tabla DONACIÓN se han guardador correctamente";
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "INSERT INTO donacion(IDNoExpediente, FolioReal, Instrumento, Tomo, Fecha, TipoActo, Calle, NoExterior, NoInterior, Colonia, Estado, Municipio, Observaciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, noExpediente);
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
                JOptionPane.showMessageDialog(null, "Hubo un error al insertar los datos.");
                validacionfinal = false;
            }
        } catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! en tabla DONACIÓN " + e);
            validacionfinal = false;
        }
    }
    public void guardarTablaDonatarios()
    {
        String mensaje ="Los datos de la tabla DONATARIOS se han guardador correctamente";
        if(jRBFisicaDonatario.isSelected()==true)
        {
            tipoPersonaDonatario = jRBFisicaDonatario.getText();
            nombreFisicaDonatario = jTNombreDonatarioFisica.getText();
            apPaFisicaDonatario = jTApPaDonatarioFisica.getText();
            apMaFisicaDonatario = jTApMaDonatarioFisica.getText();
        }
        else if (jRBMoralDonatario.isSelected()==true)
        {
            tipoPersonaDonatario = jRBMoralDonatario.getText();
            nombreMoralDonatario =jTNombreDonatarioMoral.getText();
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
                                        JOptionPane.showMessageDialog(null, "Hubo un error al insertar los datos en la Tabla DONATARIOS Extras.");
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
                JOptionPane.showMessageDialog(null, "Hubo un error al insertar los datos en la Tabla DONATARIOS.");
                validacionfinal = false;
            }
        } catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! en DONATARIOS " + e);
            validacionfinal = false;
        }     
    }
    public void guardarTablaDonantes()
    {
        String mensaje ="Los datos de la tabla DONANTES se han guardador correctamente";
        if(jRBFisicaDonante.isSelected()==true)
        {
            tipoPersonaDonante = jRBFisicaDonante.getText();
            nombreFisicaDonante = jTNombreDonanteFisica.getText();
            apPaFisicaDonante = jTApPaDonanteFisica.getText();
            apMaFisicaDonante = jTApMaDonanteFisica.getText();
        }
        else if(jRBMoralDonante.isSelected()==true)
        {
            tipoPersonaDonante = jRBMoralDonante.getText();
            nombreMoralDonante = jTNombreDonanteMoral.getText();
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
                                        JOptionPane.showMessageDialog(null, "Hubo un error al insertar los datos en la Tabla DONANTES Extras.");
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
                JOptionPane.showMessageDialog(null, "Hubo un error al insertar los datos en la Tabla DONANTES.");
                validacionfinal = false;
            }
        } catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! en DONANTES " + e);
            validacionfinal = false;
        }     
    }
    public void capturaFinal()
    {
        guardarTablaDonacion();
        guardarTablaDonatarios();
        guardarTablaDonantes();
        if(validacionfinal == true)
        {
            JOptionPane.showMessageDialog(null, "Se han guardado todos los datos correctamente!");
            EleccionTipoExpediente.cerrarDonacion(1);
        }else
        {
            JOptionPane.showMessageDialog(null, "NO se han podido completa TODAS las OPERACIONES");
            EleccionTipoExpediente.cerrarDonacion(1);
        }
    }
}
