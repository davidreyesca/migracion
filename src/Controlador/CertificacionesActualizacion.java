package Controlador;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class CertificacionesActualizacion {

    public CertificacionesActualizacion() 
    {
    }
    String tipoPersonaNombre , nombreFisicaNombre , apPaFisicaNombre , apMaFisicaNombre , nombreMoralNombre ;
    String observaciones;
    String sSQL="";
    int noExpediente= AbrirExpediente.NoExpedinte;
    int IDCliente=1;
    int numeronombres;
    String[][] nombresdatos;
    boolean validacionfinal=true;
    

    public void getNombres(String[][] nombres)
    {
        this.nombresdatos = nombres;
    } 

    public void getObservaciones(String observaciones)
    {
        this.observaciones = observaciones;
    }
    public void getNumeroNombres(int numeronombres)
    {
        this.numeronombres = numeronombres;
    }
    public void guardarTablaCertificaciones()
    {
        String mensaje ="Los datos de la tabla Testamentos se han guardado correctamente";
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "UPDATE certificaciones SET Observaciones=? WHERE IDNoExpediente = '" + AbrirExpediente.getNoExpedinte() + "'";
        try 
        {
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
                JOptionPane.showMessageDialog(null, "Hubo un error al actualizar los datos en la tabla certificaciones.");
                validacionfinal = false;
            }
        } catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! " + e);
            validacionfinal = false;
        }
    }
    public void guardarTablaNombres()
    {
        if(Vista.CertificacionesVisualizacion.jRBFisicaNombre.isSelected()==true)
        {
            tipoPersonaNombre=Vista.CertificacionesVisualizacion.jRBFisicaNombre.getText();
            nombreFisicaNombre= Vista.CertificacionesVisualizacion.jTNombreNombreFisica.getText();
            apPaFisicaNombre=Vista.CertificacionesVisualizacion.jTApPaNombreFisica.getText();
            apMaFisicaNombre=Vista.CertificacionesVisualizacion.jTApMaNombreFisica.getText();
        }
        else if(Vista.CertificacionesVisualizacion.jRBMoralNombre.isSelected())
        {
            tipoPersonaNombre = Vista.CertificacionesVisualizacion.jRBMoralNombre.getText();
            nombreMoralNombre = Vista.CertificacionesVisualizacion.jTNombreNombreMoral.getText();
        }  
        String mensaje ="Los datos de la tabla ACREDITADOS se han guardador correctamente";
        //------------------------------------------------------------------------------------        
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "INSERT INTO certificacionesnombres(IDNoExpediente , TipoCliente, NombreNombre, ApPaternoNombre, ApMaternoNombre) VALUES (?, ?, ?, ?, ?)";        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, noExpediente);
            pst.setString(2, tipoPersonaNombre);
            if("Persona Física".equals(tipoPersonaNombre))
            {
                pst.setString(3, nombreFisicaNombre);
                pst.setString(4, apPaFisicaNombre);
                pst.setString(5, apMaFisicaNombre);
            }
            if("Persona Moral".equals(tipoPersonaNombre))
            {
                pst.setString(3, nombreMoralNombre);
                pst.setString(4, "");
                pst.setString(5, "");
            }
            int validacion = pst.executeUpdate();
            if(numeronombres>0)
            {
                for(int i=0; i<numeronombres; i++)
                {
                        
                                    pst.setInt(1, noExpediente);
                                    pst.setString(2, nombresdatos[i][0]);
                                    System.out.println("Soy " + nombresdatos[i][0]);
                                    String uno=nombresdatos[i][1];
                                    String dos=nombresdatos[i][2];
                                    String tres=nombresdatos[i][3];
                                    if("Persona Física".equals(nombresdatos[i][0]))
                                    {
                                        pst.setString(3, uno);
                                        pst.setString(4, dos);
                                        pst.setString(5, tres);
                                    }
                                    if("Persona Moral".equals(nombresdatos[i][0]))
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
                                        JOptionPane.showMessageDialog(null, "Hubo un error al actualizar los datos en la Tabla NOMBRES Extras.");
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
                JOptionPane.showMessageDialog(null, "Hubo un error al actualizar los datos en la Tabla NOMBRES.");
                validacionfinal = false;
            }
        } catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! " + e);
            validacionfinal = false;
        }     
    }
    public void EliminarTablaNombres()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "DELETE FROM certificacionesnombres WHERE IDNoExpediente = '" + AbrirExpediente.getNoExpedinte() + "'";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            int validacion = pst.executeUpdate();
            if (validacion>0) {
                System.out.println("¡Eliminado con exito!");  
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
        guardarTablaCertificaciones();
        EliminarTablaNombres();
        guardarTablaNombres();
        if(validacionfinal == true)
        {
            JOptionPane.showMessageDialog(null, "¡Se han actualizado todos los datos correctamente!");
        }else
        {
            JOptionPane.showMessageDialog(null, "NO se han podido completa TODAS las OPERACIONES");
        }
    }
}
