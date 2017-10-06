package Vista;
import Controlador.*;

/**
 * Librerias importadas para que la clase se pueda ejecutar correctamente * 
 */
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
public class CancelacionHipoteca extends javax.swing.JFrame implements ActionListener{
    private int indiceAcreditado;
    private int indiceAcreditante;
    private DateFormat df = DateFormat.getDateInstance();
    private Map controlAcreditados = new HashMap();
    private Map controlAcreditantes = new HashMap();
    DefaultComboBoxModel modeloEstado, modeloMunicipio;
    Controlador.CancelacionHipoteca datos = new Controlador.CancelacionHipoteca();
    ActualizarTipoexpediente a;

    private void OcultarPanenlesIniciales()
    {
    //Se ocultan los paneles de Acreditado y Acreditante morales al iniciar, y se inicia el indice de control de ambos en 0
        jLNombreMoralAcreditado.setVisible(false);
        jTNombreAcreditadoMoral.setVisible(false);
        jLNombreMoralAcreditante.setVisible(false);
        jTNombreAcreditanteMoral.setVisible(false);
        indiceAcreditado = 0;
        indiceAcreditante = 0;
        if (indiceAcreditado==0) 
        {
                jBEliminarAcreditado.setVisible(false);
        }
        if (indiceAcreditante==0) 
        {
                jBEliminarAcreditante.setVisible(false);
        }
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
        SLetras(jTNombreAcreditadoFisica);
        SLetras(jTApPaAcreditadoFisica);
        SLetras(jTApMaAcreditadoFisica);
        SLetras(jTNombreAcreditanteFisica);
        SLetras(jTApPaAcreditanteFisica);
        SLetras(jTApMaAcreditanteFisica);

        CambiarAMayusculas(jTNombreAcreditadoFisica);
        CambiarAMayusculas(jTApPaAcreditadoFisica);
        CambiarAMayusculas(jTApMaAcreditadoFisica);  
        CambiarAMayusculas(jTNombreAcreditanteFisica);
        CambiarAMayusculas(jTApPaAcreditanteFisica);
        CambiarAMayusculas(jTApMaAcreditanteFisica);
       
        NoCaracteres(jTNombreAcreditadoFisica, 70);
        NoCaracteres(jTApPaAcreditadoFisica, 30);
        NoCaracteres(jTApMaAcreditadoFisica, 30);
        NoCaracteres(jTNombreAcreditadoMoral, 70);
        
        NoCaracteres(jTNombreAcreditanteFisica, 70);
        NoCaracteres(jTApPaAcreditanteFisica, 30);
        NoCaracteres(jTApMaAcreditanteFisica, 30);
        NoCaracteres(jTNombreAcreditanteMoral, 70);
    }
    private void ValidacionAcreditadoPrincipal()
    {
        boolean pasa=false;
        if (jRBFisicaAcreditado.isSelected()==false && jRBMoralAcreditado.isSelected()==false)
        {
            JOptionPane.showMessageDialog(null, "NO has seleccionado el tipo de Acreditado","Sin selección" ,JOptionPane.ERROR_MESSAGE);
            pasa=true;
        }else if(jRBFisicaAcreditado.isSelected()==true)
            {
                if(jTNombreAcreditadoFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del Acreditado","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }else if(jTApPaAcreditadoFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO PATERNO del Acreditado","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }else if(jTApMaAcreditadoFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO MATERNO del Acreditado","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }                           
            }
        else if(jRBMoralAcreditado.isSelected()==true)
        {
                if(jTNombreAcreditadoMoral.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE MORAL del Acreditado","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }           
         }
        if(pasa == false)
        {
            ValidacionAcreditantePrincipal(); 
        }
    }
    private void ValidacionAcreditantePrincipal()
    {
        boolean pasa=false;
        if (jRBFisicaAcreditante.isSelected()==false && jRBMoralAcreditante.isSelected()==false)
        {  
            JOptionPane.showMessageDialog(null, "NO has seleccionado el tipo de Acreditante","Sin selección" ,JOptionPane.ERROR_MESSAGE);
            pasa=true;
        }else
        {
            if(jRBFisicaAcreditante.isSelected()==true)
            {
                if(jTNombreAcreditanteFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del Acreditante","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }else if(jTApPaAcreditanteFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO PATERNO del Acreditante","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }else if(jTApMaAcreditanteFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO MATERNO del Acreditante","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }                           
            }
            else
            {
                if(jTNombreAcreditanteMoral.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE MORAL del Acreditante","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
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
            ValidacionAcreditados();
    }

    private void ValidacionAcreditados()
    {
        boolean pasa=false;
        Iterator it = controlAcreditados.entrySet().iterator();
        while (it.hasNext()) 
        {
            Map.Entry entry = (Map.Entry)it.next();
            //se obtiene el KEY -> identificador unico
            int itm = (int) entry.getKey();
            //si comando de componente es igual a comando pulsado
                //se recupera el contenido del JTextfield
                if((((CancelacionHipotecaAcreditado) entry.getValue()).jRBFisicaAcreditado.isSelected()==false) && (((CancelacionHipotecaAcreditado) entry.getValue()).jRBMoralAcreditado.isSelected()==false))
                {
                    JOptionPane.showMessageDialog(null, "No has seleccionado el tipo de persona en el Acreditado " + (itm+2),"Selección vacia" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }
                else if(((CancelacionHipotecaAcreditado) entry.getValue()).jRBFisicaAcreditado.isSelected()==true)
                {
                    if(((CancelacionHipotecaAcreditado) entry.getValue()).jTNombreAcreditadoFisica.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del Acreditado " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }else if(((CancelacionHipotecaAcreditado) entry.getValue()).jTApPaAcreditado.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO PATERNO del Acreditado " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }else if(((CancelacionHipotecaAcreditado) entry.getValue()).jTApMaAcreditado.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO MATERNO del Acreditado " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }                             
                }else if(((CancelacionHipotecaAcreditado) entry.getValue()).jRBMoralAcreditado.isSelected()==true)
                {
                    if(((CancelacionHipotecaAcreditado) entry.getValue()).jTNombreAcreditadoMoral.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del Acreditado MORAL " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }
                }
        }
        if(pasa == false)
        {
            ValidacionAcreditantes();
        }
    }
    private void ValidacionAcreditantes()
    {
        boolean pasa=false;
        Iterator it = controlAcreditantes.entrySet().iterator();
        while (it.hasNext()) 
        {
            Map.Entry entry = (Map.Entry)it.next();
            //se obtiene el KEY -> identificador unico
            int itm = (int) entry.getKey();
            //si comando de componente es igual a comando pulsado
                //se recupera el contenido del JTextfield
                if((((CancelacionHipotecaAcreditante) entry.getValue()).jRBFisicaAcreditante.isSelected()==false) && (((CancelacionHipotecaAcreditante) entry.getValue()).jRBMoralAcreditante.isSelected()==false))
                {
                    JOptionPane.showMessageDialog(null, "No has seleccionado el tipo de persona en el Acreditante " + (itm+2),"Selección vacia" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }
                else if(((CancelacionHipotecaAcreditante) entry.getValue()).jRBFisicaAcreditante.isSelected()==true)
                {
                    if(((CancelacionHipotecaAcreditante) entry.getValue()).jTNombreAcreditanteFisica.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del Acreditante " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }else if(((CancelacionHipotecaAcreditante) entry.getValue()).jTApPaAcreditante.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO PATERNO del Acreditante " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }else if(((CancelacionHipotecaAcreditante) entry.getValue()).jTApMaAcreditante.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO MATERNO del Acreditante " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }                             
                }else if(((CancelacionHipotecaAcreditante) entry.getValue()).jRBMoralAcreditante.isSelected()==true)
                {
                    if(((CancelacionHipotecaAcreditante) entry.getValue()).jTNombreAcreditanteMoral.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del Acreditante MORAL " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
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
        ValidacionAcreditadoPrincipal();
    }
    /**
     * Este metodo envía todos los datos a la clase contralador de Donacion donde se guardaran los datos ya validados
     */
    public void GuardarBD()
    {
        System.out.println("FELICITACIONES, Has pasado todas las validaciones!");
            datos.getObservaciones(jTAObservaciones.getText());
            datos.capturaFinal();
    }
    public void close()
    {
        if (JOptionPane.showConfirmDialog(rootPane, "Si sales de PERDERA TODO el PROCESO",
                "Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
        {
            if(Vista.Principal.ComprobarExpedienteEnBD(BDdocumentos.getNoExpediente())==1)
            {
                Vista.Principal.EliminarCasoDeLaBase(BDdocumentos.getNoExpediente());
                Vista.Principal.EliminarExpedienteCompleto(BDdocumentos.getNoExpediente());
                EliminarContenidoCarpetas.iniciar(ValoresInicialesPrograma.getCarpetaArchivosLocal() + "\\" + BDdocumentos.getNoExpediente());
            }
            else
            {
                Vista.Principal.EliminarExpedienteCompleto(BDdocumentos.getNoExpediente());
                EliminarContenidoCarpetas.iniciar(ValoresInicialesPrograma.getCarpetaArchivosLocal() + "\\" + BDdocumentos.getNoExpediente());
            }
             dispose();
        }
    }
    /**
     * Constructor de la clase
     */
    public CancelacionHipoteca() 
    {
        //JFrame iniciando compnente, en el centro de la pantalla, sin reescalado
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                close();  
            }
        });
        setIconImage(new ImageIcon(getClass().getResource("Imagenes/IconoNotaria.png")).getImage());
        setExtendedState(MAXIMIZED_BOTH);
        tituloVentanaNoExpedienteCancelacionHipoteca.setText("No. Expediente: #" + BDdocumentos.getNoExpediente());
        OcultarPanenlesIniciales();
        ValidacionLetrasNumerosTiempoReal();
        VistaPrevia vistaPreviaPDF = new VistaPrevia();        
        jPPDF.add(vistaPreviaPDF);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoPersonaAcreditado = new javax.swing.ButtonGroup();
        tipoPersonaAcreditante = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jSPPrincipal = new javax.swing.JScrollPane();
        Principal = new javax.swing.JPanel();
        jBEliminarAcreditado = new javax.swing.JButton();
        jBAgregarAcreditado = new javax.swing.JButton();
        jLTituloAcreditado = new javax.swing.JLabel();
        jRBFisicaAcreditado = new javax.swing.JRadioButton();
        jRBMoralAcreditado = new javax.swing.JRadioButton();
        jLNombreFisicaAcreditado = new javax.swing.JLabel();
        jLNombreMoralAcreditado = new javax.swing.JLabel();
        jTNombreAcreditadoMoral = new javax.swing.JTextField();
        jTNombreAcreditadoFisica = new javax.swing.JTextField();
        jTApPaAcreditadoFisica = new javax.swing.JTextField();
        jLApPaAcreditadoFisica = new javax.swing.JLabel();
        jTApMaAcreditadoFisica = new javax.swing.JTextField();
        tituloVentanaNoExpedienteCancelacionHipoteca = new javax.swing.JLabel();
        jSPAcreditado = new javax.swing.JScrollPane();
        PanelAcreditado = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLTituloAcreditante = new javax.swing.JLabel();
        jRBFisicaAcreditante = new javax.swing.JRadioButton();
        jRBMoralAcreditante = new javax.swing.JRadioButton();
        jTNombreAcreditanteFisica = new javax.swing.JTextField();
        jLNombreFisicaAcreditante = new javax.swing.JLabel();
        jLNombreMoralAcreditante = new javax.swing.JLabel();
        jTNombreAcreditanteMoral = new javax.swing.JTextField();
        jLApMaAcreditanteFisica = new javax.swing.JLabel();
        jTApPaAcreditanteFisica = new javax.swing.JTextField();
        jTApMaAcreditanteFisica = new javax.swing.JTextField();
        jBAgregarAcreditante = new javax.swing.JButton();
        jBEliminarAcreditante = new javax.swing.JButton();
        jSPAcreditante = new javax.swing.JScrollPane();
        PanelAcreditante = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLObservaciones = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTAObservaciones = new javax.swing.JTextArea();
        jBTerminar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLApMaAcreditadoFisica = new javax.swing.JLabel();
        jLApPaAcreditanteFisica = new javax.swing.JLabel();
        tituloVentanaCancelacionHipoteca = new javax.swing.JLabel();
        jBCambiarTipo = new javax.swing.JButton();
        jPPDF = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cancelación Hipoteca Captura");

        jPanel2.setBackground(new java.awt.Color(52, 82, 142));
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

        jBEliminarAcreditado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Eliminar Acreditado.png"))); // NOI18N
        jBEliminarAcreditado.setBorderPainted(false);
        jBEliminarAcreditado.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Eliminar Acreditado Grande.png"))); // NOI18N
        jBEliminarAcreditado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminarAcreditadoActionPerformed(evt);
            }
        });

        jBAgregarAcreditado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Agregar acreditado normal.png"))); // NOI18N
        jBAgregarAcreditado.setBorderPainted(false);
        jBAgregarAcreditado.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Agregar acreditado Grande.png"))); // NOI18N
        jBAgregarAcreditado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAgregarAcreditadoActionPerformed(evt);
            }
        });

        jLTituloAcreditado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLTituloAcreditado.setText("Acreditado");

        tipoPersonaAcreditado.add(jRBFisicaAcreditado);
        jRBFisicaAcreditado.setText("Persona Física");
        jRBFisicaAcreditado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBFisicaAcreditadoMouseClicked(evt);
            }
        });
        jRBFisicaAcreditado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBFisicaAcreditadoActionPerformed(evt);
            }
        });

        tipoPersonaAcreditado.add(jRBMoralAcreditado);
        jRBMoralAcreditado.setText("Persona Moral");
        jRBMoralAcreditado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBMoralAcreditadoMouseClicked(evt);
            }
        });
        jRBMoralAcreditado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBMoralAcreditadoActionPerformed(evt);
            }
        });

        jLNombreFisicaAcreditado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreFisicaAcreditado.setText("*Nombre:");

        jLNombreMoralAcreditado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreMoralAcreditado.setText("*Nombre: ");

        jTNombreAcreditadoMoral.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreAcreditadoMoral.setPreferredSize(new java.awt.Dimension(250, 26));
        jTNombreAcreditadoMoral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreAcreditadoMoralActionPerformed(evt);
            }
        });

        jTNombreAcreditadoFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreAcreditadoFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreAcreditadoFisicaActionPerformed(evt);
            }
        });

        jTApPaAcreditadoFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApPaAcreditadoFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApPaAcreditadoFisicaActionPerformed(evt);
            }
        });

        jLApPaAcreditadoFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApPaAcreditadoFisica.setText("*Apellido Paterno:");

        jTApMaAcreditadoFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApMaAcreditadoFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApMaAcreditadoFisicaActionPerformed(evt);
            }
        });

        tituloVentanaNoExpedienteCancelacionHipoteca.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tituloVentanaNoExpedienteCancelacionHipoteca.setText("No. Expediente: #");

        jSPAcreditado.setBorder(null);
        jSPAcreditado.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jSPAcreditado.setHorizontalScrollBar(null);

        PanelAcreditado.setBackground(new java.awt.Color(255, 255, 255));
        PanelAcreditado.setAlignmentX(0.0F);
        PanelAcreditado.setAlignmentY(0.0F);

        javax.swing.GroupLayout PanelAcreditadoLayout = new javax.swing.GroupLayout(PanelAcreditado);
        PanelAcreditado.setLayout(PanelAcreditadoLayout);
        PanelAcreditadoLayout.setHorizontalGroup(
            PanelAcreditadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 986, Short.MAX_VALUE)
        );
        PanelAcreditadoLayout.setVerticalGroup(
            PanelAcreditadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );

        jSPAcreditado.setViewportView(PanelAcreditado);

        jLTituloAcreditante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLTituloAcreditante.setText("Acreditante");

        tipoPersonaAcreditante.add(jRBFisicaAcreditante);
        jRBFisicaAcreditante.setText("Persona Física");
        jRBFisicaAcreditante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBFisicaAcreditanteMouseClicked(evt);
            }
        });
        jRBFisicaAcreditante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBFisicaAcreditanteActionPerformed(evt);
            }
        });

        tipoPersonaAcreditante.add(jRBMoralAcreditante);
        jRBMoralAcreditante.setText("Persona Moral");
        jRBMoralAcreditante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBMoralAcreditanteMouseClicked(evt);
            }
        });
        jRBMoralAcreditante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBMoralAcreditanteActionPerformed(evt);
            }
        });

        jTNombreAcreditanteFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreAcreditanteFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreAcreditanteFisicaActionPerformed(evt);
            }
        });

        jLNombreFisicaAcreditante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreFisicaAcreditante.setText("*Nombre: ");

        jLNombreMoralAcreditante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreMoralAcreditante.setText("*Nombre: ");

        jTNombreAcreditanteMoral.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreAcreditanteMoral.setPreferredSize(new java.awt.Dimension(250, 26));
        jTNombreAcreditanteMoral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreAcreditanteMoralActionPerformed(evt);
            }
        });

        jLApMaAcreditanteFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApMaAcreditanteFisica.setText("*Apellido Materno:");

        jTApPaAcreditanteFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApPaAcreditanteFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApPaAcreditanteFisicaActionPerformed(evt);
            }
        });

        jTApMaAcreditanteFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApMaAcreditanteFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApMaAcreditanteFisicaActionPerformed(evt);
            }
        });

        jBAgregarAcreditante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Agregar acreditante normal.png"))); // NOI18N
        jBAgregarAcreditante.setBorderPainted(false);
        jBAgregarAcreditante.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Agregar acreditante Grande.png"))); // NOI18N
        jBAgregarAcreditante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAgregarAcreditanteActionPerformed(evt);
            }
        });

        jBEliminarAcreditante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Eliminar Acreditante.png"))); // NOI18N
        jBEliminarAcreditante.setBorderPainted(false);
        jBEliminarAcreditante.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Eliminar Acreditante Grande.png"))); // NOI18N
        jBEliminarAcreditante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminarAcreditanteActionPerformed(evt);
            }
        });

        jSPAcreditante.setBorder(null);
        jSPAcreditante.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jSPAcreditante.setHorizontalScrollBar(null);

        PanelAcreditante.setBackground(new java.awt.Color(255, 255, 255));
        PanelAcreditante.setAlignmentX(0.0F);
        PanelAcreditante.setAlignmentY(0.0F);

        javax.swing.GroupLayout PanelAcreditanteLayout = new javax.swing.GroupLayout(PanelAcreditante);
        PanelAcreditante.setLayout(PanelAcreditanteLayout);
        PanelAcreditanteLayout.setHorizontalGroup(
            PanelAcreditanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 986, Short.MAX_VALUE)
        );
        PanelAcreditanteLayout.setVerticalGroup(
            PanelAcreditanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );

        jSPAcreditante.setViewportView(PanelAcreditante);

        jLObservaciones.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLObservaciones.setText("Observaciones:");

        jTAObservaciones.setColumns(20);
        jTAObservaciones.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jTAObservaciones.setRows(5);
        jTAObservaciones.setName(""); // NOI18N
        jTAObservaciones.setSelectionColor(new java.awt.Color(41, 168, 73));
        jScrollPane1.setViewportView(jTAObservaciones);

        jBTerminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Crear Acto Notarial.png"))); // NOI18N
        jBTerminar.setBorderPainted(false);
        jBTerminar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Crear Acto Notarial Grande.png"))); // NOI18N
        jBTerminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTerminarActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Cancelar Acto Notarial.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Cancelar Acto Notarial Grande.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLApMaAcreditadoFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApMaAcreditadoFisica.setText("*Apellido Materno:");

        jLApPaAcreditanteFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApPaAcreditanteFisica.setText("*Apellido Paterno:");

        tituloVentanaCancelacionHipoteca.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tituloVentanaCancelacionHipoteca.setText("Cancelación Hipoteca");

        jBCambiarTipo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jBCambiarTipo.setText("Cambiar el tipo de expediente");
        jBCambiarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCambiarTipoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PrincipalLayout = new javax.swing.GroupLayout(Principal);
        Principal.setLayout(PrincipalLayout);
        PrincipalLayout.setHorizontalGroup(
            PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLTituloAcreditante)
                .addGap(385, 385, 385))
            .addGroup(PrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PrincipalLayout.createSequentialGroup()
                                .addGap(303, 303, 303)
                                .addComponent(jRBFisicaAcreditado)
                                .addGap(18, 18, 18)
                                .addComponent(jRBMoralAcreditado))
                            .addGroup(PrincipalLayout.createSequentialGroup()
                                .addGap(305, 305, 305)
                                .addComponent(jRBFisicaAcreditante)
                                .addGap(19, 19, 19)
                                .addComponent(jRBMoralAcreditante))
                            .addGroup(PrincipalLayout.createSequentialGroup()
                                .addGap(401, 401, 401)
                                .addComponent(jLTituloAcreditado)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrincipalLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PrincipalLayout.createSequentialGroup()
                                .addComponent(jBAgregarAcreditado, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBEliminarAcreditado, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PrincipalLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addComponent(jLNombreFisicaAcreditante)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTNombreAcreditanteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLApPaAcreditanteFisica)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTApPaAcreditanteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLApMaAcreditanteFisica)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTApMaAcreditanteFisica))
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addComponent(jLNombreMoralAcreditante)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTNombreAcreditanteMoral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(233, 233, 233))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrincipalLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addComponent(tituloVentanaCancelacionHipoteca)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jBCambiarTipo)
                                        .addGap(57, 57, 57)
                                        .addComponent(tituloVentanaNoExpedienteCancelacionHipoteca))
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLNombreFisicaAcreditado)
                                            .addComponent(jLNombreMoralAcreditado))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(PrincipalLayout.createSequentialGroup()
                                                .addComponent(jTNombreAcreditadoMoral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(237, 237, 237))
                                            .addGroup(PrincipalLayout.createSequentialGroup()
                                                .addComponent(jTNombreAcreditadoFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLApPaAcreditadoFisica)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTApPaAcreditadoFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLApMaAcreditadoFisica)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTApMaAcreditadoFisica))))))
                            .addGroup(PrincipalLayout.createSequentialGroup()
                                .addComponent(jBAgregarAcreditante, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBEliminarAcreditante, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrincipalLayout.createSequentialGroup()
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PrincipalLayout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBTerminar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrincipalLayout.createSequentialGroup()
                                .addComponent(jLObservaciones)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrincipalLayout.createSequentialGroup()
                                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jSPAcreditante, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE)
                                    .addComponent(jSPAcreditado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(23, 23, 23))))
        );
        PrincipalLayout.setVerticalGroup(
            PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrincipalLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tituloVentanaNoExpedienteCancelacionHipoteca)
                    .addComponent(tituloVentanaCancelacionHipoteca)
                    .addComponent(jBCambiarTipo))
                .addGap(5, 5, 5)
                .addComponent(jLTituloAcreditado)
                .addGap(5, 5, 5)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRBFisicaAcreditado)
                    .addComponent(jRBMoralAcreditado))
                .addGap(18, 18, 18)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTNombreAcreditadoFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLApPaAcreditadoFisica)
                        .addComponent(jTApPaAcreditadoFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLApMaAcreditadoFisica)
                        .addComponent(jTApMaAcreditadoFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLNombreFisicaAcreditado)))
                .addGap(8, 8, 8)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLNombreMoralAcreditado)
                    .addComponent(jTNombreAcreditadoMoral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jSPAcreditado, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBAgregarAcreditado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBEliminarAcreditado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLTituloAcreditante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRBFisicaAcreditante)
                    .addComponent(jRBMoralAcreditante))
                .addGap(21, 21, 21)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLNombreFisicaAcreditante)
                    .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTNombreAcreditanteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLApPaAcreditanteFisica)
                        .addComponent(jTApPaAcreditanteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLApMaAcreditanteFisica)
                        .addComponent(jTApMaAcreditanteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTNombreAcreditanteMoral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNombreMoralAcreditante))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addComponent(jSPAcreditante, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBAgregarAcreditante, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jBEliminarAcreditante, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLObservaciones))
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTerminar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addComponent(jPPDF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSPPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBEliminarAcreditadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarAcreditadoActionPerformed
        indiceAcreditado--;
        PanelAcreditado.remove(indiceAcreditado);
        this.controlAcreditados.remove(indiceAcreditado);
        if (indiceAcreditado>0) 
        {
            PanelAcreditado.setLayout(new GridLayout(indiceAcreditado, 0));
        } 
        PanelAcreditado.updateUI();
        if (indiceAcreditado==0)
        {
            jBEliminarAcreditado.setVisible(false);
        }
    }//GEN-LAST:event_jBEliminarAcreditadoActionPerformed

    private void jBAgregarAcreditadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarAcreditadoActionPerformed
        CancelacionHipotecaAcreditado nuevoAcreditado = new CancelacionHipotecaAcreditado(indiceAcreditado);
        nuevoAcreditado.jRBFisicaAcreditado.addActionListener(this);
        nuevoAcreditado.jRBMoralAcreditado.addActionListener(this);
        nuevoAcreditado.jLNombreMoralAcreditado.setVisible(false);
        nuevoAcreditado.jTNombreAcreditadoMoral.setVisible(false);
        PanelAcreditado.add(nuevoAcreditado);
        this.controlAcreditados.put(indiceAcreditado, nuevoAcreditado);
        indiceAcreditado++;
        PanelAcreditado.setLayout(new GridLayout(indiceAcreditado, 0));
        PanelAcreditado.updateUI();
        jBEliminarAcreditado.setVisible(true);
    }//GEN-LAST:event_jBAgregarAcreditadoActionPerformed

    private void jRBFisicaAcreditadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBFisicaAcreditadoActionPerformed
        // TODO add your handling code here:
        jLNombreMoralAcreditado.setVisible(false);
        jTNombreAcreditadoMoral.setVisible(false);
        jLNombreFisicaAcreditado.setVisible(true);
        jLApMaAcreditadoFisica.setVisible(true);
        jLApPaAcreditadoFisica.setVisible(true);
        jTNombreAcreditadoFisica.setVisible(true);
        jTApMaAcreditadoFisica.setVisible(true);
        jTApPaAcreditadoFisica.setVisible(true);
    }//GEN-LAST:event_jRBFisicaAcreditadoActionPerformed

    private void jRBMoralAcreditadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBMoralAcreditadoActionPerformed
        // TODO add your handling code here:
        jLNombreFisicaAcreditado.setVisible(false);
        jLApMaAcreditadoFisica.setVisible(false);
        jLApPaAcreditadoFisica.setVisible(false);
        jTNombreAcreditadoFisica.setVisible(false);
        jTApMaAcreditadoFisica.setVisible(false);
        jTApPaAcreditadoFisica.setVisible(false);
        jLNombreMoralAcreditado.setVisible(true);
        jTNombreAcreditadoMoral.setVisible(true);
    }//GEN-LAST:event_jRBMoralAcreditadoActionPerformed

    private void jTNombreAcreditadoMoralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreAcreditadoMoralActionPerformed
         jTNombreAcreditadoMoral.transferFocus();
    }//GEN-LAST:event_jTNombreAcreditadoMoralActionPerformed

    private void jRBFisicaAcreditanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBFisicaAcreditanteActionPerformed
        // TODO add your handling code here:
        jLNombreMoralAcreditante.setVisible(false);
        jTNombreAcreditanteMoral.setVisible(false);      
        jTNombreAcreditanteFisica.setVisible(true);
        jTApPaAcreditanteFisica.setVisible(true);
        jTApMaAcreditanteFisica.setVisible(true);
        jLNombreFisicaAcreditante.setVisible(true);
        jLApPaAcreditanteFisica.setVisible(true);
        jLApMaAcreditanteFisica.setVisible(true);    
    }//GEN-LAST:event_jRBFisicaAcreditanteActionPerformed

    private void jRBMoralAcreditanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBMoralAcreditanteActionPerformed
        // TODO add your handling code here:
        jLNombreMoralAcreditante.setVisible(true);
        jTNombreAcreditanteMoral.setVisible(true);
        jTNombreAcreditanteFisica.setVisible(false);
        jTApPaAcreditanteFisica.setVisible(false);
        jTApMaAcreditanteFisica.setVisible(false);
        jLNombreFisicaAcreditante.setVisible(false);
        jLApPaAcreditanteFisica.setVisible(false);
        jLApMaAcreditanteFisica.setVisible(false);
    }//GEN-LAST:event_jRBMoralAcreditanteActionPerformed

    private void jTNombreAcreditanteMoralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreAcreditanteMoralActionPerformed
        jTNombreAcreditanteMoral.transferFocus();
    }//GEN-LAST:event_jTNombreAcreditanteMoralActionPerformed

    private void jBAgregarAcreditanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarAcreditanteActionPerformed
        CancelacionHipotecaAcreditante nuevoAcreditante = new CancelacionHipotecaAcreditante(indiceAcreditante);
        nuevoAcreditante.jRBFisicaAcreditante.addActionListener(this);
        nuevoAcreditante.jRBMoralAcreditante.addActionListener(this);
        nuevoAcreditante.jLNombreMoralAcreditante.setVisible(false);
        nuevoAcreditante.jTNombreAcreditanteMoral.setVisible(false);
        PanelAcreditante.add(nuevoAcreditante);
        this.controlAcreditantes.put(indiceAcreditante, nuevoAcreditante);
        indiceAcreditante++;
        PanelAcreditante.setLayout(new GridLayout(indiceAcreditante, 0));
        PanelAcreditante.updateUI();
        jBEliminarAcreditante.setVisible(true);
    }//GEN-LAST:event_jBAgregarAcreditanteActionPerformed

    private void jBEliminarAcreditanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarAcreditanteActionPerformed
        indiceAcreditante--;
        PanelAcreditante.remove(indiceAcreditante);
        this.controlAcreditantes.remove(indiceAcreditante);
        if (indiceAcreditante>0) 
        {
            PanelAcreditante.setLayout(new GridLayout(indiceAcreditante, 0));
        } 
        PanelAcreditante.updateUI();
        if (indiceAcreditante==0)
        {
            jBEliminarAcreditante.setVisible(false);
        }
    }//GEN-LAST:event_jBEliminarAcreditanteActionPerformed

    private void jBTerminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTerminarActionPerformed
        int banderaacreditados = controlAcreditados.size();
        int banderaacreditantes = controlAcreditantes.size();
        datos.getNumeroAcreditados(banderaacreditados);
        datos.getNumeroAcreditantes(banderaacreditantes);
        String[][] acreditadosdatos = new String[banderaacreditados][4];
        String[][] acreditantesdatos = new String[banderaacreditantes][4];
        Iterator it = controlAcreditados.entrySet().iterator();
        Iterator it2 = controlAcreditantes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            //se obtiene el KEY -> identificador unico
            int itm = (int) entry.getKey();
            //si comando de componente es igual a comando pulsado
          
                //se recupera el contenido del JTextfield
                if(((CancelacionHipotecaAcreditado) entry.getValue()).jRBFisicaAcreditado.isSelected()==true)
                {                    
                    String dos = ((CancelacionHipotecaAcreditado) entry.getValue()).jTNombreAcreditadoFisica.getText();
                    String tres = ((CancelacionHipotecaAcreditado) entry.getValue()).jTApPaAcreditado.getText();
                    String cuatro = ((CancelacionHipotecaAcreditado) entry.getValue()).jTApMaAcreditado.getText();
                    acreditadosdatos[itm][0]="Persona Física";
                    acreditadosdatos[itm][1]= dos;
                    acreditadosdatos[itm][2]= tres;
                    acreditadosdatos[itm][3]= cuatro;
                }
                else
                {
                    String uno = ((CancelacionHipotecaAcreditado) entry.getValue()).jTNombreAcreditadoMoral.getText();
                    acreditadosdatos[itm][0]="Persona Moral";
                    acreditadosdatos[itm][1]= uno;
                    acreditadosdatos[itm][2]= "";
                    acreditadosdatos[itm][3]= "";
                }
                
            }
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry)it2.next();
            //se obtiene el KEY -> identificador unico
            int itm2 = (int) entry.getKey();
            //si comando de componente es igual a comando pulsado
          
                //se recupera el contenido del JTextfield
                if(((CancelacionHipotecaAcreditante) entry.getValue()).jRBFisicaAcreditante.isSelected()==true)
                {                    
                    String dos = ((CancelacionHipotecaAcreditante) entry.getValue()).jTNombreAcreditanteFisica.getText();
                    String tres = ((CancelacionHipotecaAcreditante) entry.getValue()).jTApPaAcreditante.getText();
                    String cuatro = ((CancelacionHipotecaAcreditante) entry.getValue()).jTApMaAcreditante.getText();
                    acreditantesdatos[itm2][0]="Persona Física";
                    acreditantesdatos[itm2][1]= dos;
                    acreditantesdatos[itm2][2]= tres;
                    acreditantesdatos[itm2][3]= cuatro;
                }
                else
                {
                    String uno = ((CancelacionHipotecaAcreditante) entry.getValue()).jTNombreAcreditanteMoral.getText();
                    acreditantesdatos[itm2][0]="Persona Moral";
                    acreditantesdatos[itm2][1]= uno;
                    acreditantesdatos[itm2][2]= "";
                    acreditantesdatos[itm2][3]= "";
                }
            }
        datos.getAcreditados(acreditadosdatos);
        datos.getAcreditantes(acreditantesdatos);
        ValidacionFinal();
    }//GEN-LAST:event_jBTerminarActionPerformed

    private void jRBFisicaAcreditanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBFisicaAcreditanteMouseClicked
    }//GEN-LAST:event_jRBFisicaAcreditanteMouseClicked

    private void jRBFisicaAcreditadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBFisicaAcreditadoMouseClicked
     }//GEN-LAST:event_jRBFisicaAcreditadoMouseClicked

    private void jRBMoralAcreditadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBMoralAcreditadoMouseClicked
    }//GEN-LAST:event_jRBMoralAcreditadoMouseClicked

    private void jRBMoralAcreditanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBMoralAcreditanteMouseClicked
    }//GEN-LAST:event_jRBMoralAcreditanteMouseClicked

    private void jTNombreAcreditadoFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreAcreditadoFisicaActionPerformed
        jTNombreAcreditadoFisica.transferFocus();
    }//GEN-LAST:event_jTNombreAcreditadoFisicaActionPerformed

    private void jTApPaAcreditadoFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApPaAcreditadoFisicaActionPerformed
        jTApPaAcreditadoFisica.transferFocus();
    }//GEN-LAST:event_jTApPaAcreditadoFisicaActionPerformed

    private void jTApMaAcreditadoFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApMaAcreditadoFisicaActionPerformed
        jTApMaAcreditadoFisica.transferFocus();
    }//GEN-LAST:event_jTApMaAcreditadoFisicaActionPerformed

    private void jTNombreAcreditanteFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreAcreditanteFisicaActionPerformed
        jTNombreAcreditanteFisica.transferFocus();
    }//GEN-LAST:event_jTNombreAcreditanteFisicaActionPerformed

    private void jTApPaAcreditanteFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApPaAcreditanteFisicaActionPerformed
        jTApPaAcreditanteFisica.transferFocus();
    }//GEN-LAST:event_jTApPaAcreditanteFisicaActionPerformed

    private void jTApMaAcreditanteFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApMaAcreditanteFisicaActionPerformed
        jTApMaAcreditanteFisica.transferFocus();
    }//GEN-LAST:event_jTApMaAcreditanteFisicaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        EleccionTipoExpediente.cerrarDonacion(1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jBCambiarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCambiarTipoActionPerformed
        a = new ActualizarTipoexpediente();
        a.ExpedienteActual(3);
        a.setVisible(true);
    }//GEN-LAST:event_jBCambiarTipoActionPerformed

    /**
     * Main de la clase, inicia la ventana.
     * @param args
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                Logger.getLogger(CancelacionHipoteca.class.getName()).log(Level.SEVERE, null, ex);
            }
            new CancelacionHipoteca().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelAcreditado;
    private javax.swing.JPanel PanelAcreditante;
    private javax.swing.JPanel Principal;
    private javax.swing.JButton jBAgregarAcreditado;
    private javax.swing.JButton jBAgregarAcreditante;
    private javax.swing.JButton jBCambiarTipo;
    private javax.swing.JButton jBEliminarAcreditado;
    private javax.swing.JButton jBEliminarAcreditante;
    private javax.swing.JButton jBTerminar;
    private javax.swing.JButton jButton1;
    public javax.swing.JLabel jLApMaAcreditadoFisica;
    public javax.swing.JLabel jLApMaAcreditanteFisica;
    public javax.swing.JLabel jLApPaAcreditadoFisica;
    public javax.swing.JLabel jLApPaAcreditanteFisica;
    public javax.swing.JLabel jLNombreFisicaAcreditado;
    public javax.swing.JLabel jLNombreFisicaAcreditante;
    public javax.swing.JLabel jLNombreMoralAcreditado;
    public javax.swing.JLabel jLNombreMoralAcreditante;
    public javax.swing.JLabel jLObservaciones;
    private javax.swing.JLabel jLTituloAcreditado;
    private javax.swing.JLabel jLTituloAcreditante;
    private javax.swing.JPanel jPPDF;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JRadioButton jRBFisicaAcreditado;
    public static javax.swing.JRadioButton jRBFisicaAcreditante;
    public static javax.swing.JRadioButton jRBMoralAcreditado;
    public static javax.swing.JRadioButton jRBMoralAcreditante;
    private javax.swing.JScrollPane jSPAcreditado;
    private javax.swing.JScrollPane jSPAcreditante;
    private javax.swing.JScrollPane jSPPrincipal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea jTAObservaciones;
    public static javax.swing.JTextField jTApMaAcreditadoFisica;
    public static javax.swing.JTextField jTApMaAcreditanteFisica;
    public static javax.swing.JTextField jTApPaAcreditadoFisica;
    public static javax.swing.JTextField jTApPaAcreditanteFisica;
    public static javax.swing.JTextField jTNombreAcreditadoFisica;
    public static javax.swing.JTextField jTNombreAcreditadoMoral;
    public static javax.swing.JTextField jTNombreAcreditanteFisica;
    public static javax.swing.JTextField jTNombreAcreditanteMoral;
    private javax.swing.ButtonGroup tipoPersonaAcreditado;
    private javax.swing.ButtonGroup tipoPersonaAcreditante;
    private javax.swing.JLabel tituloVentanaCancelacionHipoteca;
    private javax.swing.JLabel tituloVentanaNoExpedienteCancelacionHipoteca;
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
        Iterator it = controlAcreditados.entrySet().iterator();
        Iterator it2 = controlAcreditantes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            //se obtiene el KEY -> identificador unico
            String itm = entry.getKey().toString();
            //si comando de componente es igual a comando pulsado
            if(comando.equals("indicefAcreditado" + itm))
            {                              
                ((CancelacionHipotecaAcreditado) entry.getValue()).jLNombreFisicaAcreditado.setVisible(true);
                ((CancelacionHipotecaAcreditado) entry.getValue()).jLApPaAcreditado.setVisible(true);
                ((CancelacionHipotecaAcreditado) entry.getValue()).jLApMaAcreditado.setVisible(true);
                ((CancelacionHipotecaAcreditado) entry.getValue()).jTNombreAcreditadoFisica.setVisible(true);
                ((CancelacionHipotecaAcreditado) entry.getValue()).jTApPaAcreditado.setVisible(true);
                ((CancelacionHipotecaAcreditado) entry.getValue()).jTApMaAcreditado.setVisible(true);
                ((CancelacionHipotecaAcreditado) entry.getValue()).jLNombreMoralAcreditado.setVisible(false);
                ((CancelacionHipotecaAcreditado) entry.getValue()).jTNombreAcreditadoMoral.setVisible(false);
            }
            else if(comando.equals("indicemAcreditado" + itm))
            {
                ((CancelacionHipotecaAcreditado) entry.getValue()).jLNombreFisicaAcreditado.setVisible(false);
                ((CancelacionHipotecaAcreditado) entry.getValue()).jLApPaAcreditado.setVisible(false);
                ((CancelacionHipotecaAcreditado) entry.getValue()).jLApMaAcreditado.setVisible(false);
                ((CancelacionHipotecaAcreditado) entry.getValue()).jTNombreAcreditadoFisica.setVisible(false);
                ((CancelacionHipotecaAcreditado) entry.getValue()).jTApPaAcreditado.setVisible(false);
                ((CancelacionHipotecaAcreditado) entry.getValue()).jTApMaAcreditado.setVisible(false);
                ((CancelacionHipotecaAcreditado) entry.getValue()).jLNombreMoralAcreditado.setVisible(true);
                ((CancelacionHipotecaAcreditado) entry.getValue()).jTNombreAcreditadoMoral.setVisible(true);
            }
        }
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry)it2.next();
            //se obtiene el KEY -> identificador unico
            String itm2 = entry.getKey().toString();
            //si comando de componente es igual a comando pulsado
            if(comando.equals("indicefAcreditante" + itm2))
            {
                //mostramos resultado
                ((CancelacionHipotecaAcreditante) entry.getValue()).jLNombreFisicaAcreditante.setVisible(true);
                ((CancelacionHipotecaAcreditante) entry.getValue()).jLApPaAcreditante.setVisible(true);
                ((CancelacionHipotecaAcreditante) entry.getValue()).jLApMaAcreditante.setVisible(true);
                ((CancelacionHipotecaAcreditante) entry.getValue()).jTNombreAcreditanteFisica.setVisible(true);
                ((CancelacionHipotecaAcreditante) entry.getValue()).jTApPaAcreditante.setVisible(true);
                ((CancelacionHipotecaAcreditante) entry.getValue()).jTApMaAcreditante.setVisible(true);
                ((CancelacionHipotecaAcreditante) entry.getValue()).jLNombreMoralAcreditante.setVisible(false);
                ((CancelacionHipotecaAcreditante) entry.getValue()).jTNombreAcreditanteMoral.setVisible(false);
                

            }
            else if(comando.equals("indicemAcreditante" + itm2))
            {
                ((CancelacionHipotecaAcreditante) entry.getValue()).jLNombreFisicaAcreditante.setVisible(false);
                ((CancelacionHipotecaAcreditante) entry.getValue()).jLApPaAcreditante.setVisible(false);
                ((CancelacionHipotecaAcreditante) entry.getValue()).jLApMaAcreditante.setVisible(false);
                ((CancelacionHipotecaAcreditante) entry.getValue()).jTNombreAcreditanteFisica.setVisible(false);
                ((CancelacionHipotecaAcreditante) entry.getValue()).jTApPaAcreditante.setVisible(false);
                ((CancelacionHipotecaAcreditante) entry.getValue()).jTApMaAcreditante.setVisible(false);
                ((CancelacionHipotecaAcreditante) entry.getValue()).jLNombreMoralAcreditante.setVisible(true);
                ((CancelacionHipotecaAcreditante) entry.getValue()).jTNombreAcreditanteMoral.setVisible(true);
            }
        }
    }    
}
