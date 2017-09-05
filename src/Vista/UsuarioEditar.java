package Vista;

import Controlador.ConexionMySql;
import java.awt.HeadlessException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author David Reyes castillo
 */
public class UsuarioEditar extends javax.swing.JFrame 
{
    boolean validacionNombreUsuarioFinal=false;
    
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
    private void ValidacionTiempoReal()
    {
        SLetras(jTNombre);
        SLetras(jTApellidoPaterno);
        SLetras(jTApellidoMaterno);
        SLetras(jTNombreUsuario);
        CambiarAMayusculas(jTApellidoPaterno);
        CambiarAMayusculas(jTApellidoMaterno);
        CambiarAMayusculas(jTNombre);
    }
    private void LlenarComboArea()
    {
        DefaultComboBoxModel modeloArea = new DefaultComboBoxModel();//esto es el modelo

        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        String sSQL="SELECT * FROM area";
        try 
        {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sSQL);
                modeloArea.addElement("- Seleccionar -");
                while(rs.next()) 
                {
                    modeloArea.addElement(rs.getObject("NombreArea"));
                }
                jCArea.setModel(modeloArea);
        } catch (SQLException ex) {
                System.out.println("Error el area" + ex);
        }finally
            //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
        {
                mysql.desconectar();
        }
    }
    private void LlenarComboEstatus()
    {
        DefaultComboBoxModel modeloEstatus = new DefaultComboBoxModel();//esto es el modelo

        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        String sSQL="SELECT * FROM estatususuario";
        try 
        {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sSQL);
                modeloEstatus.addElement("- Seleccionar -");
                while(rs.next()) 
                {
                    modeloEstatus.addElement(rs.getObject("EstatusUsuario"));
                }
                jCEstatusUsuario.setModel(modeloEstatus);
        } catch (SQLException ex) {
                System.out.println("Error el estatus del usuario" + ex);
        }finally
            //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
        {
                mysql.desconectar();
        }
    }
    private void LlenarComboTipo()
    {
        DefaultComboBoxModel modeloTipoUsuario = new DefaultComboBoxModel();//esto es el modelo

        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        String sSQL="SELECT * FROM tipousuario";
        try 
        {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sSQL);
                modeloTipoUsuario.addElement("- Seleccionar -");
                while(rs.next()) 
                {
                    modeloTipoUsuario.addElement(rs.getObject("TipoUsuario"));
                }
                jCTipoUsuario.setModel(modeloTipoUsuario);
        } catch (SQLException ex) {
                System.out.println("Error el tipo de usuario" + ex);
        }finally
            //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
        {
                mysql.desconectar();
        }
    }
    private void LlenadoCombos()
    {
        LlenarComboArea();
        LlenarComboEstatus();
        LlenarComboTipo();
        jTNombreUsuario.setEnabled(false);
        jTNombre.requestFocus();
        jLOcupado.setVisible(false);
        jLAceptado.setVisible(true);
        jLIndicador.setText("Nombre de usuario valido");
        validacionNombreUsuarioFinal=true;
    }
      
    public UsuarioEditar() 
    {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("Imagenes/IconoNotaria.png")).getImage());
        setLocationRelativeTo(null);
        setResizable(false);
        LlenadoCombos();
        ValidacionTiempoReal();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jContenedor = new javax.swing.JPanel();
        Vista = new javax.swing.JPanel();
        Icono = new javax.swing.JLabel();
        jLTitulo1 = new javax.swing.JLabel();
        jLTitulo2 = new javax.swing.JLabel();
        jLNotaria = new javax.swing.JLabel();
        jPrincipal = new javax.swing.JPanel();
        jTNombreUsuario = new javax.swing.JTextField();
        jLApellidoPaterno = new javax.swing.JLabel();
        jLInstrucciones = new javax.swing.JLabel();
        jTNombre = new javax.swing.JTextField();
        jLNombreUsuario = new javax.swing.JLabel();
        jLNombre = new javax.swing.JLabel();
        jTApellidoPaterno = new javax.swing.JTextField();
        jApellidoMaterno = new javax.swing.JLabel();
        jTApellidoMaterno = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLPassword = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPArea = new javax.swing.JPanel();
        jLArea = new javax.swing.JLabel();
        jCArea = new javax.swing.JComboBox<>();
        jPEstatusUsuario = new javax.swing.JPanel();
        jLEstatus = new javax.swing.JLabel();
        jCEstatusUsuario = new javax.swing.JComboBox<>();
        jPTipoUsuario = new javax.swing.JPanel();
        jLTipoUsuario = new javax.swing.JLabel();
        jCTipoUsuario = new javax.swing.JComboBox<>();
        jBAgregar = new javax.swing.JButton();
        jLAceptado = new javax.swing.JLabel();
        jLIndicador = new javax.swing.JLabel();
        jLOcupado = new javax.swing.JLabel();
        Espacio = new javax.swing.JLabel();
        jLIndicaciones = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alta de usuarios");

        jContenedor.setBackground(new java.awt.Color(255, 255, 255));

        Vista.setBackground(new java.awt.Color(27, 107, 215));

        Icono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/registrousuarios.png"))); // NOI18N

        jLTitulo1.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLTitulo1.setForeground(new java.awt.Color(255, 255, 255));
        jLTitulo1.setText("Modulo de edición");

        jLTitulo2.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLTitulo2.setForeground(new java.awt.Color(255, 255, 255));
        jLTitulo2.setText("Usuario");

        jLNotaria.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLNotaria.setForeground(new java.awt.Color(255, 255, 255));
        jLNotaria.setText("Notaría 21 San Luis Potosí");

        javax.swing.GroupLayout VistaLayout = new javax.swing.GroupLayout(Vista);
        Vista.setLayout(VistaLayout);
        VistaLayout.setHorizontalGroup(
            VistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VistaLayout.createSequentialGroup()
                .addGroup(VistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VistaLayout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(jLNotaria))
                    .addGroup(VistaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLTitulo1))
                    .addGroup(VistaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLTitulo2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VistaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Icono)
                .addGap(79, 79, 79))
        );
        VistaLayout.setVerticalGroup(
            VistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VistaLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLTitulo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLTitulo2)
                .addGap(41, 41, 41)
                .addComponent(Icono)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLNotaria)
                .addContainerGap())
        );

        jPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        jTNombreUsuario.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        jTNombreUsuario.setSelectionColor(new java.awt.Color(41, 168, 73));
        jTNombreUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTNombreUsuarioFocusLost(evt);
            }
        });
        jTNombreUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreUsuarioActionPerformed(evt);
            }
        });

        jLApellidoPaterno.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLApellidoPaterno.setText("Apellido Paterno:");

        jLInstrucciones.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        jLInstrucciones.setText("Completa el formulario.");

        jTNombre.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        jTNombre.setSelectionColor(new java.awt.Color(41, 168, 73));
        jTNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreActionPerformed(evt);
            }
        });

        jLNombreUsuario.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLNombreUsuario.setText("Nombre de usuario:");

        jLNombre.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLNombre.setText("Nombre(s):");

        jTApellidoPaterno.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        jTApellidoPaterno.setSelectionColor(new java.awt.Color(41, 168, 73));
        jTApellidoPaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApellidoPaternoActionPerformed(evt);
            }
        });

        jApellidoMaterno.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jApellidoMaterno.setText("Apellido Materno:");

        jTApellidoMaterno.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        jTApellidoMaterno.setSelectionColor(new java.awt.Color(41, 168, 73));
        jTApellidoMaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTApellidoMaternoActionPerformed(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(204, 204, 204));

        jLPassword.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLPassword.setText("Contraseña:");

        jSeparator3.setForeground(new java.awt.Color(204, 204, 204));

        jLArea.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLArea.setText("Área perteneciente:");

        jCArea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCAreaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPAreaLayout = new javax.swing.GroupLayout(jPArea);
        jPArea.setLayout(jPAreaLayout);
        jPAreaLayout.setHorizontalGroup(
            jPAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPAreaLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLArea)
                .addGap(18, 18, 18)
                .addComponent(jCArea, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(44, 44, 44))
        );
        jPAreaLayout.setVerticalGroup(
            jPAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPAreaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLArea)
                    .addComponent(jCArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLEstatus.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLEstatus.setText("Estatus del usuario:");

        jCEstatusUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCEstatusUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCEstatusUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPEstatusUsuarioLayout = new javax.swing.GroupLayout(jPEstatusUsuario);
        jPEstatusUsuario.setLayout(jPEstatusUsuarioLayout);
        jPEstatusUsuarioLayout.setHorizontalGroup(
            jPEstatusUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPEstatusUsuarioLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLEstatus)
                .addGap(18, 18, 18)
                .addComponent(jCEstatusUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(44, 44, 44))
        );
        jPEstatusUsuarioLayout.setVerticalGroup(
            jPEstatusUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPEstatusUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPEstatusUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLEstatus)
                    .addComponent(jCEstatusUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPTipoUsuario.setForeground(new java.awt.Color(204, 204, 204));

        jLTipoUsuario.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLTipoUsuario.setText("Tipo de usuario:");

        jCTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCTipoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCTipoUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPTipoUsuarioLayout = new javax.swing.GroupLayout(jPTipoUsuario);
        jPTipoUsuario.setLayout(jPTipoUsuarioLayout);
        jPTipoUsuarioLayout.setHorizontalGroup(
            jPTipoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPTipoUsuarioLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLTipoUsuario)
                .addGap(18, 18, 18)
                .addComponent(jCTipoUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(44, 44, 44))
        );
        jPTipoUsuarioLayout.setVerticalGroup(
            jPTipoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPTipoUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPTipoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLTipoUsuario)
                    .addComponent(jCTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jBAgregar.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        jBAgregar.setForeground(new java.awt.Color(0, 153, 51));
        jBAgregar.setText("Guardar cambios");
        jBAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAgregarActionPerformed(evt);
            }
        });

        jLAceptado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/validacion.png"))); // NOI18N

        jLIndicador.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLIndicador.setText("Formato recomendado: nombre.apellido paterno");

        jLOcupado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/ocupado.png"))); // NOI18N

        Espacio.setText("     ");

        jLIndicaciones.setText("Todos los campos son obligatorios.");

        jPasswordField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPasswordField1.setText("jPasswordField1");

        javax.swing.GroupLayout jPrincipalLayout = new javax.swing.GroupLayout(jPrincipal);
        jPrincipal.setLayout(jPrincipalLayout);
        jPrincipalLayout.setHorizontalGroup(
            jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPrincipalLayout.createSequentialGroup()
                .addGroup(jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPEstatusUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPTipoUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPrincipalLayout.createSequentialGroup()
                        .addGroup(jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPrincipalLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLNombreUsuario))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPrincipalLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLNombre, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLApellidoPaterno, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jApellidoMaterno, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(18, 18, 18)
                        .addGroup(jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTNombreUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLIndicador)
                                .addGroup(jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTApellidoMaterno, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                                    .addComponent(jTApellidoPaterno, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTNombre, javax.swing.GroupLayout.Alignment.LEADING))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLOcupado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLAceptado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Espacio)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPrincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPrincipalLayout.createSequentialGroup()
                                .addGroup(jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator2)
                                    .addComponent(jSeparator3)
                                    .addGroup(jPrincipalLayout.createSequentialGroup()
                                        .addGroup(jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLInstrucciones)
                                            .addGroup(jPrincipalLayout.createSequentialGroup()
                                                .addGap(81, 81, 81)
                                                .addComponent(jLPassword)
                                                .addGap(18, 18, 18)
                                                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(84, 84, 84))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPrincipalLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jBAgregar)))))
                .addContainerGap())
            .addGroup(jPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLIndicaciones)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPrincipalLayout.setVerticalGroup(
            jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPrincipalLayout.createSequentialGroup()
                .addComponent(jLInstrucciones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLIndicaciones)
                .addGap(39, 39, 39)
                .addGroup(jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Espacio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLAceptado, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLOcupado, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLNombreUsuario))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLIndicador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPrincipalLayout.createSequentialGroup()
                        .addGroup(jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLNombre))
                        .addGap(18, 18, 18)
                        .addGroup(jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLApellidoPaterno)
                            .addComponent(jTApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jApellidoMaterno)
                            .addComponent(jTApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLPassword)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPEstatusUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPrincipalLayout.createSequentialGroup()
                        .addGap(450, 450, 450)
                        .addComponent(jBAgregar)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jContenedorLayout = new javax.swing.GroupLayout(jContenedor);
        jContenedor.setLayout(jContenedorLayout);
        jContenedorLayout.setHorizontalGroup(
            jContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jContenedorLayout.createSequentialGroup()
                .addComponent(Vista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jContenedorLayout.setVerticalGroup(
            jContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Vista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jContenedorLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTNombreUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreUsuarioActionPerformed
        jTNombreUsuario.transferFocus();
    }//GEN-LAST:event_jTNombreUsuarioActionPerformed

    private void jTNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreActionPerformed
        jTNombre.transferFocus();
    }//GEN-LAST:event_jTNombreActionPerformed

    private void jTApellidoPaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApellidoPaternoActionPerformed
        jTApellidoPaterno.transferFocus();
    }//GEN-LAST:event_jTApellidoPaternoActionPerformed

    private void jTApellidoMaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTApellidoMaternoActionPerformed
        jTApellidoMaterno.transferFocus();
    }//GEN-LAST:event_jTApellidoMaternoActionPerformed

    private void jCAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCAreaActionPerformed
        jCArea.transferFocus();
    }//GEN-LAST:event_jCAreaActionPerformed

    private void jCEstatusUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCEstatusUsuarioActionPerformed
        jCEstatusUsuario.transferFocus();
    }//GEN-LAST:event_jCEstatusUsuarioActionPerformed

    private void jCTipoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCTipoUsuarioActionPerformed
        
    }//GEN-LAST:event_jCTipoUsuarioActionPerformed

    private void jTNombreUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTNombreUsuarioFocusLost
        String ValidacionUsuario = jTNombreUsuario.getText();
        if(ValidacionUsuario.equals(""))
        {
             jLOcupado.setVisible(true);
             jLAceptado.setVisible(false);
             jLIndicador.setText("Formato recomendado: nombre.apellido paterno");
             validacionNombreUsuarioFinal=false;
        }
        else
        {
            if(ValidacionNombreUsuario(ValidacionUsuario)==true)
            {
                jLOcupado.setVisible(true);
                jLAceptado.setVisible(false);
                jLIndicador.setText("Ese nombre de usuario ya esta en uso, prueba con otro");
                validacionNombreUsuarioFinal=false;
            }else
            {
                jLOcupado.setVisible(false);
                jLAceptado.setVisible(true);
                jLIndicador.setText("Nombre de usuario valido");
                validacionNombreUsuarioFinal=true;
            }
        }
    }//GEN-LAST:event_jTNombreUsuarioFocusLost
    private void ValidacionFinal()
    {
        int area = jCArea.getSelectedIndex();
        int estatususuario = jCEstatusUsuario.getSelectedIndex();
        int tipousuario = jCTipoUsuario.getSelectedIndex();
        if(jTNombreUsuario.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "El nombre de usuario no puede estar vacio");
        }else if(validacionNombreUsuarioFinal==false)
        {
            JOptionPane.showMessageDialog(null, "Ese nombre de usuario ya esta en uso, intenta con otro");
        }else if(jTNombre.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "El nombre no puede ser un campo vacio");
        }else if(jTApellidoPaterno.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "El apellido paterno no puede ser un campo vacio");
        }else if(jTApellidoMaterno.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "El apellido materno no puede ser un campo vacio");
        }else if(area==0)
        {
            JOptionPane.showMessageDialog(null, "Debes seleccionar el área al que pertenece el usuario");
        }else if(estatususuario==0)
        {
            JOptionPane.showMessageDialog(null, "Debes seleccionar el estatus que tendrá el usuario");
        }else if(tipousuario==0)
        {
            JOptionPane.showMessageDialog(null, "Debes tipo de usuario que se debe asignar al nuevo usuario");
        }else if(String.valueOf(jPasswordField1.getPassword()).equals(""))
        {
            JOptionPane.showMessageDialog(null, "La contraseña no puede ser un campo vacio.");
        }else
        {
            DardeAlta();
        }
    }
    private void DardeAlta()
    {
        String Nombre = jTNombre.getText();
        String ApPaterno = jTApellidoPaterno.getText();
        String ApMaterno = jTApellidoMaterno.getText();
        String password = String.valueOf(jPasswordField1.getPassword());
        int permisos = 1;
        int area = ObtenerArea(jCArea.getSelectedItem().toString());
        int estatus = ObtenerEstatus(jCEstatusUsuario.getSelectedItem().toString());
        int tipousuario = ObtenerTipoUsuario(jCTipoUsuario.getSelectedItem().toString());

        String mensaje ="Se han actualziado correctamente los datos del usuario";
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        
        String sSQL= "UPDATE usuarios SET IDArea=?, Password=?, IDEstatusUsuario=?, IDTipoUsuario=?, Permisos=?, Nombre=?, ApPaternoUsuario=?, ApMaternoUsuario=? WHERE NombreUsuario = '" + jTNombreUsuario.getText() + "'";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, area);
            pst.setString(2, password);
            pst.setInt(3, estatus);
            pst.setInt(4, tipousuario);
            pst.setInt(5, permisos);
            pst.setString(6, Nombre);
            pst.setString(7, ApPaterno);
            pst.setString(8, ApMaterno);
            int validacion = pst.executeUpdate();
            if (validacion>0) 
            {
                JOptionPane.showMessageDialog(null, mensaje);
                Principal.ActualizarListaUsuarios();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Hubo un error al insertar el usuario.");
            }
        } catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! " + e);
        }finally
        {
            mysql.desconectar();
            dispose();
        }
    }
    public int ObtenerTipoUsuario(String tipousuario)
    {
        int tipo=0;
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        String sSQL="SELECT * FROM tipousuario WHERE TipoUsuario ='" + tipousuario + "'";
        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                    tipo = rs.getInt("IDTipoUsuario");
            }
        } catch (SQLException ex) {
            System.out.println("Error al contectar la base de datos" + ex);
        }finally
        {
            mysql.desconectar();
        }
        return tipo;
    }
    
    public int ObtenerEstatus(String estatus)
    {
        int estatususuario=0;
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        String sSQL="SELECT * FROM estatususuario WHERE EstatusUsuario ='" + estatus + "'";
        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                    estatususuario = rs.getInt("IDEstatusUsuario");
            }
        } catch (SQLException ex) {
            System.out.println("Error al contectar la base de datos" + ex);
        }finally
        {
            mysql.desconectar();
        }
        return estatususuario;
    }
    public int ObtenerArea(String Area)
    {
        int area=0;
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        String sSQL="SELECT * FROM area WHERE NombreArea ='" + Area + "'";
        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                    area = rs.getInt("IDArea");
            }
        } catch (SQLException ex) {
            System.out.println("Error al contectar la base de datos" + ex);
        }finally
        {
            mysql.desconectar();
        }
        return area;
    }
    
    private void jBAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarActionPerformed
        ValidacionFinal();
    }//GEN-LAST:event_jBAgregarActionPerformed

    private boolean ValidacionNombreUsuario(String nombreUsuario)
    {
        boolean existe=false;
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        String sSQL="SELECT * FROM usuarios WHERE NombreUsuario='" + nombreUsuario + "'";
        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            if (rs.next()) 
            {
               existe=true;   
            }
        } catch (SQLException ex) {
            System.out.println("Error al contectar la base de datos" + ex);
        }finally
        {
            mysql.desconectar();
        }
        return existe;
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UsuarioEditar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Espacio;
    private javax.swing.JLabel Icono;
    private javax.swing.JPanel Vista;
    private javax.swing.JLabel jApellidoMaterno;
    private javax.swing.JButton jBAgregar;
    public javax.swing.JComboBox<String> jCArea;
    public javax.swing.JComboBox<String> jCEstatusUsuario;
    public javax.swing.JComboBox<String> jCTipoUsuario;
    private javax.swing.JPanel jContenedor;
    private javax.swing.JLabel jLAceptado;
    private javax.swing.JLabel jLApellidoPaterno;
    private javax.swing.JLabel jLArea;
    private javax.swing.JLabel jLEstatus;
    private javax.swing.JLabel jLIndicaciones;
    private javax.swing.JLabel jLIndicador;
    private javax.swing.JLabel jLInstrucciones;
    private javax.swing.JLabel jLNombre;
    private javax.swing.JLabel jLNombreUsuario;
    private javax.swing.JLabel jLNotaria;
    private javax.swing.JLabel jLOcupado;
    private javax.swing.JLabel jLPassword;
    private javax.swing.JLabel jLTipoUsuario;
    private javax.swing.JLabel jLTitulo1;
    private javax.swing.JLabel jLTitulo2;
    private javax.swing.JPanel jPArea;
    private javax.swing.JPanel jPEstatusUsuario;
    private javax.swing.JPanel jPTipoUsuario;
    public javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPanel jPrincipal;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    public javax.swing.JTextField jTApellidoMaterno;
    public javax.swing.JTextField jTApellidoPaterno;
    public javax.swing.JTextField jTNombre;
    public javax.swing.JTextField jTNombreUsuario;
    // End of variables declaration//GEN-END:variables
}
