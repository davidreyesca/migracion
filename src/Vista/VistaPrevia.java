package Vista;
import Controlador.*;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import javax.swing.*;
import com.sun.pdfview.*;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class VistaPrevia extends javax.swing.JPanel {

    
    DefaultTableModel modelo;
    String URL;
    JFileChooser selector;
    PDFFile pdffile;
    int indicepagPDF;
    int NoExpediente= BDdocumentos.getNoExpediente();

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

     
    public VistaPrevia() {
        initComponents();
        propiedadesTabla();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPContenido = new javax.swing.JPanel();
        jPPDFOpciones = new javax.swing.JPanel();
        jBSiguiente = new javax.swing.JButton();
        jBAnterior = new javax.swing.JButton();
        jBAbrirArchivo = new javax.swing.JButton();
        panelpdf = new com.sun.pdfview.PagePanel();
        jPDirectorio = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTDirecciones = new javax.swing.JTable();
        jLIcono = new javax.swing.JLabel();
        jLNombreCarpeta = new javax.swing.JLabel();
        jLTitulo = new javax.swing.JLabel();
        jBVerPDF = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jBSiguiente.setText("Siguiente");
        jBSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSiguienteActionPerformed(evt);
            }
        });

        jBAnterior.setText("Anterior");
        jBAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAnteriorActionPerformed(evt);
            }
        });

        jBAbrirArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/abrirpdfmin.png"))); // NOI18N
        jBAbrirArchivo.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/abrirpdfminapretado.png"))); // NOI18N
        jBAbrirArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAbrirArchivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPPDFOpcionesLayout = new javax.swing.GroupLayout(jPPDFOpciones);
        jPPDFOpciones.setLayout(jPPDFOpcionesLayout);
        jPPDFOpcionesLayout.setHorizontalGroup(
            jPPDFOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPPDFOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBAbrirArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 232, Short.MAX_VALUE)
                .addComponent(jBAnterior)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBSiguiente)
                .addGap(234, 234, 234))
        );
        jPPDFOpcionesLayout.setVerticalGroup(
            jPPDFOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPPDFOpcionesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPPDFOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBAnterior)
                    .addComponent(jBSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPPDFOpcionesLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jBAbrirArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelpdfLayout = new javax.swing.GroupLayout(panelpdf);
        panelpdf.setLayout(panelpdfLayout);
        panelpdfLayout.setHorizontalGroup(
            panelpdfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelpdfLayout.setVerticalGroup(
            panelpdfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPDirectorio.setBackground(new java.awt.Color(255, 255, 255));
        jPDirectorio.setPreferredSize(new java.awt.Dimension(200, 790));

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
        jTDirecciones.setShowVerticalLines(false);
        jTDirecciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTDireccionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTDirecciones);

        jLIcono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/files.png"))); // NOI18N

        jLNombreCarpeta.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLNombreCarpeta.setText("24-24");

        jLTitulo.setText("Archivos del Exp:");

        jBVerPDF.setText("Ver archivos");
        jBVerPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBVerPDFActionPerformed(evt);
            }
        });

        jButton1.setText("Eliminar un archivo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Agregar un archivo");

        javax.swing.GroupLayout jPDirectorioLayout = new javax.swing.GroupLayout(jPDirectorio);
        jPDirectorio.setLayout(jPDirectorioLayout);
        jPDirectorioLayout.setHorizontalGroup(
            jPDirectorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPDirectorioLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPDirectorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLTitulo)
                    .addGroup(jPDirectorioLayout.createSequentialGroup()
                        .addComponent(jLIcono, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLNombreCarpeta)))
                .addGap(50, 50, 50))
            .addGroup(jPDirectorioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPDirectorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBVerPDF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPDirectorioLayout.setVerticalGroup(
            jPDirectorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPDirectorioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLTitulo)
                .addGroup(jPDirectorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPDirectorioLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLNombreCarpeta))
                    .addGroup(jPDirectorioLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLIcono, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBVerPDF)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPContenidoLayout = new javax.swing.GroupLayout(jPContenido);
        jPContenido.setLayout(jPContenidoLayout);
        jPContenidoLayout.setHorizontalGroup(
            jPContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPContenidoLayout.createSequentialGroup()
                .addComponent(jPDirectorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPPDFOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelpdf, javax.swing.GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE)))
        );
        jPContenidoLayout.setVerticalGroup(
            jPContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPDirectorio, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE)
            .addGroup(jPContenidoLayout.createSequentialGroup()
                .addComponent(panelpdf, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPPDFOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPContenido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    public void Consulta()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        String sSQL="SELECT * FROM documentos WHERE IDNoExpediente=" + "'" + NoExpediente +"'";
        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {                
                String NombreArchivo = rs.getString(2);
                String Ruta = rs.getString(4);
                System.out.println("Ruta: " + Ruta);
                System.out.println("Nombre: " + NombreArchivo);
                CopiarLocal(NombreArchivo, Ruta);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error al contectar la base de datos" + ex);
        }finally
        //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
        {
            mysql.desconectar();
        }
    }
    
    public void CopiarLocal(String Nombre, String Ruta)
    {
        File carpeta = new File(ValoresInicialesPrograma.getCarpetaArchivosLocalLectura() + "\\" + BDdocumentos.getNoExpediente());
        File archivo;
        if(carpeta.exists())
        {
            try
                {
		// Url con la foto
		URL url = new URL(Ruta);
		// establecemos conexion
		URLConnection urlCon = url.openConnection();
		// Sacamos por pantalla el tipo de fichero
		System.out.println(urlCon.getContentType());

			// Se obtiene el inputStream de la foto web y se abre el fichero
			// local.
			InputStream is = urlCon.getInputStream();
			FileOutputStream fos = new FileOutputStream(ValoresInicialesPrograma.getCarpetaArchivosLocalLectura() + "\\"+ BDdocumentos.getNoExpediente()+"\\" + Nombre);
			// Lectura de la foto de la web y escritura en fichero local
                        archivo = new File(ValoresInicialesPrograma.getCarpetaArchivosLocalLectura() + "\\"+ BDdocumentos.getNoExpediente()+"\\" + Nombre);
//                        if(!archivo.exists())
//                        {
                            byte[] array = new byte[1000]; // buffer temporal de lectura.
                            int leido = is.read(array);
                            while (leido > 0) {
                                    fos.write(array, 0, leido);
                                    leido = is.read(array);
                            }
//                        }
			// cierre de conexion y fichero.
			is.close();
			fos.close();
		} catch (Exception e) 
                {
			e.printStackTrace();
		}
        }
        else
        {
            carpeta.mkdirs();
            System.out.println("Carpeta creada");
            CopiarLocal(Nombre, Ruta);
        }
    }
     
    public void VerAchivosGuardados()
    {
                //====================================================================
        // Aquí la carpeta que queremos explorar
        LimpiarTabla();
        Consulta();
        String path = ValoresInicialesPrograma.getCarpetaArchivosLocalLectura() + "\\" + BDdocumentos.getNoExpediente();

        String nombre;
        String ruta;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++)
        {
            if (listOfFiles[i].isFile())
            {
                nombre = listOfFiles[i].getName();
                ruta = listOfFiles[i].getPath();
                System.out.println("Nombre: "+nombre);
                System.out.println("Ruta: "+ruta);
//              imagen = new JLabel(new ImageIcon(getClass().getResource("icon-pdf.png")));
                if (nombre.endsWith(".pdf") || nombre.endsWith(".PDF"))
                {
                    Object datos[]={new JLabel(new ImageIcon(getClass().getResource("Imagenes/icon-pdf.png"))), nombre, ruta};
                    modelo.addRow(datos);
                }
            }
        }

        //====================================================================

    }
    private void jBVerPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBVerPDFActionPerformed
        VerAchivosGuardados();
    }//GEN-LAST:event_jBVerPDFActionPerformed

    private void jTDireccionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTDireccionesMouseClicked
        int seleccion = jTDirecciones.getSelectedRow();
        panelpdf.removeAll();
        URL = String.valueOf(jTDirecciones.getValueAt(seleccion, 2));
        System.out.println("La url a enviar es: " + URL);
        indicepagPDF=0;
        try
        {    
             File file = new File(URL);
             RandomAccessFile raf = new RandomAccessFile(file, "r");
             FileChannel channel = raf.getChannel();
             ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY,0, channel.size());
             pdffile = new PDFFile(buf);
             PDFPage page = pdffile.getPage(indicepagPDF);
             panelpdf.showPage(page);
             repaint();
        }catch(IOException ioe){
         JOptionPane.showMessageDialog(null, "Error al abrir el archivo" + URL);
        }
    }//GEN-LAST:event_jTDireccionesMouseClicked

    private void jBSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSiguienteActionPerformed
        indicepagPDF++;
        PDFPage page = pdffile.getPage(indicepagPDF);
        panelpdf.showPage(page);
    }//GEN-LAST:event_jBSiguienteActionPerformed

    private void jBAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAnteriorActionPerformed
        indicepagPDF--;
        PDFPage page = pdffile.getPage(indicepagPDF);
        panelpdf.showPage(page);
    }//GEN-LAST:event_jBAnteriorActionPerformed

    private void jBAbrirArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAbrirArchivoActionPerformed
        try {
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+ URL);
        System.out.println("Final");
        } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }
    }//GEN-LAST:event_jBAbrirArchivoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        EliminarArchivoExp a = new EliminarArchivoExp();
        a.AbrirEliminadorArchivos(NoExpediente);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jBAbrirArchivo;
    public javax.swing.JButton jBAnterior;
    public javax.swing.JButton jBSiguiente;
    public javax.swing.JButton jBVerPDF;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLIcono;
    public static javax.swing.JLabel jLNombreCarpeta;
    private javax.swing.JLabel jLTitulo;
    private javax.swing.JPanel jPContenido;
    private javax.swing.JPanel jPDirectorio;
    private javax.swing.JPanel jPPDFOpciones;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTDirecciones;
    public com.sun.pdfview.PagePanel panelpdf;
    // End of variables declaration//GEN-END:variables
}
