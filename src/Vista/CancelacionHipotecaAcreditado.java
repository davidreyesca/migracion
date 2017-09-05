package Vista;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CancelacionHipotecaAcreditado extends javax.swing.JPanel {

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
        SLetras(jTNombreAcreditadoFisica);
        SLetras(jTApPaAcreditado);
        SLetras(jTApMaAcreditado);
        CambiarAMayusculas(jTNombreAcreditadoFisica);
        CambiarAMayusculas(jTApPaAcreditado);
        CambiarAMayusculas(jTApMaAcreditado);

        NoCaracteres(jTNombreAcreditadoFisica, 70);
        NoCaracteres(jTApPaAcreditado, 30);
        NoCaracteres(jTApMaAcreditado, 30);
        NoCaracteres(jTNombreAcreditadoMoral, 70);
    }
    public CancelacionHipotecaAcreditado(int index) {
        initComponents();
        this.jRBFisicaAcreditado.setActionCommand("indicefAcreditado" + index);
        this.jRBMoralAcreditado.setActionCommand("indicemAcreditado" + index);
        ValidacionLetras();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoPersonaAcreditado = new javax.swing.ButtonGroup();
        jRBFisicaAcreditado = new javax.swing.JRadioButton();
        jRBMoralAcreditado = new javax.swing.JRadioButton();
        jLNombreFisicaAcreditado = new javax.swing.JLabel();
        jLNombreMoralAcreditado = new javax.swing.JLabel();
        jTNombreAcreditadoMoral = new javax.swing.JTextField();
        jTNombreAcreditadoFisica = new javax.swing.JTextField();
        jLApMaAcreditado = new javax.swing.JLabel();
        jTApPaAcreditado = new javax.swing.JTextField();
        jLApPaAcreditado = new javax.swing.JLabel();
        jTApMaAcreditado = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setAlignmentX(0.0F);
        setAlignmentY(0.0F);

        tipoPersonaAcreditado.add(jRBFisicaAcreditado);
        jRBFisicaAcreditado.setText("Persona Física");
        jRBFisicaAcreditado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBFisicaAcreditadoActionPerformed(evt);
            }
        });

        tipoPersonaAcreditado.add(jRBMoralAcreditado);
        jRBMoralAcreditado.setText("Persona Moral");
        jRBMoralAcreditado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBMoralAcreditadoActionPerformed(evt);
            }
        });

        jLNombreFisicaAcreditado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreFisicaAcreditado.setText("*Nombre: ");

        jLNombreMoralAcreditado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreMoralAcreditado.setText("*Nombre: ");

        jTNombreAcreditadoMoral.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreAcreditadoMoral.setPreferredSize(new java.awt.Dimension(250, 26));
        jTNombreAcreditadoMoral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreAcreditadoMoralActionPerformed(evt);
            }
        });

        jTNombreAcreditadoFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreAcreditadoFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreAcreditadoFisicaActionPerformed(evt);
            }
        });

        jLApMaAcreditado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApMaAcreditado.setText("*Apellido Materno:");

        jTApPaAcreditado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApPaAcreditado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApPaAcreditadoActionPerformed(evt);
            }
        });

        jLApPaAcreditado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApPaAcreditado.setText("*Apellido Paterno:");

        jTApMaAcreditado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApMaAcreditado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApMaAcreditadoActionPerformed(evt);
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
                        .addComponent(jRBFisicaAcreditado)
                        .addGap(18, 18, 18)
                        .addComponent(jRBMoralAcreditado))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLNombreMoralAcreditado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTNombreAcreditadoMoral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLNombreFisicaAcreditado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTNombreAcreditadoFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLApPaAcreditado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTApPaAcreditado, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLApMaAcreditado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTApMaAcreditado, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRBMoralAcreditado)
                    .addComponent(jRBFisicaAcreditado))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTNombreAcreditadoFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLApPaAcreditado)
                    .addComponent(jTApPaAcreditado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLApMaAcreditado)
                    .addComponent(jTApMaAcreditado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNombreFisicaAcreditado))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTNombreAcreditadoMoral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNombreMoralAcreditado)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTNombreAcreditadoMoralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreAcreditadoMoralActionPerformed
        jTNombreAcreditadoMoral.transferFocus();
    }//GEN-LAST:event_jTNombreAcreditadoMoralActionPerformed

    private void jRBMoralAcreditadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBMoralAcreditadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBMoralAcreditadoActionPerformed

    private void jRBFisicaAcreditadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBFisicaAcreditadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBFisicaAcreditadoActionPerformed

    private void jTNombreAcreditadoFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreAcreditadoFisicaActionPerformed
        jTNombreAcreditadoFisica.transferFocus();
    }//GEN-LAST:event_jTNombreAcreditadoFisicaActionPerformed

    private void jTApPaAcreditadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApPaAcreditadoActionPerformed
        jTApPaAcreditado.transferFocus();
    }//GEN-LAST:event_jTApPaAcreditadoActionPerformed

    private void jTApMaAcreditadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApMaAcreditadoActionPerformed
        jTApMaAcreditado.transferFocus();
    }//GEN-LAST:event_jTApMaAcreditadoActionPerformed

//Variables de los lementos añadidos dentro del jform
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel jLApMaAcreditado;
    public javax.swing.JLabel jLApPaAcreditado;
    public javax.swing.JLabel jLNombreFisicaAcreditado;
    public javax.swing.JLabel jLNombreMoralAcreditado;
    public javax.swing.JRadioButton jRBFisicaAcreditado;
    public javax.swing.JRadioButton jRBMoralAcreditado;
    public javax.swing.JTextField jTApMaAcreditado;
    public javax.swing.JTextField jTApPaAcreditado;
    public javax.swing.JTextField jTNombreAcreditadoFisica;
    public javax.swing.JTextField jTNombreAcreditadoMoral;
    public javax.swing.ButtonGroup tipoPersonaAcreditado;
    // End of variables declaration//GEN-END:variables
}
