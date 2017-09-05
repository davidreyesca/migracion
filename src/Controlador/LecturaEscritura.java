package Controlador;

import Vista.IndexarDocumentos;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class LecturaEscritura 
{
    static String[] URLsCopias;
    static String[] URLsServidor;
    static String[] NombresArchivosServidor;
    static int numeroCopias=0;
    static int NoExpe;
    static String direccion;    
    public static void setDireccion(String direccion)
    {
        LecturaEscritura.direccion=direccion;
    }
    public static void Obtenerdatos(String[]URLs) throws Exception
    {
        NoExpe = BDdocumentos.getNoExpediente();
        URLsCopias = URLs;
        int tamañoArreglo = URLsCopias.length;
        URLsServidor = new String[tamañoArreglo];
        NombresArchivosServidor = new String[tamañoArreglo];
        for (int i = 0; i < tamañoArreglo; i++) 
        {           
            URLsServidor[i]= ValoresInicialesPrograma.getCarpetaArchivosServidor() + NoExpe + "//";
        }
        for (int i = 0; i < tamañoArreglo; i++) 
        {   
            File fichero = new File(URLsCopias[i]);
            String nombreArchivoNuevo = fichero.getName();
            NombresArchivosServidor[i]=nombreArchivoNuevo;
            URLsServidor[i]=URLsServidor[i]+nombreArchivoNuevo;
        }
        for (int i = 0; i < tamañoArreglo; i++) 
        {           
            copiarFichero(URLsCopias[i]);
            numeroCopias++;
        }
        System.out.println("Ya se envio a comprimir la carpeta");
        BDdocumentos.setCarpetaServidor(URLsServidor);
        BDdocumentos.setNombresArchivos(NombresArchivosServidor);
        IndexarDocumentos.jInformacionActual.setText("Ya se envio a comprimir la carpeta");
        ComprimirDirectorio.HacerZIP(direccion, ValoresInicialesPrograma.getFicheroZipLocal());
    }

    static public void copiarFichero(String URL)
    {
        String URLFinal = URL;
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
        System.out.println("Tamaño del archivo en bytes: " +tamaño );
        IndexarDocumentos.jInformacionActual.setText("Tamaño del archivo a copiar: " + tamaño);
        crearFichero(datosEntrada, nombreArchivoNuevo);
    }
    
    static void crearFichero(int datosNuevoFichero[], String NombreFichero)
    {
        try 
        {
            //Se especifica el fichero donde será la salida del nuevo fichero y se crea el fichero con FileOutputStream
            FileOutputStream ficheroNuevo = new FileOutputStream(direccion + NombreFichero);
            //Con un ciclo for vamos escribiendo el fichero byte a byte con ayuda del array pasado desde el main
            for(int i=0; i<datosNuevoFichero.length; i++)
            {
                ficheroNuevo.write(datosNuevoFichero[i]);
            }
            System.out.println("Se ha terminado la copia del fichero" + NombreFichero);
            IndexarDocumentos.jInformacionActual.setText("Se ha terminado la copia del fichero" + NombreFichero);
            //Cerramos la comunicación del nuevo fichero para indicar que se ha terminado
            ficheroNuevo.close();         
        } catch (IOException e) 
        {
            System.out.println("Error al crear el archivo");
        }
    }
}
