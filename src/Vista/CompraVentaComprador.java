package Vista;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CompraVentaComprador extends javax.swing.JPanel {

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
        SLetras(jTNombreCompradorFisica);
        SLetras(jTApPaComprador);
        SLetras(jTApMaComprador);
        CambiarAMayusculas(jTNombreCompradorFisica);
        CambiarAMayusculas(jTApPaComprador);
        CambiarAMayusculas(jTApMaComprador);

        NoCaracteres(jTNombreCompradorFisica, 70);
        NoCaracteres(jTApPaComprador, 30);
        NoCaracteres(jTApMaComprador, 30);
        NoCaracteres(jTNombreCompradorMoral, 70);
    }
    public CompraVentaComprador(int index) {
        initComponents();
        this.jRBFisicaComprador.setActionCommand("indicefComprador" + index);
        this.jRBMoralComprador.setActionCommand("indicemComprador" + index);
        ValidacionLetras();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoPersonaComprador = new javax.swing.ButtonGroup();
        jRBFisicaComprador = new javax.swing.JRadioButton();
        jRBMoralComprador = new javax.swing.JRadioButton();
        jLNombreFisicaComprador = new javax.swing.JLabel();
        jLNombreMoralComprador = new javax.swing.JLabel();
        jTNombreCompradorMoral = new javax.swing.JTextField();
        jTNombreCompradorFisica = new javax.swing.JTextField();
        jLApMaComprador = new javax.swing.JLabel();
        jTApPaComprador = new javax.swing.JTextField();
        jLApPaComprador = new javax.swing.JLabel();
        jTApMaComprador = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setAlignmentX(0.0F);
        setAlignmentY(0.0F);

        tipoPersonaComprador.add(jRBFisicaComprador);
        jRBFisicaComprador.setText("Persona Física");
        jRBFisicaComprador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBFisicaCompradorActionPerformed(evt);
            }
        });

        tipoPersonaComprador.add(jRBMoralComprador);
        jRBMoralComprador.setText("Persona Moral");
        jRBMoralComprador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBMoralCompradorActionPerformed(evt);
            }
        });

        jLNombreFisicaComprador.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreFisicaComprador.setText("*Nombre: ");

        jLNombreMoralComprador.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreMoralComprador.setText("*Nombre: ");

        jTNombreCompradorMoral.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreCompradorMoral.setPreferredSize(new java.awt.Dimension(250, 26));
        jTNombreCompradorMoral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreCompradorMoralActionPerformed(evt);
            }
        });

        jTNombreCompradorFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreCompradorFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreCompradorFisicaActionPerformed(evt);
            }
        });

        jLApMaComprador.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApMaComprador.setText("*Apellido Materno:");

        jTApPaComprador.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApPaComprador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApPaCompradorActionPerformed(evt);
            }
        });

        jLApPaComprador.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApPaComprador.setText("*Apellido Paterno:");

        jTApMaComprador.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApMaComprador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApMaCompradorActionPerformed(evt);
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
                        .addComponent(jRBFisicaComprador)
                        .addGap(18, 18, 18)
                        .addComponent(jRBMoralComprador))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLNombreMoralComprador)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTNombreCompradorMoral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLNombreFisicaComprador)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTNombreCompradorFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLApPaComprador)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTApPaComprador, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLApMaComprador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTApMaComprador, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRBMoralComprador)
                    .addComponent(jRBFisicaComprador))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTNombreCompradorFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLApPaComprador)
                    .addComponent(jTApPaComprador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLApMaComprador)
                    .addComponent(jTApMaComprador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNombreFisicaComprador))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTNombreCompradorMoral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNombreMoralComprador)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTNombreCompradorMoralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreCompradorMoralActionPerformed
        jTNombreCompradorMoral.transferFocus();
    }//GEN-LAST:event_jTNombreCompradorMoralActionPerformed

    private void jRBMoralCompradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBMoralCompradorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBMoralCompradorActionPerformed

    private void jRBFisicaCompradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBFisicaCompradorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBFisicaCompradorActionPerformed

    private void jTNombreCompradorFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreCompradorFisicaActionPerformed
        jTNombreCompradorFisica.transferFocus();
    }//GEN-LAST:event_jTNombreCompradorFisicaActionPerformed

    private void jTApPaCompradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApPaCompradorActionPerformed
        jTApPaComprador.transferFocus();
    }//GEN-LAST:event_jTApPaCompradorActionPerformed

    private void jTApMaCompradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApMaCompradorActionPerformed
        jTApMaComprador.transferFocus();
    }//GEN-LAST:event_jTApMaCompradorActionPerformed

//Variables de los lementos añadidos dentro del jform
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel jLApMaComprador;
    public javax.swing.JLabel jLApPaComprador;
    public javax.swing.JLabel jLNombreFisicaComprador;
    public javax.swing.JLabel jLNombreMoralComprador;
    public javax.swing.JRadioButton jRBFisicaComprador;
    public javax.swing.JRadioButton jRBMoralComprador;
    public javax.swing.JTextField jTApMaComprador;
    public javax.swing.JTextField jTApPaComprador;
    public javax.swing.JTextField jTNombreCompradorFisica;
    public javax.swing.JTextField jTNombreCompradorMoral;
    public javax.swing.ButtonGroup tipoPersonaComprador;
    // End of variables declaration//GEN-END:variables
}
