
package Controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author David
 */
public class CopiarLocalArchivo 
{
    static public void crearCarpetaTemporalEnvio(String URL) throws Exception
    {
        File folder = new File(ValoresInicialesPrograma.getCarpetaArchivosLocal() + "\\Nuevo");
        if(folder.mkdir())
        {
            System.out.println("Se ha creado la carpeta con exito.");
            copiarFichero(URL);
        }else
        {   
            System.out.println("ERROR! , hubo un problema al crear la carpeta.");
        }
    }
    
    static public void copiarFichero(String URL) throws Exception
    {
        File fichero = new File(URL);
        String nombreArchivoNuevo = fichero.getName();
        int tamaño = (int) fichero.length();
        int contador=0;
        int datosEntrada[]= new int[tamaño+1];
        try 
        {
            FileInputStream archivo_lectura = new FileInputStream(URL);
            boolean final_ar = false;
            while (!final_ar) 
            {               
                //archivo_lectura.read() devolverá un -1 cuando haya terminado de leer el archivo
                int bye_entrada= archivo_lectura.read();
                //Si no encuentra ese -1 guardara en el arregla datosEntrada lo que vaya pasando en el contador
                if (bye_entrada!=-1) 
                {
                    datosEntrada[contador]=bye_entrada;
                }
                //Si encuentra el -1, significará que ha acabado de leer el archivo, y de haberse guardado en el array
                else
                {
                    final_ar=true;
                }
//                System.out.println(datosEntrada[contador]);
                contador++;
            }
            archivo_lectura.close();
        } catch (Exception e) 
        {            
            System.out.println("Hubo un problema al tratar de encontrar el archivo");
        }
        System.out.println("Tamaño del archivo en bytes: " + tamaño);
        crearFichero(datosEntrada, nombreArchivoNuevo);
    }
    
    static void crearFichero(int datosNuevoFichero[], String NombreFichero) throws Exception
    {
        String direccion = ValoresInicialesPrograma.getCarpetaArchivosLocal() + "\\Nuevo\\";
        try 
        {
            //Se especifica el fichero donde será la salida del nuevo fichero y se crea el fichero con FileOutputStream
            FileOutputStream ficheroNuevo = new FileOutputStream(ValoresInicialesPrograma.getCarpetaArchivosLocal() + "\\Nuevo\\" + NombreFichero);
            //Con un ciclo for vamos escribiendo el fichero byte a byte con ayuda del array pasado desde el main
            for(int i=0; i<datosNuevoFichero.length; i++)
            {
                ficheroNuevo.write(datosNuevoFichero[i]);
            }
            System.out.println("Se ha terminado la copia del fichero" + NombreFichero);
            //Cerramos la comunicación del nuevo fichero para indicar que se ha terminado
            ficheroNuevo.close();  
//            ComprimirDirectorio.HacerZIP(direccion, ValoresInicialesPrograma.getFicheroZipLocal());
            ComprimirDirectorioYArchivo.HacerZIP(direccion, ValoresInicialesPrograma.getFicheroZipLocal());
        } catch (IOException e) 
        {
            System.out.println("Error al crear el archivo");
        }
    }
}
