
package Vista;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class CompraVentaVendedor extends javax.swing.JPanel {
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
    private void ValidacionLetras()
    {
        SLetras(jTNombreVendedorFisica);
        SLetras(jTApPaVendedor);
        SLetras(jTApMaVendedor);
        CambiarAMayusculas(jTNombreVendedorFisica);
        CambiarAMayusculas(jTApPaVendedor);
        CambiarAMayusculas(jTApMaVendedor);

        NoCaracteres(jTNombreVendedorFisica, 70);
        NoCaracteres(jTApPaVendedor, 30);
        NoCaracteres(jTApMaVendedor, 30);
        NoCaracteres(jTNombreVendedorMoral, 70);
    }
    public CompraVentaVendedor(int index) {
        initComponents();
        this.jRBFisicaVendedor.setActionCommand("indicefVendedor" + index);
        this.jRBMoralVendedor.setActionCommand("indicemVendedor" + index);
        ValidacionLetras();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoPersonaVendedor = new javax.swing.ButtonGroup();
        jTApPaVendedor = new javax.swing.JTextField();
        jLApPaVendedor = new javax.swing.JLabel();
        jTApMaVendedor = new javax.swing.JTextField();
        jRBFisicaVendedor = new javax.swing.JRadioButton();
        jRBMoralVendedor = new javax.swing.JRadioButton();
        jLNombreFisicaVendedor = new javax.swing.JLabel();
        jLNombreMoralVendedor = new javax.swing.JLabel();
        jTNombreVendedorMoral = new javax.swing.JTextField();
        jTNombreVendedorFisica = new javax.swing.JTextField();
        jLApMaVendedor = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setPreferredSize(new java.awt.Dimension(891, 121));

        jTApPaVendedor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApPaVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApPaVendedorActionPerformed(evt);
            }
        });

        jLApPaVendedor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApPaVendedor.setText("*Apellido Paterno:");

        jTApMaVendedor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApMaVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApMaVendedorActionPerformed(evt);
            }
        });

        tipoPersonaVendedor.add(jRBFisicaVendedor);
        jRBFisicaVendedor.setText("Persona Física");
        jRBFisicaVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBFisicaVendedorActionPerformed(evt);
            }
        });

        tipoPersonaVendedor.add(jRBMoralVendedor);
        jRBMoralVendedor.setText("Persona Moral");
        jRBMoralVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBMoralVendedorActionPerformed(evt);
            }
        });

        jLNombreFisicaVendedor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreFisicaVendedor.setText("*Nombre: ");

        jLNombreMoralVendedor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreMoralVendedor.setText("*Nombre: ");

        jTNombreVendedorMoral.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreVendedorMoral.setPreferredSize(new java.awt.Dimension(250, 26));
        jTNombreVendedorMoral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreVendedorMoralActionPerformed(evt);
            }
        });

        jTNombreVendedorFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreVendedorFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreVendedorFisicaActionPerformed(evt);
            }
        });

        jLApMaVendedor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApMaVendedor.setText("*Apellido Materno:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(310, 310, 310)
                .addComponent(jRBFisicaVendedor)
                .addGap(18, 18, 18)
                .addComponent(jRBMoralVendedor))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLNombreFisicaVendedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTNombreVendedorFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLApPaVendedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTApPaVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLNombreMoralVendedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTNombreVendedorMoral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLApMaVendedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTApMaVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRBMoralVendedor)
                    .addComponent(jRBFisicaVendedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTNombreVendedorFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLApPaVendedor)
                    .addComponent(jTApPaVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLApMaVendedor)
                    .addComponent(jTApMaVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNombreFisicaVendedor))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTNombreVendedorMoral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNombreMoralVendedor))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jRBFisicaVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBFisicaVendedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBFisicaVendedorActionPerformed

    private void jRBMoralVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBMoralVendedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBMoralVendedorActionPerformed

    private void jTNombreVendedorMoralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreVendedorMoralActionPerformed
        jTNombreVendedorMoral.transferFocus();
    }//GEN-LAST:event_jTNombreVendedorMoralActionPerformed

    private void jTNombreVendedorFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreVendedorFisicaActionPerformed
        jTNombreVendedorFisica.transferFocus();
    }//GEN-LAST:event_jTNombreVendedorFisicaActionPerformed

    private void jTApPaVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApPaVendedorActionPerformed
        jTApPaVendedor.transferFocus();
    }//GEN-LAST:event_jTApPaVendedorActionPerformed

    private void jTApMaVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApMaVendedorActionPerformed
        jTApMaVendedor.transferFocus();
    }//GEN-LAST:event_jTApMaVendedorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel jLApMaVendedor;
    public javax.swing.JLabel jLApPaVendedor;
    public javax.swing.JLabel jLNombreFisicaVendedor;
    public javax.swing.JLabel jLNombreMoralVendedor;
    public javax.swing.JRadioButton jRBFisicaVendedor;
    public javax.swing.JRadioButton jRBMoralVendedor;
    public javax.swing.JTextField jTApMaVendedor;
    public javax.swing.JTextField jTApPaVendedor;
    public javax.swing.JTextField jTNombreVendedorFisica;
    public javax.swing.JTextField jTNombreVendedorMoral;
    private javax.swing.ButtonGroup tipoPersonaVendedor;
    // End of variables declaration//GEN-END:variables
}
