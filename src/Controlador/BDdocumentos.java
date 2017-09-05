
package Controlador;

import Vista.EleccionTipoExpediente;
import Vista.IndexarDocumentos;


public class BDdocumentos 
{   
    static private int NoExpediente;
    static private String[] NombresArchivos;
    static private String[] carpetaServidor;

    public static String[] getNombresArchivos() 
    {
        return NombresArchivos;
    }
    public static String[] getCarpetaServidor() 
    {
        return carpetaServidor;
    }
    public static int getNoExpediente() 
    {
        return NoExpediente;
    }
    public static void setCarpetaServidor(String[] carpetaServidor) 
    {
        BDdocumentos.carpetaServidor = carpetaServidor;
    }
    public static void setNombresArchivos(String[] NombresArchivos) 
    {
        BDdocumentos.NombresArchivos = NombresArchivos;
    }
    public static void setNoExpediente(int NoExpediente) 
    {
        BDdocumentos.NoExpediente = NoExpediente;
    }
    

    static IndexarDocumentos indexar;
    static EleccionTipoExpediente eleccion;
    
    public void inicioIndexar()
    {
        indexar = new IndexarDocumentos();
        indexar.setVisible(true);
    }
    public static void continuarOperacion()
    {
        indexar.dispose();
        EleccionTipoarchivo();
    }
    
    public static void cerrarOperacion()
    {
        indexar.dispose();
    }
    public static void EleccionTipoarchivo()
    {
        eleccion = new EleccionTipoExpediente();
        EleccionTipoExpediente.jLNoExpediente.setText("No. Expediente: " + NoExpediente);
        eleccion.setVisible(true);
    }
    public static void ocultarOperacionEleccionTipo()
    {
        eleccion.setVisible(false);
    }
    public static void FinalizacionCompraVentaCorrecta()
    {
        eleccion.dispose();
    }
    public static void FinalizacionDonacionCorrecta()
    {
        eleccion.dispose();
    }
    public static void FinalizacionCancelacionHipotecaCorrecta()
    {
        eleccion.dispose();
    }
    public static void FinalizacionAperturaCreditoCorrecta()
    {
        eleccion.dispose();
    }
    public static void FinalizacionPoderesCorrecta()
    {
        eleccion.dispose();
    }
    public static void FinalizacionTestamentosCorrecta()
    {
        eleccion.dispose();
    }
    public static void FinalizacionCertificacionesCorrecta()
    {
        eleccion.dispose();
    }
    public static void FinalizacionOtrosCorrecta()
    {
        eleccion.dispose();
    }
}
