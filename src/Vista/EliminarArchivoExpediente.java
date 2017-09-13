package Vista;

import Controlador.ConexionMySql;
import Controlador.EliminarArchivoExp;
import Controlador.ImgTabla;
import Controlador.ValoresInicialesPrograma;
import java.awt.HeadlessException;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pc
 */
public class EliminarArchivoExpediente extends javax.swing.JFrame {
    
    DefaultTableModel modelo;
    int NoArchivosEncontrados=0;

    public EliminarArchivoExpediente() 
    {
        initComponents();
        propiedadesTabla();
        ConsultaArchivos();
        jLVistaExpediente.setText("No. total de archivos: " + NoArchivosEncontrados);
        jLTitulo.setText(" Archivos actuales del expediente " + EliminarArchivoExp.getNoExpediente());
        setIconImage(new ImageIcon(getClass().getResource("Imagenes/IconoNotaria.png")).getImage());
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void propiedadesTabla()
    {
        String cabecera[]= {"Tipo", "Nombre archivo","Ruta"};
        String datos[][]={};
        jTDirecciones.setDefaultRenderer(Object.class,new ImgTabla());
        modelo= new DefaultTableModel(datos, cabecera)
        {
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
        };
        jTDirecciones.setModel(modelo);
        jTDirecciones.setRowHeight(30);
        jTDirecciones.getColumnModel().getColumn(0).setMaxWidth(50);
        jTDirecciones.getColumnModel().getColumn(0).setMaxWidth(50);
        jTDirecciones.getColumnModel().getColumn(2).setMaxWidth(0);
        jTDirecciones.getColumnModel().getColumn(2).setMinWidth(0);
        jTDirecciones.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
        jTDirecciones.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
   }
    
    private void LimpiarTabla()
    {
       for (int i = 0; i < jTDirecciones.getRowCount(); i++) {
           modelo.removeRow(i);
           i-=1;
       }
    }
    
    public void ConsultaArchivos()
    {
        NoArchivosEncontrados=0;
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        String sSQL="SELECT * FROM documentos WHERE IDNoExpediente=" + "'" + EliminarArchivoExp.getNoExpediente() +"'";
        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {     
                String NombreArchivo = rs.getString(2);
                String Ruta = rs.getString(4);
                Object datos[]={new JLabel(new ImageIcon(getClass().getResource("Imagenes/icon-pdf.png"))), NombreArchivo, Ruta};
                NoArchivosEncontrados++;
                modelo.addRow(datos);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error al contectar la base de datos" + ex);
        }finally
        //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
        {
            mysql.desconectar();
        }
        jLVistaExpediente.setText("No. total de archivos: " + NoArchivosEncontrados);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Contenedor = new javax.swing.JPanel();
        Vista = new javax.swing.JPanel();
        Icono = new javax.swing.JLabel();
        jLTitulo1 = new javax.swing.JLabel();
        jLVistaExpediente = new javax.swing.JLabel();
        jLNotaria = new javax.swing.JLabel();
        Principal = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTDirecciones = new javax.swing.JTable();
        jLTitulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Eliminar archivos del expediente");

        Contenedor.setBackground(new java.awt.Color(255, 255, 255));

        Vista.setBackground(new java.awt.Color(244, 67, 54));

        Icono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/EliminarExpediente.png"))); // NOI18N

        jLTitulo1.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLTitulo1.setForeground(new java.awt.Color(255, 255, 255));
        jLTitulo1.setText("Eliminar archivos");

        jLVistaExpediente.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLVistaExpediente.setForeground(new java.awt.Color(255, 255, 255));
        jLVistaExpediente.setText("No. total de archivos: ");

        jLNotaria.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLNotaria.setForeground(new java.awt.Color(255, 255, 255));
        jLNotaria.setText("Notaría 21 San Luis Potosí");

        javax.swing.GroupLayout VistaLayout = new javax.swing.GroupLayout(Vista);
        Vista.setLayout(VistaLayout);
        VistaLayout.setHorizontalGroup(
            VistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VistaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Icono)
                .addGap(58, 58, 58))
            .addGroup(VistaLayout.createSequentialGroup()
                .addGroup(VistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VistaLayout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(jLNotaria))
                    .addGroup(VistaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLTitulo1))
                    .addGroup(VistaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLVistaExpediente)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        VistaLayout.setVerticalGroup(
            VistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VistaLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLTitulo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLVistaExpediente)
                .addGap(54, 54, 54)
                .addComponent(Icono)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLNotaria)
                .addContainerGap())
        );

        Principal.setBackground(new java.awt.Color(255, 255, 255));

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
        jTDirecciones.setSelectionBackground(new java.awt.Color(41, 168, 73));
        jTDirecciones.setShowVerticalLines(false);
        jTDirecciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTDireccionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTDirecciones);

        jLTitulo.setFont(new java.awt.Font("Leelawadee", 1, 24)); // NOI18N
        jLTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/files.png"))); // NOI18N
        jLTitulo.setText(" Archivos actuales del expediente ");

        jLabel1.setText("Selecciona el archivo que deseas eliminar del expediente.");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 0, 51));
        jButton1.setText("Eliminar archivo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PrincipalLayout = new javax.swing.GroupLayout(Principal);
        Principal.setLayout(PrincipalLayout);
        PrincipalLayout.setHorizontalGroup(
            PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrincipalLayout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLTitulo)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PrincipalLayout.setVerticalGroup(
            PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLTitulo)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
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

    private void jTDireccionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTDireccionesMouseClicked

    }//GEN-LAST:event_jTDireccionesMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jTDirecciones.getSelectedRow()==-1)
        {
            JOptionPane.showMessageDialog(null, "Necesitas seleccionar uno de los archivos para eliminarlo");
        }else if(NoArchivosEncontrados<=1)
        {
            JOptionPane.showMessageDialog(null, "Al menos debe existir un archivo en el expediente " + EliminarArchivoExp.getNoExpediente() + ", por lo cual no se puede borrar.");
        }else
        {
                int seleccion = jTDirecciones.getSelectedRow();
                String ruta = (String) jTDirecciones.getValueAt(seleccion, 2);   
                int NoExpediente = EliminarArchivoExp.getNoExpediente();
                EliminarArchivoBaseDatos(NoExpediente, ruta);
                ELiminarArchivosServer(ruta);
                NoArchivosEncontrados=0;
                LimpiarTabla();
                ConsultaArchivos();
                
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    private void EliminarArchivoBaseDatos(int NoExpediente, String Ruta)
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        String sSQL= "DELETE FROM documentos WHERE IDNoExpediente = '" + NoExpediente + "' AND Ruta = '" + Ruta + "'";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            int validacion = pst.executeUpdate();
            if (validacion>0) {
                System.out.println("Eliminado con exito!");  
                mysql.desconectar();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Hubo un error al ACTUALIZAR los datos.");
            }
        } catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! " + e);
        }
        
    }
    
    private void ELiminarArchivosServer(String ruta)
    {
                try 
                {
                    InetAddress localHost = InetAddress.getLocalHost();
                    String IPcliente = localHost.getHostAddress();
                    System.out.println("Mi ip es: " + IPcliente);
                    Socket misocket = new Socket(ValoresInicialesPrograma.getIPServidor(), 9998);
                    DataOutputStream flujo_salida = new DataOutputStream(misocket.getOutputStream());
                    //Lo que se envia como flujo
                    flujo_salida.writeUTF(IPcliente + " archivo " + ruta);
                    flujo_salida.close();
                    misocket.close();
                } catch (IOException ex)
                {
                    System.out.println("Error!" + ex.getMessage());
                    JOptionPane.showMessageDialog(null, "El servidor esta fuera de linea, por favor comunicate con el administrador");
                }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EliminarArchivoExpediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EliminarArchivoExpediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EliminarArchivoExpediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EliminarArchivoExpediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() 
            {
                new EliminarArchivoExpediente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Contenedor;
    private javax.swing.JLabel Icono;
    private javax.swing.JPanel Principal;
    private javax.swing.JPanel Vista;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLNotaria;
    private javax.swing.JLabel jLTitulo;
    private javax.swing.JLabel jLTitulo1;
    private javax.swing.JLabel jLVistaExpediente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTDirecciones;
    // End of variables declaration//GEN-END:variables

}
