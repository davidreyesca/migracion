package Vista;

import Controlador.AgregarArchivoAlServidor;
import Controlador.AgregarArchivoExp;
import Controlador.ConexionMySql;
import Controlador.CopiarLocalArchivo;
import Controlador.ImgTabla;
import Controlador.ValoresInicialesPrograma;
import java.awt.HeadlessException;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


public class AgregarNuevoArchivo extends javax.swing.JFrame {

    DefaultTableModel modelo;
    static String ruta, nombre;
    static File archivo;
    
    private void propiedadesTabla()
    {
        String cabecera[]= {"Tipo", "Ruta"};
        String datos[][]={};
        jAgregarArchivo.setDefaultRenderer(Object.class,new ImgTabla());
        modelo= new DefaultTableModel(datos, cabecera)
        {
            @Override
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
        };
        jAgregarArchivo.setModel(modelo);
        jAgregarArchivo.setRowHeight(30);
        jAgregarArchivo.getColumnModel().getColumn(0).setMaxWidth(50);
        jAgregarArchivo.getColumnModel().getColumn(0).setMaxWidth(50);
   }
    
    public AgregarNuevoArchivo() 
    {
        initComponents();
        propiedadesTabla();
        jLTitulo.setText(" No. de expediente " + AgregarArchivoExp.getNoExpediente());
        setIconImage(new ImageIcon(getClass().getResource("Imagenes/IconoNotaria.png")).getImage());
        setLocationRelativeTo(null);
        setResizable(false);
        jIndexar.setEnabled(false);
    }
    
    private void LimpiarTabla()
    {
       for (int i = 0; i < jAgregarArchivo.getRowCount(); i++) 
       {
           modelo.removeRow(i);
           i-=1;
       }
    }

    private void SeleccionarArchivos()
    {
        LimpiarTabla();
        JFileChooser jf = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos PDFs", "pdf");
        jf.setFileFilter(filtro);
        jf.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jf.getSelectedFiles();
        int Seleccion = jf.showOpenDialog(this);
        if (Seleccion==JFileChooser.APPROVE_OPTION)
        {
            jIndexar.setEnabled(true);
            File item = jf.getSelectedFile();
            ruta= item.getAbsolutePath();
            Object datosInsertar[]={new JLabel(new ImageIcon(getClass().getResource("Imagenes/icon-pdf.png"))), jf.getSelectedFile()};
            modelo.addRow(datosInsertar);
            if(VerificarEspaciosEnBlanco(ruta)==true)
            {
                    JOptionPane.showMessageDialog(null, "El nombre del archivo NO PUEDE puede tener un ESPACIO en blanco.");
                    SeleccionarArchivos();
            }
        }else
        {
            jIndexar.setEnabled(false);
        }
    }
    
