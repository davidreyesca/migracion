package Vista;

import Controlador.BDdocumentos;
import Controlador.CrearCaso;
import Controlador.EliminarContenidoCarpetas;
import Controlador.ValoresInicialesPrograma;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class EleccionTipoExpediente extends javax.swing.JFrame {
    static CompraVenta compraVenta;
    static Donacion donacion;
    static CancelacionHipoteca cancelacionHipoteca;
    static AperturaCredito aperturaCredito;
    static Poderes poderes;
    static Testamentos testamentos;
    static Certificaciones certificaciones;
    static Otros otros;
    
    public void close()
    {
        if (JOptionPane.showConfirmDialog(rootPane, "Si sales de PERDERA TODO el PROCESO",
                "Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
        {    
                Principal.EliminarExpedienteCompleto(BDdocumentos.getNoExpediente());
                EliminarContenidoCarpetas.iniciar(ValoresInicialesPrograma.getCarpetaArchivosLocal() + "\\" + BDdocumentos.getNoExpediente());
                dispose();
        }
    }
    public EleccionTipoExpediente() 
    {
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setResizable(false);
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("Imagenes/IconoNotaria.png")).getImage());
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                close();
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPrincipal = new javax.swing.JPanel();
        Vista = new javax.swing.JPanel();
        Vista1 = new javax.swing.JPanel();
        jPOpciones = new javax.swing.JPanel();
        CompraVenta = new javax.swing.JButton();
        CancelacionHipoteca = new javax.swing.JButton();
        Testamentos = new javax.swing.JButton();
        Donacion = new javax.swing.JButton();
        Poderes = new javax.swing.JButton();
        Certificaciones = new javax.swing.JButton();
        Otros = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLNoExpediente = new javax.swing.JLabel();
        AperturaCredito = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Elección tipo de expediente");

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

        Vista1.setBackground(new java.awt.Color(40, 168, 72));

        javax.swing.GroupLayout Vista1Layout = new javax.swing.GroupLayout(Vista1);
        Vista1.setLayout(Vista1Layout);
        Vista1Layout.setHorizontalGroup(
            Vista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        Vista1Layout.setVerticalGroup(
            Vista1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("¿De que tipo de expediente son los documentos que indexaste?");

        jLNoExpediente.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLNoExpediente.setText("No. Expendiente: ");

        AperturaCredito.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        AperturaCredito.setForeground(new java.awt.Color(49, 58, 115));
        AperturaCredito.setText("Apertura de credito");
        AperturaCredito.setBorder(null);
        AperturaCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AperturaCreditoActionPerformed(evt);
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
                            .addComponent(AperturaCredito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPOpcionesLayout.createSequentialGroup()
                                .addComponent(CancelacionHipoteca, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Certificaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Donacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Testamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPOpcionesLayout.createSequentialGroup()
                        .addGroup(jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLNoExpediente)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPOpcionesLayout.setVerticalGroup(
            jPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLNoExpediente)
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
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPrincipalLayout = new javax.swing.GroupLayout(jPrincipal);
        jPrincipal.setLayout(jPrincipalLayout);
        jPrincipalLayout.setHorizontalGroup(
            jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPrincipalLayout.createSequentialGroup()
                .addGroup(jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Vista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Vista1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jPOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPrincipalLayout.setVerticalGroup(
            jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPrincipalLayout.createSequentialGroup()
                .addComponent(Vista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(Vista1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void CompraVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CompraVentaActionPerformed
        compraVenta = new CompraVenta();
        compraVenta.setVisible(true);
        VistaPrevia.jLNombreCarpeta.setText(String.valueOf(BDdocumentos.getNoExpediente()));
        BDdocumentos.ocultarOperacionEleccionTipo();
        CrearCaso.GuardarTablaCasosCliente(1);
    }//GEN-LAST:event_CompraVentaActionPerformed
    private void DonacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DonacionActionPerformed
        donacion = new Donacion();
        donacion.setVisible(true);
        VistaPrevia.jLNombreCarpeta.setText(String.valueOf(BDdocumentos.getNoExpediente()));
        BDdocumentos.ocultarOperacionEleccionTipo();
        CrearCaso.GuardarTablaCasosCliente(2);
    }//GEN-LAST:event_DonacionActionPerformed
    private void CancelacionHipotecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelacionHipotecaActionPerformed
        cancelacionHipoteca = new CancelacionHipoteca();
        cancelacionHipoteca.setVisible(true);
        VistaPrevia.jLNombreCarpeta.setText(String.valueOf(BDdocumentos.getNoExpediente()));
        BDdocumentos.ocultarOperacionEleccionTipo();
        CrearCaso.GuardarTablaCasosCliente(3);
    }//GEN-LAST:event_CancelacionHipotecaActionPerformed

    private void AperturaCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AperturaCreditoActionPerformed
        aperturaCredito = new AperturaCredito();
        aperturaCredito.setVisible(true);
        VistaPrevia.jLNombreCarpeta.setText(String.valueOf(BDdocumentos.getNoExpediente()));
        BDdocumentos.ocultarOperacionEleccionTipo();
        CrearCaso.GuardarTablaCasosCliente(4);
    }//GEN-LAST:event_AperturaCreditoActionPerformed
    private void PoderesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PoderesActionPerformed
        poderes = new Poderes();
        poderes.setVisible(true);
        VistaPrevia.jLNombreCarpeta.setText(String.valueOf(BDdocumentos.getNoExpediente()));
        BDdocumentos.ocultarOperacionEleccionTipo();
        CrearCaso.GuardarTablaCasosCliente(5);
    }//GEN-LAST:event_PoderesActionPerformed

    private void TestamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TestamentosActionPerformed
        testamentos= new Testamentos();
        testamentos.setVisible(true);
        VistaPrevia.jLNombreCarpeta.setText(String.valueOf(BDdocumentos.getNoExpediente()));
        BDdocumentos.ocultarOperacionEleccionTipo();
        CrearCaso.GuardarTablaCasosCliente(6);
    }//GEN-LAST:event_TestamentosActionPerformed

    private void CertificacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CertificacionesActionPerformed
        certificaciones = new Certificaciones();
        certificaciones.setVisible(true);
        VistaPrevia.jLNombreCarpeta.setText(String.valueOf(BDdocumentos.getNoExpediente()));
        BDdocumentos.ocultarOperacionEleccionTipo();
        CrearCaso.GuardarTablaCasosCliente(7);
    }//GEN-LAST:event_CertificacionesActionPerformed
    private void OtrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OtrosActionPerformed
        otros = new Otros();
        otros.setVisible(true);
        VistaPrevia.jLNombreCarpeta.setText(String.valueOf(BDdocumentos.getNoExpediente()));
        BDdocumentos.ocultarOperacionEleccionTipo();
        CrearCaso.GuardarTablaCasosCliente(8);
    }//GEN-LAST:event_OtrosActionPerformed
    public static void cerrarCompraVenta(int i)
    {
        if (i==1) 
        {
        compraVenta.dispose();
        BDdocumentos.FinalizacionCompraVentaCorrecta();
        }
    }
    public static void cerrarDonacion(int i)
    {
        if (i==1) 
        {
        donacion.dispose();
        BDdocumentos.FinalizacionDonacionCorrecta();
        }
    }
    public static void cerrarCancelacionHipotecas(int i)
    {
        if (i==1) 
        {
        cancelacionHipoteca.dispose();
        BDdocumentos.FinalizacionCancelacionHipotecaCorrecta();
        }
    }
    public static void cerrarAperturaCredito(int i)
    {
        if (i==1) 
        {
        aperturaCredito.dispose();
        BDdocumentos.FinalizacionAperturaCreditoCorrecta();
        }
    }
    public static void cerrarPoderes(int i)
    {
        if (i==1) 
        {
        poderes.dispose();
        BDdocumentos.FinalizacionPoderesCorrecta();
        }
    }
    public static void cerrarTestamentos(int i)
    {
        if (i==1) 
        {
        testamentos.dispose();
        BDdocumentos.FinalizacionTestamentosCorrecta();
        }
    }
    public static void cerrarCertificaciones(int i)
    {
        if (i==1) 
        {
        certificaciones.dispose();
        BDdocumentos.FinalizacionCertificacionesCorrecta();
        }
    }
        public static void cerrarOtros(int i)
    {
        if (i==1) 
        {
        otros.dispose();
        BDdocumentos.FinalizacionOtrosCorrecta();
        }
    }
    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EleccionTipoExpediente().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AperturaCredito;
    private javax.swing.JButton CancelacionHipoteca;
    private javax.swing.JButton Certificaciones;
    private javax.swing.JButton CompraVenta;
    private javax.swing.JButton Donacion;
    private javax.swing.JButton Otros;
    private javax.swing.JButton Poderes;
    private javax.swing.JButton Testamentos;
    private javax.swing.JPanel Vista;
    private javax.swing.JPanel Vista1;
    public static javax.swing.JLabel jLNoExpediente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPOpciones;
    private javax.swing.JPanel jPrincipal;
    // End of variables declaration//GEN-END:variables
}
