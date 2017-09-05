
package Controlador;

import java.io.File;

public class EliminarZip 
{
    public EliminarZip()
    {
        File fichero = new File(ValoresInicialesPrograma.getFicheroZipLocal());
        if (fichero.delete())
        {
        System.out.println("El fichero ha sido borrado satisfactoriamente");
        }
        else
        {
        System.out.println("El fichero no puede ser borrado");
        }
    }
    public static void main(String[] args) 
    {
        new EliminarZip();
    }  
}