package Vista;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AperturaCreditoAcreditante extends javax.swing.JPanel {

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
        SLetras(jTNombreAcreditanteFisica);
        SLetras(jTApPaAcreditante);
        SLetras(jTApMaAcreditante);
        CambiarAMayusculas(jTNombreAcreditanteFisica);
        CambiarAMayusculas(jTApPaAcreditante);
        CambiarAMayusculas(jTApMaAcreditante);

        NoCaracteres(jTNombreAcreditanteFisica, 70);
        NoCaracteres(jTApPaAcreditante, 30);
        NoCaracteres(jTApMaAcreditante, 30);
        NoCaracteres(jTNombreAcreditanteMoral, 70);
    }
    public AperturaCreditoAcreditante(int index) {
        initComponents();
        this.jRBFisicaAcreditante.setActionCommand("indicefAcreditante" + index);
        this.jRBMoralAcreditante.setActionCommand("indicemAcreditante" + index);
        ValidacionLetras();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoPersonaAcreditante = new javax.swing.ButtonGroup();
        jRBFisicaAcreditante = new javax.swing.JRadioButton();
        jRBMoralAcreditante = new javax.swing.JRadioButton();
        jLNombreFisicaAcreditante = new javax.swing.JLabel();
        jLNombreMoralAcreditante = new javax.swing.JLabel();
        jTNombreAcreditanteMoral = new javax.swing.JTextField();
        jTNombreAcreditanteFisica = new javax.swing.JTextField();
        jLApMaAcreditante = new javax.swing.JLabel();
        jTApPaAcreditante = new javax.swing.JTextField();
        jLApPaAcreditante = new javax.swing.JLabel();
        jTApMaAcreditante = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setAlignmentX(0.0F);
        setAlignmentY(0.0F);

        tipoPersonaAcreditante.add(jRBFisicaAcreditante);
        jRBFisicaAcreditante.setText("Persona Física");
        jRBFisicaAcreditante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBFisicaAcreditanteActionPerformed(evt);
            }
        });

        tipoPersonaAcreditante.add(jRBMoralAcreditante);
        jRBMoralAcreditante.setText("Persona Moral");
        jRBMoralAcreditante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBMoralAcreditanteActionPerformed(evt);
            }
        });

        jLNombreFisicaAcreditante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreFisicaAcreditante.setText("*Nombre: ");

        jLNombreMoralAcreditante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreMoralAcreditante.setText("*Nombre: ");

        jTNombreAcreditanteMoral.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreAcreditanteMoral.setPreferredSize(new java.awt.Dimension(250, 26));
        jTNombreAcreditanteMoral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreAcreditanteMoralActionPerformed(evt);
            }
        });

        jTNombreAcreditanteFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreAcreditanteFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreAcreditanteFisicaActionPerformed(evt);
            }
        });

        jLApMaAcreditante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApMaAcreditante.setText("*Apellido Materno:");

        jTApPaAcreditante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApPaAcreditante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApPaAcreditanteActionPerformed(evt);
            }
        });

        jLApPaAcreditante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApPaAcreditante.setText("*Apellido Paterno:");

        jTApMaAcreditante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApMaAcreditante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApMaAcreditanteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(299, 299, 299)
                        .addComponent(jRBFisicaAcreditante)
                        .addGap(18, 18, 18)
                        .addComponent(jRBMoralAcreditante))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLNombreMoralAcreditante)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTNombreAcreditanteMoral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLNombreFisicaAcreditante)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTNombreAcreditanteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLApPaAcreditante)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTApPaAcreditante, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLApMaAcreditante)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTApMaAcreditante, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRBMoralAcreditante)
                    .addComponent(jRBFisicaAcreditante))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTNombreAcreditanteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLApPaAcreditante)
                    .addComponent(jTApPaAcreditante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLApMaAcreditante)
                    .addComponent(jTApMaAcreditante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNombreFisicaAcreditante))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTNombreAcreditanteMoral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNombreMoralAcreditante)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTNombreAcreditanteMoralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreAcreditanteMoralActionPerformed
        jTNombreAcreditanteMoral.transferFocus();
    }//GEN-LAST:event_jTNombreAcreditanteMoralActionPerformed

    private void jRBMoralAcreditanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBMoralAcreditanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBMoralAcreditanteActionPerformed

    private void jRBFisicaAcreditanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBFisicaAcreditanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBFisicaAcreditanteActionPerformed

    private void jTNombreAcreditanteFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreAcreditanteFisicaActionPerformed
        jTNombreAcreditanteFisica.transferFocus();
    }//GEN-LAST:event_jTNombreAcreditanteFisicaActionPerformed

    private void jTApPaAcreditanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApPaAcreditanteActionPerformed
        jTApPaAcreditante.transferFocus();
    }//GEN-LAST:event_jTApPaAcreditanteActionPerformed

    private void jTApMaAcreditanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApMaAcreditanteActionPerformed
        jTApMaAcreditante.transferFocus();
    }//GEN-LAST:event_jTApMaAcreditanteActionPerformed

//Variables de los lementos añadidos dentro del jform
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel jLApMaAcreditante;
    public javax.swing.JLabel jLApPaAcreditante;
    public javax.swing.JLabel jLNombreFisicaAcreditante;
    public javax.swing.JLabel jLNombreMoralAcreditante;
    public javax.swing.JRadioButton jRBFisicaAcreditante;
    public javax.swing.JRadioButton jRBMoralAcreditante;
    public javax.swing.JTextField jTApMaAcreditante;
    public javax.swing.JTextField jTApPaAcreditante;
    public javax.swing.JTextField jTNombreAcreditanteFisica;
    public javax.swing.JTextField jTNombreAcreditanteMoral;
    public javax.swing.ButtonGroup tipoPersonaAcreditante;
    // End of variables declaration//GEN-END:variables
}
