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
 * Clase principal de Captura Donación
 * @author David Reyes
 */
public class DonacionVisualizacion extends javax.swing.JFrame implements ActionListener{
    private int indiceDonatario;
    private int indiceDonante;
    int NoEx;
    private Map controlDonatarios = new HashMap();
    private Map controlDonantes = new HashMap();
    DefaultComboBoxModel modeloEstado, modeloMunicipio;
    Controlador.DonacionActualizacion datos = new Controlador.DonacionActualizacion();
    ActualizarTipoexpediente a;

    private void OcultarPanenlesIniciales()
    {
    //Se ocultan los paneles de Donatario y Donante morales al iniciar, y se inicia el indice de control de ambos en 0
        jLNombreMoralDonatario.setVisible(false);
        jTNombreDonatarioMoral.setVisible(false);
        jLNombreMoralDonante.setVisible(false);
        jTNombreDonanteMoral.setVisible(false);
        indiceDonatario = 0;
        indiceDonante = 0;
        if (indiceDonatario==0) 
        {
                jBEliminarDonatario.setVisible(false);
        }
        if (indiceDonante==0) 
        {
                jBEliminarDonante.setVisible(false);
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
        SLetras(jTNombreDonatarioFisica);
        SLetras(jTApPaDonatarioFisica);
        SLetras(jTApMaDonatarioFisica);
        SLetras(jTNombreDonanteFisica);
        SLetras(jTApPaDonanteFisica);
        SLetras(jTApMaDonanteFisica);
        SLetras(jTTipoActo);
        SNumeros(jTFolioReal);
        SNumeros(jTInstrumento);
        SNumeros(jTTomo);
        CambiarAMayusculas(jTNombreDonatarioFisica);
        CambiarAMayusculas(jTApPaDonatarioFisica);
        CambiarAMayusculas(jTApMaDonatarioFisica);  
        CambiarAMayusculas(jTNombreDonanteFisica);
        CambiarAMayusculas(jTApPaDonanteFisica);
        CambiarAMayusculas(jTApMaDonanteFisica);
        ValidarSolonumeros(jTFolioReal);
        ValidarSolonumeros(jTInstrumento);
        ValidarSolonumeros(jTTomo);
        NoCaracteres(jTFolioReal, 11);
        NoCaracteres(jTInstrumento, 11);
        NoCaracteres(jTTomo, 11);
        NoCaracteres(jTTipoActo, 50);
        NoCaracteres(jTCalle, 50);
        NoCaracteres(jTNoExterior, 10);
        NoCaracteres(jTNoInterior, 10);
        NoCaracteres(jTColonia, 30);
        
        NoCaracteres(jTNombreDonatarioFisica, 70);
        NoCaracteres(jTApPaDonatarioFisica, 30);
        NoCaracteres(jTApMaDonatarioFisica, 30);
        NoCaracteres(jTNombreDonatarioMoral, 70);
        
        NoCaracteres(jTNombreDonanteFisica, 70);
        NoCaracteres(jTApPaDonanteFisica, 30);
        NoCaracteres(jTApMaDonanteFisica, 30);
        NoCaracteres(jTNombreDonanteMoral, 70);
    }
    private void ValidacionDonatarioPrincipal()
    {
        boolean pasa=false;
        if (jRBFisicaDonatario.isSelected()==false && jRBMoralDonatario.isSelected()==false)
        {
            JOptionPane.showMessageDialog(null, "NO has seleccionado el tipo de Donatario","Sin selección" ,JOptionPane.ERROR_MESSAGE);
            pasa=true;
        }else if(jRBFisicaDonatario.isSelected()==true)
            {
                if(jTNombreDonatarioFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del Donatario","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }else if(jTApPaDonatarioFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO PATERNO del Donatario","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }else if(jTApMaDonatarioFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO MATERNO del Donatario","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }                           
            }
        else if(jRBMoralDonatario.isSelected()==true)
        {
                if(jTNombreDonatarioMoral.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE MORAL del Donatario","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }           
         }
        if(pasa == false)
        {
            ValidacionDonantePrincipal(); 
        }
    }
    private void ValidacionDonantePrincipal()
    {
        boolean pasa=false;
        if (jRBFisicaDonante.isSelected()==false && jRBMoralDonante.isSelected()==false)
        {  
            JOptionPane.showMessageDialog(null, "NO has seleccionado el tipo de Donante","Sin selección" ,JOptionPane.ERROR_MESSAGE);
            pasa=true;
        }else
        {
            if(jRBFisicaDonante.isSelected()==true)
            {
                if(jTNombreDonanteFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del Donante","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }else if(jTApPaDonanteFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO PATERNO del Donante","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }else if(jTApMaDonanteFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO MATERNO del Donante","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }                           
            }
            else
            {
                if(jTNombreDonanteMoral.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE MORAL del Donante","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
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
        if(estrep==0)
        {
            JOptionPane.showMessageDialog(null, "Es necesario seleccionar el ESTADO DE LA REPUBLICA","Selección vacia" ,JOptionPane.ERROR_MESSAGE);
        }else if(b==0)
        {
            JOptionPane.showMessageDialog(null, "Es necesario seleccionar el MUNICIPIO","Selección vacia" ,JOptionPane.ERROR_MESSAGE);
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
            ValidacionDonatarios();
        }
    }
    private void ValidacionDonatarios()
    {
        boolean pasa=false;
        Iterator it = controlDonatarios.entrySet().iterator();
        while (it.hasNext()) 
        {
            Map.Entry entry = (Map.Entry)it.next();
            //se obtiene el KEY -> identificador unico
            int itm = (int) entry.getKey();
            //si comando de componente es igual a comando pulsado
                //se recupera el contenido del JTextfield
                if((((DonacionDonatario) entry.getValue()).jRBFisicaDonatario.isSelected()==false) && (((DonacionDonatario) entry.getValue()).jRBMoralDonatario.isSelected()==false))
                {
                    JOptionPane.showMessageDialog(null, "No has seleccionado el tipo de persona en el Donatario " + (itm+2),"Selección vacia" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }
                else if(((DonacionDonatario) entry.getValue()).jRBFisicaDonatario.isSelected()==true)
                {
                    if(((DonacionDonatario) entry.getValue()).jTNombreDonatarioFisica.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del Donatario " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }else if(((DonacionDonatario) entry.getValue()).jTApPaDonatario.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO PATERNO del Donatario " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }else if(((DonacionDonatario) entry.getValue()).jTApMaDonatario.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO MATERNO del Donatario " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }                             
                }else if(((DonacionDonatario) entry.getValue()).jRBMoralDonatario.isSelected()==true)
                {
                    if(((DonacionDonatario) entry.getValue()).jTNombreDonatarioMoral.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del Donatario MORAL " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }
                }
        }
        if(pasa == false)
        {
            ValidacionDonantes();
        }
    }
    public void LlenarDonatarios(int posicion, String tipoPersona, String NombrePersona, String ApPa, String ApMa)
    {
        Iterator it = controlDonatarios.entrySet().iterator();
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
                        ((DonacionDonatario)entry.getValue()).jRBFisicaDonatario.setSelected(true);
                        ((DonacionDonatario)entry.getValue()).jTNombreDonatarioFisica.setText(NombrePersona);
                        ((DonacionDonatario)entry.getValue()).jTApPaDonatario.setText(ApPa);
                        ((DonacionDonatario)entry.getValue()).jTApMaDonatario.setText(ApMa);
                    }
                    else if(tipoPersona.equals("Persona Moral"))
                    {
                        ((DonacionDonatario)entry.getValue()).jRBMoralDonatario.setSelected(true);
                        ((DonacionDonatario)entry.getValue()).jTNombreDonatarioMoral.setText(NombrePersona);
                        ((DonacionDonatario) entry.getValue()).jLNombreFisicaDonatario.setVisible(false);
                        ((DonacionDonatario) entry.getValue()).jLApPaDonatario.setVisible(false);
                        ((DonacionDonatario) entry.getValue()).jLApMaDonatario.setVisible(false);
                        ((DonacionDonatario) entry.getValue()).jTNombreDonatarioFisica.setVisible(false);
                        ((DonacionDonatario) entry.getValue()).jTApPaDonatario.setVisible(false);
                        ((DonacionDonatario) entry.getValue()).jTApMaDonatario.setVisible(false);
                        ((DonacionDonatario) entry.getValue()).jLNombreMoralDonatario.setVisible(true);
                        ((DonacionDonatario) entry.getValue()).jTNombreDonatarioMoral.setVisible(true);
                    }
                }   
        }
    }
    public void LlenarDonantes(int posicion, String tipoPersona, String NombrePersona, String ApPa, String ApMa)
    {
        Iterator it = controlDonantes.entrySet().iterator();
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
                        ((DonacionDonante)entry.getValue()).jRBFisicaDonante.setSelected(true);
                        ((DonacionDonante)entry.getValue()).jTNombreDonanteFisica.setText(NombrePersona);
                        ((DonacionDonante)entry.getValue()).jTApPaDonante.setText(ApPa);
                        ((DonacionDonante)entry.getValue()).jTApMaDonante.setText(ApMa);
                    }
                    else if(tipoPersona.equals("Persona Moral"))
                    {
                        ((DonacionDonante)entry.getValue()).jRBMoralDonante.setSelected(true);
                        ((DonacionDonante)entry.getValue()).jTNombreDonanteMoral.setText(NombrePersona);
                        ((DonacionDonante) entry.getValue()).jLNombreFisicaDonante.setVisible(false);
                        ((DonacionDonante) entry.getValue()).jLApPaDonante.setVisible(false);
                        ((DonacionDonante) entry.getValue()).jLApMaDonante.setVisible(false);
                        ((DonacionDonante) entry.getValue()).jTNombreDonanteFisica.setVisible(false);
                        ((DonacionDonante) entry.getValue()).jTApPaDonante.setVisible(false);
                        ((DonacionDonante) entry.getValue()).jTApMaDonante.setVisible(false);
                        ((DonacionDonante) entry.getValue()).jLNombreMoralDonante.setVisible(true);
                        ((DonacionDonante) entry.getValue()).jTNombreDonanteMoral.setVisible(true);
                    }
                }   
        }
    }
    private void ValidacionDonantes()
    {
        boolean pasa=false;
        Iterator it = controlDonantes.entrySet().iterator();
        while (it.hasNext()) 
        {
            Map.Entry entry = (Map.Entry)it.next();
            //se obtiene el KEY -> identificador unico
            int itm = (int) entry.getKey();
            //si comando de componente es igual a comando pulsado
                //se recupera el contenido del JTextfield
                if((((DonacionDonante) entry.getValue()).jRBFisicaDonante.isSelected()==false) && (((DonacionDonante) entry.getValue()).jRBMoralDonante.isSelected()==false))
                {
                    JOptionPane.showMessageDialog(null, "No has seleccionado el tipo de persona en el Donante " + (itm+2),"Selección vacia" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }
                else if(((DonacionDonante) entry.getValue()).jRBFisicaDonante.isSelected()==true)
                {
                    if(((DonacionDonante) entry.getValue()).jTNombreDonanteFisica.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del Donante " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }else if(((DonacionDonante) entry.getValue()).jTApPaDonante.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO PATERNO del Donante " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }else if(((DonacionDonante) entry.getValue()).jTApMaDonante.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO MATERNO del Donante " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }                             
                }else if(((DonacionDonante) entry.getValue()).jRBMoralDonante.isSelected()==true)
                {
                    if(((DonacionDonante) entry.getValue()).jTNombreDonanteMoral.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del Donante MORAL " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
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
        ValidacionDonatarioPrincipal();
    }
    /**
     * Este metodo envía todos los datos a la clase contralador de Donacion donde se guardaran los datos ya validados
     */
    public void GuardarBD()
    {
        System.out.println("FELICITACIONES, Has pasado todas las validaciones!");
            if(jTFolioReal.getText().isEmpty())
            {
                datos.getFolioReal(0);
            }else
            {
                datos.getFolioReal(Integer.parseInt(jTFolioReal.getText()));
            }
            datos.getInstrumento(Integer.parseInt(jTInstrumento.getText()));
            datos.getTomo(Integer.parseInt(jTTomo.getText()));
            datos.getFecha(jDCFecha.getDate());
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

    /**
     * Constructor de la clase
     */
    public DonacionVisualizacion() 
    {
        //JFrame iniciando compnente, en el centro de la pantalla, sin reescalado
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("Imagenes/IconoNotaria.png")).getImage());
        setExtendedState(MAXIMIZED_BOTH);
        tituloVentanaNoExpedienteDonacion.setText("No. Expediente: #" + AbrirExpediente.getNoExpedinte());
        OcultarPanenlesIniciales();
        cargarEstados();
        OcultarIntrumento();
        ValidacionLetrasNumerosTiempoReal();
        VistaPreviaLectura vistaPreviaPDF = new VistaPreviaLectura();        
        jPPDF.add(vistaPreviaPDF);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoPersonaDonatario = new javax.swing.ButtonGroup();
        tipoPersonaDonante = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jSPPrincipal = new javax.swing.JScrollPane();
        Principal = new javax.swing.JPanel();
        jBEliminarDonatario = new javax.swing.JButton();
        jBAgregarDonatario = new javax.swing.JButton();
        jLTituloDonatario = new javax.swing.JLabel();
        jRBFisicaDonatario = new javax.swing.JRadioButton();
        jRBMoralDonatario = new javax.swing.JRadioButton();
        jLNombreFisicaDonatario = new javax.swing.JLabel();
        jLNombreMoralDonatario = new javax.swing.JLabel();
        jTNombreDonatarioMoral = new javax.swing.JTextField();
        jTNombreDonatarioFisica = new javax.swing.JTextField();
        jTApPaDonatarioFisica = new javax.swing.JTextField();
        jLApPaDonatarioFisica = new javax.swing.JLabel();
        jTApMaDonatarioFisica = new javax.swing.JTextField();
        tituloVentanaNoExpedienteDonacion = new javax.swing.JLabel();
        jSPDonatario = new javax.swing.JScrollPane();
        PanelDonatario = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLTituloDonante = new javax.swing.JLabel();
        jRBFisicaDonante = new javax.swing.JRadioButton();
        jRBMoralDonante = new javax.swing.JRadioButton();
        jTNombreDonanteFisica = new javax.swing.JTextField();
        jLNombreFisicaDonante = new javax.swing.JLabel();
        jLNombreMoralDonante = new javax.swing.JLabel();
        jTNombreDonanteMoral = new javax.swing.JTextField();
        jLApMaDonanteFisica = new javax.swing.JLabel();
        jTApPaDonanteFisica = new javax.swing.JTextField();
        jTApMaDonanteFisica = new javax.swing.JTextField();
        jBAgregarDonante = new javax.swing.JButton();
        jBEliminarDonante = new javax.swing.JButton();
        jSPDonante = new javax.swing.JScrollPane();
        PanelDonante = new javax.swing.JPanel();
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
        jLApMaDonatarioFisica = new javax.swing.JLabel();
        jLApPaDonanteFisica = new javax.swing.JLabel();
        jLFecha2 = new javax.swing.JLabel();
        jDCFecha = new com.toedter.calendar.JDateChooser();
        tituloVentanaDonacion = new javax.swing.JLabel();
        jBHabilitarEdicion = new javax.swing.JButton();
        JIntrumentoInformacion = new javax.swing.JLabel();
        jInvalido = new javax.swing.JLabel();
        jValido = new javax.swing.JLabel();
        jPPDF = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Donación Visualización");

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

        jBEliminarDonatario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Eliminar Donatario.png"))); // NOI18N
        jBEliminarDonatario.setBorderPainted(false);
        jBEliminarDonatario.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Eliminar Donatario Grande.png"))); // NOI18N
        jBEliminarDonatario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminarDonatarioActionPerformed(evt);
            }
        });

        jBAgregarDonatario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Agregar Donatario.png"))); // NOI18N
        jBAgregarDonatario.setBorderPainted(false);
        jBAgregarDonatario.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Agregar Donatario Grande.png"))); // NOI18N
        jBAgregarDonatario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAgregarDonatarioActionPerformed(evt);
            }
        });

