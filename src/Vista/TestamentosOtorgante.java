package Vista;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TestamentosOtorgante extends javax.swing.JPanel {

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
        SLetras(jTNombreOtorganteFisica);
        SLetras(jTApPaOtorgante);
        SLetras(jTApMaOtorgante);
        CambiarAMayusculas(jTNombreOtorganteFisica);
        CambiarAMayusculas(jTApPaOtorgante);
        CambiarAMayusculas(jTApMaOtorgante);

        NoCaracteres(jTNombreOtorganteFisica, 70);
        NoCaracteres(jTApPaOtorgante, 30);
        NoCaracteres(jTApMaOtorgante, 30);
        NoCaracteres(jTNombreOtorganteMoral, 70);
    }
    public TestamentosOtorgante(int index) {
        initComponents();
        this.jRBFisicaOtorgante.setActionCommand("indicefOtorgante" + index);
        this.jRBMoralOtorgante.setActionCommand("indicemOtorgante" + index);
        ValidacionLetras();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoPersonaOtorgante = new javax.swing.ButtonGroup();
        jRBFisicaOtorgante = new javax.swing.JRadioButton();
        jRBMoralOtorgante = new javax.swing.JRadioButton();
        jLNombreFisicaOtorgante = new javax.swing.JLabel();
        jLNombreMoralOtorgante = new javax.swing.JLabel();
        jTNombreOtorganteMoral = new javax.swing.JTextField();
        jTNombreOtorganteFisica = new javax.swing.JTextField();
        jLApMaOtorgante = new javax.swing.JLabel();
        jTApPaOtorgante = new javax.swing.JTextField();
        jLApPaOtorgante = new javax.swing.JLabel();
        jTApMaOtorgante = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setAlignmentX(0.0F);
        setAlignmentY(0.0F);

        tipoPersonaOtorgante.add(jRBFisicaOtorgante);
        jRBFisicaOtorgante.setText("Persona Física");
        jRBFisicaOtorgante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBFisicaOtorganteActionPerformed(evt);
            }
        });

        tipoPersonaOtorgante.add(jRBMoralOtorgante);
        jRBMoralOtorgante.setText("Persona Moral");
        jRBMoralOtorgante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBMoralOtorganteActionPerformed(evt);
            }
        });

        jLNombreFisicaOtorgante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreFisicaOtorgante.setText("*Nombre: ");

        jLNombreMoralOtorgante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreMoralOtorgante.setText("*Nombre: ");

        jTNombreOtorganteMoral.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreOtorganteMoral.setPreferredSize(new java.awt.Dimension(250, 26));
        jTNombreOtorganteMoral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreOtorganteMoralActionPerformed(evt);
            }
        });

        jTNombreOtorganteFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreOtorganteFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreOtorganteFisicaActionPerformed(evt);
            }
        });

        jLApMaOtorgante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApMaOtorgante.setText("*Apellido Materno:");

        jTApPaOtorgante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApPaOtorgante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApPaOtorganteActionPerformed(evt);
            }
        });

        jLApPaOtorgante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApPaOtorgante.setText("*Apellido Paterno:");

        jTApMaOtorgante.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApMaOtorgante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApMaOtorganteActionPerformed(evt);
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
                        .addComponent(jRBFisicaOtorgante)
                        .addGap(18, 18, 18)
                        .addComponent(jRBMoralOtorgante))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLNombreMoralOtorgante)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTNombreOtorganteMoral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLNombreFisicaOtorgante)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTNombreOtorganteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLApPaOtorgante)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTApPaOtorgante, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLApMaOtorgante)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTApMaOtorgante, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRBMoralOtorgante)
                    .addComponent(jRBFisicaOtorgante))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTNombreOtorganteFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLApPaOtorgante)
                    .addComponent(jTApPaOtorgante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLApMaOtorgante)
                    .addComponent(jTApMaOtorgante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNombreFisicaOtorgante))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTNombreOtorganteMoral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNombreMoralOtorgante)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTNombreOtorganteMoralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreOtorganteMoralActionPerformed
        jTNombreOtorganteMoral.transferFocus();
    }//GEN-LAST:event_jTNombreOtorganteMoralActionPerformed

    private void jRBMoralOtorganteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBMoralOtorganteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBMoralOtorganteActionPerformed

    private void jRBFisicaOtorganteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBFisicaOtorganteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBFisicaOtorganteActionPerformed

    private void jTNombreOtorganteFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreOtorganteFisicaActionPerformed
        jTNombreOtorganteFisica.transferFocus();
    }//GEN-LAST:event_jTNombreOtorganteFisicaActionPerformed

    private void jTApPaOtorganteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApPaOtorganteActionPerformed
        jTApPaOtorgante.transferFocus();
    }//GEN-LAST:event_jTApPaOtorganteActionPerformed

    private void jTApMaOtorganteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApMaOtorganteActionPerformed
        jTApMaOtorgante.transferFocus();
    }//GEN-LAST:event_jTApMaOtorganteActionPerformed

//Variables de los lementos añadidos dentro del jform
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel jLApMaOtorgante;
    public javax.swing.JLabel jLApPaOtorgante;
    public javax.swing.JLabel jLNombreFisicaOtorgante;
    public javax.swing.JLabel jLNombreMoralOtorgante;
    public javax.swing.JRadioButton jRBFisicaOtorgante;
    public javax.swing.JRadioButton jRBMoralOtorgante;
    public javax.swing.JTextField jTApMaOtorgante;
    public javax.swing.JTextField jTApPaOtorgante;
    public javax.swing.JTextField jTNombreOtorganteFisica;
    public javax.swing.JTextField jTNombreOtorganteMoral;
    public javax.swing.ButtonGroup tipoPersonaOtorgante;
    // End of variables declaration//GEN-END:variables
}
