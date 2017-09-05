package Controlador;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class AperturaCreditoActualizacion {

    public AperturaCreditoActualizacion() 
    {
    }
    String tipoPersonaAcreditado , nombreFisicaAcreditado , apPaFisicaAcreditado , apMaFisicaAcreditado , nombreMoralAcreditado ;
    String tipoPersonaAcreditante, nombreFisicaAcreditante, apPaFisicaAcreditante, apMaFisicaAcreditante, nombreMoralAcreditante;
    String observaciones;
    String sSQL="";
    int noExpediente= AbrirExpediente.getNoExpedinte();
    int IDCliente=1;
    int numeroacreditados;
    int numeroacreditantes;
    String[][] acreditadosdatos;
    String[][] acreditantesdatos;
    boolean validacionfinal=true;
    

    public void getAcreditados(String[][] acreditados)
    {
        this.acreditadosdatos = acreditados;
    }
    public void getAcreditantes(String[][] acreditantes)
    {
        this.acreditantesdatos = acreditantes;
    }   

    public void getObservaciones(String observaciones)
    {
        this.observaciones = observaciones;
    }
    public void getNumeroAcreditados(int numeroacreditados)
    {
        this.numeroacreditados = numeroacreditados;
    }
    public void getNumeroAcreditantes(int numeroacreditantes)
    {
        this.numeroacreditantes = numeroacreditantes;
    }
    public void guardarTablaAperturaCredito()
    {
        String mensaje ="Los datos de la tabla APERTURA CREDITO se han actualizado correctamente";
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "UPDATE aperturacredito SET Observaciones=? WHERE IDNoExpediente = '" + AbrirExpediente.getNoExpedinte() + "'";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, observaciones);
            int validacion = pst.executeUpdate();
            if (validacion>0) 
            {
                System.out.println(mensaje);
                validacionfinal = true;
                mysql.desconectar();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Hubo un error al actualizar los datos en la tabla Apertura credito.");
                validacionfinal = false;
            }
        } catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! " + e);
            validacionfinal = false;
        }
    }
    public void guardarTablaAcreditados()
    {
        if(Vista.AperturaCreditoVisualizacion.jRBFisicaAcreditado.isSelected()==true)
        {
            tipoPersonaAcreditado=Vista.AperturaCreditoVisualizacion.jRBFisicaAcreditado.getText();
            nombreFisicaAcreditado= Vista.AperturaCreditoVisualizacion.jTNombreAcreditadoFisica.getText();
            apPaFisicaAcreditado=Vista.AperturaCreditoVisualizacion.jTApPaAcreditadoFisica.getText();
            apMaFisicaAcreditado=Vista.AperturaCreditoVisualizacion.jTApMaAcreditadoFisica.getText();
        }
        else if(Vista.AperturaCreditoVisualizacion.jRBMoralAcreditado.isSelected())
        {
            tipoPersonaAcreditado = Vista.AperturaCreditoVisualizacion.jRBMoralAcreditado.getText();
            nombreMoralAcreditado = Vista.AperturaCreditoVisualizacion.jTNombreAcreditadoMoral.getText();
        }  
        String mensaje ="Los datos de la tabla ACREDITADOS se han actualizado correctamente";
        //------------------------------------------------------------------------------------        
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "INSERT INTO aperturacreditoacreditado(IDNoExpediente , TipoCliente, NombreAcreditado, ApPaternoAcreditado, ApMaternoAcreditado) VALUES (?, ?, ?, ?, ?)";        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, noExpediente);
            pst.setString(2, tipoPersonaAcreditado);
            if("Persona Física".equals(tipoPersonaAcreditado))
            {
                pst.setString(3, nombreFisicaAcreditado);
                pst.setString(4, apPaFisicaAcreditado);
                pst.setString(5, apMaFisicaAcreditado);
            }
            if("Persona Moral".equals(tipoPersonaAcreditado))
            {
                pst.setString(3, nombreMoralAcreditado);
                pst.setString(4, "");
                pst.setString(5, "");
            }
            int validacion = pst.executeUpdate();
            if(numeroacreditados>0)
            {
                for(int i=0; i<numeroacreditados; i++)
                {
                        
                                    pst.setInt(1, noExpediente);
                                    pst.setString(2, acreditadosdatos[i][0]);
                                    System.out.println("Soy " + acreditadosdatos[i][0]);
                                    String uno=acreditadosdatos[i][1];
                                    String dos=acreditadosdatos[i][2];
                                    String tres=acreditadosdatos[i][3];
                                    if("Persona Física".equals(acreditadosdatos[i][0]))
                                    {
                                        pst.setString(3, uno);
                                        pst.setString(4, dos);
                                        pst.setString(5, tres);
                                    }
                                    if("Persona Moral".equals(acreditadosdatos[i][0]))
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
                                        JOptionPane.showMessageDialog(null, "Hubo un error al actualizar los datos en la Tabla ACREDITADOS Extras.");
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
                JOptionPane.showMessageDialog(null, "Hubo un error al actualizar los datos en la Tabla ACREDITADOS.");
                validacionfinal = false;
            }
        } catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! " + e);
            validacionfinal = false;
        }     
    }
    public void EliminarTablaAcreditados()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "DELETE FROM aperturacreditoacreditado WHERE IDNoExpediente = '" + AbrirExpediente.getNoExpedinte() + "'";
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
    public void guardarTablaAcreditantes()
    {
        if(Vista.AperturaCreditoVisualizacion.jRBFisicaAcreditante.isSelected()==true)
        {
            tipoPersonaAcreditante = Vista.AperturaCreditoVisualizacion.jRBFisicaAcreditante.getText();
            nombreFisicaAcreditante = Vista.AperturaCreditoVisualizacion.jTNombreAcreditanteFisica.getText();
            apPaFisicaAcreditante = Vista.AperturaCreditoVisualizacion.jTApPaAcreditanteFisica.getText();
            apMaFisicaAcreditante = Vista.AperturaCreditoVisualizacion.jTApMaAcreditanteFisica.getText();
        }
        else if(Vista.AperturaCreditoVisualizacion.jRBMoralAcreditante.isSelected())
        {
            tipoPersonaAcreditante = Vista.AperturaCreditoVisualizacion.jRBMoralAcreditante.getText();
            nombreMoralAcreditante = Vista.AperturaCreditoVisualizacion.jTNombreAcreditanteMoral.getText();
        } 
        String mensaje ="Los datos de la tabla ACREDITANTES se han actualizado correctamente";
        //------------------------------------------------------------------------------------        
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "INSERT INTO aperturacreditoacreditante(IDNoExpediente , TipoCliente, NombreAcreditante, ApPaternoAcreditante, ApMaternoAcreditante) VALUES (?, ?, ?, ?, ?)";        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, noExpediente);
            System.out.println("1 " + noExpediente);
            pst.setString(2, tipoPersonaAcreditante);
            System.out.println("2 " + tipoPersonaAcreditante);
            if("Persona Física".equals(tipoPersonaAcreditante))
            {
                pst.setString(3, nombreFisicaAcreditante);
                System.out.println("3 " + nombreFisicaAcreditante);
                pst.setString(4, apPaFisicaAcreditante);
                System.out.println("4 " + apPaFisicaAcreditante);
                pst.setString(5, apMaFisicaAcreditante);
                System.out.println("5 " + apMaFisicaAcreditante);
            }
            if("Persona Moral".equals(tipoPersonaAcreditante))
            {
                pst.setString(3, nombreMoralAcreditante);
                System.out.println("3 " + nombreMoralAcreditante);
                pst.setString(4, "");
                pst.setString(5, "");
            }
            int validacion = pst.executeUpdate();
            if(numeroacreditantes>0)
            {
                for(int i=0; i<numeroacreditantes; i++)
                {
                        
                                    pst.setInt(1, noExpediente);
                                    pst.setString(2, acreditantesdatos[i][0]);
                                    System.out.println("Soy " + acreditantesdatos[i][0]);
                                    String uno=acreditantesdatos[i][1];
                                    String dos=acreditantesdatos[i][2];
                                    String tres=acreditantesdatos[i][3];
                                    if("Persona Física".equals(acreditantesdatos[i][0]))
                                    {
                                        pst.setString(3, uno);
                                        pst.setString(4, dos);
                                        pst.setString(5, tres);
                                    }
                                    if("Persona Moral".equals(acreditantesdatos[i][0]))
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
                                        JOptionPane.showMessageDialog(null, "Hubo un error al actualizar los datos en la Tabla ACREDITANTES Extras.");
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
                JOptionPane.showMessageDialog(null, "Hubo un error al actualizar los datos en la Tabla ACREDITANTES.");
                validacionfinal = false;
            }
        } catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! en ACREDITANTES " + e);
            validacionfinal = false;
        }     
    }
     public void EliminarTablaAcreditantes()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "DELETE FROM  aperturacreditoacreditante WHERE IDNoExpediente = '" + AbrirExpediente.getNoExpedinte() + "'";
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
    public void capturaFinal()
    {
        guardarTablaAperturaCredito();
        EliminarTablaAcreditantes();
        EliminarTablaAcreditados();
        guardarTablaAcreditados();
        guardarTablaAcreditantes();
        if(validacionfinal == true)
        {
            JOptionPane.showMessageDialog(null, "¡Se han actualizado todos los datos correctamente!");
        }else
        {
            JOptionPane.showMessageDialog(null, "NO se han podido completa TODAS las OPERACIONES");
        }
    }
}
