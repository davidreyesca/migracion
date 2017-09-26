package Controlador;

import Vista.AgregarNuevoArchivo;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class IndexarUnicoArchivo 
{
    public static void copiarAlServidor()
    {
        try 
        {
        Socket cliente = new Socket(ValoresInicialesPrograma.getIPServidor(), ValoresInicialesPrograma.getPuertoSalidaArchivosCompresos());
        ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());
        FileInputStream file = new FileInputStream(ValoresInicialesPrograma.getFicheroZipLocal());
        byte[] buf = new byte[4096];
        while (true)
        {
            int len = file.read(buf);
            if (len==-1) 
            {
                System.out.println("Se han pasado los datos");
                break;
            }
            //len = número de bytes a escribir, 0= de donde empieza, buf= de donde sacará los datos
            out.write(buf, 0, len);
        }
        out.close();
        file.close();
        EliminarContenidoCarpetas.iniciar(ValoresInicialesPrograma.getCarpetaArchivosLocal());
        ValoresInicialesPrograma.getfolderLocal().mkdirs();
        AgregarNuevoArchivo.AgregarABD(true);
        } catch (IOException ex)
        {
        System.out.println("Hubo un problema, sorry");
            JOptionPane.showMessageDialog(null, "¡El servidor no esta en linea, comunicate con el administrador del servidor!");
            EliminarContenidoCarpetas.iniciar(ValoresInicialesPrograma.getCarpetaArchivosLocal());
            ValoresInicialesPrograma.getfolderLocal().mkdirs();
        }
        finally
        {
            EliminarZip eliminar = new EliminarZip();     
        }
    }
    
}
