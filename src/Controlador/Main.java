/**
Esta es la clase principal del todo el modulo de migración.
 */
package Controlador;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * En esta clase se encuentra el metodo main, que es la encargada de crear la vista del login que aparecera.
 */
public class Main {       
    /**
     * Se utiliza la clase ValoresInicialesPrograma, para verificar que el sistema vaya a tener las carpetas necesarias para su ejecución,
     * y en caso de no tenerlas, las crea en ese momento.
     */
    static Vista.LogIn sesion = new Vista.LogIn();
    public static void main(String[] args) 
    {
        try {
            // Codigo para tener vista según el sistema operativo.
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        sesion.setVisible(true);
        ValoresInicialesPrograma.LimpiandoArea();
        ValoresInicialesPrograma.ValidandoCreacionCarpetas();
    }
}
