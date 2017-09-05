package Controlador;

import java.io.File; 

/**
 *
 * @author Pc
 */

public class EliminarContenidoCarpetas 
{
 public static void iniciar(String ruta) 
 {
        funcionEliminarCarpeta (new File (ruta));  
 }

private static void funcionEliminarCarpeta(File pArchivo) 
{
    if (!pArchivo.exists()) 
    {
        return;
    }
    if (pArchivo.isDirectory()) 
    {
        for (File f : pArchivo.listFiles()) 
        {
            funcionEliminarCarpeta(f);  
        }
    }
    pArchivo.delete();
}
}