package Controlador;

import Vista.AgregarNuevoArchivo;

/**
 *
 * @author David
 */
public class AgregarArchivoExp 
{
    static int NoExpediente;
    
    public static int getNoExpediente() 
    {
        return NoExpediente;
    }
    
    public void AbrirAgregarArchivos(int NoExpediente) 
    {
        this.NoExpediente = NoExpediente;
        System.out.println("ID es: " + this.NoExpediente );
        AgregarNuevoArchivo ventana = new AgregarNuevoArchivo();
        ventana.setVisible(true);
    }
}
