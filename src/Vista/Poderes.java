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
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Clase principal de Captura Donación
 * @author David Reyes
 */
public class Poderes extends javax.swing.JFrame implements ActionListener{
    private int indiceQuienRecibe;
    private int indiceOtorgante;
    private DateFormat df = DateFormat.getDateInstance();
    private Map controlQuienesReciben = new HashMap();
    private Map controlOtorgantes = new HashMap();
    Controlador.Poderes datos = new Controlador.Poderes();
    ActualizarTipoexpediente a;

    private void OcultarPanenlesIniciales()
    {
    //Se ocultan los paneles de QuienRecibe y Otorgante morales al iniciar, y se inicia el indice de control de ambos en 0
        jLNombreMoralQuienRecibe.setVisible(false);
        jTNombreQuienRecibeMoral.setVisible(false);
        jLNombreMoralOtorgante.setVisible(false);
        jTNombreOtorganteMoral.setVisible(false);
        indiceQuienRecibe = 0;
        indiceOtorgante = 0;
        if (indiceQuienRecibe==0) 
        {
                jBEliminarQuienRecibe.setVisible(false);
        }
        if (indiceOtorgante==0) 
        {
                jBEliminarOtorgante.setVisible(false);
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
    private void ValidacionLetrasNumerosTiempoReal()
    {
        SLetras(jTNombreQuienRecibeFisica);
        SLetras(jTApPaQuienRecibeFisica);
        SLetras(jTApMaQuienRecibeFisica);
        SLetras(jTNombreOtorganteFisica);
        SLetras(jTApPaOtorganteFisica);
        SLetras(jTApMaOtorganteFisica);

        CambiarAMayusculas(jTNombreQuienRecibeFisica);
        CambiarAMayusculas(jTApPaQuienRecibeFisica);
        CambiarAMayusculas(jTApMaQuienRecibeFisica);  
        CambiarAMayusculas(jTNombreOtorganteFisica);
        CambiarAMayusculas(jTApPaOtorganteFisica);
        CambiarAMayusculas(jTApMaOtorganteFisica);
       
        NoCaracteres(jTNombreQuienRecibeFisica, 70);
        NoCaracteres(jTApPaQuienRecibeFisica, 30);
        NoCaracteres(jTApMaQuienRecibeFisica, 30);
        NoCaracteres(jTNombreQuienRecibeMoral, 70);
        
        NoCaracteres(jTNombreOtorganteFisica, 70);
        NoCaracteres(jTApPaOtorganteFisica, 30);
        NoCaracteres(jTApMaOtorganteFisica, 30);
        NoCaracteres(jTNombreOtorganteMoral, 70);
    }
    private void ValidacionQuienRecibePrincipal()
    {
        boolean pasa=false;
        if (jRBFisicaQuienRecibe.isSelected()==false && jRBMoralQuienRecibe.isSelected()==false)
        {
            JOptionPane.showMessageDialog(null, "NO has seleccionado el tipo de QuienRecibe","Sin selección" ,JOptionPane.ERROR_MESSAGE);
            pasa=true;
        }else if(jRBFisicaQuienRecibe.isSelected()==true)
            {
                if(jTNombreQuienRecibeFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del QuienRecibe","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }else if(jTApPaQuienRecibeFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO PATERNO del QuienRecibe","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }else if(jTApMaQuienRecibeFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO MATERNO del QuienRecibe","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }                           
            }
        else if(jRBMoralQuienRecibe.isSelected()==true)
        {
                if(jTNombreQuienRecibeMoral.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE MORAL del QuienRecibe","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }           
         }
        if(pasa == false)
        {
            ValidacionOtorgantePrincipal(); 
        }
    }
    private void ValidacionOtorgantePrincipal()
    {
        boolean pasa=false;
        if (jRBFisicaOtorgante.isSelected()==false && jRBMoralOtorgante.isSelected()==false)
        {  
            JOptionPane.showMessageDialog(null, "NO has seleccionado el tipo de Otorgante","Sin selección" ,JOptionPane.ERROR_MESSAGE);
            pasa=true;
        }else
        {
            if(jRBFisicaOtorgante.isSelected()==true)
            {
                if(jTNombreOtorganteFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del Otorgante","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }else if(jTApPaOtorganteFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO PATERNO del Otorgante","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }else if(jTApMaOtorganteFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO MATERNO del Otorgante","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }                           
            }
            else
            {
                if(jTNombreOtorganteMoral.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE MORAL del Otorgante","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
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
            ValidacionQuienesReciben();
    }
    private void ValidacionQuienesReciben()
    {
        boolean pasa=false;
        Iterator it = controlQuienesReciben.entrySet().iterator();
        while (it.hasNext()) 
        {
            Map.Entry entry = (Map.Entry)it.next();
            //se obtiene el KEY -> identificador unico
            int itm = (int) entry.getKey();
            //si comando de componente es igual a comando pulsado
                //se recupera el contenido del JTextfield
                if((((PoderesQuienRecibe) entry.getValue()).jRBFisicaQuienRecibe.isSelected()==false) && (((PoderesQuienRecibe) entry.getValue()).jRBMoralQuienRecibe.isSelected()==false))
                {
                    JOptionPane.showMessageDialog(null, "No has seleccionado el tipo de persona en el QuienRecibe " + (itm+2),"Selección vacia" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }
                else if(((PoderesQuienRecibe) entry.getValue()).jRBFisicaQuienRecibe.isSelected()==true)
                {
                    if(((PoderesQuienRecibe) entry.getValue()).jTNombreQuienRecibeFisica.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del QuienRecibe " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }else if(((PoderesQuienRecibe) entry.getValue()).jTApPaQuienRecibe.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO PATERNO del QuienRecibe " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }else if(((PoderesQuienRecibe) entry.getValue()).jTApMaQuienRecibe.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO MATERNO del QuienRecibe " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }                             
                }else if(((PoderesQuienRecibe) entry.getValue()).jRBMoralQuienRecibe.isSelected()==true)
                {
                    if(((PoderesQuienRecibe) entry.getValue()).jTNombreQuienRecibeMoral.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del QuienRecibe MORAL " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }
                }
        }
        if(pasa == false)
        {
            ValidacionOtorgantes();
        }
    }
    private void ValidacionOtorgantes()
    {
        boolean pasa=false;
        Iterator it = controlOtorgantes.entrySet().iterator();
        while (it.hasNext()) 
        {
            Map.Entry entry = (Map.Entry)it.next();
            //se obtiene el KEY -> identificador unico
            int itm = (int) entry.getKey();
            //si comando de componente es igual a comando pulsado
                //se recupera el contenido del JTextfield
                if((((PoderesOtorgante) entry.getValue()).jRBFisicaOtorgante.isSelected()==false) && (((PoderesOtorgante) entry.getValue()).jRBMoralOtorgante.isSelected()==false))
                {
                    JOptionPane.showMessageDialog(null, "No has seleccionado el tipo de persona en el Otorgante " + (itm+2),"Selección vacia" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }
                else if(((PoderesOtorgante) entry.getValue()).jRBFisicaOtorgante.isSelected()==true)
                {
                    if(((PoderesOtorgante) entry.getValue()).jTNombreOtorganteFisica.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del Otorgante " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }else if(((PoderesOtorgante) entry.getValue()).jTApPaOtorgante.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO PATERNO del Otorgante " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }else if(((PoderesOtorgante) entry.getValue()).jTApMaOtorgante.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO MATERNO del Otorgante " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }                             
                }else if(((PoderesOtorgante) entry.getValue()).jRBMoralOtorgante.isSelected()==true)
                {
                    if(((PoderesOtorgante) entry.getValue()).jTNombreOtorganteMoral.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del Otorgante MORAL " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
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
        ValidacionQuienRecibePrincipal();
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
    public Poderes() 
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

        tipoPersonaQuienRecibe = new javax.swing.ButtonGroup();
        tipoPersonaOtorgante = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jSPPrincipal = new javax.swing.JScrollPane();
        Principal = new javax.swing.JPanel();
        jBEliminarQuienRecibe = new javax.swing.JButton();
        jBAgregarQuienRecibe = new javax.swing.JButton();
        jLTituloQuienRecibe = new javax.swing.JLabel();
        jRBFisicaQuienRecibe = new javax.swing.JRadioButton();
        jRBMoralQuienRecibe = new javax.swing.JRadioButton();
        jLNombreFisicaQuienRecibe = new javax.swing.JLabel();
        jLNombreMoralQuienRecibe = new javax.swing.JLabel();
        jTNombreQuienRecibeMoral = new javax.swing.JTextField();
        jTNombreQuienRecibeFisica = new javax.swing.JTextField();
        jTApPaQuienRecibeFisica = new javax.swing.JTextField();
        jLApPaQuienRecibeFisica = new javax.swing.JLabel();
        jTApMaQuienRecibeFisica = new javax.swing.JTextField();
        tituloVentanaNoExpedienteCancelacionHipoteca = new javax.swing.JLabel();
        jSPQuienRecibe = new javax.swing.JScrollPane();
        PanelQuienRecibe = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLTituloOtorgante = new javax.swing.JLabel();
        jRBFisicaOtorgante = new javax.swing.JRadioButton();
        jRBMoralOtorgante = new javax.swing.JRadioButton();
        jTNombreOtorganteFisica = new javax.swing.JTextField();
        jLNombreFisicaOtorgante = new javax.swing.JLabel();
        jLNombreMoralOtorgante = new javax.swing.JLabel();
        jTNombreOtorganteMoral = new javax.swing.JTextField();
        jLApMaOtorganteFisica = new javax.swing.JLabel();
        jTApPaOtorganteFisica = new javax.swing.JTextField();
        jTApMaOtorganteFisica = new javax.swing.JTextField();
        jBAgregarOtorgante = new javax.swing.JButton();
        jBEliminarOtorgante = new javax.swing.JButton();
        jSPOtorgante = new javax.swing.JScrollPane();
        PanelOtorgante = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLObservaciones = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTAObservaciones = new javax.swing.JTextArea();
        jBTerminar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLApMaQuienRecibeFisica = new javax.swing.JLabel();
        jLApPaOtorganteFisica = new javax.swing.JLabel();
        tituloVentanaPoderes = new javax.swing.JLabel();
        jBCambiarTipo = new javax.swing.JButton();
        jPPDF = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Poderes Captura");

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

        jBEliminarQuienRecibe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Eliminar Quien Recibe.png"))); // NOI18N
        jBEliminarQuienRecibe.setBorderPainted(false);
        jBEliminarQuienRecibe.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Eliminar Quien Recibe Grande.png"))); // NOI18N
        jBEliminarQuienRecibe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminarQuienRecibeActionPerformed(evt);
            }
        });

        jBAgregarQuienRecibe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Agregar QuienRecibe normal.png"))); // NOI18N
        jBAgregarQuienRecibe.setBorderPainted(false);
        jBAgregarQuienRecibe.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Agregar QuienRecibe Grande.png"))); // NOI18N
        jBAgregarQuienRecibe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAgregarQuienRecibeActionPerformed(evt);
            }
        });

        jLTituloQuienRecibe.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLTituloQuienRecibe.setText("Quien Recibe");

        tipoPersonaQuienRecibe.add(jRBFisicaQuienRecibe);
        jRBFisicaQuienRecibe.setText("Persona Física");
        jRBFisicaQuienRecibe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBFisicaQuienRecibeMouseClicked(evt);
            }
        });
        jRBFisicaQuienRecibe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBFisicaQuienRecibeActionPerformed(evt);
            }
        });

        tipoPersonaQuienRecibe.add(jRBMoralQuienRecibe);
        jRBMoralQuienRecibe.setText("Persona Moral");
        jRBMoralQuienRecibe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBMoralQuienRecibeMouseClicked(evt);
            }
        });
        jRBMoralQuienRecibe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBMoralQuienRecibeActionPerformed(evt);
            }
        });

        jLNombreFisicaQuienRecibe.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreFisicaQuienRecibe.setText("*Nombre:");

        jLNombreMoralQuienRecibe.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreMoralQuienRecibe.setText("*Nombre: ");

        jTNombreQuienRecibeMoral.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreQuienRecibeMoral.setPreferredSize(new java.awt.Dimension(250, 26));
        jTNombreQuienRecibeMoral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreQuienRecibeMoralActionPerformed(evt);
            }
        });

        jTNombreQuienRecibeFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreQuienRecibeFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreQuienRecibeFisicaActionPerformed(evt);
            }
        });

        jTApPaQuienRecibeFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApPaQuienRecibeFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApPaQuienRecibeFisicaActionPerformed(evt);
            }
        });

        jLApPaQuienRecibeFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApPaQuienRecibeFisica.setText("*Apellido Paterno:");

        jTApMaQuienRecibeFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApMaQuienRecibeFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApMaQuienRecibeFisicaActionPerformed(evt);
            }
        });

        tituloVentanaNoExpedienteCancelacionHipoteca.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tituloVentanaNoExpedienteCancelacionHipoteca.setText("No. Expediente: #");

        jSPQuienRecibe.setBorder(null);
        jSPQuienRecibe.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jSPQuienRecibe.setHorizontalScrollBar(null);

        PanelQuienRecibe.setBackground(new java.awt.Color(255, 255, 255));
        PanelQuienRecibe.setAlignmentX(0.0F);
        PanelQuienRecibe.setAlignmentY(0.0F);

        javax.swing.GroupLayout PanelQuienRecibeLayout = new javax.swing.GroupLayout(PanelQuienRecibe);
        PanelQuienRecibe.setLayout(PanelQuienRecibeLayout);
        PanelQuienRecibeLayout.setHorizontalGroup(
            PanelQuienRecibeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 986, Short.MAX_VALUE)
        );
        PanelQuienRecibeLayout.setVerticalGroup(
            PanelQuienRecibeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );

        jSPQuienRecibe.setViewportView(PanelQuienRecibe);

        jLTituloOtorgante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLTituloOtorgante.setText("Otorgante");

        tipoPersonaOtorgante.add(jRBFisicaOtorgante);
        jRBFisicaOtorgante.setText("Persona Física");
        jRBFisicaOtorgante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBFisicaOtorganteMouseClicked(evt);
            }
        });
        jRBFisicaOtorgante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBFisicaOtorganteActionPerformed(evt);
            }
        });

        tipoPersonaOtorgante.add(jRBMoralOtorgante);
        jRBMoralOtorgante.setText("Persona Moral");
        jRBMoralOtorgante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBMoralOtorganteMouseClicked(evt);
            }
        });
        jRBMoralOtorgante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBMoralOtorganteActionPerformed(evt);
            }
        });

        jTNombreOtorganteFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreOtorganteFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreOtorganteFisicaActionPerformed(evt);
            }
        });

        jLNombreFisicaOtorgante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreFisicaOtorgante.setText("*Nombre: ");

        jLNombreMoralOtorgante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreMoralOtorgante.setText("*Nombre: ");

        jTNombreOtorganteMoral.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreOtorganteMoral.setPreferredSize(new java.awt.Dimension(250, 26));
        jTNombreOtorganteMoral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreOtorganteMoralActionPerformed(evt);
            }
        });

        jLApMaOtorganteFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApMaOtorganteFisica.setText("*Apellido Materno:");

        jTApPaOtorganteFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApPaOtorganteFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApPaOtorganteFisicaActionPerformed(evt);
            }
        });

        jTApMaOtorganteFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApMaOtorganteFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApMaOtorganteFisicaActionPerformed(evt);
            }
        });

        jBAgregarOtorgante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Agregar Otorgante normal.png"))); // NOI18N
        jBAgregarOtorgante.setBorderPainted(false);
        jBAgregarOtorgante.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Agregar Otorgante Grande.png"))); // NOI18N
        jBAgregarOtorgante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAgregarOtorganteActionPerformed(evt);
            }
        });

        jBEliminarOtorgante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Eliminar Otorgante.png"))); // NOI18N
        jBEliminarOtorgante.setBorderPainted(false);
        jBEliminarOtorgante.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Eliminar Otorgante Grande.png"))); // NOI18N
        jBEliminarOtorgante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminarOtorganteActionPerformed(evt);
            }
        });

        jSPOtorgante.setBorder(null);
        jSPOtorgante.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jSPOtorgante.setHorizontalScrollBar(null);

        PanelOtorgante.setBackground(new java.awt.Color(255, 255, 255));
        PanelOtorgante.setAlignmentX(0.0F);
        PanelOtorgante.setAlignmentY(0.0F);

        javax.swing.GroupLayout PanelOtorganteLayout = new javax.swing.GroupLayout(PanelOtorgante);
        PanelOtorgante.setLayout(PanelOtorganteLayout);
        PanelOtorganteLayout.setHorizontalGroup(
            PanelOtorganteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 986, Short.MAX_VALUE)
        );
        PanelOtorganteLayout.setVerticalGroup(
            PanelOtorganteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );

        jSPOtorgante.setViewportView(PanelOtorgante);

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

        jLApMaQuienRecibeFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApMaQuienRecibeFisica.setText("*Apellido Materno:");

        jLApPaOtorganteFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApPaOtorganteFisica.setText("*Apellido Paterno:");

        tituloVentanaPoderes.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tituloVentanaPoderes.setText("Poderes");

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
            .addGroup(PrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PrincipalLayout.createSequentialGroup()
                                .addGap(303, 303, 303)
                                .addComponent(jRBFisicaQuienRecibe)
                                .addGap(18, 18, 18)
                                .addComponent(jRBMoralQuienRecibe))
                            .addGroup(PrincipalLayout.createSequentialGroup()
                                .addGap(305, 305, 305)
                                .addComponent(jRBFisicaOtorgante)
                                .addGap(19, 19, 19)
                                .addComponent(jRBMoralOtorgante)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PrincipalLayout.createSequentialGroup()
                                .addComponent(jBAgregarQuienRecibe, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBEliminarQuienRecibe, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PrincipalLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addComponent(jLNombreFisicaOtorgante)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTNombreOtorganteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLApPaOtorganteFisica)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTApPaOtorganteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLApMaOtorganteFisica)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTApMaOtorganteFisica))
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addComponent(jLNombreMoralOtorgante)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTNombreOtorganteMoral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(233, 233, 233))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrincipalLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addComponent(tituloVentanaPoderes)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jBCambiarTipo)
                                        .addGap(57, 57, 57)
                                        .addComponent(tituloVentanaNoExpedienteCancelacionHipoteca))
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLNombreFisicaQuienRecibe)
                                            .addComponent(jLNombreMoralQuienRecibe))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(PrincipalLayout.createSequentialGroup()
                                                .addComponent(jTNombreQuienRecibeMoral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(237, 237, 237))
                                            .addGroup(PrincipalLayout.createSequentialGroup()
                                                .addComponent(jTNombreQuienRecibeFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLApPaQuienRecibeFisica)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTApPaQuienRecibeFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLApMaQuienRecibeFisica)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTApMaQuienRecibeFisica))))))
                            .addGroup(PrincipalLayout.createSequentialGroup()
                                .addComponent(jBAgregarOtorgante, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBEliminarOtorgante, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                                    .addComponent(jSPOtorgante, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE)
                                    .addComponent(jSPQuienRecibe, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrincipalLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrincipalLayout.createSequentialGroup()
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrincipalLayout.createSequentialGroup()
                                .addComponent(jLTituloOtorgante)
                                .addGap(385, 385, 385))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrincipalLayout.createSequentialGroup()
                                .addComponent(jLTituloQuienRecibe)
                                .addGap(381, 381, 381))))))
        );
        PrincipalLayout.setVerticalGroup(
            PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrincipalLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tituloVentanaNoExpedienteCancelacionHipoteca)
                    .addComponent(tituloVentanaPoderes)
                    .addComponent(jBCambiarTipo))
                .addGap(5, 5, 5)
                .addComponent(jLTituloQuienRecibe)
                .addGap(5, 5, 5)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRBFisicaQuienRecibe)
                    .addComponent(jRBMoralQuienRecibe))
                .addGap(18, 18, 18)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTNombreQuienRecibeFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLApPaQuienRecibeFisica)
                        .addComponent(jTApPaQuienRecibeFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLApMaQuienRecibeFisica)
                        .addComponent(jTApMaQuienRecibeFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLNombreFisicaQuienRecibe)))
                .addGap(8, 8, 8)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLNombreMoralQuienRecibe)
                    .addComponent(jTNombreQuienRecibeMoral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jSPQuienRecibe, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBAgregarQuienRecibe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBEliminarQuienRecibe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLTituloOtorgante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRBFisicaOtorgante)
                    .addComponent(jRBMoralOtorgante))
                .addGap(21, 21, 21)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLNombreFisicaOtorgante)
                    .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTNombreOtorganteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLApPaOtorganteFisica)
                        .addComponent(jTApPaOtorganteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLApMaOtorganteFisica)
                        .addComponent(jTApMaOtorganteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTNombreOtorganteMoral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNombreMoralOtorgante))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addComponent(jSPOtorgante, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBAgregarOtorgante, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jBEliminarOtorgante, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBTerminar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addComponent(jLObservaciones)
                        .addGap(0, 0, Short.MAX_VALUE)))
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

    private void jBEliminarQuienRecibeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarQuienRecibeActionPerformed
        indiceQuienRecibe--;
        PanelQuienRecibe.remove(indiceQuienRecibe);
        this.controlQuienesReciben.remove(indiceQuienRecibe);
        if (indiceQuienRecibe>0) 
        {
            PanelQuienRecibe.setLayout(new GridLayout(indiceQuienRecibe, 0));
        } 
        PanelQuienRecibe.updateUI();
        if (indiceQuienRecibe==0)
        {
            jBEliminarQuienRecibe.setVisible(false);
        }
    }//GEN-LAST:event_jBEliminarQuienRecibeActionPerformed

    private void jBAgregarQuienRecibeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarQuienRecibeActionPerformed
        PoderesQuienRecibe nuevoQuienRecibe = new PoderesQuienRecibe(indiceQuienRecibe);
        nuevoQuienRecibe.jRBFisicaQuienRecibe.addActionListener(this);
        nuevoQuienRecibe.jRBMoralQuienRecibe.addActionListener(this);
        nuevoQuienRecibe.jLNombreMoralQuienRecibe.setVisible(false);
        nuevoQuienRecibe.jTNombreQuienRecibeMoral.setVisible(false);
        PanelQuienRecibe.add(nuevoQuienRecibe);
        this.controlQuienesReciben.put(indiceQuienRecibe, nuevoQuienRecibe);
        indiceQuienRecibe++;
        PanelQuienRecibe.setLayout(new GridLayout(indiceQuienRecibe, 0));
        PanelQuienRecibe.updateUI();
        jBEliminarQuienRecibe.setVisible(true);
    }//GEN-LAST:event_jBAgregarQuienRecibeActionPerformed

    private void jRBFisicaQuienRecibeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBFisicaQuienRecibeActionPerformed
        // TODO add your handling code here:
        jLNombreMoralQuienRecibe.setVisible(false);
        jTNombreQuienRecibeMoral.setVisible(false);
        jLNombreFisicaQuienRecibe.setVisible(true);
        jLApMaQuienRecibeFisica.setVisible(true);
        jLApPaQuienRecibeFisica.setVisible(true);
        jTNombreQuienRecibeFisica.setVisible(true);
        jTApMaQuienRecibeFisica.setVisible(true);
        jTApPaQuienRecibeFisica.setVisible(true);
    }//GEN-LAST:event_jRBFisicaQuienRecibeActionPerformed

    private void jRBMoralQuienRecibeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBMoralQuienRecibeActionPerformed
        // TODO add your handling code here:
        jLNombreFisicaQuienRecibe.setVisible(false);
        jLApMaQuienRecibeFisica.setVisible(false);
        jLApPaQuienRecibeFisica.setVisible(false);
        jTNombreQuienRecibeFisica.setVisible(false);
        jTApMaQuienRecibeFisica.setVisible(false);
        jTApPaQuienRecibeFisica.setVisible(false);
        jLNombreMoralQuienRecibe.setVisible(true);
        jTNombreQuienRecibeMoral.setVisible(true);
    }//GEN-LAST:event_jRBMoralQuienRecibeActionPerformed

    private void jTNombreQuienRecibeMoralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreQuienRecibeMoralActionPerformed
         jTNombreQuienRecibeMoral.transferFocus();
    }//GEN-LAST:event_jTNombreQuienRecibeMoralActionPerformed

    private void jRBFisicaOtorganteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBFisicaOtorganteActionPerformed
        // TODO add your handling code here:
        jLNombreMoralOtorgante.setVisible(false);
        jTNombreOtorganteMoral.setVisible(false);      
        jTNombreOtorganteFisica.setVisible(true);
        jTApPaOtorganteFisica.setVisible(true);
        jTApMaOtorganteFisica.setVisible(true);
        jLNombreFisicaOtorgante.setVisible(true);
        jLApPaOtorganteFisica.setVisible(true);
        jLApMaOtorganteFisica.setVisible(true);    
    }//GEN-LAST:event_jRBFisicaOtorganteActionPerformed

    private void jRBMoralOtorganteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBMoralOtorganteActionPerformed
        // TODO add your handling code here:
        jLNombreMoralOtorgante.setVisible(true);
        jTNombreOtorganteMoral.setVisible(true);
        jTNombreOtorganteFisica.setVisible(false);
        jTApPaOtorganteFisica.setVisible(false);
        jTApMaOtorganteFisica.setVisible(false);
        jLNombreFisicaOtorgante.setVisible(false);
        jLApPaOtorganteFisica.setVisible(false);
        jLApMaOtorganteFisica.setVisible(false);
    }//GEN-LAST:event_jRBMoralOtorganteActionPerformed

    private void jTNombreOtorganteMoralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreOtorganteMoralActionPerformed
        jTNombreOtorganteMoral.transferFocus();
    }//GEN-LAST:event_jTNombreOtorganteMoralActionPerformed

    private void jBAgregarOtorganteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarOtorganteActionPerformed
        PoderesOtorgante nuevoOtorgante = new PoderesOtorgante(indiceOtorgante);
        nuevoOtorgante.jRBFisicaOtorgante.addActionListener(this);
        nuevoOtorgante.jRBMoralOtorgante.addActionListener(this);
        nuevoOtorgante.jLNombreMoralOtorgante.setVisible(false);
        nuevoOtorgante.jTNombreOtorganteMoral.setVisible(false);
        PanelOtorgante.add(nuevoOtorgante);
        this.controlOtorgantes.put(indiceOtorgante, nuevoOtorgante);
        indiceOtorgante++;
        PanelOtorgante.setLayout(new GridLayout(indiceOtorgante, 0));
        PanelOtorgante.updateUI();
        jBEliminarOtorgante.setVisible(true);
    }//GEN-LAST:event_jBAgregarOtorganteActionPerformed

    private void jBEliminarOtorganteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarOtorganteActionPerformed
        indiceOtorgante--;
        PanelOtorgante.remove(indiceOtorgante);
        this.controlOtorgantes.remove(indiceOtorgante);
        if (indiceOtorgante>0) 
        {
            PanelOtorgante.setLayout(new GridLayout(indiceOtorgante, 0));
        } 
        PanelOtorgante.updateUI();
        if (indiceOtorgante==0)
        {
            jBEliminarOtorgante.setVisible(false);
        }
    }//GEN-LAST:event_jBEliminarOtorganteActionPerformed

    private void jBTerminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTerminarActionPerformed
        int banderaquienesreciben = controlQuienesReciben.size();
        int banderaotorgantes = controlOtorgantes.size();
        datos.getNumeroQuienesReciben(banderaquienesreciben);
        datos.getNumeroOtorgantes(banderaotorgantes);
        String[][] quienesrecibendatos = new String[banderaquienesreciben][4];
        String[][] otorgantesdatos = new String[banderaotorgantes][4];
        Iterator it = controlQuienesReciben.entrySet().iterator();
        Iterator it2 = controlOtorgantes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            //se obtiene el KEY -> identificador unico
            int itm = (int) entry.getKey();
            //si comando de componente es igual a comando pulsado
          
                //se recupera el contenido del JTextfield
                if(((PoderesQuienRecibe) entry.getValue()).jRBFisicaQuienRecibe.isSelected()==true)
                {                    
                    String dos = ((PoderesQuienRecibe) entry.getValue()).jTNombreQuienRecibeFisica.getText();
                    String tres = ((PoderesQuienRecibe) entry.getValue()).jTApPaQuienRecibe.getText();
                    String cuatro = ((PoderesQuienRecibe) entry.getValue()).jTApMaQuienRecibe.getText();
                    quienesrecibendatos[itm][0]="Persona Física";
                    quienesrecibendatos[itm][1]= dos;
                    quienesrecibendatos[itm][2]= tres;
                    quienesrecibendatos[itm][3]= cuatro;
                }
                else
                {
                    String uno = ((PoderesQuienRecibe) entry.getValue()).jTNombreQuienRecibeMoral.getText();
                    quienesrecibendatos[itm][0]="Persona Moral";
                    quienesrecibendatos[itm][1]= uno;
                    quienesrecibendatos[itm][2]= "";
                    quienesrecibendatos[itm][3]= "";
                }
                
            }
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry)it2.next();
            //se obtiene el KEY -> identificador unico
            int itm2 = (int) entry.getKey();
            //si comando de componente es igual a comando pulsado
          
                //se recupera el contenido del JTextfield
                if(((PoderesOtorgante) entry.getValue()).jRBFisicaOtorgante.isSelected()==true)
                {                    
                    String dos = ((PoderesOtorgante) entry.getValue()).jTNombreOtorganteFisica.getText();
                    String tres = ((PoderesOtorgante) entry.getValue()).jTApPaOtorgante.getText();
                    String cuatro = ((PoderesOtorgante) entry.getValue()).jTApMaOtorgante.getText();
                    otorgantesdatos[itm2][0]="Persona Física";
                    otorgantesdatos[itm2][1]= dos;
                    otorgantesdatos[itm2][2]= tres;
                    otorgantesdatos[itm2][3]= cuatro;
                }
                else
                {
                    String uno = ((PoderesOtorgante) entry.getValue()).jTNombreOtorganteMoral.getText();
                    otorgantesdatos[itm2][0]="Persona Moral";
                    otorgantesdatos[itm2][1]= uno;
                    otorgantesdatos[itm2][2]= "";
                    otorgantesdatos[itm2][3]= "";
                }
            }
        datos.getQuienesReciben(quienesrecibendatos);
        datos.getOtorgantes(otorgantesdatos);
        ValidacionFinal();
    }//GEN-LAST:event_jBTerminarActionPerformed

    private void jRBFisicaOtorganteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBFisicaOtorganteMouseClicked
    }//GEN-LAST:event_jRBFisicaOtorganteMouseClicked

    private void jRBFisicaQuienRecibeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBFisicaQuienRecibeMouseClicked
     }//GEN-LAST:event_jRBFisicaQuienRecibeMouseClicked

    private void jRBMoralQuienRecibeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBMoralQuienRecibeMouseClicked
    }//GEN-LAST:event_jRBMoralQuienRecibeMouseClicked

    private void jRBMoralOtorganteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBMoralOtorganteMouseClicked
    }//GEN-LAST:event_jRBMoralOtorganteMouseClicked

    private void jTNombreQuienRecibeFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreQuienRecibeFisicaActionPerformed
        jTNombreQuienRecibeFisica.transferFocus();
    }//GEN-LAST:event_jTNombreQuienRecibeFisicaActionPerformed

    private void jTApPaQuienRecibeFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApPaQuienRecibeFisicaActionPerformed
        jTApPaQuienRecibeFisica.transferFocus();
    }//GEN-LAST:event_jTApPaQuienRecibeFisicaActionPerformed

    private void jTApMaQuienRecibeFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApMaQuienRecibeFisicaActionPerformed
        jTApMaQuienRecibeFisica.transferFocus();
    }//GEN-LAST:event_jTApMaQuienRecibeFisicaActionPerformed

    private void jTNombreOtorganteFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreOtorganteFisicaActionPerformed
        jTNombreOtorganteFisica.transferFocus();
    }//GEN-LAST:event_jTNombreOtorganteFisicaActionPerformed

    private void jTApPaOtorganteFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApPaOtorganteFisicaActionPerformed
        jTApPaOtorganteFisica.transferFocus();
    }//GEN-LAST:event_jTApPaOtorganteFisicaActionPerformed

    private void jTApMaOtorganteFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApMaOtorganteFisicaActionPerformed
        jTApMaOtorganteFisica.transferFocus();
    }//GEN-LAST:event_jTApMaOtorganteFisicaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        EleccionTipoExpediente.cerrarPoderes(1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jBCambiarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCambiarTipoActionPerformed
        a = new ActualizarTipoexpediente();
        a.ExpedienteActual(5);
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
                Logger.getLogger(Poderes.class.getName()).log(Level.SEVERE, null, ex);
            }
            new Poderes().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelOtorgante;
    private javax.swing.JPanel PanelQuienRecibe;
    private javax.swing.JPanel Principal;
    private javax.swing.JButton jBAgregarOtorgante;
    private javax.swing.JButton jBAgregarQuienRecibe;
    private javax.swing.JButton jBCambiarTipo;
    private javax.swing.JButton jBEliminarOtorgante;
    private javax.swing.JButton jBEliminarQuienRecibe;
    private javax.swing.JButton jBTerminar;
    private javax.swing.JButton jButton1;
    public javax.swing.JLabel jLApMaOtorganteFisica;
    public javax.swing.JLabel jLApMaQuienRecibeFisica;
    public javax.swing.JLabel jLApPaOtorganteFisica;
    public javax.swing.JLabel jLApPaQuienRecibeFisica;
    public javax.swing.JLabel jLNombreFisicaOtorgante;
    public javax.swing.JLabel jLNombreFisicaQuienRecibe;
    public javax.swing.JLabel jLNombreMoralOtorgante;
    public javax.swing.JLabel jLNombreMoralQuienRecibe;
    public javax.swing.JLabel jLObservaciones;
    private javax.swing.JLabel jLTituloOtorgante;
    private javax.swing.JLabel jLTituloQuienRecibe;
    private javax.swing.JPanel jPPDF;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JRadioButton jRBFisicaOtorgante;
    public static javax.swing.JRadioButton jRBFisicaQuienRecibe;
    public static javax.swing.JRadioButton jRBMoralOtorgante;
    public static javax.swing.JRadioButton jRBMoralQuienRecibe;
    private javax.swing.JScrollPane jSPOtorgante;
    private javax.swing.JScrollPane jSPPrincipal;
    private javax.swing.JScrollPane jSPQuienRecibe;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea jTAObservaciones;
    public static javax.swing.JTextField jTApMaOtorganteFisica;
    public static javax.swing.JTextField jTApMaQuienRecibeFisica;
    public static javax.swing.JTextField jTApPaOtorganteFisica;
    public static javax.swing.JTextField jTApPaQuienRecibeFisica;
    public static javax.swing.JTextField jTNombreOtorganteFisica;
    public static javax.swing.JTextField jTNombreOtorganteMoral;
    public static javax.swing.JTextField jTNombreQuienRecibeFisica;
    public static javax.swing.JTextField jTNombreQuienRecibeMoral;
    private javax.swing.ButtonGroup tipoPersonaOtorgante;
    private javax.swing.ButtonGroup tipoPersonaQuienRecibe;
    private javax.swing.JLabel tituloVentanaNoExpedienteCancelacionHipoteca;
    private javax.swing.JLabel tituloVentanaPoderes;
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
        Iterator it = controlQuienesReciben.entrySet().iterator();
        Iterator it2 = controlOtorgantes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            //se obtiene el KEY -> identificador unico
            String itm = entry.getKey().toString();
            //si comando de componente es igual a comando pulsado
            if(comando.equals("indicefQuienRecibe" + itm))
            {                              
                ((PoderesQuienRecibe) entry.getValue()).jLNombreFisicaQuienRecibe.setVisible(true);
                ((PoderesQuienRecibe) entry.getValue()).jLApPaQuienRecibe.setVisible(true);
                ((PoderesQuienRecibe) entry.getValue()).jLApMaQuienRecibe.setVisible(true);
                ((PoderesQuienRecibe) entry.getValue()).jTNombreQuienRecibeFisica.setVisible(true);
                ((PoderesQuienRecibe) entry.getValue()).jTApPaQuienRecibe.setVisible(true);
                ((PoderesQuienRecibe) entry.getValue()).jTApMaQuienRecibe.setVisible(true);
                ((PoderesQuienRecibe) entry.getValue()).jLNombreMoralQuienRecibe.setVisible(false);
                ((PoderesQuienRecibe) entry.getValue()).jTNombreQuienRecibeMoral.setVisible(false);
            }
            else if(comando.equals("indicemQuienRecibe" + itm))
            {
                ((PoderesQuienRecibe) entry.getValue()).jLNombreFisicaQuienRecibe.setVisible(false);
                ((PoderesQuienRecibe) entry.getValue()).jLApPaQuienRecibe.setVisible(false);
                ((PoderesQuienRecibe) entry.getValue()).jLApMaQuienRecibe.setVisible(false);
                ((PoderesQuienRecibe) entry.getValue()).jTNombreQuienRecibeFisica.setVisible(false);
                ((PoderesQuienRecibe) entry.getValue()).jTApPaQuienRecibe.setVisible(false);
                ((PoderesQuienRecibe) entry.getValue()).jTApMaQuienRecibe.setVisible(false);
                ((PoderesQuienRecibe) entry.getValue()).jLNombreMoralQuienRecibe.setVisible(true);
                ((PoderesQuienRecibe) entry.getValue()).jTNombreQuienRecibeMoral.setVisible(true);
            }
        }
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry)it2.next();
            //se obtiene el KEY -> identificador unico
            String itm2 = entry.getKey().toString();
            //si comando de componente es igual a comando pulsado
            if(comando.equals("indicefOtorgante" + itm2))
            {
                //mostramos resultado
                ((PoderesOtorgante) entry.getValue()).jLNombreFisicaOtorgante.setVisible(true);
                ((PoderesOtorgante) entry.getValue()).jLApPaOtorgante.setVisible(true);
                ((PoderesOtorgante) entry.getValue()).jLApMaOtorgante.setVisible(true);
                ((PoderesOtorgante) entry.getValue()).jTNombreOtorganteFisica.setVisible(true);
                ((PoderesOtorgante) entry.getValue()).jTApPaOtorgante.setVisible(true);
                ((PoderesOtorgante) entry.getValue()).jTApMaOtorgante.setVisible(true);
                ((PoderesOtorgante) entry.getValue()).jLNombreMoralOtorgante.setVisible(false);
                ((PoderesOtorgante) entry.getValue()).jTNombreOtorganteMoral.setVisible(false);
                

            }
            else if(comando.equals("indicemOtorgante" + itm2))
            {
                ((PoderesOtorgante) entry.getValue()).jLNombreFisicaOtorgante.setVisible(false);
                ((PoderesOtorgante) entry.getValue()).jLApPaOtorgante.setVisible(false);
                ((PoderesOtorgante) entry.getValue()).jLApMaOtorgante.setVisible(false);
                ((PoderesOtorgante) entry.getValue()).jTNombreOtorganteFisica.setVisible(false);
                ((PoderesOtorgante) entry.getValue()).jTApPaOtorgante.setVisible(false);
                ((PoderesOtorgante) entry.getValue()).jTApMaOtorgante.setVisible(false);
                ((PoderesOtorgante) entry.getValue()).jLNombreMoralOtorgante.setVisible(true);
                ((PoderesOtorgante) entry.getValue()).jTNombreOtorganteMoral.setVisible(true);
            }
        }
    }    
}
