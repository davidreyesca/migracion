package Vista;
import Controlador.*;

/**
 * Librerias importadas para que la clase se pueda ejecutar correctamente * 
 */
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Clase principal de Captura compra-venta
 * @author David Reyes
 */
public class CompraVentaVisualizacion extends javax.swing.JFrame implements ActionListener{
    private int indiceComprador;
    private int indiceVendedor;
    private DateFormat df = DateFormat.getDateInstance();
    private Map controlCompradores = new HashMap();
    private Map controlVendedores = new HashMap();
    DefaultComboBoxModel modeloEstado, modeloMunicipio;
    Controlador.CompraVentaActualizacion datos = new Controlador.CompraVentaActualizacion();
    ActualizarTipoexpediente a;

    private void OcultarPanenlesIniciales()
    {
    //Se ocultan los paneles de comprador y vendedor morales al iniciar, y se inicia el indice de control de ambos en 0
        jLNombreMoralComprador.setVisible(false);
        jTNombreCompradorMoral.setVisible(false);
        jLNombreMoralVendedor.setVisible(false);
        jTNombreVendedorMoral.setVisible(false);
        indiceComprador = 0;
        indiceVendedor = 0;
        if (indiceComprador==0) 
        {
                jBEliminarComprador.setVisible(false);
        }
        if (indiceVendedor==0) 
        {
                jBEliminarVendedor.setVisible(false);
        }
    }   
public void llenar_combo() 
{
    DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();//esto es el modelo
    ConexionMySql mysql = new ConexionMySql();
    Connection cn = mysql.getConection();
    String sSQL="SELECT * FROM tipocompraventa";
    try 
    {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            modeloCombo.addElement("- Seleccionar -");
            while(rs.next()) 
            {
                modeloCombo.addElement(rs.getObject("TipoCompraVenta"));
            }
            jCBTipoCompraVenta.setModel(modeloCombo);
    } catch (SQLException ex) {
            System.out.println("Error al obtener todos los expedientes" + ex);
    }finally
        //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
    {
            mysql.desconectar();
    }
}    
    private void cargarEstados()
    {
        jCBMunicipio.setEnabled(false);
        String[] estados = {"-Seleccionar-", "San Luis Potosí", "Aguascalientes","Baja California","Baja California Sur", "Campeche","Coahuila de Zaragoza","Colima","Chiapas",
            "Chihuahua","Distrito Federal","Durango","Guanajuato", "Guerrero","Hidalgo","Jalisco", "México", "Michoacán de Ocampo","Morelos",
            "Nayarit", "Nuevo León", "Oaxaca", "Puebla", "Quintana Roo", "Queretaro", "Sinaloa", "Sonora", "Tabasco", "Tamaulipas", 
            "Tlaxcala", "Veracruz de Ignacio de la Llave", "Yucatán", "Zacatecas"};
        modeloEstado = new DefaultComboBoxModel(estados);
        jCBEstadoRepublica.setModel(modeloEstado); 
    }
    private void SLetras(JTextField a)
    {
        a.addKeyListener(new KeyAdapter() {
        public void keyTyped(KeyEvent e)
        {
            char c=e.getKeyChar();
            if(Character.isDigit(c))
            {
                getToolkit().beep();
                e.consume();
            }
        }
        });
    }
    private void NoCaracteres(JTextField a, int b)
    {
        int numerocaracteres = b;
        a.addKeyListener(new KeyAdapter() {
        public void keyTyped(KeyEvent e)
        {
            if(a.getText().length()>=numerocaracteres)
            {
                getToolkit().beep();
                e.consume();
                JOptionPane.showMessageDialog(null, "Has llegado al limite de caracteres de " + b);
            }
        }
        });
    }
    private void SNumeros(JTextField a)
    {
        a.addKeyListener(new KeyAdapter() {
        public void keyTyped(KeyEvent e)
        {
            char c=e.getKeyChar();
            if(Character.isLetter(c))
            {
                getToolkit().beep();
                e.consume();
            }
        }
        });
    }
    private void CambiarAMayusculas(JTextField a)
    {
        a.addKeyListener(new KeyAdapter() {
        public void keyTyped(KeyEvent e)
        {
            String palabrafinal;
            String nuestrotexto = a.getText();
            String digito;
            if(nuestrotexto.length()>0)
            {
                char primeraletra=nuestrotexto.charAt(0);
                nuestrotexto=Character.toUpperCase(primeraletra)+nuestrotexto.substring(1, nuestrotexto.length());
//                nuestrotexto=Character.toUpperCase(primeraletra)+(nuestrotexto.substring(1, nuestrotexto.length())).toLowerCase();
                for(int i=0; i< nuestrotexto.length(); i++)
                {
                    digito = String.valueOf(i);
                    palabrafinal = nuestrotexto.replaceAll(digito, "");
                    nuestrotexto = palabrafinal;
                }
                a.setText(nuestrotexto);
            }
        }
        });
    }
    private void ValidarSolonumeros(JTextField a)
    {   
        a.addKeyListener(new KeyAdapter() {
        public void keyTyped(KeyEvent e)
        {
            String cambio;
            String nuestrotexto = a.getText();
            cambio = nuestrotexto.replaceAll("[^0-9]", "");
            nuestrotexto = cambio;
            a.setText(nuestrotexto);
        }
        });       
    }    
    private void ValidacionLetrasNumerosTiempoReal()
    {
        SLetras(jTNombreCompradorFisica);
        SLetras(jTApPaCompradorFisica);
        SLetras(jTApMaCompradorFisica);
        SLetras(jTNombreVendedorFisica);
        SLetras(jTApPaVendedorFisica);
        SLetras(jTApMaVendedorFisica);
        SLetras(jTTipoActo);
        SNumeros(jTFolioReal);
        SNumeros(jTInstrumento);
        SNumeros(jTTomo);
        CambiarAMayusculas(jTNombreCompradorFisica);
        CambiarAMayusculas(jTApPaCompradorFisica);
        CambiarAMayusculas(jTApMaCompradorFisica);  
        CambiarAMayusculas(jTNombreVendedorFisica);
        CambiarAMayusculas(jTApPaVendedorFisica);
        CambiarAMayusculas(jTApMaVendedorFisica);
        ValidarSolonumeros(jTFolioReal);
        ValidarSolonumeros(jTInstrumento);
        ValidarSolonumeros(jTTomo);
        NoCaracteres(jTFolioReal, 11);
        NoCaracteres(jTInstrumento, 30);
        NoCaracteres(jTTomo, 30);
        NoCaracteres(jTTipoActo, 50);
        NoCaracteres(jTCalle, 50);
        NoCaracteres(jTNoExterior, 10);
        NoCaracteres(jTNoInterior, 10);
        NoCaracteres(jTColonia, 30);
        
        NoCaracteres(jTNombreCompradorFisica, 70);
        NoCaracteres(jTApPaCompradorFisica, 30);
        NoCaracteres(jTApMaCompradorFisica, 30);
        NoCaracteres(jTNombreCompradorMoral, 70);
        
        NoCaracteres(jTNombreVendedorFisica, 70);
        NoCaracteres(jTApPaVendedorFisica, 30);
        NoCaracteres(jTApMaVendedorFisica, 30);
        NoCaracteres(jTNombreVendedorMoral, 70);
    }
    private void ValidacionCompradorPrincipal()
    {
        boolean pasa=false;
        if (jRBFisicaComprador.isSelected()==false && jRBMoralComprador.isSelected()==false)
        {
            JOptionPane.showMessageDialog(null, "NO has seleccionado el tipo de comprador","Sin selección" ,JOptionPane.ERROR_MESSAGE);
            pasa=true;
        }else if(jRBFisicaComprador.isSelected()==true)
            {
                if(jTNombreCompradorFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del COMPRADOR","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }else if(jTApPaCompradorFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO PATERNO del COMPRADOR","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }else if(jTApMaCompradorFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO MATERNO del COMPRADOR","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }                           
            }
        else if(jRBMoralComprador.isSelected()==true)
        {
                if(jTNombreCompradorMoral.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE MORAL del COMPRADOR","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }           
         }
        if(pasa == false)
        {
            ValidacionVendedorPrincipal(); 
        }
    }
    private void ValidacionVendedorPrincipal()
    {
        boolean pasa=false;
        if (jRBFisicaVendedor.isSelected()==false && jRBMoralVendedor.isSelected()==false)
        {  
            JOptionPane.showMessageDialog(null, "NO has seleccionado el tipo de vendedor","Sin selección" ,JOptionPane.ERROR_MESSAGE);
            pasa=true;
        }else
        {
            if(jRBFisicaVendedor.isSelected()==true)
            {
                if(jTNombreVendedorFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del VENDEDOR","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }else if(jTApPaVendedorFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO PATERNO del VENDEDOR","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }else if(jTApMaVendedorFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO MATERNO del VENDEDOR","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }                           
            }
            else
            {
                if(jTNombreVendedorMoral.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE MORAL del VENDEDOR","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }           
            }
        }
        if(pasa == false)
        {
            ValidacionTextFieldVacios();
        }        
    }
    private void ValidacionTextFieldVacios()
    {
        if(jTInstrumento.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Es necesario llenar el campo INSTRUMENTO","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
        }
        else if(jTTomo.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Es necesario llenar el campo TOMO","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
        }
        else if(jTTipoActo.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Es necesario llenar el campo TIPO DE ACTO","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
        }
        else if(jTCalle.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Es necesario llenar el campo CALLE","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
        }
        else if(jTColonia.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Es necesario llenar el campo COLONIA","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
        }
        else if(jTNoExterior.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Es necesario llenar el campo NO. EXTERIROR","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
        }
        else{
            ValidacionCombos();
        }
    }
    private void ValidacionCombos()
    {
        int estrep = jCBEstadoRepublica.getSelectedIndex();
        int b = jCBMunicipio.getSelectedIndex();
        int c = jCBTipoCompraVenta.getSelectedIndex();            
        if(estrep==0)
        {
            JOptionPane.showMessageDialog(null, "Es necesario seleccionar el ESTADO DE LA REPUBLICA","Selección vacia" ,JOptionPane.ERROR_MESSAGE);
        }else if(b==0)
        {
            JOptionPane.showMessageDialog(null, "Es necesario seleccionar el MUNICIPIO","Selección vacia" ,JOptionPane.ERROR_MESSAGE);
        }else if (c==0)
        {
            JOptionPane.showMessageDialog(null, "Es necesario seleccionar el TIPO de CompraVenta que se esta haciendo","Selección vacia" ,JOptionPane.ERROR_MESSAGE);
        }else
        {
            ValidacionFecha();
        }
    }
    private void ValidacionFecha()
    {
        if(jDCFecha.getDate()==null)
        {
            JOptionPane.showMessageDialog(null, "Es necesario seleccionar la fecha","Selección vacia" ,JOptionPane.ERROR_MESSAGE);
        }else
        {
            ValidacionCompradores();
        }
    }
    private void ValidacionCompradores()
    {
        boolean pasa=false;
        Iterator it = controlCompradores.entrySet().iterator();
        while (it.hasNext()) 
        {
            Map.Entry entry = (Map.Entry)it.next();
            //se obtiene el KEY -> identificador unico
            int itm = (int) entry.getKey();
            //si comando de componente es igual a comando pulsado
                //se recupera el contenido del JTextfield
                if((((CompraVentaComprador) entry.getValue()).jRBFisicaComprador.isSelected()==false) && (((CompraVentaComprador) entry.getValue()).jRBMoralComprador.isSelected()==false))
                {
                    JOptionPane.showMessageDialog(null, "No has seleccionado el tipo de persona en el COMPRADOR " + (itm+2),"Selección vacia" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }
                else if(((CompraVentaComprador) entry.getValue()).jRBFisicaComprador.isSelected()==true)
                {
                    if(((CompraVentaComprador) entry.getValue()).jTNombreCompradorFisica.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del COMPRADOR " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }else if(((CompraVentaComprador) entry.getValue()).jTApPaComprador.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO PATERNO del COMPRADOR " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }else if(((CompraVentaComprador) entry.getValue()).jTApMaComprador.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO MATERNO del COMPRADOR " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }                             
                }else if(((CompraVentaComprador) entry.getValue()).jRBMoralComprador.isSelected()==true)
                {
                    if(((CompraVentaComprador) entry.getValue()).jTNombreCompradorMoral.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del COMPRADOR MORAL " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }
                }
        }
        if(pasa == false)
        {
            ValidacionVendedores();
        }
    }
    public void LlenarCompradores(int posicion, String tipoPersona, String NombrePersona, String ApPa, String ApMa)
    {
        Iterator it = controlCompradores.entrySet().iterator();
        while (it.hasNext()) 
        {
            Map.Entry entry = (Map.Entry)it.next();
            //se obtiene el KEY -> identificador unico
            int itm = (int) entry.getKey();
            //si comando de componente es igual a comando pulsado
                //se recupera el contenido del JTextfield
                if(itm==posicion)
                {
                    if(tipoPersona.equals("Persona Física"))
                    {
                        ((CompraVentaComprador)entry.getValue()).jRBFisicaComprador.setSelected(true);
                        ((CompraVentaComprador)entry.getValue()).jTNombreCompradorFisica.setText(NombrePersona);
                        ((CompraVentaComprador)entry.getValue()).jTApPaComprador.setText(ApPa);
                        ((CompraVentaComprador)entry.getValue()).jTApMaComprador.setText(ApMa);
                    }
                    else if(tipoPersona.equals("Persona Moral"))
                    {
                        ((CompraVentaComprador)entry.getValue()).jRBMoralComprador.setSelected(true);
                        ((CompraVentaComprador)entry.getValue()).jTNombreCompradorMoral.setText(NombrePersona);
                        ((CompraVentaComprador) entry.getValue()).jLNombreFisicaComprador.setVisible(false);
                        ((CompraVentaComprador) entry.getValue()).jLApPaComprador.setVisible(false);
                        ((CompraVentaComprador) entry.getValue()).jLApMaComprador.setVisible(false);
                        ((CompraVentaComprador) entry.getValue()).jTNombreCompradorFisica.setVisible(false);
                        ((CompraVentaComprador) entry.getValue()).jTApPaComprador.setVisible(false);
                        ((CompraVentaComprador) entry.getValue()).jTApMaComprador.setVisible(false);
                        ((CompraVentaComprador) entry.getValue()).jLNombreMoralComprador.setVisible(true);
                        ((CompraVentaComprador) entry.getValue()).jTNombreCompradorMoral.setVisible(true);
                    }
                }   
        }
    }
    public void LlenarVendedores(int posicion, String tipoPersona, String NombrePersona, String ApPa, String ApMa)
    {
        Iterator it = controlVendedores.entrySet().iterator();
        while (it.hasNext()) 
        {
            Map.Entry entry = (Map.Entry)it.next();
            //se obtiene el KEY -> identificador unico
            int itm = (int) entry.getKey();
            //si comando de componente es igual a comando pulsado
                //se recupera el contenido del JTextfield
                if(itm==posicion)
                {
                    if(tipoPersona.equals("Persona Física"))
                    {
                        ((CompraVentaVendedor)entry.getValue()).jRBFisicaVendedor.setSelected(true);
                        ((CompraVentaVendedor)entry.getValue()).jTNombreVendedorFisica.setText(NombrePersona);
                        ((CompraVentaVendedor)entry.getValue()).jTApPaVendedor.setText(ApPa);
                        ((CompraVentaVendedor)entry.getValue()).jTApMaVendedor.setText(ApMa);
                    }
                    else if(tipoPersona.equals("Persona Moral"))
                    {
                        ((CompraVentaVendedor)entry.getValue()).jRBMoralVendedor.setSelected(true);
                        ((CompraVentaVendedor)entry.getValue()).jTNombreVendedorMoral.setText(NombrePersona);
                        ((CompraVentaVendedor) entry.getValue()).jLNombreFisicaVendedor.setVisible(false);
                        ((CompraVentaVendedor) entry.getValue()).jLApPaVendedor.setVisible(false);
                        ((CompraVentaVendedor) entry.getValue()).jLApMaVendedor.setVisible(false);
                        ((CompraVentaVendedor) entry.getValue()).jTNombreVendedorFisica.setVisible(false);
                        ((CompraVentaVendedor) entry.getValue()).jTApPaVendedor.setVisible(false);
                        ((CompraVentaVendedor) entry.getValue()).jTApMaVendedor.setVisible(false);
                        ((CompraVentaVendedor) entry.getValue()).jLNombreMoralVendedor.setVisible(true);
                        ((CompraVentaVendedor) entry.getValue()).jTNombreVendedorMoral.setVisible(true);
                    }
                }   
        }
    }
    private void ValidacionVendedores()
    {
        boolean pasa=false;
        Iterator it = controlVendedores.entrySet().iterator();
        while (it.hasNext()) 
        {
            Map.Entry entry = (Map.Entry)it.next();
            //se obtiene el KEY -> identificador unico
            int itm = (int) entry.getKey();
            //si comando de componente es igual a comando pulsado
                //se recupera el contenido del JTextfield
                if((((CompraVentaVendedor) entry.getValue()).jRBFisicaVendedor.isSelected()==false) && (((CompraVentaVendedor) entry.getValue()).jRBMoralVendedor.isSelected()==false))
                {
                    JOptionPane.showMessageDialog(null, "No has seleccionado el tipo de persona en el VENDEDOR " + (itm+2),"Selección vacia" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }
                else if(((CompraVentaVendedor) entry.getValue()).jRBFisicaVendedor.isSelected()==true)
                {
                    if(((CompraVentaVendedor) entry.getValue()).jTNombreVendedorFisica.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del VENDEDOR " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }else if(((CompraVentaVendedor) entry.getValue()).jTApPaVendedor.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO PATERNO del VENDEDOR " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }else if(((CompraVentaVendedor) entry.getValue()).jTApMaVendedor.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO MATERNO del VENDEDOR " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }                             
                }else if(((CompraVentaVendedor) entry.getValue()).jRBMoralVendedor.isSelected()==true)
                {
                    if(((CompraVentaVendedor) entry.getValue()).jTNombreVendedorMoral.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del VENDEDOR MORAL " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }
                }
        }
        if(pasa == false)
        {
            GuardarBD();
        }
    }
    private void ValidacionFinal()
    {
        ValidacionCompradorPrincipal();
    }
    /**
     * Este metodo envía todos los datos a la clase contralador de compraventa donde se guardaran los datos ya validados
     */
    public void GuardarBD()
    {
        System.out.println("FELICITACIONES, Has pasado todas las validaciones!");
            datos.getIDTipoCompraventa(ObtenerNoTipoCompraVenta());
            datos.getFolioReal(Integer.parseInt(jTFolioReal.getText()));
            datos.getInstrumento(Integer.parseInt(jTInstrumento.getText()));
            datos.getTomo(Integer.parseInt(jTTomo.getText()));
            datos.getFecha(df.format(jDCFecha.getDate()));
            datos.getTipoActo(jTTipoActo.getText());           
            datos.getCalle(jTCalle.getText());
            datos.getNoExteriror(jTNoExterior.getText());
            datos.getNoInterior(jTNoInterior.getText());
            datos.getColonia(jTColonia.getText());           
            datos.getEstado(jCBEstadoRepublica.getSelectedItem().toString());
            datos.getMunicipio(jCBMunicipio.getSelectedItem().toString());
            datos.getObservaciones(jTAObservaciones.getText());
            datos.capturaFinal();
            
    }
    public int ObtenerNoTipoCompraVenta()
    {
        int Numero=0;
        String cadenaTipoCompra = jCBTipoCompraVenta.getSelectedItem().toString();
        String sSQL="SELECT * FROM tipocompraventa WHERE TipoCompraVenta=" + "'" + cadenaTipoCompra +"'";
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                    Numero = rs.getInt("IDTipoCompraVenta");
                    System.out.println("No. a enviar como ID de tipo de compraventa" + Numero);
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener el ID del usuario" + ex);
        }finally
        //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
        {
            mysql.desconectar();
        }
        return Numero;
    }
    /**
     * Constructor de la clase
     */
    public CompraVentaVisualizacion() 
    {
        //JFrame iniciando compnente, en el centro de la pantalla, sin reescalado
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("Imagenes/IconoNotaria.png")).getImage());
        setExtendedState(MAXIMIZED_BOTH);
        tituloVentanaCompraVenta.setText("No. Expediente: #" + AbrirExpediente.getNoExpedinte());
        OcultarPanenlesIniciales();
        cargarEstados();
        llenar_combo();
        ValidacionLetrasNumerosTiempoReal();
        VistaPreviaLectura vistaPreviaPDF = new VistaPreviaLectura();        
        jPPDF.add(vistaPreviaPDF);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoPersonaComprador = new javax.swing.ButtonGroup();
        tipoPersonaVendedor = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jSPPrincipal = new javax.swing.JScrollPane();
        Principal = new javax.swing.JPanel();
        jBEliminarComprador = new javax.swing.JButton();
        jBAgregarComprador = new javax.swing.JButton();
        jLTituloComprador = new javax.swing.JLabel();
        jRBFisicaComprador = new javax.swing.JRadioButton();
        jRBMoralComprador = new javax.swing.JRadioButton();
        jLNombreFisicaComprador = new javax.swing.JLabel();
        jLNombreMoralComprador = new javax.swing.JLabel();
        jTNombreCompradorMoral = new javax.swing.JTextField();
        jTNombreCompradorFisica = new javax.swing.JTextField();
        jTApPaCompradorFisica = new javax.swing.JTextField();
        jLApPaCompradorFisica = new javax.swing.JLabel();
        jTApMaCompradorFisica = new javax.swing.JTextField();
        tituloVentanaCompraVenta = new javax.swing.JLabel();
        jSPComprador = new javax.swing.JScrollPane();
        PanelComprador = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLTituloVendedor = new javax.swing.JLabel();
        jRBFisicaVendedor = new javax.swing.JRadioButton();
        jRBMoralVendedor = new javax.swing.JRadioButton();
        jTNombreVendedorFisica = new javax.swing.JTextField();
        jLNombreFisicaVendedor = new javax.swing.JLabel();
        jLNombreMoralVendedor = new javax.swing.JLabel();
        jTNombreVendedorMoral = new javax.swing.JTextField();
        jLApMaVendedorFisica = new javax.swing.JLabel();
        jTApPaVendedorFisica = new javax.swing.JTextField();
        jTApMaVendedorFisica = new javax.swing.JTextField();
        jBAgregarVendedor = new javax.swing.JButton();
        jBEliminarVendedor = new javax.swing.JButton();
        jSPVendedor = new javax.swing.JScrollPane();
        PanelVendedor = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLFolioReal = new javax.swing.JLabel();
        jTFolioReal = new javax.swing.JTextField();
        jLTomo = new javax.swing.JLabel();
        jLInstrumento = new javax.swing.JLabel();
        jTInstrumento = new javax.swing.JTextField();
        jTCalle = new javax.swing.JTextField();
        jLTipoActo = new javax.swing.JLabel();
        jTTomo = new javax.swing.JTextField();
        jLCalle = new javax.swing.JLabel();
        jTTipoActo = new javax.swing.JTextField();
        jLNoInterior = new javax.swing.JLabel();
        jTNoInterior = new javax.swing.JTextField();
        jLNoExterior = new javax.swing.JLabel();
        jTNoExterior = new javax.swing.JTextField();
        jLColonia = new javax.swing.JLabel();
        jTColonia = new javax.swing.JTextField();
        jLEstadoRepublica = new javax.swing.JLabel();
        jCBEstadoRepublica = new javax.swing.JComboBox<>();
        jLMunicipio = new javax.swing.JLabel();
        jCBMunicipio = new javax.swing.JComboBox<>();
        jLObservaciones = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTAObservaciones = new javax.swing.JTextArea();
        jBTerminar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLApMaCompradorFisica = new javax.swing.JLabel();
        jLApPaVendedorFisica = new javax.swing.JLabel();
        jLFecha2 = new javax.swing.JLabel();
        jDCFecha = new com.toedter.calendar.JDateChooser();
        tituloVentanaCompraVenta1 = new javax.swing.JLabel();
        jLTipoCompraVenta = new javax.swing.JLabel();
        jCBTipoCompraVenta = new javax.swing.JComboBox<>();
        jBHabilitarEdicion = new javax.swing.JButton();
        jPPDF = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Compra-Venta Visualización");

        jPanel2.setBackground(new java.awt.Color(49, 58, 115));
        jPanel2.setAlignmentX(0.0F);
        jPanel2.setAlignmentY(0.0F);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jSPPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        jSPPrincipal.setAlignmentX(0.0F);
        jSPPrincipal.setAlignmentY(0.0F);
        jSPPrincipal.setHorizontalScrollBar(null);

        Principal.setBackground(new java.awt.Color(255, 255, 255));
        Principal.setAlignmentX(0.0F);
        Principal.setAlignmentY(0.0F);

        jBEliminarComprador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Eliminar Comprador.png"))); // NOI18N
        jBEliminarComprador.setBorderPainted(false);
        jBEliminarComprador.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Eliminar Comprador Grande.png"))); // NOI18N
        jBEliminarComprador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminarCompradorActionPerformed(evt);
            }
        });

        jBAgregarComprador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Agregarnormal.png"))); // NOI18N
        jBAgregarComprador.setBorderPainted(false);
        jBAgregarComprador.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton AgregarGrande.png"))); // NOI18N
        jBAgregarComprador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAgregarCompradorActionPerformed(evt);
            }
        });

        jLTituloComprador.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLTituloComprador.setText("Comprador");

        tipoPersonaComprador.add(jRBFisicaComprador);
        jRBFisicaComprador.setText("Persona Física");
        jRBFisicaComprador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBFisicaCompradorMouseClicked(evt);
            }
        });
        jRBFisicaComprador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBFisicaCompradorActionPerformed(evt);
            }
        });

        tipoPersonaComprador.add(jRBMoralComprador);
        jRBMoralComprador.setText("Persona Moral");
        jRBMoralComprador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBMoralCompradorMouseClicked(evt);
            }
        });
        jRBMoralComprador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBMoralCompradorActionPerformed(evt);
            }
        });

        jLNombreFisicaComprador.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreFisicaComprador.setText("*Nombre:");

        jLNombreMoralComprador.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreMoralComprador.setText("*Nombre: ");

        jTNombreCompradorMoral.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreCompradorMoral.setPreferredSize(new java.awt.Dimension(250, 26));
        jTNombreCompradorMoral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreCompradorMoralActionPerformed(evt);
            }
        });

        jTNombreCompradorFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreCompradorFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreCompradorFisicaActionPerformed(evt);
            }
        });

        jTApPaCompradorFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApPaCompradorFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApPaCompradorFisicaActionPerformed(evt);
            }
        });

        jLApPaCompradorFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApPaCompradorFisica.setText("*Apellido Paterno:");

        jTApMaCompradorFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApMaCompradorFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApMaCompradorFisicaActionPerformed(evt);
            }
        });

        tituloVentanaCompraVenta.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tituloVentanaCompraVenta.setText("No. Expediente: #");

        jSPComprador.setBorder(null);
        jSPComprador.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jSPComprador.setHorizontalScrollBar(null);

        PanelComprador.setBackground(new java.awt.Color(255, 255, 255));
        PanelComprador.setAlignmentX(0.0F);
        PanelComprador.setAlignmentY(0.0F);

        javax.swing.GroupLayout PanelCompradorLayout = new javax.swing.GroupLayout(PanelComprador);
        PanelComprador.setLayout(PanelCompradorLayout);
        PanelCompradorLayout.setHorizontalGroup(
            PanelCompradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 986, Short.MAX_VALUE)
        );
        PanelCompradorLayout.setVerticalGroup(
            PanelCompradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );

        jSPComprador.setViewportView(PanelComprador);

        jLTituloVendedor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLTituloVendedor.setText("Vendedor");

        tipoPersonaVendedor.add(jRBFisicaVendedor);
        jRBFisicaVendedor.setText("Persona Física");
        jRBFisicaVendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBFisicaVendedorMouseClicked(evt);
            }
        });
        jRBFisicaVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBFisicaVendedorActionPerformed(evt);
            }
        });

        tipoPersonaVendedor.add(jRBMoralVendedor);
        jRBMoralVendedor.setText("Persona Moral");
        jRBMoralVendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBMoralVendedorMouseClicked(evt);
            }
        });
        jRBMoralVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBMoralVendedorActionPerformed(evt);
            }
        });

        jTNombreVendedorFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreVendedorFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreVendedorFisicaActionPerformed(evt);
            }
        });

        jLNombreFisicaVendedor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreFisicaVendedor.setText("*Nombre: ");

        jLNombreMoralVendedor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreMoralVendedor.setText("*Nombre: ");

        jTNombreVendedorMoral.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreVendedorMoral.setPreferredSize(new java.awt.Dimension(250, 26));
        jTNombreVendedorMoral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreVendedorMoralActionPerformed(evt);
            }
        });

        jLApMaVendedorFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApMaVendedorFisica.setText("*Apellido Materno:");

        jTApPaVendedorFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApPaVendedorFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApPaVendedorFisicaActionPerformed(evt);
            }
        });

        jTApMaVendedorFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApMaVendedorFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApMaVendedorFisicaActionPerformed(evt);
            }
        });

        jBAgregarVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Agregar Vendedor.png"))); // NOI18N
        jBAgregarVendedor.setBorderPainted(false);
        jBAgregarVendedor.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Agregar Vendedor Grande.png"))); // NOI18N
        jBAgregarVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAgregarVendedorActionPerformed(evt);
            }
        });

        jBEliminarVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Eliminar Vendedor.png"))); // NOI18N
        jBEliminarVendedor.setBorderPainted(false);
        jBEliminarVendedor.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Eliminar Vendedor Grande.png"))); // NOI18N
        jBEliminarVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminarVendedorActionPerformed(evt);
            }
        });

        jSPVendedor.setBorder(null);
        jSPVendedor.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jSPVendedor.setHorizontalScrollBar(null);

        PanelVendedor.setBackground(new java.awt.Color(255, 255, 255));
        PanelVendedor.setAlignmentX(0.0F);
        PanelVendedor.setAlignmentY(0.0F);

        javax.swing.GroupLayout PanelVendedorLayout = new javax.swing.GroupLayout(PanelVendedor);
        PanelVendedor.setLayout(PanelVendedorLayout);
        PanelVendedorLayout.setHorizontalGroup(
            PanelVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 986, Short.MAX_VALUE)
        );
        PanelVendedorLayout.setVerticalGroup(
            PanelVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );

        jSPVendedor.setViewportView(PanelVendedor);

        jLFolioReal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLFolioReal.setText("Folio real: ");

        jTFolioReal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTFolioReal.setPreferredSize(new java.awt.Dimension(250, 26));
        jTFolioReal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFolioRealActionPerformed(evt);
            }
        });

        jLTomo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLTomo.setText("*Tomo:");

        jLInstrumento.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLInstrumento.setText("*Instrumento:");

        jTInstrumento.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTInstrumento.setPreferredSize(new java.awt.Dimension(250, 26));
        jTInstrumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTInstrumentoActionPerformed(evt);
            }
        });

        jTCalle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTCalle.setPreferredSize(new java.awt.Dimension(250, 26));
        jTCalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTCalleActionPerformed(evt);
            }
        });

        jLTipoActo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLTipoActo.setText("*Tipo de acto:");

        jTTomo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTTomo.setPreferredSize(new java.awt.Dimension(250, 26));
        jTTomo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTTomoActionPerformed(evt);
            }
        });

        jLCalle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLCalle.setText("*Calle:");

        jTTipoActo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTTipoActo.setPreferredSize(new java.awt.Dimension(250, 26));
        jTTipoActo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTTipoActoActionPerformed(evt);
            }
        });

        jLNoInterior.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNoInterior.setText("No Int:");

        jTNoInterior.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNoInterior.setPreferredSize(new java.awt.Dimension(250, 26));
        jTNoInterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNoInteriorActionPerformed(evt);
            }
        });

        jLNoExterior.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNoExterior.setText("*No Ext:");

        jTNoExterior.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNoExterior.setPreferredSize(new java.awt.Dimension(250, 26));
        jTNoExterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNoExteriorActionPerformed(evt);
            }
        });

        jLColonia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLColonia.setText("*Colonia:");

        jTColonia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTColonia.setPreferredSize(new java.awt.Dimension(250, 26));
        jTColonia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTColoniaActionPerformed(evt);
            }
        });

        jLEstadoRepublica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLEstadoRepublica.setText("*Estado:");

        jCBEstadoRepublica.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jCBEstadoRepublica.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBEstadoRepublicaItemStateChanged(evt);
            }
        });
        jCBEstadoRepublica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBEstadoRepublicaActionPerformed(evt);
            }
        });

        jLMunicipio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLMunicipio.setText("*Municipio:");

        jCBMunicipio.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jCBMunicipio.setPreferredSize(new java.awt.Dimension(225, 23));

        jLObservaciones.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLObservaciones.setText("Observaciones:");

        jTAObservaciones.setColumns(20);
        jTAObservaciones.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jTAObservaciones.setRows(5);
        jTAObservaciones.setName(""); // NOI18N
        jTAObservaciones.setSelectionColor(new java.awt.Color(41, 168, 73));
        jScrollPane1.setViewportView(jTAObservaciones);

        jBTerminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Actualizar Acto Notarial.png"))); // NOI18N
        jBTerminar.setBorderPainted(false);
        jBTerminar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Actualizar Acto Notarial Grande.png"))); // NOI18N
        jBTerminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTerminarActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Cerrar Vista Acto Notarial.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Cerrar vista Acto Notarial Grande.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLApMaCompradorFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApMaCompradorFisica.setText("*Apellido Materno:");

        jLApPaVendedorFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApPaVendedorFisica.setText("*Apellido Paterno:");

        jLFecha2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLFecha2.setText("*Fecha:");

        jDCFecha.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        tituloVentanaCompraVenta1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tituloVentanaCompraVenta1.setText("Compra-Venta");

        jLTipoCompraVenta.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLTipoCompraVenta.setText("Tipo de compra venta:");

        jCBTipoCompraVenta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Item 2", "Item 3", "Item 4" }));
        jCBTipoCompraVenta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBTipoCompraVentaItemStateChanged(evt);
            }
        });

        jBHabilitarEdicion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Edit.png"))); // NOI18N
        jBHabilitarEdicion.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/EditPresionado.png"))); // NOI18N
        jBHabilitarEdicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBHabilitarEdicionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PrincipalLayout = new javax.swing.GroupLayout(Principal);
        Principal.setLayout(PrincipalLayout);
        PrincipalLayout.setHorizontalGroup(
            PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSPVendedor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE)
                            .addComponent(jSPComprador, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrincipalLayout.createSequentialGroup()
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PrincipalLayout.createSequentialGroup()
                                .addComponent(jBAgregarComprador, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBEliminarComprador, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrincipalLayout.createSequentialGroup()
                                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addGap(303, 303, 303)
                                        .addComponent(jRBFisicaComprador)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRBMoralComprador))
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addGap(410, 410, 410)
                                        .addComponent(jLTituloVendedor))
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addGap(305, 305, 305)
                                        .addComponent(jRBFisicaVendedor)
                                        .addGap(19, 19, 19)
                                        .addComponent(jRBMoralVendedor))
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addGap(401, 401, 401)
                                        .addComponent(jLTituloComprador)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(PrincipalLayout.createSequentialGroup()
                                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addComponent(jBAgregarVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jBEliminarVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(3, 3, 3))
                            .addGroup(PrincipalLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addComponent(jLNombreFisicaVendedor)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTNombreVendedorFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLApPaVendedorFisica)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTApPaVendedorFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLApMaVendedorFisica)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTApMaVendedorFisica))
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addComponent(jLNombreMoralVendedor)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTNombreVendedorMoral, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrincipalLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addComponent(tituloVentanaCompraVenta1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tituloVentanaCompraVenta))
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLNombreFisicaComprador)
                                            .addComponent(jLNombreMoralComprador))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(PrincipalLayout.createSequentialGroup()
                                                .addComponent(jTNombreCompradorFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLApPaCompradorFisica)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTApPaCompradorFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jTNombreCompradorMoral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLApMaCompradorFisica)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTApMaCompradorFisica)))))
                        .addGap(31, 31, 31))
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(PrincipalLayout.createSequentialGroup()
                                    .addComponent(jLCalle)
                                    .addGap(72, 72, 72)
                                    .addComponent(jTCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLNoExterior)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTNoExterior, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLNoInterior)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTNoInterior, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(5, 5, 5))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrincipalLayout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLInstrumento)
                                        .addComponent(jLFolioReal)
                                        .addComponent(jLTomo)
                                        .addComponent(jLTipoActo)
                                        .addComponent(jLColonia)
                                        .addComponent(jLObservaciones))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(PrincipalLayout.createSequentialGroup()
                                            .addComponent(jTColonia, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLEstadoRepublica)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jCBEstadoRepublica, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLMunicipio)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jCBMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(PrincipalLayout.createSequentialGroup()
                                            .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jTTipoActo, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTTomo, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTInstrumento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jTFolioReal, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jCBTipoCompraVenta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLFecha2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jDCFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(PrincipalLayout.createSequentialGroup()
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jBHabilitarEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jBTerminar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLTipoCompraVenta))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        PrincipalLayout.setVerticalGroup(
            PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrincipalLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tituloVentanaCompraVenta)
                    .addComponent(tituloVentanaCompraVenta1))
                .addGap(9, 9, 9)
                .addComponent(jLTituloComprador)
                .addGap(5, 5, 5)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRBFisicaComprador)
                    .addComponent(jRBMoralComprador))
                .addGap(18, 18, 18)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTNombreCompradorFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLApPaCompradorFisica)
                        .addComponent(jTApPaCompradorFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLApMaCompradorFisica)
                        .addComponent(jTApMaCompradorFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLNombreFisicaComprador)))
                .addGap(8, 8, 8)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLNombreMoralComprador)
                    .addComponent(jTNombreCompradorMoral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jSPComprador, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBAgregarComprador, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBEliminarComprador, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLTituloVendedor)
                .addGap(4, 4, 4)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRBFisicaVendedor)
                    .addComponent(jRBMoralVendedor))
                .addGap(21, 21, 21)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLNombreFisicaVendedor)
                    .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTNombreVendedorFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLApPaVendedorFisica)
                        .addComponent(jTApPaVendedorFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLApMaVendedorFisica)
                        .addComponent(jTApMaVendedorFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTNombreVendedorMoral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNombreMoralVendedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSPVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addComponent(jBAgregarVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 31, Short.MAX_VALUE)
                        .addGap(16, 16, 16))
                    .addComponent(jBEliminarVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLTipoCompraVenta)
                    .addComponent(jCBTipoCompraVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLFolioReal)
                            .addComponent(jTFolioReal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLInstrumento)
                            .addComponent(jTInstrumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLTomo)
                            .addComponent(jTTomo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLTipoActo)
                            .addComponent(jTTipoActo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLFecha2)))
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jDCFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLCalle)
                    .addComponent(jTCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNoInterior)
                    .addComponent(jTNoInterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNoExterior)
                    .addComponent(jTNoExterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTColonia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLColonia)
                        .addComponent(jCBEstadoRepublica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLMunicipio)
                        .addComponent(jCBMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLEstadoRepublica)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLObservaciones)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTerminar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBHabilitarEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSPPrincipal.setViewportView(Principal);

        jPPDF.setBackground(new java.awt.Color(255, 255, 255));
        jPPDF.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSPPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPPDF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPPDF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSPPrincipal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBEliminarCompradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarCompradorActionPerformed
        indiceComprador--;
        PanelComprador.remove(indiceComprador);
        this.controlCompradores.remove(indiceComprador);
        if (indiceComprador>0) 
        {
            PanelComprador.setLayout(new GridLayout(indiceComprador, 0));
        } 
        PanelComprador.updateUI();
        if (indiceComprador==0)
        {
            jBEliminarComprador.setVisible(false);
        }
    }//GEN-LAST:event_jBEliminarCompradorActionPerformed

    private void jBAgregarCompradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarCompradorActionPerformed
        AgregarComprador();
    }//GEN-LAST:event_jBAgregarCompradorActionPerformed
    public void AgregarComprador()
    {
            CompraVentaComprador nuevoComprador = new CompraVentaComprador(indiceComprador);
            nuevoComprador.jRBFisicaComprador.addActionListener(this);
            nuevoComprador.jRBMoralComprador.addActionListener(this);
            nuevoComprador.jLNombreMoralComprador.setVisible(false);
            nuevoComprador.jTNombreCompradorMoral.setVisible(false);
            PanelComprador.add(nuevoComprador);
            this.controlCompradores.put(indiceComprador, nuevoComprador);
            indiceComprador++;
            PanelComprador.setLayout(new GridLayout(indiceComprador, 0));
            PanelComprador.updateUI();
            jBEliminarComprador.setVisible(true);
    }
    
    private void jRBFisicaCompradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBFisicaCompradorActionPerformed
        // TODO add your handling code here:
        jLNombreMoralComprador.setVisible(false);
        jTNombreCompradorMoral.setVisible(false);
        jLNombreFisicaComprador.setVisible(true);
        jLApMaCompradorFisica.setVisible(true);
        jLApPaCompradorFisica.setVisible(true);
        jTNombreCompradorFisica.setVisible(true);
        jTApMaCompradorFisica.setVisible(true);
        jTApPaCompradorFisica.setVisible(true);
    }//GEN-LAST:event_jRBFisicaCompradorActionPerformed

    private void jRBMoralCompradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBMoralCompradorActionPerformed
        // TODO add your handling code here:
        jLNombreFisicaComprador.setVisible(false);
        jLApMaCompradorFisica.setVisible(false);
        jLApPaCompradorFisica.setVisible(false);
        jTNombreCompradorFisica.setVisible(false);
        jTApMaCompradorFisica.setVisible(false);
        jTApPaCompradorFisica.setVisible(false);
        jLNombreMoralComprador.setVisible(true);
        jTNombreCompradorMoral.setVisible(true);
    }//GEN-LAST:event_jRBMoralCompradorActionPerformed

    private void jTNombreCompradorMoralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreCompradorMoralActionPerformed
         jTNombreCompradorMoral.transferFocus();
    }//GEN-LAST:event_jTNombreCompradorMoralActionPerformed

    private void jRBFisicaVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBFisicaVendedorActionPerformed
        // TODO add your handling code here:
        jLNombreMoralVendedor.setVisible(false);
        jTNombreVendedorMoral.setVisible(false);      
        jTNombreVendedorFisica.setVisible(true);
        jTApPaVendedorFisica.setVisible(true);
        jTApMaVendedorFisica.setVisible(true);
        jLNombreFisicaVendedor.setVisible(true);
        jLApPaVendedorFisica.setVisible(true);
        jLApMaVendedorFisica.setVisible(true);    
    }//GEN-LAST:event_jRBFisicaVendedorActionPerformed

    private void jRBMoralVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBMoralVendedorActionPerformed
        // TODO add your handling code here:
        jLNombreMoralVendedor.setVisible(true);
        jTNombreVendedorMoral.setVisible(true);
        jTNombreVendedorFisica.setVisible(false);
        jTApPaVendedorFisica.setVisible(false);
        jTApMaVendedorFisica.setVisible(false);
        jLNombreFisicaVendedor.setVisible(false);
        jLApPaVendedorFisica.setVisible(false);
        jLApMaVendedorFisica.setVisible(false);
    }//GEN-LAST:event_jRBMoralVendedorActionPerformed

    private void jTNombreVendedorMoralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreVendedorMoralActionPerformed
        jTNombreVendedorMoral.transferFocus();
    }//GEN-LAST:event_jTNombreVendedorMoralActionPerformed

    private void jBAgregarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarVendedorActionPerformed
        AgregarVendedor();
    }//GEN-LAST:event_jBAgregarVendedorActionPerformed
    public void AgregarVendedor()
    {
        CompraVentaVendedor nuevoVendedor = new CompraVentaVendedor(indiceVendedor);
        nuevoVendedor.jRBFisicaVendedor.addActionListener(this);
        nuevoVendedor.jRBMoralVendedor.addActionListener(this);
        nuevoVendedor.jLNombreMoralVendedor.setVisible(false);
        nuevoVendedor.jTNombreVendedorMoral.setVisible(false);
        PanelVendedor.add(nuevoVendedor);
        this.controlVendedores.put(indiceVendedor, nuevoVendedor);
        indiceVendedor++;
        PanelVendedor.setLayout(new GridLayout(indiceVendedor, 0));
        PanelVendedor.updateUI();
        jBEliminarVendedor.setVisible(true);
    }
    private void jBEliminarVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarVendedorActionPerformed
        indiceVendedor--;
        PanelVendedor.remove(indiceVendedor);
        this.controlVendedores.remove(indiceVendedor);
        if (indiceVendedor>0) 
        {
            PanelVendedor.setLayout(new GridLayout(indiceVendedor, 0));
        } 
        PanelVendedor.updateUI();
        if (indiceVendedor==0)
        {
            jBEliminarVendedor.setVisible(false);
        }
    }//GEN-LAST:event_jBEliminarVendedorActionPerformed

    private void jTFolioRealActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFolioRealActionPerformed
        jTFolioReal.transferFocus();
    }//GEN-LAST:event_jTFolioRealActionPerformed

    private void jTInstrumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTInstrumentoActionPerformed
        jTInstrumento.transferFocus();
    }//GEN-LAST:event_jTInstrumentoActionPerformed

    private void jTCalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTCalleActionPerformed
        jTCalle.transferFocus();
    }//GEN-LAST:event_jTCalleActionPerformed

    private void jTTomoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTTomoActionPerformed
        jTTomo.transferFocus();
    }//GEN-LAST:event_jTTomoActionPerformed

    private void jTTipoActoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTTipoActoActionPerformed
        jTTipoActo.transferFocus();
    }//GEN-LAST:event_jTTipoActoActionPerformed

    private void jTNoInteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNoInteriorActionPerformed
        jTNoInterior.transferFocus();
    }//GEN-LAST:event_jTNoInteriorActionPerformed

    private void jTNoExteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNoExteriorActionPerformed
        jTNoExterior.transferFocus();
    }//GEN-LAST:event_jTNoExteriorActionPerformed

    private void jTColoniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTColoniaActionPerformed
        jTColonia.transferFocus();
    }//GEN-LAST:event_jTColoniaActionPerformed

    private void jCBEstadoRepublicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBEstadoRepublicaActionPerformed
        jCBEstadoRepublica.transferFocus();
    }//GEN-LAST:event_jCBEstadoRepublicaActionPerformed

    private void jBTerminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTerminarActionPerformed
        int banderacompradores = controlCompradores.size();
        int banderavendedores = controlVendedores.size();
        datos.getNumeroCompradores(banderacompradores);
        datos.getNumeroVendedores(banderavendedores);
        String[][] compradoresdatos = new String[banderacompradores][4];
        String[][] vendedoresdatos = new String[banderavendedores][4];
        Iterator it = controlCompradores.entrySet().iterator();
        Iterator it2 = controlVendedores.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry entry = (Map.Entry)it.next();
            //se obtiene el KEY -> identificador unico
            int itm = (int) entry.getKey();
            //si comando de componente es igual a comando pulsado
          
                //se recupera el contenido del JTextfield
                if(((CompraVentaComprador) entry.getValue()).jRBFisicaComprador.isSelected()==true)
                {                    
                    String dos = ((CompraVentaComprador) entry.getValue()).jTNombreCompradorFisica.getText();
                    String tres = ((CompraVentaComprador) entry.getValue()).jTApPaComprador.getText();
                    String cuatro = ((CompraVentaComprador) entry.getValue()).jTApMaComprador.getText();
                    compradoresdatos[itm][0]="Persona Física";
                    compradoresdatos[itm][1]= dos;
                    compradoresdatos[itm][2]= tres;
                    compradoresdatos[itm][3]= cuatro;
                }
                else
                {
                    String uno = ((CompraVentaComprador) entry.getValue()).jTNombreCompradorMoral.getText();
                    compradoresdatos[itm][0]="Persona Moral";
                    compradoresdatos[itm][1]= uno;
                    compradoresdatos[itm][2]= "";
                    compradoresdatos[itm][3]= "";
                }      
            }
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry)it2.next();
            //se obtiene el KEY -> identificador unico
            int itm2 = (int) entry.getKey();
            //si comando de componente es igual a comando pulsado
          
                //se recupera el contenido del JTextfield
                if(((CompraVentaVendedor) entry.getValue()).jRBFisicaVendedor.isSelected()==true)
                {                    
                    String dos = ((CompraVentaVendedor) entry.getValue()).jTNombreVendedorFisica.getText();
                    String tres = ((CompraVentaVendedor) entry.getValue()).jTApPaVendedor.getText();
                    String cuatro = ((CompraVentaVendedor) entry.getValue()).jTApMaVendedor.getText();
                    vendedoresdatos[itm2][0]="Persona Física";
                    vendedoresdatos[itm2][1]= dos;
                    vendedoresdatos[itm2][2]= tres;
                    vendedoresdatos[itm2][3]= cuatro;
                }
                else
                {
                    String uno = ((CompraVentaVendedor) entry.getValue()).jTNombreVendedorMoral.getText();
                    vendedoresdatos[itm2][0]="Persona Moral";
                    vendedoresdatos[itm2][1]= uno;
                    vendedoresdatos[itm2][2]= "";
                    vendedoresdatos[itm2][3]= "";
                }
            }
        datos.getCompradores(compradoresdatos);
        datos.getVendedores(vendedoresdatos);
        ValidacionFinal();
    }//GEN-LAST:event_jBTerminarActionPerformed

    private void jCBEstadoRepublicaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBEstadoRepublicaItemStateChanged
        if(evt.getStateChange() ==ItemEvent.SELECTED)
        {
            if (this.jCBEstadoRepublica.getSelectedIndex()>0) 
            {           
                        jCBMunicipio.setEnabled(true);
                        String estadoSeleccionado= jCBEstadoRepublica.getSelectedItem().toString();
                        MunicipiosCombo municipioSeleccionado = new MunicipiosCombo();
                        String municipiosArray[] = municipioSeleccionado.CargarMunicipios(estadoSeleccionado);
                        modeloMunicipio = new DefaultComboBoxModel(municipiosArray);
                        jCBMunicipio.setModel(modeloMunicipio);
            }else{      
                        jCBMunicipio.setEnabled(false);
            }
        }
    }//GEN-LAST:event_jCBEstadoRepublicaItemStateChanged

    private void jRBFisicaVendedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBFisicaVendedorMouseClicked

    }//GEN-LAST:event_jRBFisicaVendedorMouseClicked

    private void jRBFisicaCompradorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBFisicaCompradorMouseClicked
    }//GEN-LAST:event_jRBFisicaCompradorMouseClicked

    private void jRBMoralCompradorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBMoralCompradorMouseClicked
    }//GEN-LAST:event_jRBMoralCompradorMouseClicked

    private void jRBMoralVendedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBMoralVendedorMouseClicked
    }//GEN-LAST:event_jRBMoralVendedorMouseClicked

    private void jTNombreCompradorFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreCompradorFisicaActionPerformed
        jTNombreCompradorFisica.transferFocus();
    }//GEN-LAST:event_jTNombreCompradorFisicaActionPerformed

    private void jTApPaCompradorFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApPaCompradorFisicaActionPerformed
        jTApPaCompradorFisica.transferFocus();
    }//GEN-LAST:event_jTApPaCompradorFisicaActionPerformed

    private void jTApMaCompradorFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApMaCompradorFisicaActionPerformed
        jTApMaCompradorFisica.transferFocus();
    }//GEN-LAST:event_jTApMaCompradorFisicaActionPerformed

    private void jTNombreVendedorFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreVendedorFisicaActionPerformed
        jTNombreVendedorFisica.transferFocus();
    }//GEN-LAST:event_jTNombreVendedorFisicaActionPerformed

    private void jTApPaVendedorFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApPaVendedorFisicaActionPerformed
        jTApPaVendedorFisica.transferFocus();
    }//GEN-LAST:event_jTApPaVendedorFisicaActionPerformed

    private void jTApMaVendedorFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApMaVendedorFisicaActionPerformed
        jTApMaVendedorFisica.transferFocus();
    }//GEN-LAST:event_jTApMaVendedorFisicaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCBTipoCompraVentaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBTipoCompraVentaItemStateChanged
        

    }//GEN-LAST:event_jCBTipoCompraVentaItemStateChanged

    private void jBHabilitarEdicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBHabilitarEdicionActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "¿Seguro que quieres habilitar el poder actualizar este expediente? ",
                "Habilitar actualizar sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
        {
            jBTerminar.setVisible(true);
            jBHabilitarEdicion.setVisible(false);
        }       
    }//GEN-LAST:event_jBHabilitarEdicionActionPerformed

    /**
     * Main de la clase, inicia la ventana.
     * @param args
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                Logger.getLogger(CompraVentaVisualizacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            new CompraVentaVisualizacion().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel PanelComprador;
    public static javax.swing.JPanel PanelVendedor;
    private javax.swing.JPanel Principal;
    private javax.swing.JButton jBAgregarComprador;
    private javax.swing.JButton jBAgregarVendedor;
    private javax.swing.JButton jBEliminarComprador;
    private javax.swing.JButton jBEliminarVendedor;
    public javax.swing.JButton jBHabilitarEdicion;
    public javax.swing.JButton jBTerminar;
    private javax.swing.JButton jButton1;
    public javax.swing.JComboBox<String> jCBEstadoRepublica;
    public javax.swing.JComboBox<String> jCBMunicipio;
    public javax.swing.JComboBox<String> jCBTipoCompraVenta;
    public com.toedter.calendar.JDateChooser jDCFecha;
    public javax.swing.JLabel jLApMaCompradorFisica;
    public javax.swing.JLabel jLApMaVendedorFisica;
    public javax.swing.JLabel jLApPaCompradorFisica;
    public javax.swing.JLabel jLApPaVendedorFisica;
    public javax.swing.JLabel jLCalle;
    public javax.swing.JLabel jLColonia;
    public javax.swing.JLabel jLEstadoRepublica;
    public javax.swing.JLabel jLFecha2;
    public javax.swing.JLabel jLFolioReal;
    public javax.swing.JLabel jLInstrumento;
    public javax.swing.JLabel jLMunicipio;
    public javax.swing.JLabel jLNoExterior;
    public javax.swing.JLabel jLNoInterior;
    public javax.swing.JLabel jLNombreFisicaComprador;
    public javax.swing.JLabel jLNombreFisicaVendedor;
    public javax.swing.JLabel jLNombreMoralComprador;
    public javax.swing.JLabel jLNombreMoralVendedor;
    public javax.swing.JLabel jLObservaciones;
    public javax.swing.JLabel jLTipoActo;
    public javax.swing.JLabel jLTipoCompraVenta;
    private javax.swing.JLabel jLTituloComprador;
    private javax.swing.JLabel jLTituloVendedor;
    public javax.swing.JLabel jLTomo;
    private javax.swing.JPanel jPPDF;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JRadioButton jRBFisicaComprador;
    public static javax.swing.JRadioButton jRBFisicaVendedor;
    public static javax.swing.JRadioButton jRBMoralComprador;
    public static javax.swing.JRadioButton jRBMoralVendedor;
    private javax.swing.JScrollPane jSPComprador;
    private javax.swing.JScrollPane jSPPrincipal;
    private javax.swing.JScrollPane jSPVendedor;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public javax.swing.JTextArea jTAObservaciones;
    public static javax.swing.JTextField jTApMaCompradorFisica;
    public static javax.swing.JTextField jTApMaVendedorFisica;
    public static javax.swing.JTextField jTApPaCompradorFisica;
    public static javax.swing.JTextField jTApPaVendedorFisica;
    public javax.swing.JTextField jTCalle;
    public javax.swing.JTextField jTColonia;
    public javax.swing.JTextField jTFolioReal;
    public javax.swing.JTextField jTInstrumento;
    public javax.swing.JTextField jTNoExterior;
    public javax.swing.JTextField jTNoInterior;
    public static javax.swing.JTextField jTNombreCompradorFisica;
    public static javax.swing.JTextField jTNombreCompradorMoral;
    public static javax.swing.JTextField jTNombreVendedorFisica;
    public static javax.swing.JTextField jTNombreVendedorMoral;
    public javax.swing.JTextField jTTipoActo;
    public javax.swing.JTextField jTTomo;
    private javax.swing.ButtonGroup tipoPersonaComprador;
    private javax.swing.ButtonGroup tipoPersonaVendedor;
    public static javax.swing.JLabel tituloVentanaCompraVenta;
    private javax.swing.JLabel tituloVentanaCompraVenta1;
    // End of variables declaration//GEN-END:variables

    /**
     * Este metodo nos permite ocultar o mostrar componentes de un jPanel exterior
     * @param e escucha las acciones de la ventana.
     */
    public void actionPerformed(ActionEvent e) 
    {
        //se obtiene el comando ejecutado
        String comando = e.getActionCommand();
        //se recorre el MAP
        Iterator it = controlCompradores.entrySet().iterator();
        Iterator it2 = controlVendedores.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            //se obtiene el KEY -> identificador unico
            String itm = entry.getKey().toString();
            //si comando de componente es igual a comando pulsado
            if(comando.equals("indicefComprador" + itm))
            {                              
                ((CompraVentaComprador) entry.getValue()).jLNombreFisicaComprador.setVisible(true);
                ((CompraVentaComprador) entry.getValue()).jLApPaComprador.setVisible(true);
                ((CompraVentaComprador) entry.getValue()).jLApMaComprador.setVisible(true);
                ((CompraVentaComprador) entry.getValue()).jTNombreCompradorFisica.setVisible(true);
                ((CompraVentaComprador) entry.getValue()).jTApPaComprador.setVisible(true);
                ((CompraVentaComprador) entry.getValue()).jTApMaComprador.setVisible(true);
                ((CompraVentaComprador) entry.getValue()).jLNombreMoralComprador.setVisible(false);
                ((CompraVentaComprador) entry.getValue()).jTNombreCompradorMoral.setVisible(false);
            }
            else if(comando.equals("indicemComprador" + itm))
            {
                ((CompraVentaComprador) entry.getValue()).jLNombreFisicaComprador.setVisible(false);
                ((CompraVentaComprador) entry.getValue()).jLApPaComprador.setVisible(false);
                ((CompraVentaComprador) entry.getValue()).jLApMaComprador.setVisible(false);
                ((CompraVentaComprador) entry.getValue()).jTNombreCompradorFisica.setVisible(false);
                ((CompraVentaComprador) entry.getValue()).jTApPaComprador.setVisible(false);
                ((CompraVentaComprador) entry.getValue()).jTApMaComprador.setVisible(false);
                ((CompraVentaComprador) entry.getValue()).jLNombreMoralComprador.setVisible(true);
                ((CompraVentaComprador) entry.getValue()).jTNombreCompradorMoral.setVisible(true);
            }
        }
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry)it2.next();
            //se obtiene el KEY -> identificador unico
            String itm2 = entry.getKey().toString();
            //si comando de componente es igual a comando pulsado
            if(comando.equals("indicefVendedor" + itm2))
            {
                //mostramos resultado
                ((CompraVentaVendedor) entry.getValue()).jLNombreFisicaVendedor.setVisible(true);
                ((CompraVentaVendedor) entry.getValue()).jLApPaVendedor.setVisible(true);
                ((CompraVentaVendedor) entry.getValue()).jLApMaVendedor.setVisible(true);
                ((CompraVentaVendedor) entry.getValue()).jTNombreVendedorFisica.setVisible(true);
                ((CompraVentaVendedor) entry.getValue()).jTApPaVendedor.setVisible(true);
                ((CompraVentaVendedor) entry.getValue()).jTApMaVendedor.setVisible(true);
                ((CompraVentaVendedor) entry.getValue()).jLNombreMoralVendedor.setVisible(false);
                ((CompraVentaVendedor) entry.getValue()).jTNombreVendedorMoral.setVisible(false);
                

            }
            else if(comando.equals("indicemVendedor" + itm2))
            {
                ((CompraVentaVendedor) entry.getValue()).jLNombreFisicaVendedor.setVisible(false);
                ((CompraVentaVendedor) entry.getValue()).jLApPaVendedor.setVisible(false);
                ((CompraVentaVendedor) entry.getValue()).jLApMaVendedor.setVisible(false);
                ((CompraVentaVendedor) entry.getValue()).jTNombreVendedorFisica.setVisible(false);
                ((CompraVentaVendedor) entry.getValue()).jTApPaVendedor.setVisible(false);
                ((CompraVentaVendedor) entry.getValue()).jTApMaVendedor.setVisible(false);
                ((CompraVentaVendedor) entry.getValue()).jLNombreMoralVendedor.setVisible(true);
                ((CompraVentaVendedor) entry.getValue()).jTNombreVendedorMoral.setVisible(true);
            }
        }
    }    
}
