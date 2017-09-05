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
public class Certificaciones extends javax.swing.JFrame implements ActionListener{
    private int indiceNombre;
    private DateFormat df = DateFormat.getDateInstance();
    private Map controlNombres = new HashMap();
    Controlador.Certificaciones datos = new Controlador.Certificaciones();
    ActualizarTipoexpediente a;

    private void OcultarPanenlesIniciales()
    {
    //Se ocultan los paneles de Nombre y Acreditante morales al iniciar, y se inicia el indice de control de ambos en 0
        jLNombreMoralNombre.setVisible(false);
        jTNombreNombreMoral.setVisible(false);
        indiceNombre = 0;
        if (indiceNombre==0) 
        {
                jBEliminarNombre.setVisible(false);
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
        SLetras(jTNombreNombreFisica);
        SLetras(jTApPaNombreFisica);
        SLetras(jTApMaNombreFisica);

        CambiarAMayusculas(jTNombreNombreFisica);
        CambiarAMayusculas(jTApPaNombreFisica);
        CambiarAMayusculas(jTApMaNombreFisica);  

       
        NoCaracteres(jTNombreNombreFisica, 70);
        NoCaracteres(jTApPaNombreFisica, 30);
        NoCaracteres(jTApMaNombreFisica, 30);
        NoCaracteres(jTNombreNombreMoral, 70);

    }
    private void ValidacionNombrePrincipal()
    {
        boolean pasa=false;
        if (jRBFisicaNombre.isSelected()==false && jRBMoralNombre.isSelected()==false)
        {
            JOptionPane.showMessageDialog(null, "NO has seleccionado el tipo de Nombre","Sin selección" ,JOptionPane.ERROR_MESSAGE);
            pasa=true;
        }else if(jRBFisicaNombre.isSelected()==true)
            {
                if(jTNombreNombreFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del Nombre","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }else if(jTApPaNombreFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO PATERNO del Nombre","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }else if(jTApMaNombreFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO MATERNO del Nombre","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }                           
            }
        else if(jRBMoralNombre.isSelected()==true)
        {
                if(jTNombreNombreMoral.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE MORAL del Nombre","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
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
           ValidacionNombres();
    }
    private void ValidacionNombres()
    {
        boolean pasa=false;
        Iterator it = controlNombres.entrySet().iterator();
        while (it.hasNext()) 
        {
            Map.Entry entry = (Map.Entry)it.next();
            //se obtiene el KEY -> identificador unico
            int itm = (int) entry.getKey();
            //si comando de componente es igual a comando pulsado
                //se recupera el contenido del JTextfield
                if((((CertificacionesNombres) entry.getValue()).jRBFisicaNombre.isSelected()==false) && (((CertificacionesNombres) entry.getValue()).jRBMoralNombre.isSelected()==false))
                {
                    JOptionPane.showMessageDialog(null, "No has seleccionado el tipo de persona en el Nombre " + (itm+2),"Selección vacia" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }
                else if(((CertificacionesNombres) entry.getValue()).jRBFisicaNombre.isSelected()==true)
                {
                    if(((CertificacionesNombres) entry.getValue()).jTNombreNombreFisica.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del Nombre " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }else if(((CertificacionesNombres) entry.getValue()).jTApPaNombre.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO PATERNO del Nombre " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }else if(((CertificacionesNombres) entry.getValue()).jTApMaNombre.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO MATERNO del Nombre " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }                             
                }else if(((CertificacionesNombres) entry.getValue()).jRBMoralNombre.isSelected()==true)
                {
                    if(((CertificacionesNombres) entry.getValue()).jTNombreNombreMoral.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del Nombre MORAL " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
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
        ValidacionNombrePrincipal();
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
    public Certificaciones() 
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

        tipoPersonaNombre = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jSPPrincipal = new javax.swing.JScrollPane();
        Principal = new javax.swing.JPanel();
        jBEliminarNombre = new javax.swing.JButton();
        jBAgregarNombre = new javax.swing.JButton();
        jLTituloNombres = new javax.swing.JLabel();
        jRBFisicaNombre = new javax.swing.JRadioButton();
        jRBMoralNombre = new javax.swing.JRadioButton();
        jLNombreFisicaNombre = new javax.swing.JLabel();
        jLNombreMoralNombre = new javax.swing.JLabel();
        jTNombreNombreMoral = new javax.swing.JTextField();
        jTNombreNombreFisica = new javax.swing.JTextField();
        jTApPaNombreFisica = new javax.swing.JTextField();
        jLApPaNombreFisica = new javax.swing.JLabel();
        jTApMaNombreFisica = new javax.swing.JTextField();
        tituloVentanaNoExpedienteTestamentos = new javax.swing.JLabel();
        jSPNombre = new javax.swing.JScrollPane();
        PanelNombre = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLObservaciones = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTAObservaciones = new javax.swing.JTextArea();
        jBTerminar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLApMaNombreFisica = new javax.swing.JLabel();
        tituloVentanaNombres = new javax.swing.JLabel();
        jBCambiarTipo = new javax.swing.JButton();
        jPPDF = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Apertura Credito Captura");

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

        jBEliminarNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Eliminar Nombre.png"))); // NOI18N
        jBEliminarNombre.setBorderPainted(false);
        jBEliminarNombre.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Eliminar nombre Grande.png"))); // NOI18N
        jBEliminarNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminarNombreActionPerformed(evt);
            }
        });

        jBAgregarNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Agregar nombre normal.png"))); // NOI18N
        jBAgregarNombre.setBorderPainted(false);
        jBAgregarNombre.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Agregar nombre Grande.png"))); // NOI18N
        jBAgregarNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAgregarNombreActionPerformed(evt);
            }
        });

        jLTituloNombres.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLTituloNombres.setText("Nombres");

        tipoPersonaNombre.add(jRBFisicaNombre);
        jRBFisicaNombre.setText("Persona Física");
        jRBFisicaNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBFisicaNombreMouseClicked(evt);
            }
        });
        jRBFisicaNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBFisicaNombreActionPerformed(evt);
            }
        });

        tipoPersonaNombre.add(jRBMoralNombre);
        jRBMoralNombre.setText("Persona Moral");
        jRBMoralNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBMoralNombreMouseClicked(evt);
            }
        });
        jRBMoralNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBMoralNombreActionPerformed(evt);
            }
        });

        jLNombreFisicaNombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreFisicaNombre.setText("*Nombre:");

        jLNombreMoralNombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreMoralNombre.setText("*Nombre: ");

        jTNombreNombreMoral.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreNombreMoral.setPreferredSize(new java.awt.Dimension(250, 26));
        jTNombreNombreMoral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreNombreMoralActionPerformed(evt);
            }
        });

        jTNombreNombreFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreNombreFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreNombreFisicaActionPerformed(evt);
            }
        });

        jTApPaNombreFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApPaNombreFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApPaNombreFisicaActionPerformed(evt);
            }
        });

        jLApPaNombreFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApPaNombreFisica.setText("*Apellido Paterno:");

        jTApMaNombreFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApMaNombreFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApMaNombreFisicaActionPerformed(evt);
            }
        });

        tituloVentanaNoExpedienteTestamentos.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tituloVentanaNoExpedienteTestamentos.setText("No. Expediente: #");

        jSPNombre.setBorder(null);
        jSPNombre.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jSPNombre.setHorizontalScrollBar(null);

        PanelNombre.setBackground(new java.awt.Color(255, 255, 255));
        PanelNombre.setAlignmentX(0.0F);
        PanelNombre.setAlignmentY(0.0F);

        javax.swing.GroupLayout PanelNombreLayout = new javax.swing.GroupLayout(PanelNombre);
        PanelNombre.setLayout(PanelNombreLayout);
        PanelNombreLayout.setHorizontalGroup(
            PanelNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 986, Short.MAX_VALUE)
        );
        PanelNombreLayout.setVerticalGroup(
            PanelNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );

        jSPNombre.setViewportView(PanelNombre);

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

        jLApMaNombreFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApMaNombreFisica.setText("*Apellido Materno:");

        tituloVentanaNombres.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tituloVentanaNombres.setText("Certificaciones");

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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrincipalLayout.createSequentialGroup()
                        .addGap(0, 9, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PrincipalLayout.createSequentialGroup()
                                .addComponent(jBAgregarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBEliminarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrincipalLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addComponent(tituloVentanaNombres)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jBCambiarTipo)
                                        .addGap(57, 57, 57)
                                        .addComponent(tituloVentanaNoExpedienteTestamentos))
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLNombreFisicaNombre)
                                            .addComponent(jLNombreMoralNombre))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(PrincipalLayout.createSequentialGroup()
                                                .addComponent(jTNombreNombreMoral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(237, 237, 237))
                                            .addGroup(PrincipalLayout.createSequentialGroup()
                                                .addComponent(jTNombreNombreFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLApPaNombreFisica)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTApPaNombreFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLApMaNombreFisica)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTApMaNombreFisica)))))))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrincipalLayout.createSequentialGroup()
                        .addComponent(jSPNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 851, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(jRBFisicaNombre)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRBMoralNombre))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrincipalLayout.createSequentialGroup()
                                        .addGap(401, 401, 401)
                                        .addComponent(jLTituloNombres)))
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
                    .addComponent(tituloVentanaNombres)
                    .addComponent(jBCambiarTipo))
                .addGap(5, 5, 5)
                .addComponent(jLTituloNombres)
                .addGap(5, 5, 5)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRBFisicaNombre)
                    .addComponent(jRBMoralNombre))
                .addGap(18, 18, 18)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTNombreNombreFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLApPaNombreFisica)
                        .addComponent(jTApPaNombreFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLApMaNombreFisica)
                        .addComponent(jTApMaNombreFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLNombreFisicaNombre)))
                .addGap(8, 8, 8)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLNombreMoralNombre)
                    .addComponent(jTNombreNombreMoral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jSPNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBAgregarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBEliminarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLObservaciones))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 188, Short.MAX_VALUE)
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

    private void jBEliminarNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarNombreActionPerformed
        indiceNombre--;
        PanelNombre.remove(indiceNombre);
        this.controlNombres.remove(indiceNombre);
        if (indiceNombre>0) 
        {
            PanelNombre.setLayout(new GridLayout(indiceNombre, 0));
        } 
        PanelNombre.updateUI();
        if (indiceNombre==0)
        {
            jBEliminarNombre.setVisible(false);
        }
    }//GEN-LAST:event_jBEliminarNombreActionPerformed

    private void jBAgregarNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarNombreActionPerformed
        CertificacionesNombres nuevoNombre = new CertificacionesNombres(indiceNombre);
        nuevoNombre.jRBFisicaNombre.addActionListener(this);
        nuevoNombre.jRBMoralNombre.addActionListener(this);
        nuevoNombre.jLNombreMoralNombre.setVisible(false);
        nuevoNombre.jTNombreNombreMoral.setVisible(false);
        PanelNombre.add(nuevoNombre);
        this.controlNombres.put(indiceNombre, nuevoNombre);
        indiceNombre++;
        PanelNombre.setLayout(new GridLayout(indiceNombre, 0));
        PanelNombre.updateUI();
        jBEliminarNombre.setVisible(true);
    }//GEN-LAST:event_jBAgregarNombreActionPerformed

    private void jRBFisicaNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBFisicaNombreActionPerformed
        // TODO add your handling code here:
        jLNombreMoralNombre.setVisible(false);
        jTNombreNombreMoral.setVisible(false);
        jLNombreFisicaNombre.setVisible(true);
        jLApMaNombreFisica.setVisible(true);
        jLApPaNombreFisica.setVisible(true);
        jTNombreNombreFisica.setVisible(true);
        jTApMaNombreFisica.setVisible(true);
        jTApPaNombreFisica.setVisible(true);
    }//GEN-LAST:event_jRBFisicaNombreActionPerformed

    private void jRBMoralNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBMoralNombreActionPerformed
        // TODO add your handling code here:
        jLNombreFisicaNombre.setVisible(false);
        jLApMaNombreFisica.setVisible(false);
        jLApPaNombreFisica.setVisible(false);
        jTNombreNombreFisica.setVisible(false);
        jTApMaNombreFisica.setVisible(false);
        jTApPaNombreFisica.setVisible(false);
        jLNombreMoralNombre.setVisible(true);
        jTNombreNombreMoral.setVisible(true);
    }//GEN-LAST:event_jRBMoralNombreActionPerformed

    private void jTNombreNombreMoralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreNombreMoralActionPerformed
         jTNombreNombreMoral.transferFocus();
    }//GEN-LAST:event_jTNombreNombreMoralActionPerformed

    private void jBTerminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTerminarActionPerformed
        int banderanombres = controlNombres.size();
        datos.getNumeroNombres(banderanombres);
        String[][] nombresdatos = new String[banderanombres][4];
        Iterator it = controlNombres.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            //se obtiene el KEY -> identificador unico
            int itm = (int) entry.getKey();
            //si comando de componente es igual a comando pulsado
          
                //se recupera el contenido del JTextfield
                if(((CertificacionesNombres) entry.getValue()).jRBFisicaNombre.isSelected()==true)
                {                    
                    String dos = ((CertificacionesNombres) entry.getValue()).jTNombreNombreFisica.getText();
                    String tres = ((CertificacionesNombres) entry.getValue()).jTApPaNombre.getText();
                    String cuatro = ((CertificacionesNombres) entry.getValue()).jTApMaNombre.getText();
                    nombresdatos[itm][0]="Persona Física";
                    nombresdatos[itm][1]= dos;
                    nombresdatos[itm][2]= tres;
                    nombresdatos[itm][3]= cuatro;
                }
                else
                {
                    String uno = ((CertificacionesNombres) entry.getValue()).jTNombreNombreMoral.getText();
                    nombresdatos[itm][0]="Persona Moral";
                    nombresdatos[itm][1]= uno;
                    nombresdatos[itm][2]= "";
                    nombresdatos[itm][3]= "";
                }
                
            }
        datos.getNombres(nombresdatos);
        ValidacionFinal();
    }//GEN-LAST:event_jBTerminarActionPerformed

    private void jRBFisicaNombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBFisicaNombreMouseClicked
     }//GEN-LAST:event_jRBFisicaNombreMouseClicked

    private void jRBMoralNombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBMoralNombreMouseClicked
    }//GEN-LAST:event_jRBMoralNombreMouseClicked

    private void jTNombreNombreFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreNombreFisicaActionPerformed
        jTNombreNombreFisica.transferFocus();
    }//GEN-LAST:event_jTNombreNombreFisicaActionPerformed

    private void jTApPaNombreFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApPaNombreFisicaActionPerformed
        jTApPaNombreFisica.transferFocus();
    }//GEN-LAST:event_jTApPaNombreFisicaActionPerformed

    private void jTApMaNombreFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApMaNombreFisicaActionPerformed
        jTApMaNombreFisica.transferFocus();
    }//GEN-LAST:event_jTApMaNombreFisicaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        EleccionTipoExpediente.cerrarCertificaciones(1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jBCambiarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCambiarTipoActionPerformed
        a = new ActualizarTipoexpediente();
        a.ExpedienteActual(7);
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
                Logger.getLogger(Certificaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
            new Certificaciones().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelNombre;
    private javax.swing.JPanel Principal;
    private javax.swing.JButton jBAgregarNombre;
    private javax.swing.JButton jBCambiarTipo;
    private javax.swing.JButton jBEliminarNombre;
    private javax.swing.JButton jBTerminar;
    private javax.swing.JButton jButton1;
    public javax.swing.JLabel jLApMaNombreFisica;
    public javax.swing.JLabel jLApPaNombreFisica;
    public javax.swing.JLabel jLNombreFisicaNombre;
    public javax.swing.JLabel jLNombreMoralNombre;
    public javax.swing.JLabel jLObservaciones;
    private javax.swing.JLabel jLTituloNombres;
    private javax.swing.JPanel jPPDF;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JRadioButton jRBFisicaNombre;
    public static javax.swing.JRadioButton jRBMoralNombre;
    private javax.swing.JScrollPane jSPNombre;
    private javax.swing.JScrollPane jSPPrincipal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTAObservaciones;
    public static javax.swing.JTextField jTApMaNombreFisica;
    public static javax.swing.JTextField jTApPaNombreFisica;
    public static javax.swing.JTextField jTNombreNombreFisica;
    public static javax.swing.JTextField jTNombreNombreMoral;
    private javax.swing.ButtonGroup tipoPersonaNombre;
    private javax.swing.JLabel tituloVentanaNoExpedienteTestamentos;
    private javax.swing.JLabel tituloVentanaNombres;
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
        Iterator it = controlNombres.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            //se obtiene el KEY -> identificador unico
            String itm = entry.getKey().toString();
            //si comando de componente es igual a comando pulsado
            if(comando.equals("indicefNombre" + itm))
            {                              
                ((CertificacionesNombres) entry.getValue()).jLNombreFisicaNombre.setVisible(true);
                ((CertificacionesNombres) entry.getValue()).jLApPaNombre.setVisible(true);
                ((CertificacionesNombres) entry.getValue()).jLApMaNombre.setVisible(true);
                ((CertificacionesNombres) entry.getValue()).jTNombreNombreFisica.setVisible(true);
                ((CertificacionesNombres) entry.getValue()).jTApPaNombre.setVisible(true);
                ((CertificacionesNombres) entry.getValue()).jTApMaNombre.setVisible(true);
                ((CertificacionesNombres) entry.getValue()).jLNombreMoralNombre.setVisible(false);
                ((CertificacionesNombres) entry.getValue()).jTNombreNombreMoral.setVisible(false);
            }
            else if(comando.equals("indicemNombre" + itm))
            {
                ((CertificacionesNombres) entry.getValue()).jLNombreFisicaNombre.setVisible(false);
                ((CertificacionesNombres) entry.getValue()).jLApPaNombre.setVisible(false);
                ((CertificacionesNombres) entry.getValue()).jLApMaNombre.setVisible(false);
                ((CertificacionesNombres) entry.getValue()).jTNombreNombreFisica.setVisible(false);
                ((CertificacionesNombres) entry.getValue()).jTApPaNombre.setVisible(false);
                ((CertificacionesNombres) entry.getValue()).jTApMaNombre.setVisible(false);
                ((CertificacionesNombres) entry.getValue()).jLNombreMoralNombre.setVisible(true);
                ((CertificacionesNombres) entry.getValue()).jTNombreNombreMoral.setVisible(true);
            }
        }
    }    
}