    private boolean VerificarEspaciosEnBlanco(String URL)
    {
        boolean espacio=false;
        if(URL.contains(" ") || URL.contains("    "))
        {
             espacio=true;
        }
        return espacio;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Contenedor = new javax.swing.JPanel();
        Vista = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLTitulo1 = new javax.swing.JLabel();
        jLTitulo2 = new javax.swing.JLabel();
        jLNotaria = new javax.swing.JLabel();
        Principal = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jAgregarArchivo = new javax.swing.JTable();
        jLTitulo = new javax.swing.JLabel();
        jCancelar = new javax.swing.JButton();
        jIndexar = new javax.swing.JButton();
        Seleccion = new javax.swing.JPanel();
        jLIndicaciones = new javax.swing.JLabel();
        jBAgregar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar nuevo archivo.");

        Contenedor.setBackground(new java.awt.Color(255, 255, 255));

        Vista.setBackground(new java.awt.Color(27, 107, 215));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/addfile.png"))); // NOI18N

        jLTitulo1.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLTitulo1.setForeground(new java.awt.Color(255, 255, 255));
        jLTitulo1.setText("Modulo de agregar");

        jLTitulo2.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLTitulo2.setForeground(new java.awt.Color(255, 255, 255));
        jLTitulo2.setText("nuevo archivo");

        jLNotaria.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLNotaria.setForeground(new java.awt.Color(255, 255, 255));
        jLNotaria.setText("Notaría 21 San Luis Potosí");

        javax.swing.GroupLayout VistaLayout = new javax.swing.GroupLayout(Vista);
        Vista.setLayout(VistaLayout);
        VistaLayout.setHorizontalGroup(
            VistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VistaLayout.createSequentialGroup()
                .addGroup(VistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VistaLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLTitulo1))
                    .addGroup(VistaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLTitulo2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VistaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(VistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VistaLayout.createSequentialGroup()
                        .addComponent(jLNotaria)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VistaLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(60, 60, 60))))
        );
        VistaLayout.setVerticalGroup(
            VistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VistaLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLTitulo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLTitulo2)
                .addGap(68, 68, 68)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLNotaria)
                .addContainerGap())
        );

        Principal.setBackground(new java.awt.Color(255, 255, 255));

        jAgregarArchivo.setModel(new javax.swing.table.DefaultTableModel(
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
        jAgregarArchivo.setSelectionBackground(new java.awt.Color(41, 168, 73));
        jScrollPane1.setViewportView(jAgregarArchivo);

        jLTitulo.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        jLTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/files.png"))); // NOI18N
        jLTitulo.setText("  No. de expediente ");

        jCancelar.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jCancelar.setText("Cancelar");
        jCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCancelarActionPerformed(evt);
            }
        });

        jIndexar.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        jIndexar.setForeground(new java.awt.Color(0, 153, 51));
        jIndexar.setText("Indexar");
        jIndexar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIndexarActionPerformed(evt);
            }
        });

        jLIndicaciones.setText("Selecciona el archivo a indexar. (Formato PDF)");

        jBAgregar.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        jBAgregar.setText("Seleccionar");
        jBAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SeleccionLayout = new javax.swing.GroupLayout(Seleccion);
        Seleccion.setLayout(SeleccionLayout);
        SeleccionLayout.setHorizontalGroup(
            SeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SeleccionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLIndicaciones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 205, Short.MAX_VALUE)
                .addComponent(jBAgregar)
                .addContainerGap())
        );
        SeleccionLayout.setVerticalGroup(
            SeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SeleccionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(SeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLIndicaciones)
                    .addComponent(jBAgregar))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout PrincipalLayout = new javax.swing.GroupLayout(Principal);
        Principal.setLayout(PrincipalLayout);
        PrincipalLayout.setHorizontalGroup(
            PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addComponent(jCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jIndexar))
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addComponent(jLTitulo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addComponent(Seleccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PrincipalLayout.setVerticalGroup(
            PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Seleccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCancelar)
                    .addComponent(jIndexar))
                .addContainerGap())
        );

        javax.swing.GroupLayout ContenedorLayout = new javax.swing.GroupLayout(Contenedor);
        Contenedor.setLayout(ContenedorLayout);
        ContenedorLayout.setHorizontalGroup(
            ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContenedorLayout.createSequentialGroup()
                .addComponent(Vista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ContenedorLayout.setVerticalGroup(
            ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Vista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarActionPerformed
        SeleccionarArchivos();
    }//GEN-LAST:event_jBAgregarActionPerformed

    private void jIndexarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIndexarActionPerformed
        try {
            //Variable que necesito enviar es ruta
            CopiarLocalArchivo.crearCarpetaTemporalEnvio(ruta);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(AgregarNuevoArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jIndexarActionPerformed

    public static void AgregarABD(boolean realizado)
    {
        archivo = new File(ruta);
        nombre = archivo.getName();
        if(realizado == true)
        {
            Calendar cal = Calendar.getInstance();
            ConexionMySql mysql = new ConexionMySql();
            Connection cn = mysql.getConection();
            String sSQL= "INSERT INTO documentos(IDNoExpediente , NombreArchivo, FechaCreacion, Ruta) VALUES (?, ?, ?, ?)";        
            try
            {
                PreparedStatement pst = cn.prepareStatement(sSQL);
                pst.setInt(1, AgregarArchivoExp.getNoExpediente());
                pst.setString(2, nombre);
                pst.setDate(3, new java.sql.Date(cal.getTimeInMillis()));
                pst.setString(4, ValoresInicialesPrograma.getCarpetaArchivosServidor() + AgregarArchivoExp.getNoExpediente() + "//" + nombre);                                   
                int validacion = pst.executeUpdate();                                    
                if (validacion>0) 
                {
                    System.out.println("¡Agregado correctamente!");
                    new java.util.Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        AgregarArchivoAlServidor.ELiminarArchivosServer(String.valueOf(AgregarArchivoExp.getNoExpediente()));
                    }
                    },1000*3);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Hubo un error al agregar el nuevo archivos a la bd.");
                }                                   
            } catch (HeadlessException | SQLException e) 
            {
                JOptionPane.showMessageDialog(null, "ERROR! " + e);
            }
            finally
            {
                mysql.desconectar();
            }
        }
    }
    private void jCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_jCancelarActionPerformed


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgregarNuevoArchivo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Contenedor;
    private javax.swing.JPanel Principal;
    private javax.swing.JPanel Seleccion;
    private javax.swing.JPanel Vista;
    private javax.swing.JTable jAgregarArchivo;
    private javax.swing.JButton jBAgregar;
    private javax.swing.JButton jCancelar;
    private javax.swing.JButton jIndexar;
    private javax.swing.JLabel jLIndicaciones;
    private javax.swing.JLabel jLNotaria;
    private javax.swing.JLabel jLTitulo;
    private javax.swing.JLabel jLTitulo1;
    private javax.swing.JLabel jLTitulo2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
