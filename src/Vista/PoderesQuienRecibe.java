package Vista;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PoderesQuienRecibe extends javax.swing.JPanel {

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
        SLetras(jTNombreQuienRecibeFisica);
        SLetras(jTApPaQuienRecibe);
        SLetras(jTApMaQuienRecibe);
        CambiarAMayusculas(jTNombreQuienRecibeFisica);
        CambiarAMayusculas(jTApPaQuienRecibe);
        CambiarAMayusculas(jTApMaQuienRecibe);

        NoCaracteres(jTNombreQuienRecibeFisica, 70);
        NoCaracteres(jTApPaQuienRecibe, 30);
        NoCaracteres(jTApMaQuienRecibe, 30);
        NoCaracteres(jTNombreQuienRecibeMoral, 70);
    }
    public PoderesQuienRecibe(int index) {
        initComponents();
        this.jRBFisicaQuienRecibe.setActionCommand("indicefQuienRecibe" + index);
        this.jRBMoralQuienRecibe.setActionCommand("indicemQuienRecibe" + index);
        ValidacionLetras();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoPersonaQuienRecibe = new javax.swing.ButtonGroup();
        jRBFisicaQuienRecibe = new javax.swing.JRadioButton();
        jRBMoralQuienRecibe = new javax.swing.JRadioButton();
        jLNombreFisicaQuienRecibe = new javax.swing.JLabel();
        jLNombreMoralQuienRecibe = new javax.swing.JLabel();
        jTNombreQuienRecibeMoral = new javax.swing.JTextField();
        jTNombreQuienRecibeFisica = new javax.swing.JTextField();
        jLApMaQuienRecibe = new javax.swing.JLabel();
        jTApPaQuienRecibe = new javax.swing.JTextField();
        jLApPaQuienRecibe = new javax.swing.JLabel();
        jTApMaQuienRecibe = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setAlignmentX(0.0F);
        setAlignmentY(0.0F);

        tipoPersonaQuienRecibe.add(jRBFisicaQuienRecibe);
        jRBFisicaQuienRecibe.setText("Persona Física");
        jRBFisicaQuienRecibe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBFisicaQuienRecibeActionPerformed(evt);
            }
        });

        tipoPersonaQuienRecibe.add(jRBMoralQuienRecibe);
        jRBMoralQuienRecibe.setText("Persona Moral");
        jRBMoralQuienRecibe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBMoralQuienRecibeActionPerformed(evt);
            }
        });

        jLNombreFisicaQuienRecibe.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreFisicaQuienRecibe.setText("*Nombre: ");

        jLNombreMoralQuienRecibe.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreMoralQuienRecibe.setText("*Nombre: ");

        jTNombreQuienRecibeMoral.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreQuienRecibeMoral.setPreferredSize(new java.awt.Dimension(250, 26));
        jTNombreQuienRecibeMoral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreQuienRecibeMoralActionPerformed(evt);
            }
        });

        jTNombreQuienRecibeFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreQuienRecibeFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreQuienRecibeFisicaActionPerformed(evt);
            }
        });

        jLApMaQuienRecibe.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApMaQuienRecibe.setText("*Apellido Materno:");

        jTApPaQuienRecibe.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApPaQuienRecibe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApPaQuienRecibeActionPerformed(evt);
            }
        });

        jLApPaQuienRecibe.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApPaQuienRecibe.setText("*Apellido Paterno:");

        jTApMaQuienRecibe.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApMaQuienRecibe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApMaQuienRecibeActionPerformed(evt);
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
                        .addComponent(jRBFisicaQuienRecibe)
                        .addGap(18, 18, 18)
                        .addComponent(jRBMoralQuienRecibe))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLNombreMoralQuienRecibe)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTNombreQuienRecibeMoral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLNombreFisicaQuienRecibe)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTNombreQuienRecibeFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLApPaQuienRecibe)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTApPaQuienRecibe, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLApMaQuienRecibe)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTApMaQuienRecibe, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRBMoralQuienRecibe)
                    .addComponent(jRBFisicaQuienRecibe))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTNombreQuienRecibeFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLApPaQuienRecibe)
                    .addComponent(jTApPaQuienRecibe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLApMaQuienRecibe)
                    .addComponent(jTApMaQuienRecibe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNombreFisicaQuienRecibe))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTNombreQuienRecibeMoral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNombreMoralQuienRecibe)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTNombreQuienRecibeMoralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreQuienRecibeMoralActionPerformed
        jTNombreQuienRecibeMoral.transferFocus();
    }//GEN-LAST:event_jTNombreQuienRecibeMoralActionPerformed

    private void jRBMoralQuienRecibeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBMoralQuienRecibeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBMoralQuienRecibeActionPerformed

    private void jRBFisicaQuienRecibeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBFisicaQuienRecibeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBFisicaQuienRecibeActionPerformed

    private void jTNombreQuienRecibeFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreQuienRecibeFisicaActionPerformed
        jTNombreQuienRecibeFisica.transferFocus();
    }//GEN-LAST:event_jTNombreQuienRecibeFisicaActionPerformed

    private void jTApPaQuienRecibeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApPaQuienRecibeActionPerformed
        jTApPaQuienRecibe.transferFocus();
    }//GEN-LAST:event_jTApPaQuienRecibeActionPerformed

    private void jTApMaQuienRecibeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApMaQuienRecibeActionPerformed
        jTApMaQuienRecibe.transferFocus();
    }//GEN-LAST:event_jTApMaQuienRecibeActionPerformed

//Variables de los lementos añadidos dentro del jform
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel jLApMaQuienRecibe;
    public javax.swing.JLabel jLApPaQuienRecibe;
    public javax.swing.JLabel jLNombreFisicaQuienRecibe;
    public javax.swing.JLabel jLNombreMoralQuienRecibe;
    public javax.swing.JRadioButton jRBFisicaQuienRecibe;
    public javax.swing.JRadioButton jRBMoralQuienRecibe;
    public javax.swing.JTextField jTApMaQuienRecibe;
    public javax.swing.JTextField jTApPaQuienRecibe;
    public javax.swing.JTextField jTNombreQuienRecibeFisica;
    public javax.swing.JTextField jTNombreQuienRecibeMoral;
    public javax.swing.ButtonGroup tipoPersonaQuienRecibe;
    // End of variables declaration//GEN-END:variables
}
