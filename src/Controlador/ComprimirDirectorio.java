package Controlador;

import Vista.IndexarDocumentos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ComprimirDirectorio 
{
    static String origen;
    static String destino;
    static void HacerZIP(String origen, String destino) throws Exception
    {
        System.out.println("Empezando a comprimir");
        IndexarDocumentos.jInformacionActual.setText("Empezando la compresión para el envio de los datos...");

        //Carpeta que queremos comprimir
        ComprimirDirectorio.origen = origen;
        //Carpeta compresa en el directorio especificado
        ComprimirDirectorio.destino = destino;
        comprimir_carpeta(ComprimirDirectorio.origen,ComprimirDirectorio.destino);
        IndexarDocumentos.jInformacionActual.setText("Compresión finalizada...");
        System.out.println("Carpeta comprimida");
        ClienteIndexar.copiarAlServidor();
        //Aquí se tiene que eliminar lo que hay en la carpeta de temporal

    }
    
    static void comprimir_carpeta(String origen, String destino) throws Exception 
    {
      ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(destino));
      añadir_carpeta_al_zip("", origen, zip); 
      zip.flush();
      zip.close();
   }

  static void añadir_archivo_al_zip(String path, String fichero, ZipOutputStream zip)throws Exception 
  {
    File archivo = new File(fichero);    
    if (archivo.isDirectory()) 
    {
      añadir_carpeta_al_zip(path, fichero, zip);     
    } 
    else 
    {      
      byte [] buffer = new byte[4096];
      int leido;
      FileInputStream entrada = new FileInputStream(archivo);
      zip.putNextEntry(new ZipEntry(path + "/" + archivo.getName()));
        while ((leido = entrada.read(buffer)) > 0) 
        {
          zip.write(buffer, 0, leido);
        }
     entrada.close();
    }
  }

  static void añadir_carpeta_al_zip(String path, String carpeta, ZipOutputStream zip)throws Exception 
  {
    File directorio = new File(carpeta);
    for (String nombre_archivo : directorio.list()) 
    {
      if (path.equals("")) 
      {
        añadir_archivo_al_zip(directorio.getName(), carpeta + "/" + nombre_archivo, zip);    
      } else 
      {
        añadir_archivo_al_zip(path + "/" + directorio.getName(), carpeta + "/" + nombre_archivo, zip);
      }
    }
  }
}
