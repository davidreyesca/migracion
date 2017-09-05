package Controlador;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class AbrirExpediente 
{
    static int NoExpedinte, tipoExpediente, Editar;
   
    public static int getNoExpedinte() {
        return NoExpedinte;
    }

    public static int getTipoExpediente() {
        return tipoExpediente;
    }

    public static int getEditar() {
        return Editar;
    }
    
    public void RecibirDatos(int NoExp, int edicion)
    {
        Editar=edicion;
        ObternerTipoExpediente(NoExp);
    }
 
    private void ObternerTipoExpediente(int NoExpediente)
    {
        ConexionMySql mysql = new ConexionMySql();
        Connection cn = mysql.getConection();
        String sSQL="SELECT * FROM casoscliente WHERE IDNoExpediente ='" + NoExpediente + "'";
        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {   
                    tipoExpediente= rs.getInt("IDTipoExpediente");
            }
            this.NoExpedinte = NoExpediente;
            EleccionApertura();
        } catch (SQLException ex) {
            System.out.println("Error los datos del expedientes" + ex);
        }finally
        //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
        {
            mysql.desconectar();
        }
    }
    private void EleccionApertura()
    {
        switch (tipoExpediente) 
        {
            case 1:
                AbrirCompraVenta();
            break;
            case 2:
                AbrirDonacion();
            break;
            case 3:
                AbrirCancelacionHipoteca();
            break;
            case 4:
                AbrirAperturaCredito();
            break;
            case 5:
                AbrirPoderes();
            break;
            case 6:
                AbrirTestamentos();
            break;
            case 7:
                AbrirCertificacion();
            break;
            case 8:
                AbrirOtros();
            break;
            default:
            break;
        }
    }
    private void AbrirCompraVenta()
    {
        AbrirCompraVenta abrircompraventa = new AbrirCompraVenta();
        abrircompraventa.ObtenerDatos();
    }
    private void AbrirCancelacionHipoteca()
    {
        AbrirCancelacionHipoteca abrircancelacionhipoteca = new AbrirCancelacionHipoteca();
        abrircancelacionhipoteca.ObtenerDatos();
    }
    private void AbrirTestamentos()
    {
        AbrirTestamentos abrirtestamentos= new AbrirTestamentos();
        abrirtestamentos.ObtenerDatos();
    }
    private void AbrirPoderes()
    {
        AbrirPoderes abrirpoderes= new AbrirPoderes();
        abrirpoderes.ObtenerDatos();
    }
    private void AbrirDonacion()
    {
        AbrirDonacion abrirdonacion= new AbrirDonacion();
        abrirdonacion.ObtenerDatos();
    }
    private void AbrirAperturaCredito()
    {
        AbrirAperturaCredito abriraperturacredito= new AbrirAperturaCredito();
        abriraperturacredito.ObtenerDatos();
    }
    private void AbrirCertificacion()
    {
         AbrirCertificacion abrircertificacion = new AbrirCertificacion();
         abrircertificacion.ObtenerDatos();
    }
    private void AbrirOtros()
    {
        AbrirOtros abrirotros = new AbrirOtros();
        abrirotros.ObtenerDatos();
    }
}
