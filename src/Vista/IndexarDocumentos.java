package Vista;

import Controlador.BDdocumentos;
import Controlador.ConexionMySql;
import Controlador.LecturaEscritura;
import Controlador.ValoresInicialesPrograma;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


public final class IndexarDocumentos extends javax.swing.JFrame implements Runnable{
    DefaultTableModel modelo;
    String[] URLs;
    String direccionFinal;
    int pasar=0;
    //Carpeta donde queremos que se guarden los archivos temporales
    String direccion= ValoresInicialesPrograma.getCarpetaCopiaTemporalArchivos();
    
    public void propiedadesTabla()
    {
        jTDirecciones.setRowHeight(30);
        String cabecera[]= {"Ruta"};
        String datos[][]={};
        modelo= new DefaultTableModel(datos, cabecera)
        {
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
        };
        jTDirecciones.setModel(modelo);
    }
    private void LimpiarTabla()
    {
       for (int i = 0; i < jTDirecciones.getRowCount(); i++) {
           modelo.removeRow(i);
           i-=1;
       }
    }
    private void crearCarpetaTemporal()
    {      
            String NombreCarpeta=jTNoExpediente.getText();
            File c;
            c = new File(direccion + "\\" + NombreCarpeta);
                if(c.mkdir())
                {
                    System.out.println("Se ha creado la carpeta con exito.");
                    direccionFinal=direccion.concat("\\"+NombreCarpeta+"\\");
                    System.out.println("Dirección final: " + direccionFinal);
                }else
                {   
                    System.out.println("ERROR! , hubo un problema al crear la carpeta.");
                }
            LecturaEscritura.setDireccion(direccionFinal);
    }
    public void ocultarPaneles()
    {
        jPDirecciones.setVisible(false);
        jPFinalizar.setVisible(false);
    }
    public void close()
    {
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente salir de la indexación de los archivos?",
                "Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
        {
             dispose();
        }
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
    public IndexarDocumentos() 
    {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("Imagenes/IconoNotaria.png")).getImage());
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                close();
            }
        });
        setLocationRelativeTo(null);
        setSize(600, 140);
        setResizable(false);
        propiedadesTabla();
        ocultarPaneles();
        ValidarSolonumeros(jTNoExpediente);
        SNumeros(jTNoExpediente);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jPrincipal = new javax.swing.JPanel();
        Vista = new javax.swing.JPanel();
        jPNoExpediente = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTNoExpediente = new javax.swing.JTextField();
        jBValidar = new javax.swing.JButton();
        jPDirecciones = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTDirecciones = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jBSeleccionar = new javax.swing.JButton();
        jPFinalizar = new javax.swing.JPanel();
        jBIndexar = new javax.swing.JButton();
        jBCambiarExp = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        jPCargando = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jInformacionActual = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Indexar nuevo expediente");

        bg.setBackground(new java.awt.Color(204, 204, 204));
        bg.setLayout(new java.awt.CardLayout());

        jPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        Vista.setBackground(new java.awt.Color(49, 58, 115));

        javax.swing.GroupLayout VistaLayout = new javax.swing.GroupLayout(Vista);
        Vista.setLayout(VistaLayout);
        VistaLayout.setHorizontalGroup(
            VistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        VistaLayout.setVerticalGroup(
            VistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPNoExpediente.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setText("Ingresa el número de expediente que se trabajara.");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setText("Número de expediente:");

        jTNoExpediente.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N

        jBValidar.setBackground(new java.awt.Color(41, 168, 73));
        jBValidar.setForeground(new java.awt.Color(255, 255, 255));
        jBValidar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Validar.png"))); // NOI18N
        jBValidar.setBorder(null);
        jBValidar.setBorderPainted(false);
        jBValidar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/ValidarApretado.png"))); // NOI18N
        jBValidar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBValidarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPNoExpedienteLayout = new javax.swing.GroupLayout(jPNoExpediente);
        jPNoExpediente.setLayout(jPNoExpedienteLayout);
        jPNoExpedienteLayout.setHorizontalGroup(
            jPNoExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPNoExpedienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPNoExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPNoExpedienteLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTNoExpediente, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBValidar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPNoExpedienteLayout.setVerticalGroup(
            jPNoExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPNoExpedienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPNoExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBValidar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPNoExpedienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTNoExpediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addContainerGap())
        );

        jPDirecciones.setBackground(new java.awt.Color(255, 255, 255));

        jTDirecciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTDirecciones);

        jLabel5.setText("Selecciona los archivos a indexar.");

        jBSeleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Seleccionar.png"))); // NOI18N
        jBSeleccionar.setBorder(null);
        jBSeleccionar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/SeleccionarApretado.png"))); // NOI18N
        jBSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSeleccionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPDireccionesLayout = new javax.swing.GroupLayout(jPDirecciones);
        jPDirecciones.setLayout(jPDireccionesLayout);
        jPDireccionesLayout.setHorizontalGroup(
            jPDireccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPDireccionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBSeleccionar)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPDireccionesLayout.setVerticalGroup(
            jPDireccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPDireccionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPDireccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jBSeleccionar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPFinalizar.setBackground(new java.awt.Color(255, 255, 255));

        jBIndexar.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jBIndexar.setText("Indexar archivos");
        jBIndexar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBIndexarActionPerformed(evt);
            }
        });

        jBCambiarExp.setText("Cambiar de No. Expediente");
        jBCambiarExp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCambiarExpActionPerformed(evt);
            }
        });

        Cancelar.setText("Cancelar");
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPFinalizarLayout = new javax.swing.GroupLayout(jPFinalizar);
        jPFinalizar.setLayout(jPFinalizarLayout);
        jPFinalizarLayout.setHorizontalGroup(
            jPFinalizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPFinalizarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Cancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBCambiarExp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBIndexar, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPFinalizarLayout.setVerticalGroup(
            jPFinalizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPFinalizarLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPFinalizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBIndexar)
                    .addComponent(jBCambiarExp)
                    .addComponent(Cancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPrincipalLayout = new javax.swing.GroupLayout(jPrincipal);
        jPrincipal.setLayout(jPrincipalLayout);
        jPrincipalLayout.setHorizontalGroup(
            jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPrincipalLayout.createSequentialGroup()
                .addComponent(Vista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPNoExpediente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPDirecciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPFinalizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPrincipalLayout.setVerticalGroup(
            jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Vista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPrincipalLayout.createSequentialGroup()
                .addComponent(jPNoExpediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPDirecciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bg.add(jPrincipal, "card2");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/ring.gif"))); // NOI18N

        jInformacionActual.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jInformacionActual.setText("Información...");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Progreso...");

        javax.swing.GroupLayout jPCargandoLayout = new javax.swing.GroupLayout(jPCargando);
        jPCargando.setLayout(jPCargandoLayout);
        jPCargandoLayout.setHorizontalGroup(
            jPCargandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCargandoLayout.createSequentialGroup()
                .addGroup(jPCargandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPCargandoLayout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addComponent(jLabel2))
                    .addGroup(jPCargandoLayout.createSequentialGroup()
                        .addGap(249, 249, 249)
                        .addComponent(jLabel6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPCargandoLayout.createSequentialGroup()
                .addGap(0, 64, Short.MAX_VALUE)
                .addComponent(jInformacionActual, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPCargandoLayout.setVerticalGroup(
            jPCargandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCargandoLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jInformacionActual, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jLabel2)
                .addContainerGap(181, Short.MAX_VALUE))
        );

        bg.add(jPCargando, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBIndexarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBIndexarActionPerformed
        try {
            CambiarPaneles();
        } catch (Exception ex) {
            Logger.getLogger(IndexarDocumentos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBIndexarActionPerformed
    public void CambiarPaneles() throws Exception
    {
            jPrincipal.setVisible(false);
            jPCargando.setVisible(true);
            Thread mihilo = new Thread(this);
            mihilo.start();
    }
    public void Indexar()
    {
        crearCarpetaTemporal();
            try
            {                 
                LecturaEscritura.Obtenerdatos(URLs);
            } catch (Exception ex) 
            {
                System.out.println("Error en metodo Obtener Datos");
            }
    }
    
    private void jBSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSeleccionarActionPerformed
        SeleccionarArchivos();
    }//GEN-LAST:event_jBSeleccionarActionPerformed
    private void SeleccionarArchivos()
    {
        LimpiarTabla();
        int contador=0;
        JFileChooser jf = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos PDFs", "pdf");
        jf.setFileFilter(filtro);
        jf.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jf.setMultiSelectionEnabled(true);
        jf.getSelectedFiles();
        int Seleccion = jf.showOpenDialog(this);
        if (Seleccion==JFileChooser.APPROVE_OPTION)
        {
            jBIndexar.setEnabled(true);
            for (File item : jf.getSelectedFiles())
            {
                String ruta= item.getAbsolutePath().toString();
                Object datosInsertar[]={ruta};
                modelo.addRow(datosInsertar);
                contador++;
            }
            int contador2=0;
            URLs = new String[contador];
            for (File item : jf.getSelectedFiles())
            {
                URLs[contador2] = item.getAbsolutePath().toString();
                contador2++;
            }
            System.out.println(URLs.length);
            for (String URL : URLs) {
                System.out.println(URL);
                if(VerificarEspaciosEnBlanco(URL)==true)
                {
                    JOptionPane.showMessageDialog(null, "El nombre del archivo NO PUEDE puede tener un ESPACIO en blanco.");
                    SeleccionarArchivos();
                }  
            }
        }
    }
    private boolean VerificarEspaciosEnBlanco(String URL)
    {
        boolean espacio=false;
        if(URL.indexOf(" ")!=-1 || URL.indexOf("    ")!=-1)
        {
             espacio=true;
        }
        return espacio;
    }
    private int validarNoExpedienteExistente()
    {
         int resultado=0; 
         int verificar= Integer.parseInt(jTNoExpediente.getText());
         ConexionMySql mysql = new ConexionMySql();
         Connection cn = mysql.getConection();
         String sSQL="SELECT * FROM casoscliente WHERE IDNoExpediente=" + "'" + verificar +"'";
         try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            if (rs.next()) 
            {
                System.out.println("Ya existe este Expediente, intenta con otro!");
                resultado=1;
            }
        } catch (SQLException ex) {
            System.out.println("Error al contectar la base de datos" + ex);
        }finally
        {
            mysql.desconectar();
        }
         return resultado;
    }
    
    private void jBValidarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBValidarActionPerformed
        if (jTNoExpediente.getText().isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, "Es necesario indicar el No. de folio.");           
        }else if(validarNoExpedienteExistente()==1)
        {
            JOptionPane.showMessageDialog(null, "Ya existe este Expediente, intenta con otro!");
        }else
        {
            BDdocumentos.setNoExpediente(Integer.parseInt(jTNoExpediente.getText()));
            System.out.println("Enviando el No. Expediente: " + Integer.parseInt(jTNoExpediente.getText()));
            jTNoExpediente.setEditable(false);
            jTNoExpediente.setEnabled(false); 
            jBValidar.setEnabled(false);
            jPDirecciones.setVisible(true);
            jPFinalizar.setVisible(true);
            jBIndexar.setEnabled(false);
            setSize(600, 560);
        }
    }//GEN-LAST:event_jBValidarActionPerformed

    private void jBCambiarExpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCambiarExpActionPerformed
            jTNoExpediente.setEditable(true);
            jTNoExpediente.setEnabled(true); 
            jBValidar.setEnabled(true);
            jBIndexar.setEnabled(false);
            setSize(600, 140);
            LimpiarTabla();
            ocultarPaneles();
    }//GEN-LAST:event_jBCambiarExpActionPerformed

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
         close();   
    }//GEN-LAST:event_CancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new IndexarDocumentos().setVisible(true);
                }
            });
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(IndexarDocumentos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancelar;
    private javax.swing.JPanel Vista;
    private javax.swing.JPanel bg;
    private javax.swing.JButton jBCambiarExp;
    private javax.swing.JButton jBIndexar;
    private javax.swing.JButton jBSeleccionar;
    private javax.swing.JButton jBValidar;
    public static javax.swing.JLabel jInformacionActual;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    public static javax.swing.JPanel jPCargando;
    private javax.swing.JPanel jPDirecciones;
    private javax.swing.JPanel jPFinalizar;
    private javax.swing.JPanel jPNoExpediente;
    public static javax.swing.JPanel jPrincipal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTDirecciones;
    public static javax.swing.JTextField jTNoExpediente;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() 
    {
        Indexar();
    }
}
