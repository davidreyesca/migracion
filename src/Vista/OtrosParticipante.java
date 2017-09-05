package Vista;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class OtrosParticipante extends javax.swing.JPanel {

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
        SLetras(jTNombreParticipanteFisica);
        SLetras(jTApPaParticipante);
        SLetras(jTApMaParticipante);
        CambiarAMayusculas(jTNombreParticipanteFisica);
        CambiarAMayusculas(jTApPaParticipante);
        CambiarAMayusculas(jTApMaParticipante);

        NoCaracteres(jTNombreParticipanteFisica, 70);
        NoCaracteres(jTApPaParticipante, 30);
        NoCaracteres(jTApMaParticipante, 30);
        NoCaracteres(jTNombreParticipanteMoral, 70);
    }
    public OtrosParticipante(int index) {
        initComponents();
        this.jRBFisicaParticipante.setActionCommand("indicefParticipante" + index);
        this.jRBMoralParticipante.setActionCommand("indicemParticipante" + index);
        ValidacionLetras();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoPersonaParticipante = new javax.swing.ButtonGroup();
        jRBFisicaParticipante = new javax.swing.JRadioButton();
        jRBMoralParticipante = new javax.swing.JRadioButton();
        jLNombreFisicaParticipante = new javax.swing.JLabel();
        jLNombreMoralParticipante = new javax.swing.JLabel();
        jTNombreParticipanteMoral = new javax.swing.JTextField();
        jTNombreParticipanteFisica = new javax.swing.JTextField();
        jLApMaParticipante = new javax.swing.JLabel();
        jTApPaParticipante = new javax.swing.JTextField();
        jLApPaParticipante = new javax.swing.JLabel();
        jTApMaParticipante = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setAlignmentX(0.0F);
        setAlignmentY(0.0F);

        tipoPersonaParticipante.add(jRBFisicaParticipante);
        jRBFisicaParticipante.setText("Persona Física");
        jRBFisicaParticipante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBFisicaParticipanteActionPerformed(evt);
            }
        });

        tipoPersonaParticipante.add(jRBMoralParticipante);
        jRBMoralParticipante.setText("Persona Moral");
        jRBMoralParticipante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBMoralParticipanteActionPerformed(evt);
            }
        });

        jLNombreFisicaParticipante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreFisicaParticipante.setText("*Nombre: ");

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

        jLApMaParticipante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApMaParticipante.setText("*Apellido Materno:");

        jTApPaParticipante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApPaParticipante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApPaParticipanteActionPerformed(evt);
            }
        });

        jLApPaParticipante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApPaParticipante.setText("*Apellido Paterno:");

        jTApMaParticipante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApMaParticipante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApMaParticipanteActionPerformed(evt);
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
                        .addComponent(jRBFisicaParticipante)
                        .addGap(18, 18, 18)
                        .addComponent(jRBMoralParticipante))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLNombreMoralParticipante)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTNombreParticipanteMoral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLNombreFisicaParticipante)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTNombreParticipanteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLApPaParticipante)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTApPaParticipante, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLApMaParticipante)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTApMaParticipante, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRBMoralParticipante)
                    .addComponent(jRBFisicaParticipante))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTNombreParticipanteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLApPaParticipante)
                    .addComponent(jTApPaParticipante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLApMaParticipante)
                    .addComponent(jTApMaParticipante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNombreFisicaParticipante))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTNombreParticipanteMoral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNombreMoralParticipante)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTNombreParticipanteMoralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreParticipanteMoralActionPerformed
        jTNombreParticipanteMoral.transferFocus();
    }//GEN-LAST:event_jTNombreParticipanteMoralActionPerformed

    private void jRBMoralParticipanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBMoralParticipanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBMoralParticipanteActionPerformed

    private void jRBFisicaParticipanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBFisicaParticipanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBFisicaParticipanteActionPerformed

    private void jTNombreParticipanteFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreParticipanteFisicaActionPerformed
        jTNombreParticipanteFisica.transferFocus();
    }//GEN-LAST:event_jTNombreParticipanteFisicaActionPerformed

    private void jTApPaParticipanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApPaParticipanteActionPerformed
        jTApPaParticipante.transferFocus();
    }//GEN-LAST:event_jTApPaParticipanteActionPerformed

    private void jTApMaParticipanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApMaParticipanteActionPerformed
        jTApMaParticipante.transferFocus();
    }//GEN-LAST:event_jTApMaParticipanteActionPerformed

//Variables de los lementos añadidos dentro del jform
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel jLApMaParticipante;
    public javax.swing.JLabel jLApPaParticipante;
    public javax.swing.JLabel jLNombreFisicaParticipante;
    public javax.swing.JLabel jLNombreMoralParticipante;
    public javax.swing.JRadioButton jRBFisicaParticipante;
    public javax.swing.JRadioButton jRBMoralParticipante;
    public javax.swing.JTextField jTApMaParticipante;
    public javax.swing.JTextField jTApPaParticipante;
    public javax.swing.JTextField jTNombreParticipanteFisica;
    public javax.swing.JTextField jTNombreParticipanteMoral;
    public javax.swing.ButtonGroup tipoPersonaParticipante;
    // End of variables declaration//GEN-END:variables
}
