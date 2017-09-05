package Controlador;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class PoderesActualizacion {

    public PoderesActualizacion() 
    {
    }
    String tipoPersonaQuienRecibe , nombreFisicaQuienRecibe , apPaFisicaQuienRecibe , apMaFisicaQuienRecibe , nombreMoralQuienRecibe ;
    String tipoPersonaOtorgante, nombreFisicaOtorgante, apPaFisicaOtorgante, apMaFisicaOtorgante, nombreMoralOtorgante;
    String observaciones;
    String sSQL="";
    int noExpediente= AbrirExpediente.getNoExpedinte();
    int IDCliente=1;
    int numeroquienesreciben;
    int numerootorgantes;
    String[][] quienesrecibendatos;
    String[][] otorgantesdatos;
    boolean validacionfinal=true;
    

    public void getQuienesReciben(String[][] quienesreciben)
    {
        this.quienesrecibendatos = quienesreciben;
    }
    public void getOtorgantes(String[][] otorgantes)
    {
        this.otorgantesdatos = otorgantes;
    }   

    public void getObservaciones(String observaciones)
    {
        this.observaciones = observaciones;
    }
    public void getNumeroQuienesReciben(int numeroquienesreciben)
    {
        this.numeroquienesreciben = numeroquienesreciben;
    }
    public void getNumeroOtorgantes(int numerootorgantes)
    {
        this.numerootorgantes = numerootorgantes;
    }
    public void guardarTablaAperturaCredito()
    {
        String mensaje ="Los datos de la tabla Poderes se han guardado correctamente";
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "UPDATE poderes SET Observaciones=? WHERE IDNoExpediente = '" + AbrirExpediente.getNoExpedinte() + "'";
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
                JOptionPane.showMessageDialog(null, "Hubo un error al Actualizar los datos en la tabla Poderes.");
                validacionfinal = false;
            }
        } catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! " + e);
            validacionfinal = false;
        }
    }
    public void guardarTablaQuienesReciben()
    {
        if(Vista.PoderesVisualizacion.jRBFisicaQuienRecibe.isSelected()==true)
        {
            tipoPersonaQuienRecibe=Vista.PoderesVisualizacion.jRBFisicaQuienRecibe.getText();
            nombreFisicaQuienRecibe= Vista.PoderesVisualizacion.jTNombreQuienRecibeFisica.getText();
            apPaFisicaQuienRecibe=Vista.PoderesVisualizacion.jTApPaQuienRecibeFisica.getText();
            apMaFisicaQuienRecibe=Vista.PoderesVisualizacion.jTApMaQuienRecibeFisica.getText();
        }
        else if(Vista.PoderesVisualizacion.jRBMoralQuienRecibe.isSelected())
        {
            tipoPersonaQuienRecibe = Vista.PoderesVisualizacion.jRBMoralQuienRecibe.getText();
            nombreMoralQuienRecibe = Vista.PoderesVisualizacion.jTNombreQuienRecibeMoral.getText();
        }  
        String mensaje ="Los datos de la tabla PODERES se han guardador correctamente";
        //------------------------------------------------------------------------------------        
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "INSERT INTO poderesquienrecibe(IDNoExpediente , TipoCliente, NombreQuienRecibe, ApPaternoQuienRecibe, ApMaternoQuienRecibe) VALUES (?, ?, ?, ?, ?)";        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, noExpediente);
            pst.setString(2, tipoPersonaQuienRecibe);
            if("Persona Física".equals(tipoPersonaQuienRecibe))
            {
                pst.setString(3, nombreFisicaQuienRecibe);
                pst.setString(4, apPaFisicaQuienRecibe);
                pst.setString(5, apMaFisicaQuienRecibe);
            }
            if("Persona Moral".equals(tipoPersonaQuienRecibe))
            {
                pst.setString(3, nombreMoralQuienRecibe);
                pst.setString(4, "");
                pst.setString(5, "");
            }
            int validacion = pst.executeUpdate();
            if(numeroquienesreciben>0)
            {
                for(int i=0; i<numeroquienesreciben; i++)
                {
                        
                                    pst.setInt(1, noExpediente);
                                    pst.setString(2, quienesrecibendatos[i][0]);
                                    System.out.println("Soy " + quienesrecibendatos[i][0]);
                                    String uno=quienesrecibendatos[i][1];
                                    String dos=quienesrecibendatos[i][2];
                                    String tres=quienesrecibendatos[i][3];
                                    if("Persona Física".equals(quienesrecibendatos[i][0]))
                                    {
                                        pst.setString(3, uno);
                                        pst.setString(4, dos);
                                        pst.setString(5, tres);
                                    }
                                    if("Persona Moral".equals(quienesrecibendatos[i][0]))
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
                                        JOptionPane.showMessageDialog(null, "Hubo un error al Actualizar los datos en la Tabla QUIENES RECIBEN Extras.");
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
                JOptionPane.showMessageDialog(null, "Hubo un error al Actualizar los datos en la Tabla QUIENES RECIBEN.");
                validacionfinal = false;
            }
        } catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! " + e);
            validacionfinal = false;
        }     
    }
    public void EliminarTablaQuienesReciben()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "DELETE FROM poderesquienrecibe WHERE IDNoExpediente = '" + AbrirExpediente.getNoExpedinte() + "'";
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
    public void guardarTablaOtorgantes()
    {
        if(Vista.PoderesVisualizacion.jRBFisicaOtorgante.isSelected()==true)
        {
            tipoPersonaOtorgante = Vista.PoderesVisualizacion.jRBFisicaOtorgante.getText();
            nombreFisicaOtorgante = Vista.PoderesVisualizacion.jTNombreOtorganteFisica.getText();
            apPaFisicaOtorgante = Vista.PoderesVisualizacion.jTApPaOtorganteFisica.getText();
            apMaFisicaOtorgante = Vista.PoderesVisualizacion.jTApMaOtorganteFisica.getText();
        }
        else if(Vista.PoderesVisualizacion.jRBMoralOtorgante.isSelected())
        {
            tipoPersonaOtorgante = Vista.PoderesVisualizacion.jRBMoralOtorgante.getText();
            nombreMoralOtorgante = Vista.PoderesVisualizacion.jTNombreOtorganteMoral.getText();
        } 
        String mensaje ="Los datos de la tabla OTORGANTES se han guardador correctamente";
        //------------------------------------------------------------------------------------        
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "INSERT INTO poderesotorgante(IDNoExpediente , TipoCliente, NombreOtorgante, ApPaternoOtorgante, ApMaternoOtorgante) VALUES (?, ?, ?, ?, ?)";        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, noExpediente);
            System.out.println("1 " + noExpediente);
            pst.setString(2, tipoPersonaOtorgante);
            System.out.println("2 " + tipoPersonaOtorgante);
            if("Persona Física".equals(tipoPersonaOtorgante))
            {
                pst.setString(3, nombreFisicaOtorgante);
                System.out.println("3 " + nombreFisicaOtorgante);
                pst.setString(4, apPaFisicaOtorgante);
                System.out.println("4 " + apPaFisicaOtorgante);
                pst.setString(5, apMaFisicaOtorgante);
                System.out.println("5 " + apMaFisicaOtorgante);
            }
            if("Persona Moral".equals(tipoPersonaOtorgante))
            {
                pst.setString(3, nombreMoralOtorgante);
                System.out.println("3 " + nombreMoralOtorgante);
                pst.setString(4, "");
                pst.setString(5, "");
            }
            int validacion = pst.executeUpdate();
            if(numerootorgantes>0)
            {
                for(int i=0; i<numerootorgantes; i++)
                {
                        
                                    pst.setInt(1, noExpediente);
                                    pst.setString(2, otorgantesdatos[i][0]);
                                    System.out.println("Soy " + otorgantesdatos[i][0]);
                                    String uno=otorgantesdatos[i][1];
                                    String dos=otorgantesdatos[i][2];
                                    String tres=otorgantesdatos[i][3];
                                    if("Persona Física".equals(otorgantesdatos[i][0]))
                                    {
                                        pst.setString(3, uno);
                                        pst.setString(4, dos);
                                        pst.setString(5, tres);
                                    }
                                    if("Persona Moral".equals(otorgantesdatos[i][0]))
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
                                        JOptionPane.showMessageDialog(null, "Hubo un error al Actualizar los datos en la Tabla OTORGANTES Extras.");
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
                JOptionPane.showMessageDialog(null, "Hubo un error al Actualizar los datos en la Tabla OTORGANTES.");
                validacionfinal = false;
            }
        } catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! en OTORGANTES " + e);
            validacionfinal = false;
        }     
    }
    public void EliminarTablaOtorgantes()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "DELETE FROM poderesotorgante WHERE IDNoExpediente = '" + AbrirExpediente.getNoExpedinte() + "'";
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
        EliminarTablaOtorgantes();
        EliminarTablaQuienesReciben();
        guardarTablaQuienesReciben();
        guardarTablaOtorgantes();
        if(validacionfinal == true)
        {
            JOptionPane.showMessageDialog(null, "¡Se han guardado todos los datos correctamente!");
        }else
        {
            JOptionPane.showMessageDialog(null, "NO se han podido completa TODAS las OPERACIONES");
        }
    }
}
