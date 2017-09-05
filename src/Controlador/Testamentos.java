package Controlador;
import static Vista.Testamentos.*;
import Vista.EleccionTipoExpediente;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Testamentos {

    public Testamentos() 
    {
    }
    String tipoPersonaOtorgante , nombreFisicaOtorgante , apPaFisicaOtorgante , apMaFisicaOtorgante , nombreMoralOtorgante ;
    String observaciones;
    String sSQL="";
    int noExpediente= BDdocumentos.getNoExpediente();
    int IDCliente=1, folio;
    int numerootorgantes;
    String[][] otorgantesdatos;
    boolean validacionfinal=true;
    

    public void getOtorgantes(String[][] otorgantes)
    {
        this.otorgantesdatos = otorgantes;
    } 

    public void getFolio(int folio)
    {
        this.folio = folio;
    }
    public void getObservaciones(String observaciones)
    {
        this.observaciones = observaciones;
    }
    public void getNumeroOtorgantes(int numerootorgantes)
    {
        this.numerootorgantes = numerootorgantes;
    }
    public void guardarTablaTestamentos()
    {
        String mensaje ="Los datos de la tabla Testamentos se han guardado correctamente";
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "INSERT INTO testamentos(IDNoExpediente, Folio ,Observaciones) VALUES (?, ?, ?)";        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, noExpediente);
            pst.setInt(2, folio);
            pst.setString(3, observaciones);
            int validacion = pst.executeUpdate();
            if (validacion>0) 
            {
                System.out.println(mensaje);
                validacionfinal = true;
                mysql.desconectar();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Hubo un error al insertar los datos en la tabla testamentos.");
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
        if(jRBFisicaOtorgante.isSelected()==true)
        {
            tipoPersonaOtorgante=jRBFisicaOtorgante.getText();
            nombreFisicaOtorgante= jTNombreOtorganteFisica.getText();
            apPaFisicaOtorgante=jTApPaOtorganteFisica.getText();
            apMaFisicaOtorgante=jTApMaOtorganteFisica.getText();
        }
        else if(jRBMoralOtorgante.isSelected())
        {
            tipoPersonaOtorgante = jRBMoralOtorgante.getText();
            nombreMoralOtorgante = jTNombreOtorganteMoral.getText();
        }  
        String mensaje ="Los datos de la tabla OTORGANTES se han guardado correctamente";
        //------------------------------------------------------------------------------------        
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        sSQL= "INSERT INTO testamentosotorgante(IDNoExpediente , TipoCliente, NombreOtorgante, ApPaternoOtorgante, ApMaternoOtorgante) VALUES (?, ?, ?, ?, ?)";        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, noExpediente);
            pst.setString(2, tipoPersonaOtorgante);
            if("Persona Física".equals(tipoPersonaOtorgante))
            {
                pst.setString(3, nombreFisicaOtorgante);
                pst.setString(4, apPaFisicaOtorgante);
                pst.setString(5, apMaFisicaOtorgante);
            }
            if("Persona Moral".equals(tipoPersonaOtorgante))
            {
                pst.setString(3, nombreMoralOtorgante);
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
                                        JOptionPane.showMessageDialog(null, "Hubo un error al insertar los datos en la Tabla OTORGANTES Extras.");
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
                JOptionPane.showMessageDialog(null, "Hubo un error al insertar los datos en la Tabla OTORGANTES.");
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
        guardarTablaTestamentos();
        guardarTablaOtorgantes();
        if(validacionfinal == true)
        {
            JOptionPane.showMessageDialog(null, "Se han guardado todos los datos correctamente!");
            EleccionTipoExpediente.cerrarTestamentos(1);
        }else
        {
            JOptionPane.showMessageDialog(null, "NO se han podido completa TODAS las OPERACIONES");
            EleccionTipoExpediente.cerrarTestamentos(1);
        }
    }
}
