package Vista;
import Controlador.AbrirExpediente;
import Controlador.AgregarArchivoExp;
import Controlador.BDdocumentos;
import Controlador.ConexionMySql;
import Controlador.DatosSesionIniciada;
import Controlador.EliminarArchivoExp;
import Controlador.ObtenerValoresUsuarios;
import Controlador.ValoresInicialesPrograma;
import java.awt.HeadlessException;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
/**
 * @author David Reyes
 * Frame que tiene toda la vista principal del usuario, fungiendo como el menú principal
 */
public final class Principal extends javax.swing.JFrame implements Runnable{
    /**
     *Metodo encargado de ocultar o mostrar paneles según sea Administrador o no.
     */
int buscar, admin;
public static DefaultTableModel modeloBuscarCap;
DefaultTableModel modeloTiposCompraventa;
static DefaultTableModel modeloUsuarios;
public void VisualizarPaneles()
{
    int NumeroPaneles = jTPSeleccion.getTabCount();
    String NombreTitulo;
    //Caso para administradores
    if(DatosSesionIniciada.getIDTipoUsuario()==1)
    {
        for (int i = 0; i < NumeroPaneles; i++) 
        {
            NombreTitulo=jTPSeleccion.getTitleAt(i);
            if(NombreTitulo.equals("Administrador"))
            {
                admin=i;
                jTPSeleccion.setSelectedIndex(i);
                break;
            }
        }      
    }
    //Caso para Capturistas
    if(DatosSesionIniciada.getIDTipoUsuario()==2)
    {
        
        for (int i = 0; i < NumeroPaneles; i++) 
        {
            NombreTitulo=jTPSeleccion.getTitleAt(i);
            if(NombreTitulo.equals("Administrador"))
            {
                jTPSeleccion.removeTabAt(i);
                break;
            }
        }
    }
    for (int i = 0; i < NumeroPaneles; i++) 
        {
            NombreTitulo=jTPSeleccion.getTitleAt(i);
            if(NombreTitulo.equals("Capturista"))
            {
                buscar=i;
                break;
            }
        }
    ImageIcon tab1Icon = new ImageIcon(this.getClass().getResource("Imagenes/estadisticas.png"));
    jTAdministrador.setIconAt(0, tab1Icon);
    ImageIcon tab2Icon = new ImageIcon(this.getClass().getResource("Imagenes/personal.png"));
    jTAdministrador.setIconAt(1, tab2Icon);
    ImageIcon tab3Icon = new ImageIcon(this.getClass().getResource("Imagenes/reportes.jpg"));
    jTAdministrador.setIconAt(2, tab3Icon);
    ImageIcon tab4Icon = new ImageIcon(this.getClass().getResource("Imagenes/ventas.png"));
    jTAdministrador.setIconAt(3, tab4Icon);
    }
    /**
     *Metodo encargado de salir de la acplicación
     */
public void close()
{
    //Confirmación del cierre total del programa.
    if (JOptionPane.showConfirmDialog(rootPane, "¿Si sales de le aplición tu sesión se cerrara, deseas continuar?",
                "Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
    {
        System.exit(0);
    }
}
    /**
     * Metodo encargado de cerrar la sesión
     */
public void cerrarSesion()
{
    //Confirmación del cierre total del programa.
    if (JOptionPane.showConfirmDialog(rootPane, "¿Seguro que deseas cerrar tu sesión?",
                "Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
    {
        System.exit(0);
    }
}
    /**
    *Metodo encargado del área de buscar, cuando se escribe algo de muestra el boton de buscar y te de la opción de lismpar el TextField,
    *todo esto mientras se tenga algo escrito en el.
    */
private void escondeBotonBuscar(JTextField a)
{
        a.addKeyListener(new KeyAdapter() {
        public void keyTyped(KeyEvent e)
        {
                if(a.getText().isEmpty() || a.getText().equals("No. Expediente, nombres..."))
                {
                    jBuscar.setVisible(false);
                    jlIconoBuscar.setIcon(new ImageIcon(getClass().getResource("Imagenes/BuscarPrincipal.png"))); 
                }
                else
                {
                    jBuscar.setVisible(true);             
                    jlIconoBuscar.setIcon(new ImageIcon(getClass().getResource("Imagenes/eliminar.png")));
                    LimpiarTablaCapturista();
                    ResultadosCapturista();
                }     
        }
        });
}
public static void LimpiarTablaCapturista()
{
       for (int i = 0; i < jTableBusquedaCapturista.getRowCount(); i++) {
           modeloBuscarCap.removeRow(i);
       }
}
public void ResultadosCapturista()
{
    Controlador.Busqueda.ResultadosCapturista();
}
public void MostrarResultadosCapturista()
{
        jTPSeleccion.setSelectedIndex(buscar);
        modeloBuscarCap = new DefaultTableModel()
        {
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
        };
        jTableBusquedaCapturista.setRowHeight(30);
        modeloBuscarCap.setColumnIdentifiers(new Object[]{"Tipo de expediente con coincidencia", "Resultados encontrados","ID del Expediente", "Fecha de creación"});
        jTableBusquedaCapturista.setModel(modeloBuscarCap);
        jPBusquedaComoCapturista.setVisible(true);
        jPVerExpedientes.setVisible(false);
        jPAdministrador.setVisible(false);
        jPCapturista.setVisible(false);
        jPMiPerfil.setVisible(false);
        jPEstadisticas.setVisible(false);
        jPAdmUsuarios.setVisible(false);
        jPReportes.setVisible(false);
        jPEstadisticasPrincipal.setVisible(false);
        jPEstadiscasGenerales.setVisible(false);
        jPEstadisticasIndividuales.setVisible(false);
        jPMenuCapturista.setVisible(false);
        jPMisEstadisticas.setVisible(false);     
}
public void llenar_comboUsuarios()
{
    DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();//esto es el modelo
    ConexionMySql mysql = new ConexionMySql();
    Connection cn = mysql.getConection();
    String sSQL="SELECT * FROM usuarios";
    try 
    {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            modeloCombo.addElement("- Seleccionar -");
            while(rs.next()) 
            {
                modeloCombo.addElement(rs.getObject("NombreUsuario"));
            }
            jCBUsuariosDisponibles.setModel(modeloCombo);
            jCFiltroUsuario.setModel(modeloCombo);
    } catch (SQLException ex) {
            System.out.println("Error al obtener todos los expedientes" + ex);
    }finally
        //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
    {
            mysql.desconectar();
    }
    jBGenerarReporte.setEnabled(false);
}
public void llenar_comboTipoActos()
{
    DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();//esto es el modelo
    ConexionMySql mysql = new ConexionMySql();
    Connection cn = mysql.getConection();
    String sSQL="SELECT * FROM tipoexpediente";
    try 
    {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            modeloCombo.addElement("- Seleccionar -");
            while(rs.next()) 
            {
                modeloCombo.addElement(rs.getObject("TipoExpediente"));
            }
            jCFiltroTipoExpediente.setModel(modeloCombo);
    }catch (SQLException ex) {
            System.out.println("Error al obtener todos los expedientes" + ex);
    }finally
        //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
    {
            mysql.desconectar();
    }
}
private void mostrarMisestadisticas()
{
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();      
        DefaultTableModel modeloMisEstadisticas = new DefaultTableModel()
        {
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
        };
        DefaultTableModel modeloMiResumen = new DefaultTableModel()
        {
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
        };
        jTMisEstadisticas.setRowHeight(30);
        jTMiResumen.setRowHeight(30);
        String sSQL="SELECT * FROM bitacora WHERE IDUsuario=" + "'" + DatosSesionIniciada.getIDUsuario() +"'";
        modeloMisEstadisticas.setColumnIdentifiers(new Object[]{"Día","ID del Expediente Generado"});
        modeloMiResumen.setColumnIdentifiers(new Object[]{"Día","No. total de Expedientes generados"});
        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {   
                    modeloMisEstadisticas.addRow(new Object[]{rs.getString("FechaOrigen"), rs.getInt("IDNoExpediente")});
            }
            jTMisEstadisticas.setModel(modeloMisEstadisticas);
        } catch (SQLException ex) {
            System.out.println("Error al obtener mis estadisticas" + ex);
        }finally
        //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
        {
            mysql.desconectar();
        }
        int fils = modeloMisEstadisticas.getRowCount();
        int contador=0;
        String diaNuevo="", diaViejo=""; 
        for(int i=0; i<fils; i++) 
        {
            if(i==0)
            {
                diaNuevo = modeloMisEstadisticas.getValueAt(i, 0).toString();
                diaViejo = diaNuevo;
            }
            else
            {
                diaNuevo = modeloMisEstadisticas.getValueAt(i, 0).toString();
            }
            if(diaNuevo.equals(diaViejo))
            {
                contador++;
                System.out.println("Contador: " + contador);
                if(i==fils-1)
                {
                    System.out.println("Día: " + diaViejo + "No de Exp: " + contador);
                    modeloMiResumen.addRow(new Object[]{diaViejo, contador});
                }
            }else
            {
                System.out.println("Día: " + diaViejo + "No de Exp: " + contador);
                modeloMiResumen.addRow(new Object[]{diaViejo, contador});
                contador=1;
            }
            diaViejo = diaNuevo; 
        }
        jTMiResumen.setModel(modeloMiResumen);
    }
    /**
     *Constructor del Frame Principal.
     */
public Principal() 
{
        initComponents();
        
        Thread mihilo= new Thread(this);
        mihilo.start();
        setIconImage(new ImageIcon(getClass().getResource("Imagenes/IconoNotaria.png")).getImage());
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                close();
            }
        });
        jBuscar.setVisible(false);
        VisualizarPaneles();
        mostrarTiposCompraVenta();
        mostarUsuarios();
        escondeBotonBuscar(jTBuscar);
//        MostrarPanelBuscar(jTBuscar);
        llenar_comboUsuarios();
        llenar_comboTipoActos();
        jLTituloPerfil.setText("¡Bienvenido " + DatosSesionIniciada.getNombre()+ " " + DatosSesionIniciada.getApPaternoUsuario() + " " + DatosSesionIniciada.getApMaternoUsuario() + "!");
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPEncabezado = new javax.swing.JPanel();
        Logo = new javax.swing.JLabel();
        jlIconoBuscar = new javax.swing.JButton();
        jUsuario = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTBuscar = new javax.swing.JTextField();
        jBuscar = new javax.swing.JButton();
        jBCerrarSesion = new javax.swing.JButton();
        jPContenedor = new javax.swing.JPanel();
        jTPSeleccion = new javax.swing.JTabbedPane();
        jPAdministrador = new javax.swing.JPanel();
        jTAdministrador = new javax.swing.JTabbedPane();
        jPEstadisticas = new javax.swing.JPanel();
        jPEstadisticasPrincipal = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jCBUsuariosDisponibles = new javax.swing.JComboBox<>();
        jBGenerarReporte = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPEstadiscasGenerales = new javax.swing.JPanel();
        jPEstadisticasIndividuales = new javax.swing.JPanel();
        jButton16 = new javax.swing.JButton();
        jLReporteUsuario = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTResumenIndividual3 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTResumenIndividual1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTResumenIndividual2 = new javax.swing.JTable();
        jPAdmUsuarios = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTUsuarios = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jPReportes = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTTiposCompraVenta = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTEditarTipoCompraVenta = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jBAgregarTipoCompraVenta = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTTipoCompraVenta = new javax.swing.JTextField();
        jPCapturista = new javax.swing.JPanel();
        jPMenuCapturista = new javax.swing.JPanel();
        jBMisEstadisticas = new javax.swing.JButton();
        jBVerExpedientes = new javax.swing.JButton();
        jBIndexar = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jPMisEstadisticas = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTMisEstadisticas = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTMiResumen = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jPBusquedaComoCapturista = new javax.swing.JPanel();
        jBRegresarDeVerExpedientes1 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableBusquedaCapturista = new javax.swing.JTable();
        JBVerExpedientes = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jBEliminarTodo = new javax.swing.JButton();
        jLInstruccionesTodosExpedientes1 = new javax.swing.JLabel();
        jPVerExpedientes = new javax.swing.JPanel();
        jLInstruccionesTodosExpedientes = new javax.swing.JLabel();
        jBRegresarDeVerExpedientes = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTTodosExpedientes = new javax.swing.JTable();
        jBAbrirTodos = new javax.swing.JButton();
        jBEliminarExpediente = new javax.swing.JButton();
        jBEditarTodos = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jCFiltroUsuario = new javax.swing.JComboBox<>();
        jCFiltroTipoExpediente = new javax.swing.JComboBox<>();
        jPMiPerfil = new javax.swing.JPanel();
        jLTituloPerfil = new javax.swing.JLabel();
        jPCambioContraseña = new javax.swing.JPanel();
        jLPasswordActual = new javax.swing.JLabel();
        jLTituloCambioPassword = new javax.swing.JLabel();
        jLPasswordNuevo = new javax.swing.JLabel();
        jLPasswordConfirmacion = new javax.swing.JLabel();
        jBActualizarPassword = new javax.swing.JButton();
        jPPasswordConfirmacion = new javax.swing.JPasswordField();
        jPPasswordNuevo = new javax.swing.JPasswordField();
        jPPasswordActual = new javax.swing.JPasswordField();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú Principal");

        jPEncabezado.setBackground(new java.awt.Color(255, 255, 255));

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/EBPArriba.png"))); // NOI18N

        jlIconoBuscar.setBackground(new java.awt.Color(255, 255, 255));
        jlIconoBuscar.setForeground(new java.awt.Color(255, 255, 255));
        jlIconoBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/BuscarPrincipal.png"))); // NOI18N
        jlIconoBuscar.setBorderPainted(false);
        jlIconoBuscar.setContentAreaFilled(false);
        jlIconoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlIconoBuscarActionPerformed(evt);
            }
        });

        jUsuario.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jUsuario.setForeground(new java.awt.Color(49, 58, 115));
        jUsuario.setText("Usuario");

        jTBuscar.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        jTBuscar.setForeground(new java.awt.Color(41, 168, 73));
        jTBuscar.setText("No. Expediente, nombres...");
        jTBuscar.setBorder(null);
        jTBuscar.setSelectionColor(new java.awt.Color(41, 168, 73));
        jTBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTBuscarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTBuscarFocusLost(evt);
            }
        });
        jTBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBuscarMouseClicked(evt);
            }
        });
        jTBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTBuscarActionPerformed(evt);
            }
        });

        jBuscar.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        jBuscar.setForeground(new java.awt.Color(49, 58, 115));
        jBuscar.setText("Buscar");
        jBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBuscarActionPerformed(evt);
            }
        });

        jBCerrarSesion.setBackground(new java.awt.Color(255, 255, 255));
        jBCerrarSesion.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jBCerrarSesion.setForeground(new java.awt.Color(49, 58, 115));
        jBCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/SalirApp.png"))); // NOI18N
        jBCerrarSesion.setContentAreaFilled(false);
        jBCerrarSesion.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/SalirAppG.png"))); // NOI18N
        jBCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCerrarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPEncabezadoLayout = new javax.swing.GroupLayout(jPEncabezado);
        jPEncabezado.setLayout(jPEncabezadoLayout);
        jPEncabezadoLayout.setHorizontalGroup(
            jPEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPEncabezadoLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(Logo)
                .addGap(44, 44, 44)
                .addGroup(jPEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPEncabezadoLayout.createSequentialGroup()
                        .addComponent(jlIconoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPEncabezadoLayout.setVerticalGroup(
            jPEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPEncabezadoLayout.createSequentialGroup()
                .addGroup(jPEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jBCerrarSesion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPEncabezadoLayout.createSequentialGroup()
                        .addGroup(jPEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPEncabezadoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jTBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPEncabezadoLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jlIconoBuscar))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPEncabezadoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(Logo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jUsuario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPContenedor.setBackground(new java.awt.Color(27, 107, 215));

        jTPSeleccion.setBackground(new java.awt.Color(27, 107, 215));
        jTPSeleccion.setForeground(new java.awt.Color(0, 18, 50));
        jTPSeleccion.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTPSeleccion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTPSeleccion.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N

        jPAdministrador.setBackground(new java.awt.Color(255, 255, 255));

        jTAdministrador.setBackground(new java.awt.Color(255, 255, 255));
        jTAdministrador.setToolTipText("");
        jTAdministrador.setAlignmentX(2.0F);
        jTAdministrador.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTAdministrador.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        jPEstadisticas.setBackground(new java.awt.Color(255, 255, 255));
        jPEstadisticas.setLayout(new java.awt.CardLayout());

        jPEstadisticasPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        jLabel3.setText("Sacar estadisticas de todos los capturistas");

        jButton13.setFont(new java.awt.Font("Leelawadee", 1, 24)); // NOI18N
        jButton13.setForeground(new java.awt.Color(49, 58, 115));
        jButton13.setText("Estadisticas de productividad generales");

        jCBUsuariosDisponibles.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jCBUsuariosDisponibles.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCBUsuariosDisponibles.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBUsuariosDisponiblesItemStateChanged(evt);
            }
        });
        jCBUsuariosDisponibles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBUsuariosDisponiblesActionPerformed(evt);
            }
        });

        jBGenerarReporte.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        jBGenerarReporte.setText("Generar el reporte");
        jBGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGenerarReporteActionPerformed(evt);
            }
        });

        jLabel4.setText("Sacar estadisticas de un capturista.");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCBUsuariosDisponibles, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBGenerarReporte)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBUsuariosDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBGenerarReporte))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel18.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Logo21arriba.png"))); // NOI18N

        javax.swing.GroupLayout jPEstadisticasPrincipalLayout = new javax.swing.GroupLayout(jPEstadisticasPrincipal);
        jPEstadisticasPrincipal.setLayout(jPEstadisticasPrincipalLayout);
        jPEstadisticasPrincipalLayout.setHorizontalGroup(
            jPEstadisticasPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPEstadisticasPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPEstadisticasPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, 1464, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPEstadisticasPrincipalLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPEstadisticasPrincipalLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel18)))
                .addContainerGap())
        );
        jPEstadisticasPrincipalLayout.setVerticalGroup(
            jPEstadisticasPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPEstadisticasPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 390, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addContainerGap())
        );

        jPEstadisticas.add(jPEstadisticasPrincipal, "card2");

        javax.swing.GroupLayout jPEstadiscasGeneralesLayout = new javax.swing.GroupLayout(jPEstadiscasGenerales);
        jPEstadiscasGenerales.setLayout(jPEstadiscasGeneralesLayout);
        jPEstadiscasGeneralesLayout.setHorizontalGroup(
            jPEstadiscasGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1494, Short.MAX_VALUE)
        );
        jPEstadiscasGeneralesLayout.setVerticalGroup(
            jPEstadiscasGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 668, Short.MAX_VALUE)
        );

        jPEstadisticas.add(jPEstadiscasGenerales, "card3");

        jPEstadisticasIndividuales.setBackground(new java.awt.Color(255, 255, 255));

        jButton16.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jButton16.setForeground(new java.awt.Color(7, 71, 166));
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/atras3.png"))); // NOI18N
        jButton16.setText(" Regresar");
        jButton16.setContentAreaFilled(false);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jLReporteUsuario.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        jLReporteUsuario.setText("Reporte de usuario");

        jTResumenIndividual3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTResumenIndividual3.setSelectionBackground(new java.awt.Color(41, 168, 73));
        jTResumenIndividual3.setShowHorizontalLines(false);
        jTResumenIndividual3.setShowVerticalLines(false);
        jScrollPane6.setViewportView(jTResumenIndividual3);

        jLabel7.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLabel7.setText("Resumen general del usuario.");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1434, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                .addGap(27, 27, 27))
        );

        jLabel1.setText("Todas los expedientes que ha generado.");

        jTResumenIndividual1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTResumenIndividual1.setRowSelectionAllowed(false);
        jTResumenIndividual1.setSelectionBackground(new java.awt.Color(41, 168, 73));
        jTResumenIndividual1.setShowHorizontalLines(false);
        jTResumenIndividual1.setShowVerticalLines(false);
        jScrollPane4.setViewportView(jTResumenIndividual1);

        jLabel6.setText("Resumen de productividad por día.");

        jTResumenIndividual2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTResumenIndividual2.setRowSelectionAllowed(false);
        jTResumenIndividual2.setSelectionBackground(new java.awt.Color(41, 168, 73));
        jTResumenIndividual2.setShowHorizontalLines(false);
        jTResumenIndividual2.setShowVerticalLines(false);
        jScrollPane5.setViewportView(jTResumenIndividual2);

        javax.swing.GroupLayout jPEstadisticasIndividualesLayout = new javax.swing.GroupLayout(jPEstadisticasIndividuales);
        jPEstadisticasIndividuales.setLayout(jPEstadisticasIndividualesLayout);
        jPEstadisticasIndividualesLayout.setHorizontalGroup(
            jPEstadisticasIndividualesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPEstadisticasIndividualesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPEstadisticasIndividualesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPEstadisticasIndividualesLayout.createSequentialGroup()
                        .addGroup(jPEstadisticasIndividualesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton16)
                            .addComponent(jLReporteUsuario)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPEstadisticasIndividualesLayout.createSequentialGroup()
                        .addGroup(jPEstadisticasIndividualesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPEstadisticasIndividualesLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap())))
        );
        jPEstadisticasIndividualesLayout.setVerticalGroup(
            jPEstadisticasIndividualesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPEstadisticasIndividualesLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jButton16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLReporteUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPEstadisticas.add(jPEstadisticasIndividuales, "card4");

        jTAdministrador.addTab("Estadisticas", jPEstadisticas);

        jPAdmUsuarios.setBackground(new java.awt.Color(255, 255, 255));

        jButton6.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(49, 58, 115));
        jButton6.setText("Agregar un Nuevo Usuario");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(49, 58, 115));
        jButton8.setText("Editar Usuario");
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jTUsuarios.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTUsuarios.setSelectionBackground(new java.awt.Color(41, 168, 73));
        jTUsuarios.setShowVerticalLines(false);
        jScrollPane9.setViewportView(jTUsuarios);

        jLabel14.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        jLabel14.setText("Usuarios actuales en el sistema");

        javax.swing.GroupLayout jPAdmUsuariosLayout = new javax.swing.GroupLayout(jPAdmUsuarios);
        jPAdmUsuarios.setLayout(jPAdmUsuariosLayout);
        jPAdmUsuariosLayout.setHorizontalGroup(
            jPAdmUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPAdmUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPAdmUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9)
                    .addGroup(jPAdmUsuariosLayout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 841, Short.MAX_VALUE))
                    .addGroup(jPAdmUsuariosLayout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton8)))
                .addContainerGap())
        );
        jPAdmUsuariosLayout.setVerticalGroup(
            jPAdmUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPAdmUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPAdmUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton8))
                .addContainerGap())
        );

        jTAdministrador.addTab("Administración de Usuarios", jPAdmUsuarios);

        jPReportes.setBackground(new java.awt.Color(255, 255, 255));

        jButton4.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton4.setForeground(new java.awt.Color(49, 58, 115));
        jButton4.setText("Generales");

        jButton9.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton9.setForeground(new java.awt.Color(49, 58, 115));
        jButton9.setText("Compra-Ventas");

        jButton10.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton10.setForeground(new java.awt.Color(49, 58, 115));
        jButton10.setText("Cancelación Hipotecas");

        jButton11.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton11.setForeground(new java.awt.Color(49, 58, 115));
        jButton11.setText("Donación");

        jButton12.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton12.setForeground(new java.awt.Color(49, 58, 115));
        jButton12.setText("Apertura Creditos");

        javax.swing.GroupLayout jPReportesLayout = new javax.swing.GroupLayout(jPReportes);
        jPReportes.setLayout(jPReportesLayout);
        jPReportesLayout.setHorizontalGroup(
            jPReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPReportesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPReportesLayout.createSequentialGroup()
                        .addGroup(jPReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE)
                            .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(46, 46, 46))
        );
        jPReportesLayout.setVerticalGroup(
            jPReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPReportesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(414, Short.MAX_VALUE))
        );

        jTAdministrador.addTab("Reportes", jPReportes);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        jLabel8.setText("Agregar nuevo tipo de \"compra venta\" a tipo de compra-venta.");

        jLabel10.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLabel10.setText("Opciones actuales de tipos de compra venta");

        jTTiposCompraVenta.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jTTiposCompraVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTTiposCompraVenta.setSelectionBackground(new java.awt.Color(41, 168, 73));
        jTTiposCompraVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTTiposCompraVentaMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(jTTiposCompraVenta);

        jLabel9.setFont(new java.awt.Font("Leelawadee", 1, 24)); // NOI18N
        jLabel9.setText("Editar");

        jLabel12.setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
        jLabel12.setText("Seleccione y edite una de las opciones actuales que hay en  tipo de compra-venta.");

        jTEditarTipoCompraVenta.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jButton14.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        jButton14.setForeground(new java.awt.Color(49, 58, 115));
        jButton14.setText("Editar");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTEditarTipoCompraVenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton14))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel12))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTEditarTipoCompraVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jBAgregarTipoCompraVenta.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        jBAgregarTipoCompraVenta.setForeground(new java.awt.Color(49, 58, 115));
        jBAgregarTipoCompraVenta.setText("Agregar");
        jBAgregarTipoCompraVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAgregarTipoCompraVentaActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
        jLabel11.setText("Indique el nombre del nuevo tipo de compra-venta que desea añadir.");

        jLabel13.setFont(new java.awt.Font("Leelawadee", 1, 24)); // NOI18N
        jLabel13.setText("Nuevo");

        jTTipoCompraVenta.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTTipoCompraVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jTTipoCompraVenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBAgregarTipoCompraVenta))
                    .addComponent(jLabel11)
                    .addComponent(jLabel13))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBAgregarTipoCompraVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTTipoCompraVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 1464, Short.MAX_VALUE)
                    .addComponent(jScrollPane10)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTAdministrador.addTab("Compra-Venta", jPanel1);

        javax.swing.GroupLayout jPAdministradorLayout = new javax.swing.GroupLayout(jPAdministrador);
        jPAdministrador.setLayout(jPAdministradorLayout);
        jPAdministradorLayout.setHorizontalGroup(
            jPAdministradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTAdministrador)
        );
        jPAdministradorLayout.setVerticalGroup(
            jPAdministradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTAdministrador)
        );

        jTPSeleccion.addTab("Administrador", jPAdministrador);

        jPCapturista.setBackground(new java.awt.Color(255, 255, 255));
        jPCapturista.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPCapturista.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPCapturistaFocusGained(evt);
            }
        });
        jPCapturista.setLayout(new java.awt.CardLayout());

        jPMenuCapturista.setBackground(new java.awt.Color(255, 255, 255));

        jBMisEstadisticas.setBackground(new java.awt.Color(204, 204, 204));
        jBMisEstadisticas.setFont(new java.awt.Font("Leelawadee", 1, 24)); // NOI18N
        jBMisEstadisticas.setForeground(new java.awt.Color(49, 58, 115));
        jBMisEstadisticas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/informe.png"))); // NOI18N
        jBMisEstadisticas.setText(" Mis estadisticas");
        jBMisEstadisticas.setContentAreaFilled(false);
        jBMisEstadisticas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBMisEstadisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBMisEstadisticasActionPerformed(evt);
            }
        });

        jBVerExpedientes.setFont(new java.awt.Font("Leelawadee", 1, 36)); // NOI18N
        jBVerExpedientes.setForeground(new java.awt.Color(49, 58, 115));
        jBVerExpedientes.setText("Todos los expedientes");
        jBVerExpedientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBVerExpedientesActionPerformed(evt);
            }
        });

        jBIndexar.setBackground(new java.awt.Color(204, 204, 204));
        jBIndexar.setFont(new java.awt.Font("Leelawadee", 1, 36)); // NOI18N
        jBIndexar.setForeground(new java.awt.Color(49, 58, 115));
        jBIndexar.setText("Indexar nuevo expediente");
        jBIndexar.setBorderPainted(false);
        jBIndexar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBIndexar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBIndexarActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        jLabel16.setText("Selecciona la opción a realizar.");

        javax.swing.GroupLayout jPMenuCapturistaLayout = new javax.swing.GroupLayout(jPMenuCapturista);
        jPMenuCapturista.setLayout(jPMenuCapturistaLayout);
        jPMenuCapturistaLayout.setHorizontalGroup(
            jPMenuCapturistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPMenuCapturistaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPMenuCapturistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPMenuCapturistaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBMisEstadisticas, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jBIndexar, javax.swing.GroupLayout.DEFAULT_SIZE, 1469, Short.MAX_VALUE)
                    .addComponent(jBVerExpedientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPMenuCapturistaLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPMenuCapturistaLayout.setVerticalGroup(
            jPMenuCapturistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPMenuCapturistaLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBIndexar, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jBVerExpedientes, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 381, Short.MAX_VALUE)
                .addComponent(jBMisEstadisticas, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jPCapturista.add(jPMenuCapturista, "card2");

        jPMisEstadisticas.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLabel2.setText("Todos los expediente que he dado de alta");

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jButton5.setForeground(new java.awt.Color(7, 71, 166));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/atras3.png"))); // NOI18N
        jButton5.setText(" Regresar");
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTMisEstadisticas.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jTMisEstadisticas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTMisEstadisticas.setOpaque(false);
        jTMisEstadisticas.setSelectionBackground(new java.awt.Color(41, 168, 73));
        jTMisEstadisticas.setShowVerticalLines(false);
        jScrollPane1.setViewportView(jTMisEstadisticas);

        jTMiResumen.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jTMiResumen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTMiResumen.setSelectionBackground(new java.awt.Color(41, 168, 73));
        jTMiResumen.setShowHorizontalLines(false);
        jTMiResumen.setShowVerticalLines(false);
        jScrollPane2.setViewportView(jTMiResumen);

        jLabel5.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLabel5.setText("Resumen por día");

        javax.swing.GroupLayout jPMisEstadisticasLayout = new javax.swing.GroupLayout(jPMisEstadisticas);
        jPMisEstadisticas.setLayout(jPMisEstadisticasLayout);
        jPMisEstadisticasLayout.setHorizontalGroup(
            jPMisEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPMisEstadisticasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPMisEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1469, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(jPMisEstadisticasLayout.createSequentialGroup()
                        .addGroup(jPMisEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPMisEstadisticasLayout.setVerticalGroup(
            jPMisEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPMisEstadisticasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(151, Short.MAX_VALUE))
        );

        jPCapturista.add(jPMisEstadisticas, "card3");

        jPBusquedaComoCapturista.setBackground(new java.awt.Color(255, 255, 255));

        jBRegresarDeVerExpedientes1.setBackground(new java.awt.Color(255, 255, 255));
        jBRegresarDeVerExpedientes1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jBRegresarDeVerExpedientes1.setForeground(new java.awt.Color(7, 71, 166));
        jBRegresarDeVerExpedientes1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/atras3.png"))); // NOI18N
        jBRegresarDeVerExpedientes1.setText(" Regresar");
        jBRegresarDeVerExpedientes1.setBorderPainted(false);
        jBRegresarDeVerExpedientes1.setContentAreaFilled(false);
        jBRegresarDeVerExpedientes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRegresarDeVerExpedientes1ActionPerformed(evt);
            }
        });

        jTableBusquedaCapturista.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jTableBusquedaCapturista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableBusquedaCapturista.setSelectionBackground(new java.awt.Color(41, 168, 73));
        jTableBusquedaCapturista.setShowHorizontalLines(false);
        jTableBusquedaCapturista.setShowVerticalLines(false);
        jTableBusquedaCapturista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableBusquedaCapturistaMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTableBusquedaCapturista);

        JBVerExpedientes.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        JBVerExpedientes.setText("Abrir expediente");
        JBVerExpedientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBVerExpedientesActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jButton3.setText("Editar expediente");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jBEliminarTodo.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jBEliminarTodo.setForeground(new java.awt.Color(255, 51, 51));
        jBEliminarTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/eliminarcarpeta.png"))); // NOI18N
        jBEliminarTodo.setText("Eliminar expediente");
        jBEliminarTodo.setBorderPainted(false);
        jBEliminarTodo.setContentAreaFilled(false);
        jBEliminarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminarTodoActionPerformed(evt);
            }
        });

        jLInstruccionesTodosExpedientes1.setText("Todos los resultados de la busqueda...");

        javax.swing.GroupLayout jPBusquedaComoCapturistaLayout = new javax.swing.GroupLayout(jPBusquedaComoCapturista);
        jPBusquedaComoCapturista.setLayout(jPBusquedaComoCapturistaLayout);
        jPBusquedaComoCapturistaLayout.setHorizontalGroup(
            jPBusquedaComoCapturistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPBusquedaComoCapturistaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPBusquedaComoCapturistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPBusquedaComoCapturistaLayout.createSequentialGroup()
                        .addComponent(jBEliminarTodo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 893, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBVerExpedientes))
                    .addGroup(jPBusquedaComoCapturistaLayout.createSequentialGroup()
                        .addGroup(jPBusquedaComoCapturistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBRegresarDeVerExpedientes1)
                            .addComponent(jLInstruccionesTodosExpedientes1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPBusquedaComoCapturistaLayout.setVerticalGroup(
            jPBusquedaComoCapturistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPBusquedaComoCapturistaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBRegresarDeVerExpedientes1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLInstruccionesTodosExpedientes1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPBusquedaComoCapturistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBVerExpedientes)
                    .addComponent(jButton3)
                    .addComponent(jBEliminarTodo))
                .addContainerGap())
        );

        jPCapturista.add(jPBusquedaComoCapturista, "card5");

        jPVerExpedientes.setBackground(new java.awt.Color(255, 255, 255));

        jLInstruccionesTodosExpedientes.setText("Todos los expedientes.");

        jBRegresarDeVerExpedientes.setBackground(new java.awt.Color(255, 255, 255));
        jBRegresarDeVerExpedientes.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jBRegresarDeVerExpedientes.setForeground(new java.awt.Color(7, 71, 166));
        jBRegresarDeVerExpedientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/atras3.png"))); // NOI18N
        jBRegresarDeVerExpedientes.setText(" Regresar");
        jBRegresarDeVerExpedientes.setBorderPainted(false);
        jBRegresarDeVerExpedientes.setContentAreaFilled(false);
        jBRegresarDeVerExpedientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRegresarDeVerExpedientesActionPerformed(evt);
            }
        });

        jTTodosExpedientes.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jTTodosExpedientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTTodosExpedientes.setGridColor(new java.awt.Color(51, 51, 51));
        jTTodosExpedientes.setSelectionBackground(new java.awt.Color(41, 168, 73));
        jTTodosExpedientes.setShowVerticalLines(false);
        jTTodosExpedientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTTodosExpedientesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTTodosExpedientes);

        jBAbrirTodos.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        jBAbrirTodos.setText("Abrir Expediente");
        jBAbrirTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAbrirTodosActionPerformed(evt);
            }
        });

        jBEliminarExpediente.setBackground(new java.awt.Color(255, 255, 255));
        jBEliminarExpediente.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jBEliminarExpediente.setForeground(new java.awt.Color(255, 0, 51));
        jBEliminarExpediente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/eliminarcarpeta.png"))); // NOI18N
        jBEliminarExpediente.setText("Eliminar expediente");
        jBEliminarExpediente.setContentAreaFilled(false);
        jBEliminarExpediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminarExpedienteActionPerformed(evt);
            }
        });

        jBEditarTodos.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jBEditarTodos.setText("Editar expediente");
        jBEditarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEditarTodosActionPerformed(evt);
            }
        });

        jButton18.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jButton18.setForeground(new java.awt.Color(255, 51, 51));
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/eliminararchivo.png"))); // NOI18N
        jButton18.setText("Eliminar un archivo");
        jButton18.setContentAreaFilled(false);
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton21.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jButton21.setText("Agregar un archivo al expediente");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLabel15.setText("Filtros:");

        jCFiltroUsuario.setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
        jCFiltroUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCFiltroUsuario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCFiltroUsuarioItemStateChanged(evt);
            }
        });

        jCFiltroTipoExpediente.setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
        jCFiltroTipoExpediente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCFiltroTipoExpediente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCFiltroTipoExpedienteItemStateChanged(evt);
            }
        });
        jCFiltroTipoExpediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCFiltroTipoExpedienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(jCFiltroUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCFiltroTipoExpediente, 0, 215, Short.MAX_VALUE)
                .addGap(44, 44, 44))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jCFiltroUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCFiltroTipoExpediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPVerExpedientesLayout = new javax.swing.GroupLayout(jPVerExpedientes);
        jPVerExpedientes.setLayout(jPVerExpedientesLayout);
        jPVerExpedientesLayout.setHorizontalGroup(
            jPVerExpedientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPVerExpedientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPVerExpedientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPVerExpedientesLayout.createSequentialGroup()
                        .addComponent(jBEliminarExpediente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 365, Short.MAX_VALUE)
                        .addComponent(jButton21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBEditarTodos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBAbrirTodos))
                    .addGroup(jPVerExpedientesLayout.createSequentialGroup()
                        .addComponent(jLInstruccionesTodosExpedientes)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPVerExpedientesLayout.createSequentialGroup()
                        .addComponent(jBRegresarDeVerExpedientes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPVerExpedientesLayout.setVerticalGroup(
            jPVerExpedientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPVerExpedientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPVerExpedientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBRegresarDeVerExpedientes)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLInstruccionesTodosExpedientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPVerExpedientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBAbrirTodos)
                    .addComponent(jBEliminarExpediente)
                    .addComponent(jBEditarTodos)
                    .addComponent(jButton18)
                    .addComponent(jButton21))
                .addContainerGap())
        );

        jPCapturista.add(jPVerExpedientes, "card4");

        jTPSeleccion.addTab("Capturista", jPCapturista);

        jPMiPerfil.setBackground(new java.awt.Color(255, 255, 255));

        jLTituloPerfil.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLTituloPerfil.setText("¡Bienvenido!");

        jPCambioContraseña.setBackground(new java.awt.Color(255, 255, 255));

        jLPasswordActual.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLPasswordActual.setForeground(new java.awt.Color(153, 153, 153));
        jLPasswordActual.setText("Contraseña actual:");

        jLTituloCambioPassword.setBackground(new java.awt.Color(255, 255, 255));
        jLTituloCambioPassword.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLTituloCambioPassword.setForeground(new java.awt.Color(102, 102, 102));
        jLTituloCambioPassword.setText("Cambio de contraseña");

        jLPasswordNuevo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLPasswordNuevo.setForeground(new java.awt.Color(102, 102, 102));
        jLPasswordNuevo.setText("Nueva contraseña:");

        jLPasswordConfirmacion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLPasswordConfirmacion.setForeground(new java.awt.Color(102, 102, 102));
        jLPasswordConfirmacion.setText("Confirma contraseña:");

        jBActualizarPassword.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jBActualizarPassword.setForeground(new java.awt.Color(49, 58, 115));
        jBActualizarPassword.setText("Actualizar");
        jBActualizarPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBActualizarPasswordActionPerformed(evt);
            }
        });

        jPPasswordConfirmacion.setText("Passwordnuevo");
        jPPasswordConfirmacion.setSelectionColor(new java.awt.Color(41, 168, 73));
        jPPasswordConfirmacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPPasswordConfirmacionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPPasswordConfirmacionFocusLost(evt);
            }
        });
        jPPasswordConfirmacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPPasswordConfirmacionActionPerformed(evt);
            }
        });

        jPPasswordNuevo.setText("Passwordnuevo");
        jPPasswordNuevo.setSelectionColor(new java.awt.Color(41, 168, 73));
        jPPasswordNuevo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPPasswordNuevoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPPasswordNuevoFocusLost(evt);
            }
        });
        jPPasswordNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPPasswordNuevoActionPerformed(evt);
            }
        });

        jPPasswordActual.setText("PasswordAntiguo");
        jPPasswordActual.setSelectionColor(new java.awt.Color(41, 168, 73));
        jPPasswordActual.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPPasswordActualFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPPasswordActualFocusLost(evt);
            }
        });
        jPPasswordActual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPPasswordActualActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPCambioContraseñaLayout = new javax.swing.GroupLayout(jPCambioContraseña);
        jPCambioContraseña.setLayout(jPCambioContraseñaLayout);
        jPCambioContraseñaLayout.setHorizontalGroup(
            jPCambioContraseñaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCambioContraseñaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPCambioContraseñaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jBActualizarPassword)
                    .addGroup(jPCambioContraseñaLayout.createSequentialGroup()
                        .addGroup(jPCambioContraseñaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLPasswordActual)
                            .addComponent(jLPasswordNuevo)
                            .addComponent(jLPasswordConfirmacion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPCambioContraseñaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLTituloCambioPassword)
                            .addComponent(jPPasswordConfirmacion, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                            .addComponent(jPPasswordNuevo)
                            .addComponent(jPPasswordActual))))
                .addContainerGap(892, Short.MAX_VALUE))
        );
        jPCambioContraseñaLayout.setVerticalGroup(
            jPCambioContraseñaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCambioContraseñaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLTituloCambioPassword)
                .addGap(18, 18, 18)
                .addGroup(jPCambioContraseñaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLPasswordActual)
                    .addComponent(jPPasswordActual, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPCambioContraseñaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLPasswordNuevo)
                    .addComponent(jPPasswordNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPCambioContraseñaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLPasswordConfirmacion)
                    .addComponent(jPPasswordConfirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jBActualizarPassword)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel17.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Logo21arriba.png"))); // NOI18N

        javax.swing.GroupLayout jPMiPerfilLayout = new javax.swing.GroupLayout(jPMiPerfil);
        jPMiPerfil.setLayout(jPMiPerfilLayout);
        jPMiPerfilLayout.setHorizontalGroup(
            jPMiPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPMiPerfilLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPMiPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPCambioContraseña, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPMiPerfilLayout.createSequentialGroup()
                        .addComponent(jLTituloPerfil)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPMiPerfilLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel17)))
                .addContainerGap())
        );
        jPMiPerfilLayout.setVerticalGroup(
            jPMiPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPMiPerfilLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLTituloPerfil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPCambioContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 283, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addContainerGap())
        );

        jTPSeleccion.addTab("Mi perfil", jPMiPerfil);

        javax.swing.GroupLayout jPContenedorLayout = new javax.swing.GroupLayout(jPContenedor);
        jPContenedor.setLayout(jPContenedorLayout);
        jPContenedorLayout.setHorizontalGroup(
            jPContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTPSeleccion)
        );
        jPContenedorLayout.setVerticalGroup(
            jPContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPContenedorLayout.createSequentialGroup()
                .addComponent(jTPSeleccion)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
   //Creación del objeto BDdocumentos
    private void jBIndexarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBIndexarActionPerformed
        BDdocumentos crear = new BDdocumentos();
        crear.inicioIndexar();
    }//GEN-LAST:event_jBIndexarActionPerformed

    private void jTBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTBuscarFocusGained
        if (jTBuscar.getText().equals("No. Expediente, nombres...")) 
        {
            jTBuscar.setText("");
                MostrarResultadosCapturista();
        }else
        {
            jTBuscar.setText(jTBuscar.getText());
                MostrarResultadosCapturista();
            
        }
    }//GEN-LAST:event_jTBuscarFocusGained
    private void jTBuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTBuscarFocusLost
        if (jTBuscar.getText().equals(""))
        {
            jTBuscar.setText("No. Expediente, nombres...");
        }
    }//GEN-LAST:event_jTBuscarFocusLost
