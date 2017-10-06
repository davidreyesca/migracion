package Vista;

import Controlador.DatosSesionIniciada;
import java.awt.event.KeyEvent;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Frame del login
 * @author David Reyes
 */
public class LogIn extends javax.swing.JFrame {
    /**
     *Constructor de la Frame
     */
    public LogIn() 
    {    
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("Imagenes/IconoNotaria.png")).getImage());
        setLocationRelativeTo(null);
        setResizable(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_bg = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        login = new javax.swing.JPanel();
        jLTitulo = new javax.swing.JLabel();
        jLIconoUsuario = new javax.swing.JLabel();
        jTUsuario = new javax.swing.JTextField();
        jSeparatorUsuario = new javax.swing.JSeparator();
        jLIconoPassword = new javax.swing.JLabel();
        jTpassword = new javax.swing.JPasswordField();
        jSeparatorPassword = new javax.swing.JSeparator();
        btn_login = new java.awt.Button();
        jLOlvidarPassword = new javax.swing.JLabel();
        jLOlvidarPassword1 = new javax.swing.JLabel();
        jLOlvidarPassword2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        loader = new javax.swing.JPanel();
        img_loader = new javax.swing.JLabel();
        jLIniciando = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inicio de Sesión");

        pnl_bg.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));
        login.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                loginMouseDragged(evt);
            }
        });
        login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                loginMousePressed(evt);
            }
        });

        jLTitulo.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLTitulo.setForeground(new java.awt.Color(51, 51, 51));
        jLTitulo.setText("Inicio de sesión notaría No. 21");

        jLIconoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/contacts_18px.png"))); // NOI18N

        jTUsuario.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTUsuario.setForeground(new java.awt.Color(102, 102, 102));
        jTUsuario.setText("Nombre de usuario");
        jTUsuario.setBorder(null);
        jTUsuario.setSelectionColor(new java.awt.Color(41, 168, 73));
        jTUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTUsuarioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTUsuarioFocusLost(evt);
            }
        });
        jTUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTUsuarioActionPerformed(evt);
            }
        });

        jSeparatorUsuario.setBackground(new java.awt.Color(41, 168, 73));
        jSeparatorUsuario.setForeground(new java.awt.Color(41, 168, 73));

        jLIconoPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/unlock_18px.png"))); // NOI18N
        jLIconoPassword.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jTpassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTpassword.setForeground(new java.awt.Color(102, 102, 102));
        jTpassword.setText("contraseña");
        jTpassword.setBorder(null);
        jTpassword.setSelectionColor(new java.awt.Color(41, 168, 73));
        jTpassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTpasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTpasswordFocusLost(evt);
            }
        });
        jTpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTpasswordActionPerformed(evt);
            }
        });

        jSeparatorPassword.setBackground(new java.awt.Color(41, 168, 73));
        jSeparatorPassword.setForeground(new java.awt.Color(41, 168, 73));

        btn_login.setActionCommand("Entrar");
        btn_login.setBackground(new java.awt.Color(41, 168, 73));
        btn_login.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_login.setForeground(new java.awt.Color(255, 255, 255));
        btn_login.setLabel("Login");
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });
        btn_login.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_loginKeyPressed(evt);
            }
        });

        jLOlvidarPassword.setForeground(new java.awt.Color(51, 51, 51));
        jLOlvidarPassword.setText("¿Olvidates tu contraseña?");

        jLOlvidarPassword1.setForeground(new java.awt.Color(51, 51, 51));
        jLOlvidarPassword1.setText("Usuario:");

        jLOlvidarPassword2.setForeground(new java.awt.Color(51, 51, 51));
        jLOlvidarPassword2.setText("Contraseña:");

        jPanel2.setBackground(new java.awt.Color(52, 82, 142));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(loginLayout.createSequentialGroup()
                        .addComponent(jLIconoPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparatorPassword)
                                .addComponent(jTpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(loginLayout.createSequentialGroup()
                                .addComponent(jLOlvidarPassword)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_login, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(loginLayout.createSequentialGroup()
                        .addComponent(jLIconoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLOlvidarPassword1)
                            .addComponent(jLOlvidarPassword2)
                            .addComponent(jLTitulo)
                            .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSeparatorUsuario)
                                .addComponent(jTUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLTitulo)
                .addGap(32, 32, 32)
                .addComponent(jLOlvidarPassword1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLIconoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jTUsuario))
                .addGap(8, 8, 8)
                .addComponent(jSeparatorUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLOlvidarPassword2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLIconoPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparatorPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLOlvidarPassword)
                    .addComponent(btn_login, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btn_login.getAccessibleContext().setAccessibleName("Entrar");
        btn_login.getAccessibleContext().setAccessibleDescription("");

        jPanel1.add(login, "card2");

        loader.setBackground(new java.awt.Color(255, 255, 255));

        img_loader.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/ring.gif"))); // NOI18N

        jLIniciando.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLIniciando.setForeground(new java.awt.Color(41, 168, 73));
        jLIniciando.setText("Iniciando sesión...");

        jLabel1.setText("Desarrollado por...");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/EBPLogIn.png"))); // NOI18N

        javax.swing.GroupLayout loaderLayout = new javax.swing.GroupLayout(loader);
        loader.setLayout(loaderLayout);
        loaderLayout.setHorizontalGroup(
            loaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loaderLayout.createSequentialGroup()
                .addGroup(loaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLIniciando)
                    .addGroup(loaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(loaderLayout.createSequentialGroup()
                            .addGap(182, 182, 182)
                            .addComponent(img_loader, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(loaderLayout.createSequentialGroup()
                            .addGap(172, 172, 172)
                            .addGroup(loaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)))))
                .addContainerGap(180, Short.MAX_VALUE))
        );
        loaderLayout.setVerticalGroup(
            loaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loaderLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(img_loader, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jLIniciando)
                .addGap(61, 61, 61))
        );

        jPanel1.add(loader, "card3");

        javax.swing.GroupLayout pnl_bgLayout = new javax.swing.GroupLayout(pnl_bg);
        pnl_bg.setLayout(pnl_bgLayout);
        pnl_bgLayout.setHorizontalGroup(
            pnl_bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnl_bgLayout.setVerticalGroup(
            pnl_bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTUsuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTUsuarioFocusGained
            jTUsuario.setText("");
    }//GEN-LAST:event_jTUsuarioFocusGained
    /**
     * Validación en base a la clase controlador LogIn.
     */
    public void iniciarLog()    
    {
        Controlador.LogIn log = new Controlador.LogIn();
        String usuario = jTUsuario.getText();
        String password = String.valueOf(jTpassword.getPassword());
        log.setUsuario(usuario);
        log.setPassword(password);
        //Si el objeto log devuelve 1 y 1 es un acceso correcto.
        if(log.InicioSesion()==1 && log.VerificacionBloqueo()==1)
        {   
            loader.setVisible(true);
            login.setVisible(false);
            new java.util.Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    DatosSesionIniciada.setNombreUsuario(usuario);
                    DatosSesionIniciada.GuardarDatos();
                    dispose();
                }
            },1000*3);
        }
        //Si el objeto log devuelve 1 y 0 es un el usuario esta bloqueado.
        else if(log.InicioSesion()==1 && log.VerificacionBloqueo()==0)
        {
            JOptionPane.showMessageDialog(null, "Usted se encuentra bloqueado del sistema, por favor contacte con un administrador");
            jTUsuario.transferFocus();
        }else if(log.InicioSesion()==0)
        //Si el objeto log devuelve 0 es esta mal la contraseña o el usuario.
        {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta");
            jTUsuario.transferFocus();
        }
    }
    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
        if(jTUsuario.getText().equals("Nombre de usuario"))
        {
            JOptionPane.showMessageDialog(null, "Campo usuario vacio");
        }else if(String.valueOf(jTpassword.getPassword()).equals("contraseña"))
        {
            JOptionPane.showMessageDialog(null, "Campo contraseña vacio");
        }else
        {
            iniciarLog();
        }
    }//GEN-LAST:event_btn_loginActionPerformed
    int xy, xx;
    private void jTpasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTpasswordFocusGained
        // TODO add your handling code here:
        jTpassword.setText("");
    }//GEN-LAST:event_jTpasswordFocusGained

    private void loginMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_loginMouseDragged

    private void loginMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMousePressed
        // TODO add your handling code here:
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_loginMousePressed

    private void jTpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTpasswordActionPerformed
        if(jTUsuario.getText().equals("Nombre de usuario"))
        {
            jTpassword.transferFocusBackward();
        }
        else
        {
            iniciarLog();
        }
    }//GEN-LAST:event_jTpasswordActionPerformed

    private void jTUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTUsuarioFocusLost
        if(jTUsuario.getText().isEmpty())
        {
            jTUsuario.setText("Nombre de usuario");
        }
    }//GEN-LAST:event_jTUsuarioFocusLost

    private void jTpasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTpasswordFocusLost
        if(jTpassword.getPassword().length==0)
        {
            jTpassword.setText("Nombre de usuario");
        }
    }//GEN-LAST:event_jTpasswordFocusLost

    private void jTUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTUsuarioActionPerformed
        jTUsuario.transferFocus();
    }//GEN-LAST:event_jTUsuarioActionPerformed

    private void btn_loginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_loginKeyPressed
        char caracter = evt.getKeyChar();
        if(KeyEvent.VK_ENTER == caracter)
        {
            if(jTUsuario.getText().equals("Nombre de usuario"))
        {
            JOptionPane.showMessageDialog(null, "Campo usuario vacio");
        }else if(String.valueOf(jTpassword.getPassword()).equals("contraseña"))
        {
            JOptionPane.showMessageDialog(null, "Campo contraseña vacio");
        }else
        {
            iniciarLog();
        }
        }
    }//GEN-LAST:event_btn_loginKeyPressed

    /**
     * Clase main que inicia la ventana de login
     * @param args
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
                }
                new LogIn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btn_login;
    private javax.swing.JLabel img_loader;
    private javax.swing.JLabel jLIconoPassword;
    private javax.swing.JLabel jLIconoUsuario;
    private javax.swing.JLabel jLIniciando;
    private javax.swing.JLabel jLOlvidarPassword;
    private javax.swing.JLabel jLOlvidarPassword1;
    private javax.swing.JLabel jLOlvidarPassword2;
    private javax.swing.JLabel jLTitulo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparatorPassword;
    private javax.swing.JSeparator jSeparatorUsuario;
    private javax.swing.JTextField jTUsuario;
    private javax.swing.JPasswordField jTpassword;
    public static javax.swing.JPanel loader;
    public static javax.swing.JPanel login;
    public static javax.swing.JPanel pnl_bg;
    // End of variables declaration//GEN-END:variables

}
