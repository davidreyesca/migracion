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
public class Testamentos extends javax.swing.JFrame implements ActionListener{
    private int indiceOtorgante;
    private DateFormat df = DateFormat.getDateInstance();
    private Map controlOtorgantes = new HashMap();
    Controlador.Testamentos datos = new Controlador.Testamentos();
    ActualizarTipoexpediente a;

    private void OcultarPanenlesIniciales()
    {
    //Se ocultan los paneles de Otorgante y Acreditante morales al iniciar, y se inicia el indice de control de ambos en 0
        jLNombreMoralOtorgante.setVisible(false);
        jTNombreOtorganteMoral.setVisible(false);
        indiceOtorgante = 0;
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
        SLetras(jTNombreOtorganteFisica);
        SLetras(jTApPaOtorganteFisica);
        SLetras(jTApMaOtorganteFisica);

        CambiarAMayusculas(jTNombreOtorganteFisica);
        CambiarAMayusculas(jTApPaOtorganteFisica);
        CambiarAMayusculas(jTApMaOtorganteFisica);  

       
        NoCaracteres(jTNombreOtorganteFisica, 70);
        NoCaracteres(jTApPaOtorganteFisica, 30);
        NoCaracteres(jTApMaOtorganteFisica, 30);
        NoCaracteres(jTNombreOtorganteMoral, 70);
        NoCaracteres(jTFolio, 10);
        
        SNumeros(jTFolio);
        ValidarSolonumeros(jTFolio);
    }
    private void ValidacionOtorgantePrincipal()
    {
        boolean pasa=false;
        if (jRBFisicaOtorgante.isSelected()==false && jRBMoralOtorgante.isSelected()==false)
        {
            JOptionPane.showMessageDialog(null, "NO has seleccionado el tipo de Otorgante","Sin selección" ,JOptionPane.ERROR_MESSAGE);
            pasa=true;
        }else if(jRBFisicaOtorgante.isSelected()==true)
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
        else if(jRBMoralOtorgante.isSelected()==true)
        {
                if(jTNombreOtorganteMoral.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE MORAL del Otorgante","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }           
         }
        if(pasa == false)
        {
            ValidacionTextFieldVacios();
        }
    }
    private void ValidacionTextFieldVacios()
    {
            ValidacionOtorgantes();
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
                if((((TestamentosOtorgante) entry.getValue()).jRBFisicaOtorgante.isSelected()==false) && (((TestamentosOtorgante) entry.getValue()).jRBMoralOtorgante.isSelected()==false))
                {
                    JOptionPane.showMessageDialog(null, "No has seleccionado el tipo de persona en el Otorgante " + (itm+2),"Selección vacia" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }
                else if(((TestamentosOtorgante) entry.getValue()).jRBFisicaOtorgante.isSelected()==true)
                {
                    if(((TestamentosOtorgante) entry.getValue()).jTNombreOtorganteFisica.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del Otorgante " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }else if(((TestamentosOtorgante) entry.getValue()).jTApPaOtorgante.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO PATERNO del Otorgante " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }else if(((TestamentosOtorgante) entry.getValue()).jTApMaOtorgante.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO MATERNO del Otorgante " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }                             
                }else if(((TestamentosOtorgante) entry.getValue()).jRBMoralOtorgante.isSelected()==true)
                {
                    if(((TestamentosOtorgante) entry.getValue()).jTNombreOtorganteMoral.getText().isEmpty())
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
        ValidacionOtorgantePrincipal();
    }
    /**
     * Este metodo envía todos los datos a la clase contralador de Donacion donde se guardaran los datos ya validados
     */
    public void GuardarBD()
    {
        System.out.println("FELICITACIONES, Has pasado todas las validaciones!");
            datos.getFolio(Integer.parseInt(jTFolio.getText()));
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
    public Testamentos() 
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
        tituloVentanaNoExpedienteTestamentos.setText("No. Expediente: #" + BDdocumentos.getNoExpediente());
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
        jBEliminarOtorgante = new javax.swing.JButton();
        jBAgregarOtorgante = new javax.swing.JButton();
        jLTituloOtorgante = new javax.swing.JLabel();
        jRBFisicaOtorgante = new javax.swing.JRadioButton();
        jRBMoralOtorgante = new javax.swing.JRadioButton();
        jLNombreFisicaOtorgante = new javax.swing.JLabel();
        jLNombreMoralOtorgante = new javax.swing.JLabel();
        jTNombreOtorganteMoral = new javax.swing.JTextField();
        jTNombreOtorganteFisica = new javax.swing.JTextField();
        jTApPaOtorganteFisica = new javax.swing.JTextField();
        jLApPaOtorganteFisica = new javax.swing.JLabel();
        jTApMaOtorganteFisica = new javax.swing.JTextField();
        tituloVentanaNoExpedienteTestamentos = new javax.swing.JLabel();
        jSPAcreditado = new javax.swing.JScrollPane();
        PanelOtorgante = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLObservaciones = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTAObservaciones = new javax.swing.JTextArea();
        jBTerminar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLApMaOtorganteFisica = new javax.swing.JLabel();
        tituloVentanaTestamentos = new javax.swing.JLabel();
        jBCambiarTipo = new javax.swing.JButton();
        jLFolio = new javax.swing.JLabel();
        jTFolio = new javax.swing.JTextField();
        jPPDF = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Testamentos");

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

        jBEliminarOtorgante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Eliminar Otorgante.png"))); // NOI18N
        jBEliminarOtorgante.setBorderPainted(false);
        jBEliminarOtorgante.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Eliminar Otorgante Grande.png"))); // NOI18N
        jBEliminarOtorgante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminarOtorganteActionPerformed(evt);
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

        jLTituloOtorgante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLTituloOtorgante.setText("Otorgante");

        tipoPersonaAcreditado.add(jRBFisicaOtorgante);
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

        tipoPersonaAcreditado.add(jRBMoralOtorgante);
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

        jLNombreFisicaOtorgante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreFisicaOtorgante.setText("*Nombre:");

        jLNombreMoralOtorgante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreMoralOtorgante.setText("*Nombre: ");

        jTNombreOtorganteMoral.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreOtorganteMoral.setPreferredSize(new java.awt.Dimension(250, 26));
        jTNombreOtorganteMoral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreOtorganteMoralActionPerformed(evt);
            }
        });

        jTNombreOtorganteFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreOtorganteFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreOtorganteFisicaActionPerformed(evt);
            }
        });

        jTApPaOtorganteFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApPaOtorganteFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApPaOtorganteFisicaActionPerformed(evt);
            }
        });

        jLApPaOtorganteFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApPaOtorganteFisica.setText("*Apellido Paterno:");

        jTApMaOtorganteFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApMaOtorganteFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApMaOtorganteFisicaActionPerformed(evt);
            }
        });

        tituloVentanaNoExpedienteTestamentos.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tituloVentanaNoExpedienteTestamentos.setText("No. Expediente: #");

        jSPAcreditado.setBorder(null);
        jSPAcreditado.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jSPAcreditado.setHorizontalScrollBar(null);

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

        jSPAcreditado.setViewportView(PanelOtorgante);

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

        jLApMaOtorganteFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApMaOtorganteFisica.setText("*Apellido Materno:");

        tituloVentanaTestamentos.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tituloVentanaTestamentos.setText("Testamentos");

        jBCambiarTipo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jBCambiarTipo.setText("Cambiar el tipo de expediente");
        jBCambiarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCambiarTipoActionPerformed(evt);
            }
        });

        jLFolio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLFolio.setText("*Folio:");

        jTFolio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout PrincipalLayout = new javax.swing.GroupLayout(Principal);
        Principal.setLayout(PrincipalLayout);
        PrincipalLayout.setHorizontalGroup(
            PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrincipalLayout.createSequentialGroup()
                        .addGap(0, 9, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PrincipalLayout.createSequentialGroup()
                                .addComponent(jBAgregarOtorgante, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBEliminarOtorgante, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrincipalLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addComponent(tituloVentanaTestamentos)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jBCambiarTipo)
                                        .addGap(57, 57, 57)
                                        .addComponent(tituloVentanaNoExpedienteTestamentos))
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLNombreFisicaOtorgante)
                                            .addComponent(jLNombreMoralOtorgante))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(PrincipalLayout.createSequentialGroup()
                                                .addComponent(jTNombreOtorganteMoral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(237, 237, 237))
                                            .addGroup(PrincipalLayout.createSequentialGroup()
                                                .addComponent(jTNombreOtorganteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLApPaOtorganteFisica)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTApPaOtorganteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLApMaOtorganteFisica)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTApMaOtorganteFisica)))))))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrincipalLayout.createSequentialGroup()
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSPAcreditado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 851, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrincipalLayout.createSequentialGroup()
                                .addComponent(jLFolio)
                                .addGap(72, 72, 72)
                                .addComponent(jTFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrincipalLayout.createSequentialGroup()
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrincipalLayout.createSequentialGroup()
                                .addComponent(jLObservaciones)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrincipalLayout.createSequentialGroup()
                                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrincipalLayout.createSequentialGroup()
                                        .addGap(303, 303, 303)
                                        .addComponent(jRBFisicaOtorgante)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRBMoralOtorgante))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrincipalLayout.createSequentialGroup()
                                        .addGap(401, 401, 401)
                                        .addComponent(jLTituloOtorgante)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(PrincipalLayout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBTerminar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(23, 23, 23))))
        );
        PrincipalLayout.setVerticalGroup(
            PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrincipalLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tituloVentanaNoExpedienteTestamentos)
                    .addComponent(tituloVentanaTestamentos)
                    .addComponent(jBCambiarTipo))
                .addGap(5, 5, 5)
                .addComponent(jLTituloOtorgante)
                .addGap(5, 5, 5)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRBFisicaOtorgante)
                    .addComponent(jRBMoralOtorgante))
                .addGap(18, 18, 18)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTNombreOtorganteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLApPaOtorganteFisica)
                        .addComponent(jTApPaOtorganteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLApMaOtorganteFisica)
                        .addComponent(jTApMaOtorganteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLNombreFisicaOtorgante)))
                .addGap(8, 8, 8)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLNombreMoralOtorgante)
                    .addComponent(jTNombreOtorganteMoral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jSPAcreditado, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBAgregarOtorgante, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBEliminarOtorgante, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLFolio)
                    .addComponent(jTFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLObservaciones))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBTerminar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addComponent(jSPPrincipal)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void jBAgregarOtorganteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarOtorganteActionPerformed
        TestamentosOtorgante nuevoOtorgante = new TestamentosOtorgante(indiceOtorgante);
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

    private void jRBFisicaOtorganteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBFisicaOtorganteActionPerformed
        // TODO add your handling code here:
        jLNombreMoralOtorgante.setVisible(false);
        jTNombreOtorganteMoral.setVisible(false);
        jLNombreFisicaOtorgante.setVisible(true);
        jLApMaOtorganteFisica.setVisible(true);
        jLApPaOtorganteFisica.setVisible(true);
        jTNombreOtorganteFisica.setVisible(true);
        jTApMaOtorganteFisica.setVisible(true);
        jTApPaOtorganteFisica.setVisible(true);
    }//GEN-LAST:event_jRBFisicaOtorganteActionPerformed

    private void jRBMoralOtorganteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBMoralOtorganteActionPerformed
        // TODO add your handling code here:
        jLNombreFisicaOtorgante.setVisible(false);
        jLApMaOtorganteFisica.setVisible(false);
        jLApPaOtorganteFisica.setVisible(false);
        jTNombreOtorganteFisica.setVisible(false);
        jTApMaOtorganteFisica.setVisible(false);
        jTApPaOtorganteFisica.setVisible(false);
        jLNombreMoralOtorgante.setVisible(true);
        jTNombreOtorganteMoral.setVisible(true);
    }//GEN-LAST:event_jRBMoralOtorganteActionPerformed

    private void jTNombreOtorganteMoralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreOtorganteMoralActionPerformed
         jTNombreOtorganteMoral.transferFocus();
    }//GEN-LAST:event_jTNombreOtorganteMoralActionPerformed

    private void jBTerminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTerminarActionPerformed
        int banderaotorgantes = controlOtorgantes.size();
        datos.getNumeroOtorgantes(banderaotorgantes);
        String[][] otorgantesdatos = new String[banderaotorgantes][4];
        Iterator it = controlOtorgantes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            //se obtiene el KEY -> identificador unico
            int itm = (int) entry.getKey();
            //si comando de componente es igual a comando pulsado
          
                //se recupera el contenido del JTextfield
                if(((TestamentosOtorgante) entry.getValue()).jRBFisicaOtorgante.isSelected()==true)
                {                    
                    String dos = ((TestamentosOtorgante) entry.getValue()).jTNombreOtorganteFisica.getText();
                    String tres = ((TestamentosOtorgante) entry.getValue()).jTApPaOtorgante.getText();
                    String cuatro = ((TestamentosOtorgante) entry.getValue()).jTApMaOtorgante.getText();
                    otorgantesdatos[itm][0]="Persona Física";
                    otorgantesdatos[itm][1]= dos;
                    otorgantesdatos[itm][2]= tres;
                    otorgantesdatos[itm][3]= cuatro;
                }
                else
                {
                    String uno = ((TestamentosOtorgante) entry.getValue()).jTNombreOtorganteMoral.getText();
                    otorgantesdatos[itm][0]="Persona Moral";
                    otorgantesdatos[itm][1]= uno;
                    otorgantesdatos[itm][2]= "";
                    otorgantesdatos[itm][3]= "";
                }
                
            }
        datos.getOtorgantes(otorgantesdatos);
        ValidacionFinal();
    }//GEN-LAST:event_jBTerminarActionPerformed

    private void jRBFisicaOtorganteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBFisicaOtorganteMouseClicked
     }//GEN-LAST:event_jRBFisicaOtorganteMouseClicked

    private void jRBMoralOtorganteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBMoralOtorganteMouseClicked
    }//GEN-LAST:event_jRBMoralOtorganteMouseClicked

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
        EleccionTipoExpediente.cerrarTestamentos(1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jBCambiarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCambiarTipoActionPerformed
        a = new ActualizarTipoexpediente();
        a.ExpedienteActual(6);
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
                Logger.getLogger(Testamentos.class.getName()).log(Level.SEVERE, null, ex);
            }
            new Testamentos().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelOtorgante;
    private javax.swing.JPanel Principal;
    private javax.swing.JButton jBAgregarOtorgante;
    private javax.swing.JButton jBCambiarTipo;
    private javax.swing.JButton jBEliminarOtorgante;
    private javax.swing.JButton jBTerminar;
    private javax.swing.JButton jButton1;
    public javax.swing.JLabel jLApMaOtorganteFisica;
    public javax.swing.JLabel jLApPaOtorganteFisica;
    public javax.swing.JLabel jLFolio;
    public javax.swing.JLabel jLNombreFisicaOtorgante;
    public javax.swing.JLabel jLNombreMoralOtorgante;
    public javax.swing.JLabel jLObservaciones;
    private javax.swing.JLabel jLTituloOtorgante;
    private javax.swing.JPanel jPPDF;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JRadioButton jRBFisicaOtorgante;
    public static javax.swing.JRadioButton jRBMoralOtorgante;
    private javax.swing.JScrollPane jSPAcreditado;
    private javax.swing.JScrollPane jSPPrincipal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTAObservaciones;
    public static javax.swing.JTextField jTApMaOtorganteFisica;
    public static javax.swing.JTextField jTApPaOtorganteFisica;
    private javax.swing.JTextField jTFolio;
    public static javax.swing.JTextField jTNombreOtorganteFisica;
    public static javax.swing.JTextField jTNombreOtorganteMoral;
    private javax.swing.ButtonGroup tipoPersonaAcreditado;
    private javax.swing.ButtonGroup tipoPersonaAcreditante;
    private javax.swing.JLabel tituloVentanaNoExpedienteTestamentos;
    private javax.swing.JLabel tituloVentanaTestamentos;
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
        Iterator it = controlOtorgantes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            //se obtiene el KEY -> identificador unico
            String itm = entry.getKey().toString();
            //si comando de componente es igual a comando pulsado
            if(comando.equals("indicefOtorgante" + itm))
            {                              
                ((TestamentosOtorgante) entry.getValue()).jLNombreFisicaOtorgante.setVisible(true);
                ((TestamentosOtorgante) entry.getValue()).jLApPaOtorgante.setVisible(true);
                ((TestamentosOtorgante) entry.getValue()).jLApMaOtorgante.setVisible(true);
                ((TestamentosOtorgante) entry.getValue()).jTNombreOtorganteFisica.setVisible(true);
                ((TestamentosOtorgante) entry.getValue()).jTApPaOtorgante.setVisible(true);
                ((TestamentosOtorgante) entry.getValue()).jTApMaOtorgante.setVisible(true);
                ((TestamentosOtorgante) entry.getValue()).jLNombreMoralOtorgante.setVisible(false);
                ((TestamentosOtorgante) entry.getValue()).jTNombreOtorganteMoral.setVisible(false);
            }
            else if(comando.equals("indicemOtorgante" + itm))
            {
                ((TestamentosOtorgante) entry.getValue()).jLNombreFisicaOtorgante.setVisible(false);
                ((TestamentosOtorgante) entry.getValue()).jLApPaOtorgante.setVisible(false);
                ((TestamentosOtorgante) entry.getValue()).jLApMaOtorgante.setVisible(false);
                ((TestamentosOtorgante) entry.getValue()).jTNombreOtorganteFisica.setVisible(false);
                ((TestamentosOtorgante) entry.getValue()).jTApPaOtorgante.setVisible(false);
                ((TestamentosOtorgante) entry.getValue()).jTApMaOtorgante.setVisible(false);
                ((TestamentosOtorgante) entry.getValue()).jLNombreMoralOtorgante.setVisible(true);
                ((TestamentosOtorgante) entry.getValue()).jTNombreOtorganteMoral.setVisible(true);
            }
        }
    }    
}
