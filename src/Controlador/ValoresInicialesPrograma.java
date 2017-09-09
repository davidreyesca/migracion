package Controlador;

import java.io.File;
/**
 * Clase encargada de verificar que las rutas necesarias para el funcionamiento del sistema existan, y si no se encarga de crearlas.
 * @author David Reyes
 */

public class ValoresInicialesPrograma 
{
    final static private String CarpetaArchivosLocal = "C:\\Users\\Pc\\Documents\\EBPConsultores\\Temporales";
    final static private String CarpetaArchivosLocalCompresos = "C:\\Users\\Pc\\Documents\\EBPConsultores\\Enviar";
    final static private String CarpetaArchivosLocalLectura = "C:\\Users\\Pc\\Documents\\EBPConsultores\\Lectura";
    final static File folderLocal = new File(CarpetaArchivosLocal);
    final static File folderLocalCompresos = new File(CarpetaArchivosLocalCompresos);
    final static File folderLocalLectura = new File(CarpetaArchivosLocalLectura);
    final static String FicheroZipLocal= CarpetaArchivosLocalCompresos + "\\compreso.zip";
    final static String CarpetaCopiaTemporalArchivos= CarpetaArchivosLocal;
    
    final static int PuertoSalidaArchivosCompresos= 9999;
    final static int PuertoEntradaInformacionServidor= 9090;
    
    final static String IPServidor="localhost";
    final static String CarpetaArchivosServidor = "http://localhost//";
    
//    final static String IPServidor="192.168.1.81";
//    final static String CarpetaArchivosServidor = "http://192.168.1.81//";
    


    /**
     * Getters publicos del sistema.
     * @return CarpetaArchivosServidor
     */
    public static int getPuertoSalidaArchivosCompresos() {
        return PuertoSalidaArchivosCompresos;
    }

    public static String getIPServidor() {
        return IPServidor;
    }
    public static int getPuertoEntradaInformacionServidor() {
        return PuertoEntradaInformacionServidor;
    }
    public static String getCarpetaArchivosServidor() 
    {
        return CarpetaArchivosServidor;
    }
    public static File getfolderLocal() 
    {
        return folderLocal;
    }
    public static File getfolderLocalLectura() 
    {
        return folderLocalLectura;
    }
    public static String getCarpetaArchivosLocalLectura() 
    {
        return CarpetaArchivosLocalLectura;
    }
    public static String getCarpetaArchivosLocal() 
    {
        return CarpetaArchivosLocal;
    }
    public static String getCarpetaCopiaTemporalArchivos() 
    {
        return CarpetaCopiaTemporalArchivos;
    }
    public static String getFicheroZipLocal() 
    {
        return FicheroZipLocal;
    }
    
    /**
     * Metodo encargado de verificar que existan los directorios, o crearlos.
     */
    public static void ValidandoCreacionCarpetas() 
    {
        boolean validacion = false;
        if (folderLocal.isDirectory()) 
        {
            System.out.println("Todo bien, puede continuar, su folder local existe");
        }
        else
        {
            folderLocal.mkdirs();
            validacion = true;
        }
        if (folderLocalCompresos.isDirectory()) 
        {
            System.out.println("Todo bien, puede continuar, su folder compresos existe");
        }
        else
        {
            if(validacion==true)
            {
                folderLocalCompresos.mkdirs();
            }
            else
            {
                folderLocalCompresos.mkdirs();
            }  
        }
        if (folderLocalLectura.isDirectory()) 
        {
            System.out.println("Todo bien, puede continuar, su folder lectura existe");
        }
        else
        {
             folderLocalLectura.mkdirs();
        }
    }
    
    public static void LimpiandoArea() 
    {
        EliminarContenidoCarpetas.iniciar(CarpetaArchivosLocalLectura + "\\");
        EliminarContenidoCarpetas.iniciar(CarpetaArchivosLocal + "\\");
        EliminarContenidoCarpetas.iniciar(CarpetaArchivosLocalCompresos + "\\");
    }
}
