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
public class OtrosVisualizacion extends javax.swing.JFrame implements ActionListener{
    private int indiceParticipante;
    private DateFormat df = DateFormat.getDateInstance();
    private Map controlParticipantes = new HashMap();
    Controlador.OtrosActualizacion datos = new Controlador.OtrosActualizacion();
    ActualizarTipoexpediente a;

    private void OcultarPanenlesIniciales()
    {
    //Se ocultan los paneles de Participante y Acreditante morales al iniciar, y se inicia el indice de control de ambos en 0
        jLNombreMoralParticipante.setVisible(false);
        jTNombreParticipanteMoral.setVisible(false);
        indiceParticipante = 0;
        if (indiceParticipante==0) 
        {
                jBEliminarParticipante.setVisible(false);
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
        SLetras(jTNombreParticipanteFisica);
        SLetras(jTApPaParticipanteFisica);
        SLetras(jTApMaParticipanteFisica);

        CambiarAMayusculas(jTNombreParticipanteFisica);
        CambiarAMayusculas(jTApPaParticipanteFisica);
        CambiarAMayusculas(jTApMaParticipanteFisica);  

       
        NoCaracteres(jTNombreParticipanteFisica, 70);
        NoCaracteres(jTApPaParticipanteFisica, 30);
        NoCaracteres(jTApMaParticipanteFisica, 30);
        NoCaracteres(jTNombreParticipanteMoral, 70);
        NoCaracteres(jTTipoActo, 50);
        
    }
    private void ValidacionParticipantePrincipal()
    {
        boolean pasa=false;
        if (jRBFisicaParticipante.isSelected()==false && jRBMoralParticipante.isSelected()==false)
        {
            JOptionPane.showMessageDialog(null, "NO has seleccionado el tipo de Participante","Sin selección" ,JOptionPane.ERROR_MESSAGE);
            pasa=true;
        }else if(jRBFisicaParticipante.isSelected()==true)
            {
                if(jTNombreParticipanteFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del Participante","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }else if(jTApPaParticipanteFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO PATERNO del Participante","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }else if(jTApMaParticipanteFisica.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO MATERNO del Participante","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }                           
            }
        else if(jRBMoralParticipante.isSelected()==true)
        {
                if(jTNombreParticipanteMoral.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE MORAL del Participante","Campo vacio" ,JOptionPane.ERROR_MESSAGE);
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
            ValidacionParticipantes();
    }

    private void ValidacionParticipantes()
    {
        boolean pasa=false;
        Iterator it = controlParticipantes.entrySet().iterator();
        while (it.hasNext()) 
        {
            Map.Entry entry = (Map.Entry)it.next();
            //se obtiene el KEY -> identificador unico
            int itm = (int) entry.getKey();
            //si comando de componente es igual a comando pulsado
                //se recupera el contenido del JTextfield
                if((((OtrosParticipante) entry.getValue()).jRBFisicaParticipante.isSelected()==false) && (((OtrosParticipante) entry.getValue()).jRBMoralParticipante.isSelected()==false))
                {
                    JOptionPane.showMessageDialog(null, "No has seleccionado el tipo de persona en el Participante " + (itm+2),"Selección vacia" ,JOptionPane.ERROR_MESSAGE);
                    pasa=true;
                }
                else if(((OtrosParticipante) entry.getValue()).jRBFisicaParticipante.isSelected()==true)
                {
                    if(((OtrosParticipante) entry.getValue()).jTNombreParticipanteFisica.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del Participante " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }else if(((OtrosParticipante) entry.getValue()).jTApPaParticipante.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO PATERNO del Participante " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }else if(((OtrosParticipante) entry.getValue()).jTApMaParticipante.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el APELLIDO MATERNO del Participante " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }                             
                }else if(((OtrosParticipante) entry.getValue()).jRBMoralParticipante.isSelected()==true)
                {
                    if(((OtrosParticipante) entry.getValue()).jTNombreParticipanteMoral.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Es necesario llenar el NOMBRE del Participante MORAL " + (itm+2),"Campo vacio" ,JOptionPane.ERROR_MESSAGE);
                        pasa=true;
                    }
                }
        }
        if(pasa == false)
        {
            GuardarBD();
        }
    }
    public void LlenarParticipantes(int posicion, String tipoPersona, String NombrePersona, String ApPa, String ApMa)
    {
        Iterator it = controlParticipantes.entrySet().iterator();
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
                        ((OtrosParticipante)entry.getValue()).jRBFisicaParticipante.setSelected(true);
                        ((OtrosParticipante)entry.getValue()).jTNombreParticipanteFisica.setText(NombrePersona);
                        ((OtrosParticipante)entry.getValue()).jTApPaParticipante.setText(ApPa);
                        ((OtrosParticipante)entry.getValue()).jTApMaParticipante.setText(ApMa);
                    }
                    else if(tipoPersona.equals("Persona Moral"))
                    {
                        ((OtrosParticipante)entry.getValue()).jRBMoralParticipante.setSelected(true);
                        ((OtrosParticipante)entry.getValue()).jTNombreParticipanteMoral.setText(NombrePersona);
                        ((OtrosParticipante) entry.getValue()).jLNombreFisicaParticipante.setVisible(false);
                        ((OtrosParticipante) entry.getValue()).jLApPaParticipante.setVisible(false);
                        ((OtrosParticipante) entry.getValue()).jLApMaParticipante.setVisible(false);
                        ((OtrosParticipante) entry.getValue()).jTNombreParticipanteFisica.setVisible(false);
                        ((OtrosParticipante) entry.getValue()).jTApPaParticipante.setVisible(false);
                        ((OtrosParticipante) entry.getValue()).jTApMaParticipante.setVisible(false);
                        ((OtrosParticipante) entry.getValue()).jLNombreMoralParticipante.setVisible(true);
                        ((OtrosParticipante) entry.getValue()).jTNombreParticipanteMoral.setVisible(true);
                    }
                }   
        }
    }
    private void ValidacionFinal()
    {
        ValidacionParticipantePrincipal();
    }
    /**
     * Este metodo envía todos los datos a la clase contralador de Donacion donde se guardaran los datos ya validados
     */
    public void GuardarBD()
    {
        System.out.println("FELICITACIONES, Has pasado todas las validaciones!");
            datos.gettipoOtros(jTTipoActo.getText());
            datos.getObservaciones(jTAObservaciones.getText());           
            datos.capturaFinal();
    }

    /**
     * Constructor de la clase
     */
    public OtrosVisualizacion() 
    {
        //JFrame iniciando compnente, en el centro de la pantalla, sin reescalado
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("Imagenes/IconoNotaria.png")).getImage());
        setExtendedState(MAXIMIZED_BOTH);
        tituloVentanaNoExpedienteOtros.setText("No. Expediente: #" + AbrirExpediente.getNoExpedinte());
        OcultarPanenlesIniciales();
        ValidacionLetrasNumerosTiempoReal();
        VistaPreviaLectura vistaPreviaPDF = new VistaPreviaLectura();        
        jPPDF.add(vistaPreviaPDF);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoPersonaParticipante = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jSPPrincipal = new javax.swing.JScrollPane();
        Principal = new javax.swing.JPanel();
        jBEliminarParticipante = new javax.swing.JButton();
        jBAgregarParticipante = new javax.swing.JButton();
        jLTituloParticipante = new javax.swing.JLabel();
        jRBFisicaParticipante = new javax.swing.JRadioButton();
        jRBMoralParticipante = new javax.swing.JRadioButton();
        jLNombreFisicaParticipante = new javax.swing.JLabel();
        jLNombreMoralParticipante = new javax.swing.JLabel();
        jTNombreParticipanteMoral = new javax.swing.JTextField();
        jTNombreParticipanteFisica = new javax.swing.JTextField();
        jTApPaParticipanteFisica = new javax.swing.JTextField();
        jLApPaParticipanteFisica = new javax.swing.JLabel();
        jTApMaParticipanteFisica = new javax.swing.JTextField();
        tituloVentanaNoExpedienteOtros = new javax.swing.JLabel();
        jSPParticipante = new javax.swing.JScrollPane();
        PanelParticipante = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLObservaciones = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTAObservaciones = new javax.swing.JTextArea();
        jBTerminar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLApMaParticipanteFisica = new javax.swing.JLabel();
        tituloVentanaOtros = new javax.swing.JLabel();
        jLTipoActo = new javax.swing.JLabel();
        jTTipoActo = new javax.swing.JTextField();
        jLObservaciones1 = new javax.swing.JLabel();
        jPPDF = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Otros");

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

