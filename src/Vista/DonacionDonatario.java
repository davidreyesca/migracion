package Vista;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DonacionDonatario extends javax.swing.JPanel {
    
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
        SLetras(jTNombreDonatarioFisica);
        SLetras(jTApPaDonatario);
        SLetras(jTApMaDonatario);
        CambiarAMayusculas(jTNombreDonatarioFisica);
        CambiarAMayusculas(jTApPaDonatario);
        CambiarAMayusculas(jTApMaDonatario);

        NoCaracteres(jTNombreDonatarioFisica, 70);
        NoCaracteres(jTApPaDonatario, 30);
        NoCaracteres(jTApMaDonatario, 30);
        NoCaracteres(jTNombreDonatarioMoral, 70);
    }
    public DonacionDonatario(int index) {
        initComponents();
        this.jRBFisicaDonatario.setActionCommand("indicefDonatario" + index);
        this.jRBMoralDonatario.setActionCommand("indicemDonatario" + index);
        ValidacionLetras();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoPersonaDonatario = new javax.swing.ButtonGroup();
        jRBFisicaDonatario = new javax.swing.JRadioButton();
        jRBMoralDonatario = new javax.swing.JRadioButton();
        jLNombreFisicaDonatario = new javax.swing.JLabel();
        jLNombreMoralDonatario = new javax.swing.JLabel();
        jTNombreDonatarioMoral = new javax.swing.JTextField();
        jTNombreDonatarioFisica = new javax.swing.JTextField();
        jLApMaDonatario = new javax.swing.JLabel();
        jTApPaDonatario = new javax.swing.JTextField();
        jLApPaDonatario = new javax.swing.JLabel();
        jTApMaDonatario = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        tipoPersonaDonatario.add(jRBFisicaDonatario);
        jRBFisicaDonatario.setText("Persona Física");
        jRBFisicaDonatario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBFisicaDonatarioActionPerformed(evt);
            }
        });

        tipoPersonaDonatario.add(jRBMoralDonatario);
        jRBMoralDonatario.setText("Persona Moral");
        jRBMoralDonatario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBMoralDonatarioActionPerformed(evt);
            }
        });

        jLNombreFisicaDonatario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreFisicaDonatario.setText("*Nombre: ");

        jLNombreMoralDonatario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreMoralDonatario.setText("*Nombre: ");

        jTNombreDonatarioMoral.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreDonatarioMoral.setPreferredSize(new java.awt.Dimension(250, 26));
        jTNombreDonatarioMoral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreDonatarioMoralActionPerformed(evt);
            }
        });

        jTNombreDonatarioFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreDonatarioFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreDonatarioFisicaActionPerformed(evt);
            }
        });

        jLApMaDonatario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApMaDonatario.setText("*Apellido Materno:");

        jTApPaDonatario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApPaDonatario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApPaDonatarioActionPerformed(evt);
            }
        });

        jLApPaDonatario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApPaDonatario.setText("*Apellido Paterno:");

        jTApMaDonatario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApMaDonatario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApMaDonatarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(296, 296, 296)
                        .addComponent(jRBFisicaDonatario)
                        .addGap(18, 18, 18)
                        .addComponent(jRBMoralDonatario))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLNombreMoralDonatario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTNombreDonatarioMoral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(89, 89, 89))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLNombreFisicaDonatario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTNombreDonatarioFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLApPaDonatario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTApPaDonatario, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLApMaDonatario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTApMaDonatario, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRBMoralDonatario)
                    .addComponent(jRBFisicaDonatario))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTNombreDonatarioFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLApPaDonatario)
                    .addComponent(jTApPaDonatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLApMaDonatario)
                    .addComponent(jTApMaDonatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNombreFisicaDonatario))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTNombreDonatarioMoral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNombreMoralDonatario)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jRBFisicaDonatarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBFisicaDonatarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBFisicaDonatarioActionPerformed

    private void jRBMoralDonatarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBMoralDonatarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBMoralDonatarioActionPerformed

    private void jTNombreDonatarioMoralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreDonatarioMoralActionPerformed
        jTNombreDonatarioMoral.transferFocus();
    }//GEN-LAST:event_jTNombreDonatarioMoralActionPerformed

    private void jTNombreDonatarioFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreDonatarioFisicaActionPerformed
        jTNombreDonatarioFisica.transferFocus();
    }//GEN-LAST:event_jTNombreDonatarioFisicaActionPerformed

    private void jTApPaDonatarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApPaDonatarioActionPerformed
        jTApPaDonatario.transferFocus();
    }//GEN-LAST:event_jTApPaDonatarioActionPerformed

    private void jTApMaDonatarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApMaDonatarioActionPerformed
        jTApMaDonatario.transferFocus();
    }//GEN-LAST:event_jTApMaDonatarioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel jLApMaDonatario;
    public javax.swing.JLabel jLApPaDonatario;
    public javax.swing.JLabel jLNombreFisicaDonatario;
    public javax.swing.JLabel jLNombreMoralDonatario;
    public javax.swing.JRadioButton jRBFisicaDonatario;
    public javax.swing.JRadioButton jRBMoralDonatario;
    public javax.swing.JTextField jTApMaDonatario;
    public javax.swing.JTextField jTApPaDonatario;
    public javax.swing.JTextField jTNombreDonatarioFisica;
    public javax.swing.JTextField jTNombreDonatarioMoral;
    public javax.swing.ButtonGroup tipoPersonaDonatario;
    // End of variables declaration//GEN-END:variables
}
