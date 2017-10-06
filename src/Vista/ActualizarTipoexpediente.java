package Vista;

import Controlador.BDdocumentos;
import Controlador.ConexionMySql;
import static Vista.EleccionTipoExpediente.aperturaCredito;
import static Vista.EleccionTipoExpediente.cancelacionHipoteca;
import static Vista.EleccionTipoExpediente.certificaciones;
import static Vista.EleccionTipoExpediente.compraVenta;
import static Vista.EleccionTipoExpediente.donacion;
import static Vista.EleccionTipoExpediente.otros;
import static Vista.EleccionTipoExpediente.poderes;
import static Vista.EleccionTipoExpediente.testamentos;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class ActualizarTipoexpediente extends javax.swing.JFrame {

    int formularioActual;
    /**
     * Consutructor de la clase
     */
    private void cerrarVentanaActual()
    {
        switch (formularioActual) 
        {
            case 1:
                EleccionTipoExpediente.cerrarCompraVenta(1);
            break;
            case 2:
                EleccionTipoExpediente.cerrarDonacion(1);
            break;
            case 3: 
                EleccionTipoExpediente.cerrarCancelacionHipotecas(1);
            break;
            case 4: 
                EleccionTipoExpediente.cerrarAperturaCredito(1);
            break;
            case 5: 
                EleccionTipoExpediente.cerrarPoderes(1);
            break;
            case 6: 
                EleccionTipoExpediente.cerrarTestamentos(1);
            break;
            case 7: 
                EleccionTipoExpediente.cerrarCertificaciones(1);
            break;
            case 8: 
                EleccionTipoExpediente.cerrarOtros(1);
            break;
            default: System.out.println ("Sin especificar");
            break;
        }
    }
    private void ActualizarBD(int a)
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        String sSQL= "UPDATE casoscliente SET IDTipoExpediente = ? WHERE IDNoExpediente = '"+ BDdocumentos.getNoExpediente() +"'";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, a);
            int validacion = pst.executeUpdate();
            if (validacion>0) 
            {
                System.out.println("Se ha actualizado correctamente tu tipo de formulario");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Hubo un error al ACTUALIZAR la Tabla");
            }
        } catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! de SINTAXIS" + e);
        }
        finally
        {
            mysql.desconectar();
        }        
    }
    public ActualizarTipoexpediente() 
    {       
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("Imagenes/IconoNotaria.png")).getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPrincipal = new javax.swing.JPanel();
        Vista = new javax.swing.JPanel();
        jPOpciones = new javax.swing.JPanel();
        CompraVenta = new javax.swing.JButton();
        CancelacionHipoteca = new javax.swing.JButton();
        Testamentos = new javax.swing.JButton();
        Donacion = new javax.swing.JButton();
        Poderes = new javax.swing.JButton();
        Certificaciones = new javax.swing.JButton();
        Otros = new javax.swing.JButton();
        jLInstrucciones = new javax.swing.JLabel();
        jLTipoExpActual = new javax.swing.JLabel();
        AperturaCredito = new javax.swing.JButton();
        jLNoExpediente1 = new javax.swing.JLabel();
        Cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Actualizar Tipo Expediente");

        jPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        Vista.setBackground(new java.awt.Color(27, 107, 215));

        javax.swing.GroupLayout VistaLayout = new javax.swing.GroupLayout(Vista);
        Vista.setLayout(VistaLayout);
        VistaLayout.setHorizontalGroup(
            VistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 17, Short.MAX_VALUE)
        );
        VistaLayout.setVerticalGroup(
            VistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPOpciones.setBackground(new java.awt.Color(255, 255, 255));

        CompraVenta.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        CompraVenta.setForeground(new java.awt.Color(49, 58, 115));
        CompraVenta.setText("Compra-Venta");
        CompraVenta.setBorder(null);
        CompraVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CompraVentaActionPerformed(evt);
            }
        });

        CancelacionHipoteca.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        CancelacionHipoteca.setForeground(new java.awt.Color(49, 58, 115));
        CancelacionHipoteca.setText("Cancelación Hipoteca");
        CancelacionHipoteca.setBorder(null);
        CancelacionHipoteca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelacionHipotecaActionPerformed(evt);
            }
        });

        Testamentos.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        Testamentos.setForeground(new java.awt.Color(49, 58, 115));
        Testamentos.setText("Testamentos");
        Testamentos.setBorder(null);
        Testamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TestamentosActionPerformed(evt);
            }
        });

        Donacion.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        Donacion.setForeground(new java.awt.Color(49, 58, 115));
        Donacion.setText("Donación");
        Donacion.setBorder(null);
        Donacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DonacionActionPerformed(evt);
            }
        });

        Poderes.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        Poderes.setForeground(new java.awt.Color(49, 58, 115));
        Poderes.setText("Poderes");
        Poderes.setBorder(null);
        Poderes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PoderesActionPerformed(evt);
            }
        });

        Certificaciones.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        Certificaciones.setForeground(new java.awt.Color(49, 58, 115));
        Certificaciones.setText("Certificaciones");
        Certificaciones.setBorder(null);
        Certificaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CertificacionesActionPerformed(evt);
            }
        });

        Otros.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        Otros.setForeground(new java.awt.Color(49, 58, 115));
        Otros.setText("Otros");
        Otros.setBorder(null);
        Otros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OtrosActionPerformed(evt);
            }
        });

        jLInstrucciones.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLInstrucciones.setText("Quieres cambiar el No. expediente: ");

        jLTipoExpActual.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLTipoExpActual.setText("Actual:");

        AperturaCredito.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        AperturaCredito.setForeground(new java.awt.Color(49, 58, 115));
        AperturaCredito.setText("Apertura de credito");
        AperturaCredito.setBorder(null);
        AperturaCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AperturaCreditoActionPerformed(evt);
            }
        });

        jLNoExpediente1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLNoExpediente1.setText("Cambiar a:");

        Cancelar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Cancelar.setForeground(new java.awt.Color(255, 0, 51));
        Cancelar.setText("Cancelar");
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPOpcionesLayout = new javax.swing.GroupLayout(jPOpciones);
        jPOpciones.setLayout(jPOpcionesLayout);
        jPOpcionesLayout.setHorizontalGroup(
            jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Otros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CompraVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPOpcionesLayout.createSequentialGroup()
                        .addGroup(jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Poderes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CancelacionHipoteca, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                            .addComponent(AperturaCredito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Donacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Certificaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                            .addComponent(Testamentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPOpcionesLayout.createSequentialGroup()
                        .addComponent(jLTipoExpActual)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLNoExpediente1))
                    .addGroup(jPOpcionesLayout.createSequentialGroup()
                        .addComponent(jLInstrucciones)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPOpcionesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Cancelar)))
                .addContainerGap())
        );
        jPOpcionesLayout.setVerticalGroup(
            jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLInstrucciones)
                .addGap(18, 18, 18)
                .addGroup(jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLTipoExpActual)
                    .addComponent(jLNoExpediente1))
                .addGap(28, 28, 28)
                .addComponent(CompraVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CancelacionHipoteca, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Testamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Poderes, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Donacion, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Certificaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AperturaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Otros, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Cancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPrincipalLayout = new javax.swing.GroupLayout(jPrincipal);
        jPrincipal.setLayout(jPrincipalLayout);
        jPrincipalLayout.setHorizontalGroup(
            jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPrincipalLayout.createSequentialGroup()
                .addComponent(Vista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPrincipalLayout.setVerticalGroup(
            jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Vista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CompraVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CompraVentaActionPerformed
        compraVenta = new CompraVenta();
        compraVenta.setVisible(true);
        VistaPrevia.jLNombreCarpeta.setText(String.valueOf(BDdocumentos.getNoExpediente()));
        ActualizarBD(1);
        cerrarVentanaActual();
        dispose();
    }//GEN-LAST:event_CompraVentaActionPerformed
    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
        dispose();
    }//GEN-LAST:event_CancelarActionPerformed
    private void DonacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DonacionActionPerformed
        donacion = new Donacion();
        donacion.setVisible(true);
        VistaPrevia.jLNombreCarpeta.setText(String.valueOf(BDdocumentos.getNoExpediente()));
        ActualizarBD(2);
        cerrarVentanaActual();
        dispose();
    }//GEN-LAST:event_DonacionActionPerformed
    private void CancelacionHipotecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelacionHipotecaActionPerformed
        cancelacionHipoteca = new CancelacionHipoteca();
        cancelacionHipoteca.setVisible(true);
        VistaPrevia.jLNombreCarpeta.setText(String.valueOf(BDdocumentos.getNoExpediente()));
        ActualizarBD(3);
        cerrarVentanaActual();
        dispose();
    }//GEN-LAST:event_CancelacionHipotecaActionPerformed

    private void AperturaCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AperturaCreditoActionPerformed
        aperturaCredito = new AperturaCredito();
        aperturaCredito.setVisible(true);
        VistaPrevia.jLNombreCarpeta.setText(String.valueOf(BDdocumentos.getNoExpediente()));
        ActualizarBD(4);
        cerrarVentanaActual();
        dispose();
    }//GEN-LAST:event_AperturaCreditoActionPerformed
    private void PoderesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PoderesActionPerformed
        poderes = new Poderes();
        poderes.setVisible(true);
        VistaPrevia.jLNombreCarpeta.setText(String.valueOf(BDdocumentos.getNoExpediente()));
        ActualizarBD(5);
        cerrarVentanaActual();
        dispose();
    }//GEN-LAST:event_PoderesActionPerformed
    private void TestamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TestamentosActionPerformed
        testamentos= new Testamentos();
        testamentos.setVisible(true);
        VistaPrevia.jLNombreCarpeta.setText(String.valueOf(BDdocumentos.getNoExpediente()));
        ActualizarBD(6);
        cerrarVentanaActual();
        dispose();
    }//GEN-LAST:event_TestamentosActionPerformed
    private void CertificacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CertificacionesActionPerformed
        certificaciones = new Certificaciones();
        certificaciones.setVisible(true);
        VistaPrevia.jLNombreCarpeta.setText(String.valueOf(BDdocumentos.getNoExpediente()));
        ActualizarBD(7);
        cerrarVentanaActual();
        dispose();
    }//GEN-LAST:event_CertificacionesActionPerformed
    private void OtrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OtrosActionPerformed
        otros = new Otros();
        otros.setVisible(true);
        VistaPrevia.jLNombreCarpeta.setText(String.valueOf(BDdocumentos.getNoExpediente()));
        ActualizarBD(8);
        cerrarVentanaActual();
        dispose();
    }//GEN-LAST:event_OtrosActionPerformed
    public void ExpedienteActual(int a)
    {
        jLInstrucciones.setText("Quieres cambiar el No. expediente: " + BDdocumentos.getNoExpediente());
        if(a==1)
        {
            formularioActual = 1;
            CompraVenta.setVisible(false);
            jLTipoExpActual.setText("Tipo actual: Compra-Venta");
        }
        if(a==2)
        {
            formularioActual = 2;
            Donacion.setVisible(false);
            Donacion.setSize(0, 0);
            jLTipoExpActual.setText("Tipo actual: Donación");
        }
        if(a==3)
        {
            formularioActual = 3;
            CancelacionHipoteca.setVisible(false);
            CancelacionHipoteca.setSize(0, 0);
            jLTipoExpActual.setText("Tipo actual: Cancelación Hipotecas");
        }
        if(a==4)
        {
            formularioActual = 4;
            AperturaCredito.setVisible(false);
            AperturaCredito.setSize(0, 0);
            jLTipoExpActual.setText("Tipo actual: Apertura de Credito");
        }
        if(a==5)
        {
            formularioActual = 5;
            Poderes.setVisible(false);
            Poderes.setSize(0, 0);
            jLTipoExpActual.setText("Tipo actual: Poderes");
        }
        if(a==6)
        {
            formularioActual = 6;
            Testamentos.setVisible(false);
            Testamentos.setSize(0, 0);
            jLTipoExpActual.setText("Tipo actual: Testamentos");
        }
        if(a==7)
        {
            formularioActual = 7;
            Certificaciones.setVisible(false);
            Certificaciones.setSize(0, 0);
            jLTipoExpActual.setText("Tipo actual: Certificaciones");
        }
        if(a==8)
        {
            formularioActual = 8;
            otros.setVisible(false);
            otros.setSize(0, 0);
            jLTipoExpActual.setText("Tipo actual: Otros");
        }
    }
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new ActualizarTipoexpediente().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AperturaCredito;
    private javax.swing.JButton CancelacionHipoteca;
    private javax.swing.JButton Cancelar;
    private javax.swing.JButton Certificaciones;
    private javax.swing.JButton CompraVenta;
    private javax.swing.JButton Donacion;
    private javax.swing.JButton Otros;
    private javax.swing.JButton Poderes;
    private javax.swing.JButton Testamentos;
    private javax.swing.JPanel Vista;
    private javax.swing.JLabel jLInstrucciones;
    public static javax.swing.JLabel jLNoExpediente1;
    public static javax.swing.JLabel jLTipoExpActual;
    private javax.swing.JPanel jPOpciones;
    private javax.swing.JPanel jPrincipal;
    // End of variables declaration//GEN-END:variables

}
