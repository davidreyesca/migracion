package Vista;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author David Reyes
 */
public class DonacionDonante extends javax.swing.JPanel {

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
        SLetras(jTNombreDonanteFisica);
        SLetras(jTApPaDonante);
        SLetras(jTApMaDonante);
        CambiarAMayusculas(jTNombreDonanteFisica);
        CambiarAMayusculas(jTApPaDonante);
        CambiarAMayusculas(jTApMaDonante);

        NoCaracteres(jTNombreDonanteFisica, 70);
        NoCaracteres(jTApPaDonante, 30);
        NoCaracteres(jTApMaDonante, 30);
        NoCaracteres(jTNombreDonanteMoral, 70);
    }
    public DonacionDonante(int index) {
        initComponents();
        this.jRBFisicaDonante.setActionCommand("indicefDonante" + index);
        this.jRBMoralDonante.setActionCommand("indicemDonante" + index);
        ValidacionLetras();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoPersonaDonante = new javax.swing.ButtonGroup();
        jTApPaDonante = new javax.swing.JTextField();
        jLApPaDonante = new javax.swing.JLabel();
        jTApMaDonante = new javax.swing.JTextField();
        jRBFisicaDonante = new javax.swing.JRadioButton();
        jRBMoralDonante = new javax.swing.JRadioButton();
        jLNombreFisicaDonante = new javax.swing.JLabel();
        jLNombreMoralDonante = new javax.swing.JLabel();
        jTNombreDonanteMoral = new javax.swing.JTextField();
        jTNombreDonanteFisica = new javax.swing.JTextField();
        jLApMaDonante = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jTApPaDonante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApPaDonante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApPaDonanteActionPerformed(evt);
            }
        });

        jLApPaDonante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApPaDonante.setText("*Apellido Paterno:");

        jTApMaDonante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApMaDonante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApMaDonanteActionPerformed(evt);
            }
        });

        tipoPersonaDonante.add(jRBFisicaDonante);
        jRBFisicaDonante.setText("Persona Física");
        jRBFisicaDonante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBFisicaDonanteActionPerformed(evt);
            }
        });

        tipoPersonaDonante.add(jRBMoralDonante);
        jRBMoralDonante.setText("Persona Moral");
        jRBMoralDonante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBMoralDonanteActionPerformed(evt);
            }
        });

        jLNombreFisicaDonante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreFisicaDonante.setText("*Nombre: ");

        jLNombreMoralDonante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreMoralDonante.setText("*Nombre: ");

        jTNombreDonanteMoral.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreDonanteMoral.setPreferredSize(new java.awt.Dimension(250, 26));
        jTNombreDonanteMoral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreDonanteMoralActionPerformed(evt);
            }
        });

        jTNombreDonanteFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreDonanteFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreDonanteFisicaActionPerformed(evt);
            }
        });

        jLApMaDonante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApMaDonante.setText("*Apellido Materno:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(304, 304, 304)
                        .addComponent(jRBFisicaDonante)
                        .addGap(18, 18, 18)
                        .addComponent(jRBMoralDonante))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLNombreFisicaDonante)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTNombreDonanteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLApPaDonante)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTApPaDonante, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLNombreMoralDonante)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTNombreDonanteMoral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(85, 85, 85)))
                        .addComponent(jLApMaDonante)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTApMaDonante, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRBMoralDonante)
                    .addComponent(jRBFisicaDonante))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTNombreDonanteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLApPaDonante)
                    .addComponent(jTApPaDonante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLApMaDonante)
                    .addComponent(jTApMaDonante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNombreFisicaDonante))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTNombreDonanteMoral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNombreMoralDonante)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTApPaDonanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApPaDonanteActionPerformed
        jTApPaDonante.transferFocus();
    }//GEN-LAST:event_jTApPaDonanteActionPerformed

    private void jTApMaDonanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApMaDonanteActionPerformed
        jTApMaDonante.transferFocus();
    }//GEN-LAST:event_jTApMaDonanteActionPerformed

    private void jRBFisicaDonanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBFisicaDonanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBFisicaDonanteActionPerformed

    private void jRBMoralDonanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBMoralDonanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBMoralDonanteActionPerformed

    private void jTNombreDonanteMoralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreDonanteMoralActionPerformed
        jTNombreDonanteMoral.transferFocus();
    }//GEN-LAST:event_jTNombreDonanteMoralActionPerformed

    private void jTNombreDonanteFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreDonanteFisicaActionPerformed
        jTNombreDonanteFisica.transferFocus();
    }//GEN-LAST:event_jTNombreDonanteFisicaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel jLApMaDonante;
    public javax.swing.JLabel jLApPaDonante;
    public javax.swing.JLabel jLNombreFisicaDonante;
    public javax.swing.JLabel jLNombreMoralDonante;
    public javax.swing.JRadioButton jRBFisicaDonante;
    public javax.swing.JRadioButton jRBMoralDonante;
    public javax.swing.JTextField jTApMaDonante;
    public javax.swing.JTextField jTApPaDonante;
    public javax.swing.JTextField jTNombreDonanteFisica;
    public javax.swing.JTextField jTNombreDonanteMoral;
    private javax.swing.ButtonGroup tipoPersonaDonante;
    // End of variables declaration//GEN-END:variables
}