        jBEliminarParticipante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Eliminar participante.png"))); // NOI18N
        jBEliminarParticipante.setBorderPainted(false);
        jBEliminarParticipante.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Eliminar participante Grande.png"))); // NOI18N
        jBEliminarParticipante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminarParticipanteActionPerformed(evt);
            }
        });

        jBAgregarParticipante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Agregar participante normal.png"))); // NOI18N
        jBAgregarParticipante.setBorderPainted(false);
        jBAgregarParticipante.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Boton Agregar participante Grande.png"))); // NOI18N
        jBAgregarParticipante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAgregarParticipanteActionPerformed(evt);
            }
        });

        jLTituloParticipante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLTituloParticipante.setText("Participante");

        tipoPersonaParticipante.add(jRBFisicaParticipante);
        jRBFisicaParticipante.setText("Persona Física");
        jRBFisicaParticipante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBFisicaParticipanteMouseClicked(evt);
            }
        });
        jRBFisicaParticipante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBFisicaParticipanteActionPerformed(evt);
            }
        });

        tipoPersonaParticipante.add(jRBMoralParticipante);
        jRBMoralParticipante.setText("Persona Moral");
        jRBMoralParticipante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBMoralParticipanteMouseClicked(evt);
            }
        });
        jRBMoralParticipante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBMoralParticipanteActionPerformed(evt);
            }
        });

        jLNombreFisicaParticipante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreFisicaParticipante.setText("*Nombre:");

        jLNombreMoralParticipante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreMoralParticipante.setText("*Nombre: ");

        jTNombreParticipanteMoral.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreParticipanteMoral.setPreferredSize(new java.awt.Dimension(250, 26));
        jTNombreParticipanteMoral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreParticipanteMoralActionPerformed(evt);
            }
        });

        jTNombreParticipanteFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreParticipanteFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreParticipanteFisicaActionPerformed(evt);
            }
        });

        jTApPaParticipanteFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApPaParticipanteFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApPaParticipanteFisicaActionPerformed(evt);
            }
        });

        jLApPaParticipanteFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApPaParticipanteFisica.setText("*Apellido Paterno:");

        jTApMaParticipanteFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApMaParticipanteFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApMaParticipanteFisicaActionPerformed(evt);
            }
        });

        tituloVentanaNoExpedienteOtros.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tituloVentanaNoExpedienteOtros.setText("No. Expediente: #");

        jSPParticipante.setBorder(null);
        jSPParticipante.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jSPParticipante.setHorizontalScrollBar(null);

        PanelParticipante.setBackground(new java.awt.Color(255, 255, 255));
        PanelParticipante.setAlignmentX(0.0F);
        PanelParticipante.setAlignmentY(0.0F);

        javax.swing.GroupLayout PanelParticipanteLayout = new javax.swing.GroupLayout(PanelParticipante);
        PanelParticipante.setLayout(PanelParticipanteLayout);
        PanelParticipanteLayout.setHorizontalGroup(
            PanelParticipanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 986, Short.MAX_VALUE)
        );
        PanelParticipanteLayout.setVerticalGroup(
            PanelParticipanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );

        jSPParticipante.setViewportView(PanelParticipante);

        jLObservaciones.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLObservaciones.setText("Información");

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

        jLApMaParticipanteFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApMaParticipanteFisica.setText("*Apellido Materno:");

        tituloVentanaOtros.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tituloVentanaOtros.setText("Otros");

        jLTipoActo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLTipoActo.setText("*Tipo de acto:");

        jTTipoActo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLObservaciones1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLObservaciones1.setText("general:");

        javax.swing.GroupLayout PrincipalLayout = new javax.swing.GroupLayout(Principal);
        Principal.setLayout(PrincipalLayout);
        PrincipalLayout.setHorizontalGroup(
            PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PrincipalLayout.createSequentialGroup()
                                .addComponent(jBAgregarParticipante, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBEliminarParticipante, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrincipalLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addComponent(tituloVentanaOtros)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tituloVentanaNoExpedienteOtros))
                                    .addGroup(PrincipalLayout.createSequentialGroup()
                                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLNombreFisicaParticipante)
                                            .addComponent(jLNombreMoralParticipante))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(PrincipalLayout.createSequentialGroup()
                                                .addComponent(jTNombreParticipanteMoral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(237, 237, 237))
                                            .addGroup(PrincipalLayout.createSequentialGroup()
                                                .addComponent(jTNombreParticipanteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLApPaParticipanteFisica)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTApPaParticipanteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLApMaParticipanteFisica)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTApMaParticipanteFisica)))))))
                        .addGap(31, 31, 31))
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addComponent(jLTipoActo)
                        .addGap(18, 18, 18)
                        .addComponent(jTTipoActo, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrincipalLayout.createSequentialGroup()
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PrincipalLayout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBTerminar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PrincipalLayout.createSequentialGroup()
                                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLObservaciones)
                                    .addComponent(jLObservaciones1))
                                .addGap(38, 38, 38)
                                .addComponent(jScrollPane1))
                            .addGroup(PrincipalLayout.createSequentialGroup()
                                .addGap(0, 9, Short.MAX_VALUE)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34))
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSPParticipante, javax.swing.GroupLayout.PREFERRED_SIZE, 851, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PrincipalLayout.createSequentialGroup()
                                .addGap(303, 303, 303)
                                .addComponent(jRBFisicaParticipante)
                                .addGap(18, 18, 18)
                                .addComponent(jRBMoralParticipante))
                            .addGroup(PrincipalLayout.createSequentialGroup()
                                .addGap(401, 401, 401)
                                .addComponent(jLTituloParticipante)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        PrincipalLayout.setVerticalGroup(
            PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrincipalLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tituloVentanaNoExpedienteOtros)
                    .addComponent(tituloVentanaOtros))
                .addGap(9, 9, 9)
                .addComponent(jLTituloParticipante)
                .addGap(5, 5, 5)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRBFisicaParticipante)
                    .addComponent(jRBMoralParticipante))
                .addGap(18, 18, 18)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTNombreParticipanteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLApPaParticipanteFisica)
                        .addComponent(jTApPaParticipanteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLApMaParticipanteFisica)
                        .addComponent(jTApMaParticipanteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLNombreFisicaParticipante)))
                .addGap(8, 8, 8)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLNombreMoralParticipante)
                    .addComponent(jTNombreParticipanteMoral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jSPParticipante, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBAgregarParticipante, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBEliminarParticipante, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLTipoActo)
                    .addComponent(jTTipoActo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addComponent(jLObservaciones)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLObservaciones1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
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

    private void jBEliminarParticipanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarParticipanteActionPerformed
        indiceParticipante--;
        PanelParticipante.remove(indiceParticipante);
        this.controlParticipantes.remove(indiceParticipante);
        if (indiceParticipante>0) 
        {
            PanelParticipante.setLayout(new GridLayout(indiceParticipante, 0));
        } 
        PanelParticipante.updateUI();
        if (indiceParticipante==0)
        {
            jBEliminarParticipante.setVisible(false);
        }
    }//GEN-LAST:event_jBEliminarParticipanteActionPerformed

    private void jBAgregarParticipanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarParticipanteActionPerformed
        AgregarParticipante();
    }//GEN-LAST:event_jBAgregarParticipanteActionPerformed
    public void AgregarParticipante()
    {
        OtrosParticipante nuevoParticipante = new OtrosParticipante(indiceParticipante);
        nuevoParticipante.jRBFisicaParticipante.addActionListener(this);
        nuevoParticipante.jRBMoralParticipante.addActionListener(this);
        nuevoParticipante.jLNombreMoralParticipante.setVisible(false);
        nuevoParticipante.jTNombreParticipanteMoral.setVisible(false);
        PanelParticipante.add(nuevoParticipante);
        this.controlParticipantes.put(indiceParticipante, nuevoParticipante);
        indiceParticipante++;
        PanelParticipante.setLayout(new GridLayout(indiceParticipante, 0));
        PanelParticipante.updateUI();
        jBEliminarParticipante.setVisible(true);
    }
    private void jRBFisicaParticipanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBFisicaParticipanteActionPerformed
        // TODO add your handling code here:
        jLNombreMoralParticipante.setVisible(false);
        jTNombreParticipanteMoral.setVisible(false);
        jLNombreFisicaParticipante.setVisible(true);
        jLApMaParticipanteFisica.setVisible(true);
        jLApPaParticipanteFisica.setVisible(true);
        jTNombreParticipanteFisica.setVisible(true);
        jTApMaParticipanteFisica.setVisible(true);
        jTApPaParticipanteFisica.setVisible(true);
    }//GEN-LAST:event_jRBFisicaParticipanteActionPerformed

    private void jRBMoralParticipanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBMoralParticipanteActionPerformed
        // TODO add your handling code here:
        jLNombreFisicaParticipante.setVisible(false);
        jLApMaParticipanteFisica.setVisible(false);
        jLApPaParticipanteFisica.setVisible(false);
        jTNombreParticipanteFisica.setVisible(false);
        jTApMaParticipanteFisica.setVisible(false);
        jTApPaParticipanteFisica.setVisible(false);
        jLNombreMoralParticipante.setVisible(true);
        jTNombreParticipanteMoral.setVisible(true);
    }//GEN-LAST:event_jRBMoralParticipanteActionPerformed

    private void jTNombreParticipanteMoralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreParticipanteMoralActionPerformed
         jTNombreParticipanteMoral.transferFocus();
    }//GEN-LAST:event_jTNombreParticipanteMoralActionPerformed

    private void jBTerminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTerminarActionPerformed
        int banderaparticipantes = controlParticipantes.size();
        datos.getNumeroParticipantes(banderaparticipantes);
        String[][] participantesdatos = new String[banderaparticipantes][4];
        Iterator it = controlParticipantes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            //se obtiene el KEY -> identificador unico
            int itm = (int) entry.getKey();
            //si comando de componente es igual a comando pulsado
          
                //se recupera el contenido del JTextfield
                if(((OtrosParticipante) entry.getValue()).jRBFisicaParticipante.isSelected()==true)
                {                    
                    String dos = ((OtrosParticipante) entry.getValue()).jTNombreParticipanteFisica.getText();
                    String tres = ((OtrosParticipante) entry.getValue()).jTApPaParticipante.getText();
                    String cuatro = ((OtrosParticipante) entry.getValue()).jTApMaParticipante.getText();
                    participantesdatos[itm][0]="Persona Física";
                    participantesdatos[itm][1]= dos;
                    participantesdatos[itm][2]= tres;
                    participantesdatos[itm][3]= cuatro;
                }
                else
                {
                    String uno = ((OtrosParticipante) entry.getValue()).jTNombreParticipanteMoral.getText();
                    participantesdatos[itm][0]="Persona Moral";
                    participantesdatos[itm][1]= uno;
                    participantesdatos[itm][2]= "";
                    participantesdatos[itm][3]= "";
                }
                
            }
        datos.getParticipantes(participantesdatos);
        ValidacionFinal();
    }//GEN-LAST:event_jBTerminarActionPerformed

    private void jRBFisicaParticipanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBFisicaParticipanteMouseClicked
     }//GEN-LAST:event_jRBFisicaParticipanteMouseClicked

    private void jRBMoralParticipanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBMoralParticipanteMouseClicked
    }//GEN-LAST:event_jRBMoralParticipanteMouseClicked

    private void jTNombreParticipanteFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreParticipanteFisicaActionPerformed
        jTNombreParticipanteFisica.transferFocus();
    }//GEN-LAST:event_jTNombreParticipanteFisicaActionPerformed

    private void jTApPaParticipanteFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApPaParticipanteFisicaActionPerformed
        jTApPaParticipanteFisica.transferFocus();
    }//GEN-LAST:event_jTApPaParticipanteFisicaActionPerformed

    private void jTApMaParticipanteFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApMaParticipanteFisicaActionPerformed
        jTApMaParticipanteFisica.transferFocus();
    }//GEN-LAST:event_jTApMaParticipanteFisicaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * Main de la clase, inicia la ventana.
     * @param args
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                Logger.getLogger(OtrosVisualizacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            new OtrosVisualizacion().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelParticipante;
    private javax.swing.JPanel Principal;
    private javax.swing.JButton jBAgregarParticipante;
    private javax.swing.JButton jBEliminarParticipante;
    private javax.swing.JButton jBTerminar;
    private javax.swing.JButton jButton1;
    public javax.swing.JLabel jLApMaParticipanteFisica;
    public javax.swing.JLabel jLApPaParticipanteFisica;
    public javax.swing.JLabel jLNombreFisicaParticipante;
    public javax.swing.JLabel jLNombreMoralParticipante;
    public javax.swing.JLabel jLObservaciones;
    public javax.swing.JLabel jLObservaciones1;
    public javax.swing.JLabel jLTipoActo;
    private javax.swing.JLabel jLTituloParticipante;
    private javax.swing.JPanel jPPDF;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JRadioButton jRBFisicaParticipante;
    public static javax.swing.JRadioButton jRBMoralParticipante;
    private javax.swing.JScrollPane jSPParticipante;
    private javax.swing.JScrollPane jSPPrincipal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JTextArea jTAObservaciones;
    public static javax.swing.JTextField jTApMaParticipanteFisica;
    public static javax.swing.JTextField jTApPaParticipanteFisica;
    public static javax.swing.JTextField jTNombreParticipanteFisica;
    public static javax.swing.JTextField jTNombreParticipanteMoral;
    public javax.swing.JTextField jTTipoActo;
    private javax.swing.ButtonGroup tipoPersonaParticipante;
    private javax.swing.JLabel tituloVentanaNoExpedienteOtros;
    private javax.swing.JLabel tituloVentanaOtros;
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
        Iterator it = controlParticipantes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            //se obtiene el KEY -> identificador unico
            String itm = entry.getKey().toString();
            //si comando de componente es igual a comando pulsado
            if(comando.equals("indicefParticipante" + itm))
            {                              
                ((OtrosParticipante) entry.getValue()).jLNombreFisicaParticipante.setVisible(true);
                ((OtrosParticipante) entry.getValue()).jLApPaParticipante.setVisible(true);
                ((OtrosParticipante) entry.getValue()).jLApMaParticipante.setVisible(true);
                ((OtrosParticipante) entry.getValue()).jTNombreParticipanteFisica.setVisible(true);
                ((OtrosParticipante) entry.getValue()).jTApPaParticipante.setVisible(true);
                ((OtrosParticipante) entry.getValue()).jTApMaParticipante.setVisible(true);
                ((OtrosParticipante) entry.getValue()).jLNombreMoralParticipante.setVisible(false);
                ((OtrosParticipante) entry.getValue()).jTNombreParticipanteMoral.setVisible(false);
            }
            else if(comando.equals("indicemParticipante" + itm))
            {
                ((OtrosParticipante) entry.getValue()).jLNombreFisicaParticipante.setVisible(false);
                ((OtrosParticipante) entry.getValue()).jLApPaParticipante.setVisible(false);
                ((OtrosParticipante) entry.getValue()).jLApMaParticipante.setVisible(false);
                ((OtrosParticipante) entry.getValue()).jTNombreParticipanteFisica.setVisible(false);
                ((OtrosParticipante) entry.getValue()).jTApPaParticipante.setVisible(false);
                ((OtrosParticipante) entry.getValue()).jTApMaParticipante.setVisible(false);
                ((OtrosParticipante) entry.getValue()).jLNombreMoralParticipante.setVisible(true);
                ((OtrosParticipante) entry.getValue()).jTNombreParticipanteMoral.setVisible(true);
            }
        }
    }    
}