        jLTituloDonatario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLTituloDonatario.setText("Donatario");

        tipoPersonaDonatario.add(jRBFisicaDonatario);
        jRBFisicaDonatario.setText("Persona Física");
        jRBFisicaDonatario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBFisicaDonatarioMouseClicked(evt);
            }
        });
        jRBFisicaDonatario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBFisicaDonatarioActionPerformed(evt);
            }
        });

        tipoPersonaDonatario.add(jRBMoralDonatario);
        jRBMoralDonatario.setText("Persona Moral");
        jRBMoralDonatario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBMoralDonatarioMouseClicked(evt);
            }
        });
        jRBMoralDonatario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBMoralDonatarioActionPerformed(evt);
            }
        });

        jLNombreFisicaDonatario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreFisicaDonatario.setText("*Nombre:");

        jLNombreMoralDonatario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreMoralDonatario.setText("*Nombre: ");

        jTNombreDonatarioMoral.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreDonatarioMoral.setPreferredSize(new java.awt.Dimension(250, 26));
        jTNombreDonatarioMoral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreDonatarioMoralActionPerformed(evt);
            }
        });

        jTNombreDonatarioFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreDonatarioFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreDonatarioFisicaActionPerformed(evt);
            }
        });

        jTApPaDonatarioFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApPaDonatarioFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApPaDonatarioFisicaActionPerformed(evt);
            }
        });

        jLApPaDonatarioFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApPaDonatarioFisica.setText("*Apellido Paterno:");

        jTApMaDonatarioFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApMaDonatarioFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApMaDonatarioFisicaActionPerformed(evt);
            }
        });

        tituloVentanaNoExpedienteDonacion.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tituloVentanaNoExpedienteDonacion.setText("No. Expediente: #");

        jSPDonatario.setBorder(null);
        jSPDonatario.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jSPDonatario.setHorizontalScrollBar(null);

        PanelDonatario.setBackground(new java.awt.Color(255, 255, 255));
        PanelDonatario.setAlignmentX(0.0F);
        PanelDonatario.setAlignmentY(0.0F);

        javax.swing.GroupLayout PanelDonatarioLayout = new javax.swing.GroupLayout(PanelDonatario);
        PanelDonatario.setLayout(PanelDonatarioLayout);
        PanelDonatarioLayout.setHorizontalGroup(
            PanelDonatarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 986, Short.MAX_VALUE)
        );
        PanelDonatarioLayout.setVerticalGroup(
            PanelDonatarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );

        jSPDonatario.setViewportView(PanelDonatario);

        jLTituloDonante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLTituloDonante.setText("Donante");

        tipoPersonaDonante.add(jRBFisicaDonante);
        jRBFisicaDonante.setText("Persona Física");
        jRBFisicaDonante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBFisicaDonanteMouseClicked(evt);
            }
        });
        jRBFisicaDonante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBFisicaDonanteActionPerformed(evt);
            }
        });

        tipoPersonaDonante.add(jRBMoralDonante);
        jRBMoralDonante.setText("Persona Moral");
        jRBMoralDonante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBMoralDonanteMouseClicked(evt);
            }
        });
        jRBMoralDonante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBMoralDonanteActionPerformed(evt);
            }
        });

        jTNombreDonanteFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreDonanteFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreDonanteFisicaActionPerformed(evt);
            }
        });

        jLNombreFisicaDonante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreFisicaDonante.setText("*Nombre: ");

        jLNombreMoralDonante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreMoralDonante.setText("*Nombre: ");

        jTNombreDonanteMoral.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreDonanteMoral.setPreferredSize(new java.awt.Dimension(250, 26));
        jTNombreDonanteMoral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreDonanteMoralActionPerformed(evt);
            }
        });

        jLApMaDonanteFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApMaDonanteFisica.setText("*Apellido Materno:");

        jTApPaDonanteFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApPaDonanteFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApPaDonanteFisicaActionPerformed(evt);
            }
        });

        jTApMaDonanteFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApMaDonanteFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApMaDonanteFisicaActionPerformed(evt);
            }
        });

        jBAgregarDonante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Agregar donante normal.png"))); // NOI18N
        jBAgregarDonante.setBorderPainted(false);
        jBAgregarDonante.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Agregar donante Grande.png"))); // NOI18N
        jBAgregarDonante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAgregarDonanteActionPerformed(evt);
            }
        });

        jBEliminarDonante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Eliminar Donante.png"))); // NOI18N
        jBEliminarDonante.setBorderPainted(false);
        jBEliminarDonante.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Eliminar Donante Grande.png"))); // NOI18N
        jBEliminarDonante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminarDonanteActionPerformed(evt);
            }
        });

        jSPDonante.setBorder(null);
        jSPDonante.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jSPDonante.setHorizontalScrollBar(null);

        PanelDonante.setBackground(new java.awt.Color(255, 255, 255));
        PanelDonante.setAlignmentX(0.0F);
        PanelDonante.setAlignmentY(0.0F);

        javax.swing.GroupLayout PanelDonanteLayout = new javax.swing.GroupLayout(PanelDonante);
        PanelDonante.setLayout(PanelDonanteLayout);
        PanelDonanteLayout.setHorizontalGroup(
            PanelDonanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 986, Short.MAX_VALUE)
        );
        PanelDonanteLayout.setVerticalGroup(
            PanelDonanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );

        jSPDonante.setViewportView(PanelDonante);

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
        jTInstrumento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTInstrumentoFocusLost(evt);
            }
        });
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

        jLApMaDonatarioFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApMaDonatarioFisica.setText("*Apellido Materno:");

        jLApPaDonanteFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApPaDonanteFisica.setText("*Apellido Paterno:");

        jLFecha2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLFecha2.setText("*Fecha:");

        jDCFecha.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        tituloVentanaDonacion.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tituloVentanaDonacion.setText("Donación");

        jBHabilitarEdicion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Edit.png"))); // NOI18N
        jBHabilitarEdicion.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/EditPresionado.png"))); // NOI18N
        jBHabilitarEdicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBHabilitarEdicionActionPerformed(evt);
            }
        });

        JIntrumentoInformacion.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        JIntrumentoInformacion.setText("Campo instrumento correcto.");

        jInvalido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/ocupado.png"))); // NOI18N

        jValido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/validacion.png"))); // NOI18N

        javax.swing.GroupLayout PrincipalLayout = new javax.swing.GroupLayout(Principal);
        Principal.setLayout(PrincipalLayout);
        PrincipalLayout.setHorizontalGroup(
            PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSPDonante, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE)
                            .addComponent(jSPDonatario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrincipalLayout.createSequentialGroup()
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PrincipalLayout.createSequentialGroup()
                                .addComponent(jBAgregarDonatario, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBEliminarDonatario, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PrincipalLayout.createSequentialGroup()
                                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addComponent(jBAgregarDonante, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jBEliminarDonante, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(3, 3, 3))
                            .addGroup(PrincipalLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addComponent(jLNombreFisicaDonante)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTNombreDonanteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLApPaDonanteFisica)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTApPaDonanteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLApMaDonanteFisica)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTApMaDonanteFisica))
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addComponent(jLNombreMoralDonante)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTNombreDonanteMoral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(233, 233, 233))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrincipalLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addComponent(tituloVentanaDonacion)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tituloVentanaNoExpedienteDonacion))
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLNombreFisicaDonatario)
                                            .addComponent(jLNombreMoralDonatario))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(PrincipalLayout.createSequentialGroup()
                                                .addComponent(jTNombreDonatarioMoral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(237, 237, 237))
                                            .addGroup(PrincipalLayout.createSequentialGroup()
                                                .addComponent(jTNombreDonatarioFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLApPaDonatarioFisica)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTApPaDonatarioFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLApMaDonatarioFisica)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTApMaDonatarioFisica))))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrincipalLayout.createSequentialGroup()
                                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrincipalLayout.createSequentialGroup()
                                        .addGap(303, 303, 303)
                                        .addComponent(jRBFisicaDonatario)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRBMoralDonatario))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrincipalLayout.createSequentialGroup()
                                        .addGap(410, 410, 410)
                                        .addComponent(jLTituloDonante))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrincipalLayout.createSequentialGroup()
                                        .addGap(305, 305, 305)
                                        .addComponent(jRBFisicaDonante)
                                        .addGap(19, 19, 19)
                                        .addComponent(jRBMoralDonante))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrincipalLayout.createSequentialGroup()
                                        .addGap(401, 401, 401)
                                        .addComponent(jLTituloDonatario))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrincipalLayout.createSequentialGroup()
                                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(JIntrumentoInformacion)
                                            .addGroup(PrincipalLayout.createSequentialGroup()
                                                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLInstrumento)
                                                    .addComponent(jLFolioReal))
                                                .addGap(12, 12, 12)
                                                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jTInstrumento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jTFolioReal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jInvalido)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jValido)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(31, 31, 31))
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(PrincipalLayout.createSequentialGroup()
                                    .addComponent(jBHabilitarEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jBTerminar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(PrincipalLayout.createSequentialGroup()
                                    .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                            .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTTipoActo, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTTomo, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLFecha2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jDCFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(PrincipalLayout.createSequentialGroup()
                                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrincipalLayout.createSequentialGroup()
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
                                        .addComponent(jTNoInterior, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(5, 5, 5)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        PrincipalLayout.setVerticalGroup(
            PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrincipalLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tituloVentanaNoExpedienteDonacion)
                    .addComponent(tituloVentanaDonacion))
                .addGap(9, 9, 9)
                .addComponent(jLTituloDonatario)
                .addGap(5, 5, 5)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRBFisicaDonatario)
                    .addComponent(jRBMoralDonatario))
                .addGap(18, 18, 18)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTNombreDonatarioFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLApPaDonatarioFisica)
                        .addComponent(jTApPaDonatarioFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLApMaDonatarioFisica)
                        .addComponent(jTApMaDonatarioFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLNombreFisicaDonatario)))
                .addGap(8, 8, 8)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLNombreMoralDonatario)
                    .addComponent(jTNombreDonatarioMoral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jSPDonatario, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBAgregarDonatario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBEliminarDonatario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLTituloDonante)
                .addGap(4, 4, 4)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRBFisicaDonante)
                    .addComponent(jRBMoralDonante))
                .addGap(21, 21, 21)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLNombreFisicaDonante)
                    .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTNombreDonanteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLApPaDonanteFisica)
                        .addComponent(jTApPaDonanteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLApMaDonanteFisica)
                        .addComponent(jTApMaDonanteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTNombreDonanteMoral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNombreMoralDonante))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSPDonante, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addComponent(jBAgregarDonante, javax.swing.GroupLayout.PREFERRED_SIZE, 33, Short.MAX_VALUE)
                        .addGap(16, 16, 16))
                    .addComponent(jBEliminarDonante, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLFolioReal)
                    .addComponent(jTFolioReal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLInstrumento)
                            .addComponent(jTInstrumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jInvalido))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JIntrumentoInformacion))
                    .addComponent(jValido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLTomo)
                            .addComponent(jTTomo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLTipoActo)
                            .addComponent(jTTipoActo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLFecha2)))
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
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
                .addGap(36, 36, 36)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTerminar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBHabilitarEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addComponent(jPPDF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSPPrincipal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBEliminarDonatarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarDonatarioActionPerformed
        indiceDonatario--;
        PanelDonatario.remove(indiceDonatario);
        this.controlDonatarios.remove(indiceDonatario);
        if (indiceDonatario>0) 
        {
            PanelDonatario.setLayout(new GridLayout(indiceDonatario, 0));
        } 
        PanelDonatario.updateUI();
        if (indiceDonatario==0)
        {
            jBEliminarDonatario.setVisible(false);
        }
    }//GEN-LAST:event_jBEliminarDonatarioActionPerformed

    private void jBAgregarDonatarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarDonatarioActionPerformed
        AgregarDonatario();
    }//GEN-LAST:event_jBAgregarDonatarioActionPerformed
    public void AgregarDonatario()
    {
        DonacionDonatario nuevoDonatario = new DonacionDonatario(indiceDonatario);
        nuevoDonatario.jRBFisicaDonatario.addActionListener(this);
        nuevoDonatario.jRBMoralDonatario.addActionListener(this);
        nuevoDonatario.jLNombreMoralDonatario.setVisible(false);
        nuevoDonatario.jTNombreDonatarioMoral.setVisible(false);
        PanelDonatario.add(nuevoDonatario);
        this.controlDonatarios.put(indiceDonatario, nuevoDonatario);
        indiceDonatario++;
        PanelDonatario.setLayout(new GridLayout(indiceDonatario, 0));
        PanelDonatario.updateUI();
        jBEliminarDonatario.setVisible(true);
    }
    private void jRBFisicaDonatarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBFisicaDonatarioActionPerformed
        // TODO add your handling code here:
        jLNombreMoralDonatario.setVisible(false);
        jTNombreDonatarioMoral.setVisible(false);
        jLNombreFisicaDonatario.setVisible(true);
        jLApMaDonatarioFisica.setVisible(true);
        jLApPaDonatarioFisica.setVisible(true);
        jTNombreDonatarioFisica.setVisible(true);
        jTApMaDonatarioFisica.setVisible(true);
        jTApPaDonatarioFisica.setVisible(true);
    }//GEN-LAST:event_jRBFisicaDonatarioActionPerformed

    private void jRBMoralDonatarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBMoralDonatarioActionPerformed
        // TODO add your handling code here:
        jLNombreFisicaDonatario.setVisible(false);
        jLApMaDonatarioFisica.setVisible(false);
        jLApPaDonatarioFisica.setVisible(false);
        jTNombreDonatarioFisica.setVisible(false);
        jTApMaDonatarioFisica.setVisible(false);
        jTApPaDonatarioFisica.setVisible(false);
        jLNombreMoralDonatario.setVisible(true);
        jTNombreDonatarioMoral.setVisible(true);
    }//GEN-LAST:event_jRBMoralDonatarioActionPerformed

    private void jTNombreDonatarioMoralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreDonatarioMoralActionPerformed
         jTNombreDonatarioMoral.transferFocus();
    }//GEN-LAST:event_jTNombreDonatarioMoralActionPerformed

    private void jRBFisicaDonanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBFisicaDonanteActionPerformed
        // TODO add your handling code here:
        jLNombreMoralDonante.setVisible(false);
        jTNombreDonanteMoral.setVisible(false);      
        jTNombreDonanteFisica.setVisible(true);
        jTApPaDonanteFisica.setVisible(true);
        jTApMaDonanteFisica.setVisible(true);
        jLNombreFisicaDonante.setVisible(true);
        jLApPaDonanteFisica.setVisible(true);
        jLApMaDonanteFisica.setVisible(true);    
    }//GEN-LAST:event_jRBFisicaDonanteActionPerformed

    private void jRBMoralDonanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBMoralDonanteActionPerformed
        // TODO add your handling code here:
        jLNombreMoralDonante.setVisible(true);
        jTNombreDonanteMoral.setVisible(true);
        jTNombreDonanteFisica.setVisible(false);
        jTApPaDonanteFisica.setVisible(false);
        jTApMaDonanteFisica.setVisible(false);
        jLNombreFisicaDonante.setVisible(false);
        jLApPaDonanteFisica.setVisible(false);
        jLApMaDonanteFisica.setVisible(false);
    }//GEN-LAST:event_jRBMoralDonanteActionPerformed

    private void jTNombreDonanteMoralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreDonanteMoralActionPerformed
        jTNombreDonanteMoral.transferFocus();
    }//GEN-LAST:event_jTNombreDonanteMoralActionPerformed

    private void jBAgregarDonanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarDonanteActionPerformed
        AgregarDonante();
    }//GEN-LAST:event_jBAgregarDonanteActionPerformed
    public void AgregarDonante()
    {
        DonacionDonante nuevoDonante = new DonacionDonante(indiceDonante);
        nuevoDonante.jRBFisicaDonante.addActionListener(this);
        nuevoDonante.jRBMoralDonante.addActionListener(this);
        nuevoDonante.jLNombreMoralDonante.setVisible(false);
        nuevoDonante.jTNombreDonanteMoral.setVisible(false);
        PanelDonante.add(nuevoDonante);
        this.controlDonantes.put(indiceDonante, nuevoDonante);
        indiceDonante++;
        PanelDonante.setLayout(new GridLayout(indiceDonante, 0));
        PanelDonante.updateUI();
        jBEliminarDonante.setVisible(true);
    }
    private void jBEliminarDonanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarDonanteActionPerformed
        indiceDonante--;
        PanelDonante.remove(indiceDonante);
        this.controlDonantes.remove(indiceDonante);
        if (indiceDonante>0) 
        {
            PanelDonante.setLayout(new GridLayout(indiceDonante, 0));
        } 
        PanelDonante.updateUI();
        if (indiceDonante==0)
        {
            jBEliminarDonante.setVisible(false);
        }
    }//GEN-LAST:event_jBEliminarDonanteActionPerformed

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
        int banderadonatarios = controlDonatarios.size();
        int banderadonantes = controlDonantes.size();
        datos.getNumeroDonatarios(banderadonatarios);
        datos.getNumeroDonantes(banderadonantes);
        String[][] donatariosdatos = new String[banderadonatarios][4];
        String[][] donantesdatos = new String[banderadonantes][4];
        Iterator it = controlDonatarios.entrySet().iterator();
        Iterator it2 = controlDonantes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            //se obtiene el KEY -> identificador unico
            int itm = (int) entry.getKey();
            //si comando de componente es igual a comando pulsado
          
                //se recupera el contenido del JTextfield
                if(((DonacionDonatario) entry.getValue()).jRBFisicaDonatario.isSelected()==true)
                {                    
                    String dos = ((DonacionDonatario) entry.getValue()).jTNombreDonatarioFisica.getText();
                    String tres = ((DonacionDonatario) entry.getValue()).jTApPaDonatario.getText();
                    String cuatro = ((DonacionDonatario) entry.getValue()).jTApMaDonatario.getText();
                    donatariosdatos[itm][0]="Persona Física";
                    donatariosdatos[itm][1]= dos;
                    donatariosdatos[itm][2]= tres;
                    donatariosdatos[itm][3]= cuatro;
                }
                else
                {
                    String uno = ((DonacionDonatario) entry.getValue()).jTNombreDonatarioMoral.getText();
                    donatariosdatos[itm][0]="Persona Moral";
                    donatariosdatos[itm][1]= uno;
                    donatariosdatos[itm][2]= "";
                    donatariosdatos[itm][3]= "";
                }
                
            }
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry)it2.next();
            //se obtiene el KEY -> identificador unico
            int itm2 = (int) entry.getKey();
            //si comando de componente es igual a comando pulsado
          
                //se recupera el contenido del JTextfield
                if(((DonacionDonante) entry.getValue()).jRBFisicaDonante.isSelected()==true)
                {                    
                    String dos = ((DonacionDonante) entry.getValue()).jTNombreDonanteFisica.getText();
                    String tres = ((DonacionDonante) entry.getValue()).jTApPaDonante.getText();
                    String cuatro = ((DonacionDonante) entry.getValue()).jTApMaDonante.getText();
                    donantesdatos[itm2][0]="Persona Física";
                    donantesdatos[itm2][1]= dos;
                    donantesdatos[itm2][2]= tres;
                    donantesdatos[itm2][3]= cuatro;
                }
                else
                {
                    String uno = ((DonacionDonante) entry.getValue()).jTNombreDonanteMoral.getText();
                    donantesdatos[itm2][0]="Persona Moral";
                    donantesdatos[itm2][1]= uno;
                    donantesdatos[itm2][2]= "";
                    donantesdatos[itm2][3]= "";
                }
            }
        datos.getDonatarios(donatariosdatos);
        datos.getDonantes(donantesdatos);
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

    private void jRBFisicaDonanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBFisicaDonanteMouseClicked
    }//GEN-LAST:event_jRBFisicaDonanteMouseClicked

    private void jRBFisicaDonatarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBFisicaDonatarioMouseClicked
     }//GEN-LAST:event_jRBFisicaDonatarioMouseClicked

    private void jRBMoralDonatarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBMoralDonatarioMouseClicked
    }//GEN-LAST:event_jRBMoralDonatarioMouseClicked

    private void jRBMoralDonanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBMoralDonanteMouseClicked
    }//GEN-LAST:event_jRBMoralDonanteMouseClicked

    private void jTNombreDonatarioFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreDonatarioFisicaActionPerformed
        jTNombreDonatarioFisica.transferFocus();
    }//GEN-LAST:event_jTNombreDonatarioFisicaActionPerformed

    private void jTApPaDonatarioFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApPaDonatarioFisicaActionPerformed
        jTApPaDonatarioFisica.transferFocus();
    }//GEN-LAST:event_jTApPaDonatarioFisicaActionPerformed

    private void jTApMaDonatarioFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApMaDonatarioFisicaActionPerformed
        jTApMaDonatarioFisica.transferFocus();
    }//GEN-LAST:event_jTApMaDonatarioFisicaActionPerformed

    private void jTNombreDonanteFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreDonanteFisicaActionPerformed
        jTNombreDonanteFisica.transferFocus();
    }//GEN-LAST:event_jTNombreDonanteFisicaActionPerformed

    private void jTApPaDonanteFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApPaDonanteFisicaActionPerformed
        jTApPaDonanteFisica.transferFocus();
    }//GEN-LAST:event_jTApPaDonanteFisicaActionPerformed

    private void jTApMaDonanteFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApMaDonanteFisicaActionPerformed
        jTApMaDonanteFisica.transferFocus();
    }//GEN-LAST:event_jTApMaDonanteFisicaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jBHabilitarEdicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBHabilitarEdicionActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "¿Seguro que quieres habilitar el poder actualizar este expediente? ",
            "Habilitar actualizar sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
    {
        jBTerminar.setVisible(true);
        jBHabilitarEdicion.setVisible(false);
        }
    }//GEN-LAST:event_jBHabilitarEdicionActionPerformed

    private void jTInstrumentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTInstrumentoFocusLost
        if(jTInstrumento.getText().isEmpty())
        {
            jValido.setVisible(false);
            jInvalido.setVisible(true);
            JIntrumentoInformacion.setText("El campo instrumento es obligatorio.");
            jBTerminar.setEnabled(false);
        }else
        {
            if(ValidarInstrumentoPropio(jTInstrumento.getText())== true)
            {
                jValido.setVisible(true);
                jInvalido.setVisible(false);
                JIntrumentoInformacion.setText("Campo instrumento correcto.");
                jBTerminar.setEnabled(true);
            }
            else
            {
                if(ValidacionInstrumento(jTInstrumento.getText())==true || ValidacionInstrumento1(jTInstrumento.getText())==true)
                {
                    jInvalido.setVisible(true);
                    jValido.setVisible(false);
                    JIntrumentoInformacion.setText("Este No. de instrumento ya esta en el expediente " + NoEx + ".");
                    jBTerminar.setEnabled(false);
                }
                else
                {
                    jInvalido.setVisible(false);
                    jValido.setVisible(true);
                    JIntrumentoInformacion.setText("No. de instrumento disponible y correcto.");
                    jBTerminar.setEnabled(true);
                }
            }
        }
    }//GEN-LAST:event_jTInstrumentoFocusLost

    public void OcultarIntrumento()
    {
        jValido.setVisible(true);
        jInvalido.setVisible(false);
        JIntrumentoInformacion.setText("Campo instrumento correcto.");
        jBTerminar.setEnabled(true);
    }
    private boolean ValidacionInstrumento1(String Instrumento)
    {
        boolean existe = false; 
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        String sSQL="SELECT IDNoExpediente, Instrumento FROM donacion WHERE Instrumento='" + Instrumento + "'";
        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            if (rs.next()) 
            {
               existe=true;
               NoEx = rs.getInt("IDNoExpediente");
            }
        } catch (SQLException ex) {
            System.out.println("Error al contectar la base de datos" + ex);
        }finally
        {
            mysql.desconectar();
        }
        return existe;
    }
    private boolean ValidacionInstrumento(String Instrumento)
    {
        boolean existe=false; 
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        String sSQL="SELECT IDNoExpediente, Instrumento FROM compraventa WHERE Instrumento='" + Instrumento + "'";
        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            if (rs.next()) 
            {
               existe=true;
               NoEx = rs.getInt("IDNoExpediente");
            }
        } catch (SQLException ex) {
            System.out.println("Error al contectar la base de datos" + ex);
        }finally
        {
            mysql.desconectar();
        }
        return existe;
    }
    public boolean ValidarInstrumentoPropio(String Instrumento)
    {
        boolean validado = false;
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        String sSQL="SELECT Instrumento, IDNoExpediente FROM donacion WHERE Instrumento='" + Instrumento + "' AND IDNoExpediente='" + AbrirExpediente.getNoExpedinte() + "'";
        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            if (rs.next()) 
            {
               validado = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error al contectar la base de datos" + ex);
        }finally
        {
            mysql.desconectar();
        }
        return validado;
    }
    /**
     * Main de la clase, inicia la ventana.
     * @param args
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                Logger.getLogger(DonacionVisualizacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            new DonacionVisualizacion().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JIntrumentoInformacion;
    private javax.swing.JPanel PanelDonante;
    private javax.swing.JPanel PanelDonatario;
    private javax.swing.JPanel Principal;
    private javax.swing.JButton jBAgregarDonante;
    private javax.swing.JButton jBAgregarDonatario;
    private javax.swing.JButton jBEliminarDonante;
    private javax.swing.JButton jBEliminarDonatario;
    public javax.swing.JButton jBHabilitarEdicion;
    public javax.swing.JButton jBTerminar;
    private javax.swing.JButton jButton1;
    public javax.swing.JComboBox<String> jCBEstadoRepublica;
    public javax.swing.JComboBox<String> jCBMunicipio;
    public com.toedter.calendar.JDateChooser jDCFecha;
    private javax.swing.JLabel jInvalido;
    public javax.swing.JLabel jLApMaDonanteFisica;
    public javax.swing.JLabel jLApMaDonatarioFisica;
    public javax.swing.JLabel jLApPaDonanteFisica;
    public javax.swing.JLabel jLApPaDonatarioFisica;
    public javax.swing.JLabel jLCalle;
    public javax.swing.JLabel jLColonia;
    public javax.swing.JLabel jLEstadoRepublica;
    public javax.swing.JLabel jLFecha2;
    public javax.swing.JLabel jLFolioReal;
    public javax.swing.JLabel jLInstrumento;
    public javax.swing.JLabel jLMunicipio;
    public javax.swing.JLabel jLNoExterior;
    public javax.swing.JLabel jLNoInterior;
    public javax.swing.JLabel jLNombreFisicaDonante;
    public javax.swing.JLabel jLNombreFisicaDonatario;
    public javax.swing.JLabel jLNombreMoralDonante;
    public javax.swing.JLabel jLNombreMoralDonatario;
    public javax.swing.JLabel jLObservaciones;
    public javax.swing.JLabel jLTipoActo;
    private javax.swing.JLabel jLTituloDonante;
    private javax.swing.JLabel jLTituloDonatario;
    public javax.swing.JLabel jLTomo;
    private javax.swing.JPanel jPPDF;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JRadioButton jRBFisicaDonante;
    public static javax.swing.JRadioButton jRBFisicaDonatario;
    public static javax.swing.JRadioButton jRBMoralDonante;
    public static javax.swing.JRadioButton jRBMoralDonatario;
    private javax.swing.JScrollPane jSPDonante;
    private javax.swing.JScrollPane jSPDonatario;
    private javax.swing.JScrollPane jSPPrincipal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public javax.swing.JTextArea jTAObservaciones;
    public static javax.swing.JTextField jTApMaDonanteFisica;
    public static javax.swing.JTextField jTApMaDonatarioFisica;
    public static javax.swing.JTextField jTApPaDonanteFisica;
    public static javax.swing.JTextField jTApPaDonatarioFisica;
    public javax.swing.JTextField jTCalle;
    public javax.swing.JTextField jTColonia;
    public javax.swing.JTextField jTFolioReal;
    public javax.swing.JTextField jTInstrumento;
    public javax.swing.JTextField jTNoExterior;
    public javax.swing.JTextField jTNoInterior;
    public static javax.swing.JTextField jTNombreDonanteFisica;
    public static javax.swing.JTextField jTNombreDonanteMoral;
    public static javax.swing.JTextField jTNombreDonatarioFisica;
    public static javax.swing.JTextField jTNombreDonatarioMoral;
    public javax.swing.JTextField jTTipoActo;
    public javax.swing.JTextField jTTomo;
    private javax.swing.JLabel jValido;
    private javax.swing.ButtonGroup tipoPersonaDonante;
    private javax.swing.ButtonGroup tipoPersonaDonatario;
    private javax.swing.JLabel tituloVentanaDonacion;
    private javax.swing.JLabel tituloVentanaNoExpedienteDonacion;
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
        Iterator it = controlDonatarios.entrySet().iterator();
        Iterator it2 = controlDonantes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            //se obtiene el KEY -> identificador unico
            String itm = entry.getKey().toString();
            //si comando de componente es igual a comando pulsado
            if(comando.equals("indicefDonatario" + itm))
            {                              
                ((DonacionDonatario) entry.getValue()).jLNombreFisicaDonatario.setVisible(true);
                ((DonacionDonatario) entry.getValue()).jLApPaDonatario.setVisible(true);
                ((DonacionDonatario) entry.getValue()).jLApMaDonatario.setVisible(true);
                ((DonacionDonatario) entry.getValue()).jTNombreDonatarioFisica.setVisible(true);
                ((DonacionDonatario) entry.getValue()).jTApPaDonatario.setVisible(true);
                ((DonacionDonatario) entry.getValue()).jTApMaDonatario.setVisible(true);
                ((DonacionDonatario) entry.getValue()).jLNombreMoralDonatario.setVisible(false);
                ((DonacionDonatario) entry.getValue()).jTNombreDonatarioMoral.setVisible(false);
            }
            else if(comando.equals("indicemDonatario" + itm))
            {
                ((DonacionDonatario) entry.getValue()).jLNombreFisicaDonatario.setVisible(false);
                ((DonacionDonatario) entry.getValue()).jLApPaDonatario.setVisible(false);
                ((DonacionDonatario) entry.getValue()).jLApMaDonatario.setVisible(false);
                ((DonacionDonatario) entry.getValue()).jTNombreDonatarioFisica.setVisible(false);
                ((DonacionDonatario) entry.getValue()).jTApPaDonatario.setVisible(false);
                ((DonacionDonatario) entry.getValue()).jTApMaDonatario.setVisible(false);
                ((DonacionDonatario) entry.getValue()).jLNombreMoralDonatario.setVisible(true);
                ((DonacionDonatario) entry.getValue()).jTNombreDonatarioMoral.setVisible(true);
            }
        }
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry)it2.next();
            //se obtiene el KEY -> identificador unico
            String itm2 = entry.getKey().toString();
            //si comando de componente es igual a comando pulsado
            if(comando.equals("indicefDonante" + itm2))
            {
                //mostramos resultado
                ((DonacionDonante) entry.getValue()).jLNombreFisicaDonante.setVisible(true);
                ((DonacionDonante) entry.getValue()).jLApPaDonante.setVisible(true);
                ((DonacionDonante) entry.getValue()).jLApMaDonante.setVisible(true);
                ((DonacionDonante) entry.getValue()).jTNombreDonanteFisica.setVisible(true);
                ((DonacionDonante) entry.getValue()).jTApPaDonante.setVisible(true);
                ((DonacionDonante) entry.getValue()).jTApMaDonante.setVisible(true);
                ((DonacionDonante) entry.getValue()).jLNombreMoralDonante.setVisible(false);
                ((DonacionDonante) entry.getValue()).jTNombreDonanteMoral.setVisible(false);
                

            }
            else if(comando.equals("indicemDonante" + itm2))
            {
                ((DonacionDonante) entry.getValue()).jLNombreFisicaDonante.setVisible(false);
                ((DonacionDonante) entry.getValue()).jLApPaDonante.setVisible(false);
                ((DonacionDonante) entry.getValue()).jLApMaDonante.setVisible(false);
                ((DonacionDonante) entry.getValue()).jTNombreDonanteFisica.setVisible(false);
                ((DonacionDonante) entry.getValue()).jTApPaDonante.setVisible(false);
                ((DonacionDonante) entry.getValue()).jTApMaDonante.setVisible(false);
                ((DonacionDonante) entry.getValue()).jLNombreMoralDonante.setVisible(true);
                ((DonacionDonante) entry.getValue()).jTNombreDonanteMoral.setVisible(true);
            }
        }
    }    
}
