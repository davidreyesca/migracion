package Vista;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CertificacionesNombres extends javax.swing.JPanel {

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
        SLetras(jTNombreNombreFisica);
        SLetras(jTApPaNombre);
        SLetras(jTApMaNombre);
        CambiarAMayusculas(jTNombreNombreFisica);
        CambiarAMayusculas(jTApPaNombre);
        CambiarAMayusculas(jTApMaNombre);

        NoCaracteres(jTNombreNombreFisica, 70);
        NoCaracteres(jTApPaNombre, 30);
        NoCaracteres(jTApMaNombre, 30);
        NoCaracteres(jTNombreNombreMoral, 70);
    }
    public CertificacionesNombres(int index) {
        initComponents();
        this.jRBFisicaNombre.setActionCommand("indicefNombre" + index);
        this.jRBMoralNombre.setActionCommand("indicemNombre" + index);
        ValidacionLetras();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoPersonaOtorgante = new javax.swing.ButtonGroup();
        jRBFisicaNombre = new javax.swing.JRadioButton();
        jRBMoralNombre = new javax.swing.JRadioButton();
        jLNombreFisicaNombre = new javax.swing.JLabel();
        jLNombreMoralNombre = new javax.swing.JLabel();
        jTNombreNombreMoral = new javax.swing.JTextField();
        jTNombreNombreFisica = new javax.swing.JTextField();
        jLApMaNombre = new javax.swing.JLabel();
        jTApPaNombre = new javax.swing.JTextField();
        jLApPaNombre = new javax.swing.JLabel();
        jTApMaNombre = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setAlignmentX(0.0F);
        setAlignmentY(0.0F);

        tipoPersonaOtorgante.add(jRBFisicaNombre);
        jRBFisicaNombre.setText("Persona Física");
        jRBFisicaNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBFisicaNombreActionPerformed(evt);
            }
        });

        tipoPersonaOtorgante.add(jRBMoralNombre);
        jRBMoralNombre.setText("Persona Moral");
        jRBMoralNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBMoralNombreActionPerformed(evt);
            }
        });

        jLNombreFisicaNombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreFisicaNombre.setText("*Nombre: ");

        jLNombreMoralNombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLNombreMoralNombre.setText("*Nombre: ");

        jTNombreNombreMoral.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreNombreMoral.setPreferredSize(new java.awt.Dimension(250, 26));
        jTNombreNombreMoral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreNombreMoralActionPerformed(evt);
            }
        });

        jTNombreNombreFisica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTNombreNombreFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreNombreFisicaActionPerformed(evt);
            }
        });

        jLApMaNombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApMaNombre.setText("*Apellido Materno:");

        jTApPaNombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApPaNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApPaNombreActionPerformed(evt);
            }
        });

        jLApPaNombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLApPaNombre.setText("*Apellido Paterno:");

        jTApMaNombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTApMaNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApMaNombreActionPerformed(evt);
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
                        .addComponent(jRBFisicaNombre)
                        .addGap(18, 18, 18)
                        .addComponent(jRBMoralNombre))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLNombreMoralNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTNombreNombreMoral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLNombreFisicaNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTNombreNombreFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLApPaNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTApPaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLApMaNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTApMaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRBMoralNombre)
                    .addComponent(jRBFisicaNombre))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTNombreNombreFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLApPaNombre)
                    .addComponent(jTApPaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLApMaNombre)
                    .addComponent(jTApMaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNombreFisicaNombre))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTNombreNombreMoral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNombreMoralNombre)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTNombreNombreMoralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreNombreMoralActionPerformed
        jTNombreNombreMoral.transferFocus();
    }//GEN-LAST:event_jTNombreNombreMoralActionPerformed

    private void jRBMoralNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBMoralNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBMoralNombreActionPerformed

    private void jRBFisicaNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBFisicaNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBFisicaNombreActionPerformed

    private void jTNombreNombreFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreNombreFisicaActionPerformed
        jTNombreNombreFisica.transferFocus();
    }//GEN-LAST:event_jTNombreNombreFisicaActionPerformed

    private void jTApPaNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApPaNombreActionPerformed
        jTApPaNombre.transferFocus();
    }//GEN-LAST:event_jTApPaNombreActionPerformed

    private void jTApMaNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApMaNombreActionPerformed
        jTApMaNombre.transferFocus();
    }//GEN-LAST:event_jTApMaNombreActionPerformed

//Variables de los lementos añadidos dentro del jform
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel jLApMaNombre;
    public javax.swing.JLabel jLApPaNombre;
    public javax.swing.JLabel jLNombreFisicaNombre;
    public javax.swing.JLabel jLNombreMoralNombre;
    public javax.swing.JRadioButton jRBFisicaNombre;
    public javax.swing.JRadioButton jRBMoralNombre;
    public javax.swing.JTextField jTApMaNombre;
    public javax.swing.JTextField jTApPaNombre;
    public javax.swing.JTextField jTNombreNombreFisica;
    public javax.swing.JTextField jTNombreNombreMoral;
    public javax.swing.ButtonGroup tipoPersonaOtorgante;
    // End of variables declaration//GEN-END:variables
}