//Limpia el icono de lupa cuando hay texto en el textfield buscar
    private void jlIconoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlIconoBuscarActionPerformed
        jTBuscar.setText("");
        jTBuscar.requestFocus();
                MostrarResultadosCapturista();
        jlIconoBuscar.setIcon(new ImageIcon(getClass().getResource("Imagenes/BuscarPrincipal.png")));  
    }//GEN-LAST:event_jlIconoBuscarActionPerformed

    private void jBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBuscarActionPerformed
        AccionBuscar();
    }//GEN-LAST:event_jBuscarActionPerformed
    public void AccionBuscar()
    {  
                MostrarResultadosCapturista();
                ResultadosCapturista();   
    }
    private void jPCapturistaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPCapturistaFocusGained
        jPCapturista.transferFocus();
    }//GEN-LAST:event_jPCapturistaFocusGained

    private void jBVerExpedientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBVerExpedientesActionPerformed
        jPMenuCapturista.setVisible(false);
        jPVerExpedientes.setVisible(true);
        MostrarTodosExpedientes();
    }//GEN-LAST:event_jBVerExpedientesActionPerformed
    private void MostrarTodosExpedientes()
    {
        llenar_comboUsuarios();
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        DefaultTableModel modeloExpedientes = new DefaultTableModel()
        {
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
        };
        jTTodosExpedientes.setRowHeight(30);
        String sSQL="SELECT casoscliente.IDNoExpediente, tipoexpediente.TipoExpediente, bitacora.FechaOrigen, bitacora.ActividadRealizada, usuarios.Nombre, usuarios.ApPaternoUsuario FROM casoscliente INNER JOIN tipoexpediente ON casoscliente.IDTipoExpediente = tipoexpediente.IDTipoExpediente INNER JOIN bitacora ON casoscliente.IDNoExpediente = bitacora.IDNoExpediente INNER JOIN usuarios ON bitacora.IDUsuario = usuarios.IDUsuario ORDER BY casoscliente.IDNoExpediente";
        modeloExpedientes.setColumnIdentifiers(new Object[]{"ID del Expediente","Tipo de Expediente", "Fecha de creación" ,"Actividad Realizada", "Creado por" });
        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                modeloExpedientes.addRow(new Object[]{rs.getInt("IDNoExpediente"), rs.getString("TipoExpediente"), rs.getString("FechaOrigen"), rs.getString("ActividadRealizada"), rs.getString("Nombre").concat(" "+rs.getString("ApPaternoUsuario"))});
            }
            jTTodosExpedientes.setModel(modeloExpedientes);
        } catch (SQLException ex) {
            System.out.println("Error al obtener todos los expedientes" + ex);
        }finally
        //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
        {
            mysql.desconectar();
        }
        jTTodosExpedientes.getColumnModel().getColumn(0).setResizable(true);
        jTTodosExpedientes.getColumnModel().getColumn(1).setResizable(true);
        jTTodosExpedientes.getColumnModel().getColumn(2).setResizable(true);
        jTTodosExpedientes.getColumnModel().getColumn(3).setResizable(true);
        jTTodosExpedientes.getColumnModel().getColumn(4).setResizable(true);
    }
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        jPMenuCapturista.setVisible(true);
        jPMisEstadisticas.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed
    public void mostrarTiposCompraVenta()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();      
        modeloTiposCompraventa = new DefaultTableModel()
        {
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
        };
        String sSQL="SELECT * FROM tipocompraventa";
        modeloTiposCompraventa.setColumnIdentifiers(new Object[]{"Tipos de compra-venta"});
        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {   
                    modeloTiposCompraventa.addRow(new Object[]{rs.getString("TipoCompraVenta")});
            }
            jTTiposCompraVenta.setModel(modeloTiposCompraventa);
        } catch (SQLException ex) {
            System.out.println("Error al obtener mis estadisticas" + ex);
        }finally
        //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
        {
            mysql.desconectar();
        }
        jTTiposCompraVenta.setRowHeight(30);
    }
    public static void mostarUsuarios()
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        modeloUsuarios = new DefaultTableModel(){
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
        };
        jTUsuarios.setRowHeight(30);
        String sSQL="SELECT usuarios.*, estatususuario.EstatusUsuario, tipousuario.TipoUsuario FROM usuarios LEFT JOIN estatususuario ON estatususuario.IDEstatusUsuario = usuarios.IDEstatusUsuario LEFT JOIN tipousuario ON tipousuario.IDTipoUsuario = usuarios.IDTipoUsuario";
        modeloUsuarios.setColumnIdentifiers(new Object[]{"Nombre(s)", "Apellido Paterno", "Apellido Materno", "Nombre Usuario", "Estado del usuario", "Tipo de Usuario"});
        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {   
                    modeloUsuarios.addRow(new Object[]{rs.getString("Nombre"), rs.getString("ApPaternoUsuario"), rs.getString("ApMaternoUsuario"), rs.getString("NombreUsuario"), rs.getString("EstatusUsuario"), rs.getString("TipoUsuario")});
            }
            jTUsuarios.setModel(modeloUsuarios);
        } catch (SQLException ex) {
            System.out.println("Error al obtener a los usuarios del sistema" + ex);
        }finally
        //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
        {
            mysql.desconectar();
        }
    }    
    private void jBMisEstadisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBMisEstadisticasActionPerformed
        jPMenuCapturista.setVisible(false);
        jPMisEstadisticas.setVisible(true);
        mostrarMisestadisticas();
    }//GEN-LAST:event_jBMisEstadisticasActionPerformed
    private void validacionVacios()
    {
        String passwordActual = String.valueOf(jPPasswordActual.getPassword());
        String passwordNuevo = String.valueOf(jPPasswordNuevo.getPassword());
        String passwordConfirmado = String.valueOf(jPPasswordConfirmacion.getPassword());
        if(passwordActual.equals("PasswordAntiguo") || passwordActual.length() == 0)
        {
            JOptionPane.showMessageDialog(null, "El campo de contraseña actual no puede ser vacio");
        }else if(passwordNuevo.equals("Passwordnuevo") || passwordNuevo.length() == 0)
        {
            JOptionPane.showMessageDialog(null, "El campo de nueva contraseña no puede ser vacio");
        }else if(passwordConfirmado.equals("Passwordnuevo") || passwordConfirmado.length() == 0)
        {
            JOptionPane.showMessageDialog(null, "El campo de confirma contraseña no puede ser vacio");
        }else if(!passwordNuevo.equals(passwordConfirmado))
        {
            JOptionPane.showMessageDialog(null, "Tu confirmación de contraseña no coincide con la nueva contraseña");
            jPPasswordNuevo.transferFocus();
            jPPasswordConfirmacion.setText("");
        }else
        {
            validacionBD(passwordActual, passwordNuevo);
        }
    }
    private void validacionBD(String passwordActual, String passwordNuevo)
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        String sSQL="SELECT * FROM usuarios WHERE NombreUsuario='" + DatosSesionIniciada.getNombreUsuario() + "' AND Password='" + passwordActual +"'";
        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            if (rs.next()) 
            {
                ActualizarContraseña(passwordNuevo);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Tu contraseña actual no es correcta, por favor vuelve a intentarlo");
            }
        } catch (SQLException ex) {
            System.out.println("Error al contectar la base de datos" + ex);
        }finally
        {
            mysql.desconectar();
        }
    }
    private void ActualizarContraseña(String passwordNuevo)
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        String sSQL= "UPDATE usuarios SET Password = ? WHERE NombreUsuario = '"+ DatosSesionIniciada.getNombreUsuario() +"'";
        try 
        {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, passwordNuevo);
            int validacion = pst.executeUpdate();
            if (validacion>0) 
            {
                JOptionPane.showMessageDialog(null, "Tu contraseña se ha actualizado exitosamente");
                jPPasswordActual.setText("PasswordAntiguo");
                jPPasswordNuevo.setText("Passwordnuevo");
                jPPasswordConfirmacion.setText("Passwordnuevo");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Hubo un error al ACTUALIZAR tu CONTRASEÑA");
            }
        } catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! de SINTAXIS" + e);
        }
        finally
        {
            mysql.desconectar();
        }
    }  
    private void jBActualizarPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBActualizarPasswordActionPerformed
        validacionVacios();  
    }//GEN-LAST:event_jBActualizarPasswordActionPerformed
    private void jBCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCerrarSesionActionPerformed
        cerrarSesion();
    }//GEN-LAST:event_jBCerrarSesionActionPerformed
    private void jPPasswordActualFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPPasswordActualFocusGained
        String passwordActual = String.valueOf(jPPasswordActual.getPassword());
        if(passwordActual.equals("PasswordAntiguo") || passwordActual.length() == 0)
        {
            jPPasswordActual.setText("");
        }
    }//GEN-LAST:event_jPPasswordActualFocusGained
    private void jPPasswordActualFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPPasswordActualFocusLost
        if(jPPasswordActual.getPassword().length==0)
        {
            jPPasswordActual.setText("PasswordAntiguo");
        }
    }//GEN-LAST:event_jPPasswordActualFocusLost
    private void jPPasswordNuevoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPPasswordNuevoFocusGained
        String passwordNuevo = String.valueOf(jPPasswordNuevo.getPassword());
        if(passwordNuevo.equals("Passwordnuevo") || passwordNuevo.length() == 0)
        {
            jPPasswordNuevo.setText("");
        }
    }//GEN-LAST:event_jPPasswordNuevoFocusGained
    private void jPPasswordNuevoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPPasswordNuevoFocusLost
        if(jPPasswordNuevo.getPassword().length==0)
        {
            jPPasswordNuevo.setText("Passwordnuevo");
        }
    }//GEN-LAST:event_jPPasswordNuevoFocusLost
    private void jPPasswordConfirmacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPPasswordConfirmacionFocusGained
        String passwordConfirmado = String.valueOf(jPPasswordConfirmacion.getPassword());
        if(passwordConfirmado.equals("Passwordnuevo") || passwordConfirmado.length() == 0)
        {
            jPPasswordConfirmacion.setText("");
        }
    }//GEN-LAST:event_jPPasswordConfirmacionFocusGained
    private void jPPasswordConfirmacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPPasswordConfirmacionFocusLost
        if(jPPasswordConfirmacion.getPassword().length==0)
        {
            jPPasswordConfirmacion.setText("Passwordnuevo");
        }
    }//GEN-LAST:event_jPPasswordConfirmacionFocusLost
    private void jPPasswordActualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPPasswordActualActionPerformed
        jPPasswordActual.transferFocus();
    }//GEN-LAST:event_jPPasswordActualActionPerformed
    private void jPPasswordNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPPasswordNuevoActionPerformed
        jPPasswordNuevo.transferFocus();
    }//GEN-LAST:event_jPPasswordNuevoActionPerformed
    private void jPPasswordConfirmacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPPasswordConfirmacionActionPerformed
        validacionVacios();
    }//GEN-LAST:event_jPPasswordConfirmacionActionPerformed
    private void jBRegresarDeVerExpedientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRegresarDeVerExpedientesActionPerformed
        jPMenuCapturista.setVisible(true);
        jPVerExpedientes.setVisible(false);
    }//GEN-LAST:event_jBRegresarDeVerExpedientesActionPerformed
    private void jCBUsuariosDisponiblesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBUsuariosDisponiblesItemStateChanged
        if(evt.getStateChange() ==ItemEvent.SELECTED)
        {
            if (this.jCBUsuariosDisponibles.getSelectedIndex()>0) 
            { 
                jBGenerarReporte.setEnabled(true);
            }
            else
            {
                jBGenerarReporte.setEnabled(false);
            }
        }
    }//GEN-LAST:event_jCBUsuariosDisponiblesItemStateChanged
    private void jBGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGenerarReporteActionPerformed
        jPEstadisticasPrincipal.setVisible(false);
        jPEstadisticasIndividuales.setVisible(true);
        jLReporteUsuario.setText("Reporte del usuario: " + jCBUsuariosDisponibles.getSelectedItem().toString());
        String Usuario=jCBUsuariosDisponibles.getSelectedItem().toString();
        LlenarResumenIndividual(Usuario);
    }//GEN-LAST:event_jBGenerarReporteActionPerformed
    private void LlenarResumenIndividual(String usuario)
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();      
        DefaultTableModel modelo1 = new DefaultTableModel()
        {
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
        };
        DefaultTableModel modelo2 = new DefaultTableModel()
        {
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
        };
        DefaultTableModel modelo3 = new DefaultTableModel()
        {
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
        };
        jTResumenIndividual1.setRowHeight(30);
        jTResumenIndividual2.setRowHeight(30);
        jTResumenIndividual3.setRowHeight(30);
        String sSQL="SELECT * FROM bitacora WHERE IDUsuario=" + "'" + ObtenerIDUsuario(usuario) +"'";
        modelo1.setColumnIdentifiers(new Object[]{"Día","ID del Expediente Generado"});
        modelo2.setColumnIdentifiers(new Object[]{"Días","No. total de Expedientes generados"});
        modelo3.setColumnIdentifiers(new Object[]{"Promedio por día","Total de expedientes generados hasta la fecha","Menor número de Expedientes hechos por día", "Mejor número de Expedientes hechos por día"});
        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {   
                    modelo1.addRow(new Object[]{rs.getString("FechaOrigen"), rs.getInt("IDNoExpediente")});
            }
            jTResumenIndividual1.setModel(modelo1);
        } catch (SQLException ex) {
            System.out.println("Error al obtener mis estadisticas" + ex);
        }finally
        //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
        {
            mysql.desconectar();
        }     
        int fils = modelo1.getRowCount();
        int contador=0;
        String diaNuevo="", diaViejo=""; 
        for(int i=0; i<fils; i++) 
        {
            if(i==0)
            {
                diaNuevo = modelo1.getValueAt(i, 0).toString();
                diaViejo = diaNuevo;
            }
            else
            {
                diaNuevo = modelo1.getValueAt(i, 0).toString();
            }
            if(diaNuevo.equals(diaViejo))
            {
                contador++;
                System.out.println("Contador: " + contador);
                if(i==fils-1)
                {
                    System.out.println("Día: " + diaViejo + "No de Exp: " + contador);
                    modelo2.addRow(new Object[]{diaViejo, contador});
                }
            }else
            {
                System.out.println("Día: " + diaViejo + "No de Exp: " + contador);
                modelo2.addRow(new Object[]{diaViejo, contador});
                contador=1;
            }
            diaViejo = diaNuevo; 
        }
        jTResumenIndividual2.setModel(modelo2);
        int diastotales = modelo2.getRowCount();
        if(diastotales>0)
        {
            int sumaTotal=0, menor=1000, mayor=0;
            for(int i=0; i<diastotales; i++) 
            {
                sumaTotal = (int)modelo2.getValueAt(i, 1)+sumaTotal;
                System.out.println("Suma Total: "+ sumaTotal);
                if((int) modelo2.getValueAt(i, 1)>mayor)
                {
                    mayor= (int) modelo2.getValueAt(i, 1);
                    System.out.println("Menor: "+ menor);
                }
                if((int) modelo2.getValueAt(i, 1)<menor)
                {
                    menor= (int) modelo2.getValueAt(i, 1);
                    System.out.println("Mayor: "+ mayor);
                }
            }
            float promedio = sumaTotal/diastotales;
            System.out.println("Promedio: " + promedio);
            modelo3.addRow(new Object[]{promedio, sumaTotal ,menor, mayor});
            jTResumenIndividual3.setModel(modelo3);
        }else
        {
            modelo3.addRow(new Object[]{"Aún no tiene Expedientes creados", "Sin información","Sin información", "Sin información"});
            jTResumenIndividual3.setModel(modelo3);
            
        }
        
    }
    private int ObtenerIDUsuario(String Usuario)
    {
        int IDUsuario=0;
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        String sSQL="SELECT * FROM usuarios WHERE NombreUsuario=" + "'" + Usuario +"'";
        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {   
                    IDUsuario = rs.getInt("IDUsuario");
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener el ID del usuario" + ex);
        }finally
        //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
        {
            mysql.desconectar();
        }
        return IDUsuario;
    }
    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        jPEstadisticasPrincipal.setVisible(true);
        jPEstadisticasIndividuales.setVisible(false);
    }//GEN-LAST:event_jButton16ActionPerformed
    private void jTBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBuscarMouseClicked
            MostrarResultadosCapturista();
    }//GEN-LAST:event_jTBuscarMouseClicked
    private void jBRegresarDeVerExpedientes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRegresarDeVerExpedientes1ActionPerformed
        jPBusquedaComoCapturista.setVisible(false);
        jPMenuCapturista.setVisible(true);
        jTBuscar.setText("");
    }//GEN-LAST:event_jBRegresarDeVerExpedientes1ActionPerformed
    private void jTBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTBuscarActionPerformed
        AccionBuscar();
    }//GEN-LAST:event_jTBuscarActionPerformed
    private void JBVerExpedientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBVerExpedientesActionPerformed
        if(jTableBusquedaCapturista.getSelectedRow()==-1)
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún expediente");
        }else
        {
            AbrirexpedienteBusquedaPersonalizada(0);
        }   
    }//GEN-LAST:event_JBVerExpedientesActionPerformed
    private void jBAbrirTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAbrirTodosActionPerformed
        if(jTTodosExpedientes.getSelectedRow()==-1)
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún expediente");
        }else
        {
            AbrirexpedienteBusquedaTotal(0);
        }
    }//GEN-LAST:event_jBAbrirTodosActionPerformed
    private void jBEditarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEditarTodosActionPerformed
        if(jTTodosExpedientes.getSelectedRow()==-1)
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún expediente");
        }else
        {
        AbrirexpedienteBusquedaTotal(1);
        }
    }//GEN-LAST:event_jBEditarTodosActionPerformed
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(jTableBusquedaCapturista.getSelectedRow()==-1)
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún expediente");
        }else
        {
            AbrirexpedienteBusquedaPersonalizada(1);
        }  
    }//GEN-LAST:event_jButton3ActionPerformed
    private void jBEliminarExpedienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarExpedienteActionPerformed
        if(jTTodosExpedientes.getSelectedRow()==-1)
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún expediente");
        }else
        {
            if (JOptionPane.showConfirmDialog(rootPane, "¿Seguro que quieres eliminar este expediente? (Una vez realizada la acción no habrá forma de recuperar los datos)",
                "Eliminar expediente del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
            int seleccion = jTTodosExpedientes.getSelectedRow();
            int IDNoExpediente = (int) jTTodosExpedientes.getValueAt(seleccion, 0);
            EliminarCasoDeLaBase(IDNoExpediente);
            EliminarExpedienteCompleto(IDNoExpediente);
            }
        }
    }//GEN-LAST:event_jBEliminarExpedienteActionPerformed
    public static void EliminarExpedienteCompleto(int NoExpediente)
    {
        try 
        {
            InetAddress localHost = InetAddress.getLocalHost();
            String IPcliente = localHost.getHostAddress();
            System.out.println("Mi ip es: " + IPcliente);
            Socket misocket = new Socket(ValoresInicialesPrograma.getIPServidor(), 9998);
            DataOutputStream flujo_salida = new DataOutputStream(misocket.getOutputStream());
            //Lo que se envia como flujo
            flujo_salida.writeUTF(IPcliente + " carpeta " + NoExpediente);
            flujo_salida.close();
            misocket.close();
        } catch (IOException ex)
        {
            System.out.println("Error!" + ex.getMessage());
        }
    }
    private void jBEliminarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarTodoActionPerformed
        if(jTableBusquedaCapturista.getSelectedRow()==-1)
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún expediente");
        }else
        {
            if (JOptionPane.showConfirmDialog(rootPane, "¿Seguro que quieres eliminar este expediente, Una vez hecha la acción no se puede deshacer? ",
                "Eliminar expediente del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
            int seleccion = jTableBusquedaCapturista.getSelectedRow();
            int IDNoExpediente = (int) jTableBusquedaCapturista.getValueAt(seleccion, 2);
            EliminarCasoDeLaBase(IDNoExpediente);
            EliminarExpedienteCompleto(IDNoExpediente);
            }    
        }
    }//GEN-LAST:event_jBEliminarTodoActionPerformed
    private void jBAgregarTipoCompraVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarTipoCompraVentaActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "Si agregar este campo, ya solo se podrá modificar en el futuro, ¿Estas de acuerdo? ",
                "Agregar un nuevo tipo de compra venta", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
        {
            String tipoCompraVenta;
            tipoCompraVenta = jTTipoCompraVenta.getText();
            ConexionMySql mysql = new ConexionMySql();
            Connection cn = mysql.getConection();
            String sSQL= "INSERT INTO tipocompraventa(TipoCompraVenta) VALUES (?)";        
            try {
                PreparedStatement pst = cn.prepareStatement(sSQL);
                pst.setString(1, tipoCompraVenta);
                int validacion = pst.executeUpdate();
                if (validacion>0) 
                {
                    JOptionPane.showMessageDialog(null, "Se a añadido el nuevo tipo de compra-venta con éxito");
                    ActualizarListaTipoCompraVenta();
                    jTTipoCompraVenta.setText("");
                    mysql.desconectar();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Hubo un error");
                    mysql.desconectar();
                }
            } catch (HeadlessException | SQLException e) 
            {
                JOptionPane.showMessageDialog(null, "ERROR! " + e);
            }
        }
    }//GEN-LAST:event_jBAgregarTipoCompraVentaActionPerformed
    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        if(jTTiposCompraVenta.getSelectedRow()==-1)
        {
            JOptionPane.showMessageDialog(null, "Se necesita seleccionar la opción que desea editar");
        }else if(jTEditarTipoCompraVenta.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Necesita teclear el nuevo nombre con el que se actualizará a esa opción");
        }else 
        {
                String tipoCompraVenta;
                tipoCompraVenta = jTEditarTipoCompraVenta.getText();
                int seleccion = jTTiposCompraVenta.getSelectedRow();
                System.out.println("Selección: " + seleccion);
                String cambiar = (String) jTTiposCompraVenta.getValueAt(seleccion, 0);
                System.out.println("Cambiar = " + cambiar);
            if (JOptionPane.showConfirmDialog(rootPane, "Si actualizas '" + cambiar + "' por '" + tipoCompraVenta + "', afectara a todos los registros creados con antrioridad, ¿Estas de acuerdo? ",
                "Habilitar actualizar tipos de compra-venta", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {              
                ConexionMySql mysql = new ConexionMySql();
                Connection cn = mysql.getConection();
                String sSQL= "UPDATE tipocompraventa SET TipoCompraVenta=? WHERE TipoCompraVenta = '" + cambiar + "'";        
                try {
                    PreparedStatement pst = cn.prepareStatement(sSQL);
                    pst.setString(1, tipoCompraVenta);
                    int validacion = pst.executeUpdate();
                    if (validacion>0) 
                    {
                        JOptionPane.showMessageDialog(null, "Actualización realizada con éxito");
                        ActualizarListaTipoCompraVenta();
                        jTEditarTipoCompraVenta.setText("");
                        mysql.desconectar();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Hubo un error");
                        mysql.desconectar();
                    }
                } catch (HeadlessException | SQLException e) 
                {
                    JOptionPane.showMessageDialog(null, "ERROR! " + e);
                }
            }
        }
    }//GEN-LAST:event_jButton14ActionPerformed
    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        if(jTTodosExpedientes.getSelectedRow()==-1)
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún expediente");
        }else
        {
            int seleccion = jTTodosExpedientes.getSelectedRow();
            int IDNoExpediente = (int) jTTodosExpedientes.getValueAt(seleccion, 0);
            AgregarArchivoExp a = new AgregarArchivoExp();
            a.AbrirAgregarArchivos(IDNoExpediente);
        }
    }//GEN-LAST:event_jButton21ActionPerformed
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        UsuarioNuevo newuser = new UsuarioNuevo();
        newuser.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if(jTUsuarios.getSelectedRow()==-1)
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún usuario para editar.");
        }else
        {
            int seleccion = jTUsuarios.getSelectedRow();
            String usuario = jTUsuarios.getValueAt(seleccion, 3).toString();
            UsuarioEditar nuevaedicion = new UsuarioEditar();

            ConexionMySql mysql = new ConexionMySql();
            Connection cn = mysql.getConection();
            String sSQL="SELECT * FROM usuarios WHERE NombreUsuario ='" + usuario + "'";
            try 
            {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sSQL);
                while(rs.next()) 
                {
                        nuevaedicion.jTNombreUsuario.setText(rs.getString("NombreUsuario"));
                        nuevaedicion.jTNombre.setText(rs.getString("Nombre"));
                        nuevaedicion.jTApellidoPaterno.setText(rs.getString("ApPaternoUsuario"));
                        nuevaedicion.jTApellidoMaterno.setText(rs.getString("ApMaternoUsuario"));
                        nuevaedicion.jPasswordField1.setText(rs.getString("Password"));
                        nuevaedicion.jCArea.setSelectedItem(ObtenerValoresUsuarios.ObtenerArea(rs.getInt("IDArea")));
                        nuevaedicion.jCEstatusUsuario.setSelectedItem(ObtenerValoresUsuarios.ObtenerEstatus(rs.getInt("IDEstatusUsuario")));
                        nuevaedicion.jCTipoUsuario.setSelectedItem(ObtenerValoresUsuarios.ObtenerTipoUsuario(rs.getInt("IDTipoUsuario")));
                }
            } catch (SQLException ex) {
                System.out.println("Error al contectar la base de datos" + ex);
            }finally
            {
                mysql.desconectar();
            }

            nuevaedicion.setVisible(true);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jCFiltroTipoExpedienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCFiltroTipoExpedienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCFiltroTipoExpedienteActionPerformed

    private void jCBUsuariosDisponiblesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBUsuariosDisponiblesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBUsuariosDisponiblesActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        if(jTTodosExpedientes.getSelectedRow()==-1)
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún expediente");
        }else
        {
            int seleccion = jTTodosExpedientes.getSelectedRow();
            int IDNoExpediente = (int) jTTodosExpedientes.getValueAt(seleccion, 0);
            EliminarArchivoExp a = new EliminarArchivoExp();
            a.AbrirEliminadorArchivos(IDNoExpediente);  
        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jTableBusquedaCapturistaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableBusquedaCapturistaMouseClicked
        if(evt.getClickCount()==2)
        {
            AbrirexpedienteBusquedaPersonalizada(0);
        }
    }//GEN-LAST:event_jTableBusquedaCapturistaMouseClicked

    private void jTTodosExpedientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTTodosExpedientesMouseClicked
        if(evt.getClickCount()==2)
        {
            AbrirexpedienteBusquedaTotal(0);
        }
    }//GEN-LAST:event_jTTodosExpedientesMouseClicked

    private void jTTiposCompraVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTTiposCompraVentaMouseClicked
        int seleccion = jTTiposCompraVenta.getSelectedRow();
        String cambiar = (String) jTTiposCompraVenta.getValueAt(seleccion, 0);
        jTEditarTipoCompraVenta.setText(cambiar);
    }//GEN-LAST:event_jTTiposCompraVentaMouseClicked

    private void jCFiltroUsuarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCFiltroUsuarioItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED)
        {
            if (this.jCFiltroUsuario.getSelectedIndex()>0 && this.jCFiltroTipoExpediente.getSelectedIndex()==0) 
            {           
                        String usuarioSeleccionado= jCFiltroUsuario.getSelectedItem().toString();
                        MostrarExpedientesPorUsuario(usuarioSeleccionado);
            }else if(this.jCFiltroUsuario.getSelectedIndex()>0 && this.jCFiltroTipoExpediente.getSelectedIndex()>0)
            {
                        String usuarioSeleccionado= jCFiltroUsuario.getSelectedItem().toString();
                        String tipoSeleccionado= jCFiltroTipoExpediente.getSelectedItem().toString();
                        MostrarExpedientesPorUsuarioYExpediente(usuarioSeleccionado, tipoSeleccionado);
            }else
            {      
                        MostrarTodosExpedientes();
            }
        }
    }//GEN-LAST:event_jCFiltroUsuarioItemStateChanged

    private void jCFiltroTipoExpedienteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCFiltroTipoExpedienteItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED)
        {
            if (this.jCFiltroTipoExpediente.getSelectedIndex()>0 && this.jCFiltroUsuario.getSelectedIndex()==0) 
            {           
                        String tipoSeleccionado= jCFiltroTipoExpediente.getSelectedItem().toString();
                        MostrarExpedientesPorTipoExpediente(tipoSeleccionado);
            }else if(this.jCFiltroUsuario.getSelectedIndex()>0 && this.jCFiltroTipoExpediente.getSelectedIndex()>0)
            {
                        String usuarioSeleccionado= jCFiltroUsuario.getSelectedItem().toString();
                        String tipoSeleccionado= jCFiltroTipoExpediente.getSelectedItem().toString();
                        MostrarExpedientesPorUsuarioYExpediente(usuarioSeleccionado, tipoSeleccionado);
            }else
            {      
                        MostrarTodosExpedientes();
            }
        }
    }//GEN-LAST:event_jCFiltroTipoExpedienteItemStateChanged
    private void MostrarExpedientesPorUsuarioYExpediente(String usuario, String expediente)
    {
        int contador = 0;
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        DefaultTableModel modeloExpedientes = new DefaultTableModel()
        {
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
        };
        jTTodosExpedientes.setRowHeight(30);
        String sSQL="SELECT casoscliente.IDNoExpediente, tipoexpediente.TipoExpediente, bitacora.FechaOrigen, bitacora.ActividadRealizada, usuarios.Nombre, usuarios.ApPaternoUsuario, usuarios.NombreUsuario FROM casoscliente INNER JOIN tipoexpediente ON casoscliente.IDTipoExpediente = tipoexpediente.IDTipoExpediente INNER JOIN bitacora ON casoscliente.IDNoExpediente = bitacora.IDNoExpediente INNER JOIN usuarios ON bitacora.IDUsuario = usuarios.IDUsuario WHERE usuarios.NombreUsuario ='" + usuario + "' AND tipoexpediente.TipoExpediente ='" + expediente + "' ORDER BY casoscliente.IDNoExpediente";
        modeloExpedientes.setColumnIdentifiers(new Object[]{"ID del Expediente","Tipo de Expediente", "Fecha de creación" ,"Actividad Realizada", "Creado por" });
        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                modeloExpedientes.addRow(new Object[]{rs.getInt("IDNoExpediente"), rs.getString("TipoExpediente"), rs.getString("FechaOrigen"), rs.getString("ActividadRealizada"), rs.getString("Nombre").concat(" "+rs.getString("ApPaternoUsuario"))});
                contador++;
            }
            jTTodosExpedientes.setModel(modeloExpedientes);
            if(contador == 0)
            {
                modeloExpedientes.addRow(new Object[]{"No hay coincidencias", " - ", " - ", " - ", " - "});
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener todos los expedientes" + ex);
        }finally
        //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
        {
            mysql.desconectar();
        }
        jTTodosExpedientes.getColumnModel().getColumn(0).setResizable(true);
        jTTodosExpedientes.getColumnModel().getColumn(1).setResizable(true);
        jTTodosExpedientes.getColumnModel().getColumn(2).setResizable(true);
        jTTodosExpedientes.getColumnModel().getColumn(3).setResizable(true);
        jTTodosExpedientes.getColumnModel().getColumn(4).setResizable(true);
    }
    private void MostrarExpedientesPorTipoExpediente(String expediente)
    {
        int contador = 0;
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        DefaultTableModel modeloExpedientes = new DefaultTableModel()
        {
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
        };
        jTTodosExpedientes.setRowHeight(30);
        String sSQL="SELECT casoscliente.IDNoExpediente, tipoexpediente.TipoExpediente, bitacora.FechaOrigen, bitacora.ActividadRealizada, usuarios.Nombre, usuarios.ApPaternoUsuario FROM casoscliente INNER JOIN tipoexpediente ON casoscliente.IDTipoExpediente = tipoexpediente.IDTipoExpediente INNER JOIN bitacora ON casoscliente.IDNoExpediente = bitacora.IDNoExpediente INNER JOIN usuarios ON bitacora.IDUsuario = usuarios.IDUsuario WHERE tipoexpediente.TipoExpediente ='" + expediente + "' ORDER BY casoscliente.IDNoExpediente";
        modeloExpedientes.setColumnIdentifiers(new Object[]{"ID del Expediente","Tipo de Expediente", "Fecha de creación" ,"Actividad Realizada", "Creado por" });
        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                modeloExpedientes.addRow(new Object[]{rs.getInt("IDNoExpediente"), rs.getString("TipoExpediente"), rs.getString("FechaOrigen"), rs.getString("ActividadRealizada"), rs.getString("Nombre").concat(" "+rs.getString("ApPaternoUsuario"))});
                contador++;
            }
            jTTodosExpedientes.setModel(modeloExpedientes);
            if(contador == 0)
            {
                modeloExpedientes.addRow(new Object[]{"NO hay este tipo de expedientes", " - ", " - ", " - ", " - "});
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener todos los expedientes" + ex);
        }finally
        //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
        {
            mysql.desconectar();
        }
        jTTodosExpedientes.getColumnModel().getColumn(0).setResizable(true);
        jTTodosExpedientes.getColumnModel().getColumn(1).setResizable(true);
        jTTodosExpedientes.getColumnModel().getColumn(2).setResizable(true);
        jTTodosExpedientes.getColumnModel().getColumn(3).setResizable(true);
        jTTodosExpedientes.getColumnModel().getColumn(4).setResizable(true);
    }
    private void MostrarExpedientesPorUsuario(String usuario)
    {
        int contador = 0;
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        DefaultTableModel modeloExpedientes = new DefaultTableModel()
        {
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
        };
        jTTodosExpedientes.setRowHeight(30);
        String sSQL="SELECT casoscliente.IDNoExpediente, tipoexpediente.TipoExpediente, bitacora.FechaOrigen, bitacora.ActividadRealizada, usuarios.Nombre, usuarios.ApPaternoUsuario, usuarios.NombreUsuario FROM casoscliente INNER JOIN tipoexpediente ON casoscliente.IDTipoExpediente = tipoexpediente.IDTipoExpediente INNER JOIN bitacora ON casoscliente.IDNoExpediente = bitacora.IDNoExpediente INNER JOIN usuarios ON bitacora.IDUsuario = usuarios.IDUsuario WHERE usuarios.NombreUsuario ='" + usuario + "' ORDER BY casoscliente.IDNoExpediente";
        modeloExpedientes.setColumnIdentifiers(new Object[]{"ID del Expediente","Tipo de Expediente", "Fecha de creación" ,"Actividad Realizada", "Creado por" });
        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                modeloExpedientes.addRow(new Object[]{rs.getInt("IDNoExpediente"), rs.getString("TipoExpediente"), rs.getString("FechaOrigen"), rs.getString("ActividadRealizada"), rs.getString("Nombre").concat(" "+rs.getString("ApPaternoUsuario"))});
                contador++;
            }
            jTTodosExpedientes.setModel(modeloExpedientes);
            if(contador == 0)
            {
                modeloExpedientes.addRow(new Object[]{"No hay datos del usuario", " - ", " - ", " - ", " - "});
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener todos los expedientes" + ex);
        }finally
        //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
        {
            mysql.desconectar();
        }
        jTTodosExpedientes.getColumnModel().getColumn(0).setResizable(true);
        jTTodosExpedientes.getColumnModel().getColumn(1).setResizable(true);
        jTTodosExpedientes.getColumnModel().getColumn(2).setResizable(true);
        jTTodosExpedientes.getColumnModel().getColumn(3).setResizable(true);
        jTTodosExpedientes.getColumnModel().getColumn(4).setResizable(true);
    }
    
    public static void ActualizarListaUsuarios()
    {
        for (int i = 0; i < jTUsuarios.getRowCount(); i++) 
                {
                    modeloUsuarios.removeRow(i);
                }
        mostarUsuarios();
    }
    public void ActualizarListaTipoCompraVenta(){
    for (int i = 0; i < jTTiposCompraVenta.getRowCount(); i++) 
                {
                    modeloTiposCompraventa.removeRow(i);
                }
    mostrarTiposCompraVenta();
    }
    public void AbrirexpedienteBusquedaPersonalizada(int edicion)
    {
        AbrirExpediente a = new AbrirExpediente();
        int seleccion = jTableBusquedaCapturista.getSelectedRow();
        int IDNoExpediente = (int) jTableBusquedaCapturista.getValueAt(seleccion, 2);
        a.RecibirDatos(IDNoExpediente, edicion);
    }
    public void AbrirexpedienteBusquedaTotal(int edicion)
    {
        AbrirExpediente b = new AbrirExpediente();
        int seleccion = jTTodosExpedientes.getSelectedRow();
        int IDNoExpediente = (int) jTTodosExpedientes.getValueAt(seleccion, 0);
        b.RecibirDatos(IDNoExpediente, edicion);
    }
        /**
     * @param args Clase principal del Frame
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            new Principal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBVerExpedientes;
    private javax.swing.JLabel Logo;
    private javax.swing.JButton jBAbrirTodos;
    private javax.swing.JButton jBActualizarPassword;
    private javax.swing.JButton jBAgregarTipoCompraVenta;
    private javax.swing.JButton jBCerrarSesion;
    private javax.swing.JButton jBEditarTodos;
    private javax.swing.JButton jBEliminarExpediente;
    private javax.swing.JButton jBEliminarTodo;
    private javax.swing.JButton jBGenerarReporte;
    private javax.swing.JButton jBIndexar;
    private javax.swing.JButton jBMisEstadisticas;
    private javax.swing.JButton jBRegresarDeVerExpedientes;
    private javax.swing.JButton jBRegresarDeVerExpedientes1;
    private javax.swing.JButton jBVerExpedientes;
    private javax.swing.JButton jBuscar;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jCBUsuariosDisponibles;
    private javax.swing.JComboBox<String> jCFiltroTipoExpediente;
    private javax.swing.JComboBox<String> jCFiltroUsuario;
    private javax.swing.JLabel jLInstruccionesTodosExpedientes;
    private javax.swing.JLabel jLInstruccionesTodosExpedientes1;
    private javax.swing.JLabel jLPasswordActual;
    private javax.swing.JLabel jLPasswordConfirmacion;
    private javax.swing.JLabel jLPasswordNuevo;
    private javax.swing.JLabel jLReporteUsuario;
    private javax.swing.JLabel jLTituloCambioPassword;
    private javax.swing.JLabel jLTituloPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPAdmUsuarios;
    private javax.swing.JPanel jPAdministrador;
    private javax.swing.JPanel jPBusquedaComoCapturista;
    private javax.swing.JPanel jPCambioContraseña;
    private javax.swing.JPanel jPCapturista;
    private javax.swing.JPanel jPContenedor;
    private javax.swing.JPanel jPEncabezado;
    private javax.swing.JPanel jPEstadiscasGenerales;
    private javax.swing.JPanel jPEstadisticas;
    private javax.swing.JPanel jPEstadisticasIndividuales;
    private javax.swing.JPanel jPEstadisticasPrincipal;
    private javax.swing.JPanel jPMenuCapturista;
    private javax.swing.JPanel jPMiPerfil;
    private javax.swing.JPanel jPMisEstadisticas;
    private javax.swing.JPasswordField jPPasswordActual;
    private javax.swing.JPasswordField jPPasswordConfirmacion;
    private javax.swing.JPasswordField jPPasswordNuevo;
    private javax.swing.JPanel jPReportes;
    public static javax.swing.JPanel jPVerExpedientes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTAdministrador;
    public static javax.swing.JTextField jTBuscar;
    private javax.swing.JTextField jTEditarTipoCompraVenta;
    private javax.swing.JTable jTMiResumen;
    private javax.swing.JTable jTMisEstadisticas;
    private javax.swing.JTabbedPane jTPSeleccion;
    private javax.swing.JTable jTResumenIndividual1;
    private javax.swing.JTable jTResumenIndividual2;
    private javax.swing.JTable jTResumenIndividual3;
    private javax.swing.JTextField jTTipoCompraVenta;
    private javax.swing.JTable jTTiposCompraVenta;
    private javax.swing.JTable jTTodosExpedientes;
    public static javax.swing.JTable jTUsuarios;
    public static javax.swing.JTable jTableBusquedaCapturista;
    public javax.swing.JLabel jUsuario;
    private javax.swing.JButton jlIconoBuscar;
    // End of variables declaration//GEN-END:variables
    public static void EliminarCasoDeLaBase(int NombreCarpeta)
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        String sSQL = "DELETE FROM casoscliente WHERE IDNoExpediente = '" + NombreCarpeta + "'";
        try 
        {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            int validacion = pst.executeUpdate();
            if (validacion>0) 
            {
                System.out.println("");  
                mysql.desconectar();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "¡Hubo un error al eliminar el expediente " + NombreCarpeta + "!");
            }
        } catch (HeadlessException | SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "ERROR! " + e);
        }
    }
    public static int ComprobarExpedienteEnBD(int NombreCarpeta)
    {
            ConexionMySql mysql = new ConexionMySql();
            Connection cn = mysql.getConection();
            int resultado = 0;
            String sSQL="SELECT * FROM casoscliente WHERE IDNoExpediente = '" + NombreCarpeta + "'";
            try 
            {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sSQL);
                if (rs.next()) 
                {
                   resultado=1;
                }
            } catch (SQLException ex) {
                System.out.println("Error al contectar la base de datos" + ex);
            }finally
            {
                mysql.desconectar();
            }
        return resultado;
    }
        @Override
    public void run() 
    {
        System.out.println("Listo para escuchar notificaciones del servidor");
        try 
        {
            ServerSocket servidor2 = new ServerSocket(ValoresInicialesPrograma.getPuertoEntradaInformacionServidor());
            while (true) 
            {                
                Socket misocket2 = servidor2.accept();
                System.out.println("Solicitud del cliente aceptada");
                //Flujo de entrada
                DataInputStream flujo_entradacliente = new DataInputStream(misocket2.getInputStream());
                String mensaje_textodevuelto = flujo_entradacliente.readUTF();               
                JOptionPane.showMessageDialog(null, mensaje_textodevuelto);
                misocket2.close();
                flujo_entradacliente.close();
                MostrarTodosExpedientes();
            }
        } catch (IOException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
