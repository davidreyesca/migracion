package Controlador;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class CancelacionHipotecaActualizacion {

    public CancelacionHipotecaActualizacion() 
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
    public void guardarTablaDonacion()
    {
        String mensaje ="Los datos de la tabla CANCELACION HIPOTECA se han guardado correctamente";
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "UPDATE cancelacionhipoteca SET Observaciones=? WHERE IDNoExpediente = '" + AbrirExpediente.getNoExpedinte() + "'";
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
                JOptionPane.showMessageDialog(null, "Hubo un error al actualizar los datos en la tabla CANCELACIÓN HIPOTECAS.");
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
        sSQL= "DELETE FROM cancelacionhipotecaacreditado WHERE IDNoExpediente = '" + AbrirExpediente.getNoExpedinte() + "'";
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
    public void guardarTablaAcreditados()
    {
        if(Vista.CancelacionHipotecaVisualizacion.jRBFisicaAcreditado.isSelected()==true)
        {
            tipoPersonaAcreditado=Vista.CancelacionHipotecaVisualizacion.jRBFisicaAcreditado.getText();
            nombreFisicaAcreditado= Vista.CancelacionHipotecaVisualizacion.jTNombreAcreditadoFisica.getText();
            apPaFisicaAcreditado=Vista.CancelacionHipotecaVisualizacion.jTApPaAcreditadoFisica.getText();
            apMaFisicaAcreditado=Vista.CancelacionHipotecaVisualizacion.jTApMaAcreditadoFisica.getText();
        }
        else if(Vista.CancelacionHipotecaVisualizacion.jRBMoralAcreditado.isSelected())
        {
            tipoPersonaAcreditado = Vista.CancelacionHipotecaVisualizacion.jRBMoralAcreditado.getText();
            nombreMoralAcreditado = Vista.CancelacionHipotecaVisualizacion.jTNombreAcreditadoMoral.getText();
        }  
        String mensaje ="Los datos de la tabla ACREDITADOS se han guardador correctamente";
        //------------------------------------------------------------------------------------        
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "INSERT INTO cancelacionhipotecaacreditado(IDNoExpediente , TipoCliente, NombreAcreditado, ApPaternoAcreditado, ApMaternoAcreditado) VALUES (?, ?, ?, ?, ?)";        
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
                JOptionPane.showMessageDialog(null, "Hubo un error al insertar los datos en la Tabla ACREDITADOS.");
                validacionfinal = false;
            }
        } catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! " + e);
            validacionfinal = false;
        }     
    }
    public void EliminarTablaAcreditantes()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "DELETE FROM cancelacionhipotecaacreditante WHERE IDNoExpediente = '" + AbrirExpediente.getNoExpedinte() + "'";
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
        if(Vista.CancelacionHipotecaVisualizacion.jRBFisicaAcreditante.isSelected()==true)
        {
            tipoPersonaAcreditante = Vista.CancelacionHipotecaVisualizacion.jRBFisicaAcreditante.getText();
            nombreFisicaAcreditante = Vista.CancelacionHipotecaVisualizacion.jTNombreAcreditanteFisica.getText();
            apPaFisicaAcreditante = Vista.CancelacionHipotecaVisualizacion.jTApPaAcreditanteFisica.getText();
            apMaFisicaAcreditante = Vista.CancelacionHipotecaVisualizacion.jTApMaAcreditanteFisica.getText();
        }
        else if(Vista.CancelacionHipotecaVisualizacion.jRBMoralAcreditante.isSelected())
        {
            tipoPersonaAcreditante = Vista.CancelacionHipotecaVisualizacion.jRBMoralAcreditante.getText();
            nombreMoralAcreditante = Vista.CancelacionHipotecaVisualizacion.jTNombreAcreditanteMoral.getText();
        } 
        String mensaje ="Los datos de la tabla ACREDITANTES se han guardador correctamente";
        //------------------------------------------------------------------------------------        
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "INSERT INTO cancelacionhipotecaacreditante(IDNoExpediente , TipoCliente, NombreAcreditante, ApPaternoAcreditante, ApMaternoAcreditante) VALUES (?, ?, ?, ?, ?)";        
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
                                        JOptionPane.showMessageDialog(null, "Hubo un error al insertar los datos en la Tabla ACREDITANTES Extras.");
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
    public void capturaFinal()
    {
        guardarTablaDonacion();
        EliminarTablaAcreditados();
        EliminarTablaAcreditantes();
        guardarTablaAcreditados();
        guardarTablaAcreditantes();
        if(validacionfinal == true)
        {
            JOptionPane.showMessageDialog(null, "¡Se han actualizado todos los datos correctamente!");
        }else
        {
            JOptionPane.showMessageDialog(null, "NO se han podido actualizar TODAS los DATOS");
        }
    }
}
