/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class AgregarArchivoAlServidor 
{
    public static void ELiminarArchivosServer(String NoExpediente)
    {
                try 
                {
                    InetAddress localHost = InetAddress.getLocalHost();
                    String IPcliente = localHost.getHostAddress();
                    System.out.println("Mi ip es: " + IPcliente);
                    Socket misocket = new Socket(ValoresInicialesPrograma.getIPServidor(), 9998);
                    DataOutputStream flujo_salida = new DataOutputStream(misocket.getOutputStream());
                    //Lo que se envia como flujo
                    flujo_salida.writeUTF(IPcliente + " mover " + NoExpediente);
                    flujo_salida.close();
                    misocket.close();
                } catch (IOException ex)
                {
                    System.out.println("Error!" + ex.getMessage());
                    JOptionPane.showMessageDialog(null, "El servidor esta fuera de linea, por favor comunicate con el administrador");
                }
    }
    
}
