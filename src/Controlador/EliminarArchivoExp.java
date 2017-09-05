package Controlador;

import Vista.EliminarArchivoExpediente;


public class EliminarArchivoExp 
{
    static int NoExpediente;

    public static int getNoExpediente() 
    {
        return NoExpediente;
    }

    public void AbrirEliminadorArchivos(int NoExpediente) 
    {
        this.NoExpediente = NoExpediente;
        System.out.println("ID es: " + this.NoExpediente );
        EliminarArchivoExpediente ventana = new EliminarArchivoExpediente();
        ventana.setVisible(true);
    }
}
