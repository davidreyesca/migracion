package Controlador;

import Vista.Principal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author David Reyes
 */
public class Busqueda 
{
    public static void ResultadosCapturista()
    {
    Principal.LimpiarTablaCapturista();
    String busqueda = Principal.jTBuscar.getText();
    ConexionMySql mysql = new ConexionMySql();
    Connection cn = mysql.getConection();
    String sSQL= "SELECT IDNoExpediente, tipoexpediente.TipoExpediente, IDCliente registro FROM casoscliente"
            + "	INNER JOIN tipoexpediente ON tipoexpediente.IDTipoExpediente = casoscliente.IDTipoExpediente"
            + "	WHERE IDNoExpediente LIKE '%" + busqueda + "%'"
            + " UNION "
            + "SELECT cvv.IDNoExpediente, tipoexpediente.TipoExpediente, CONCAT(cvv.NombreVendedor,' ',cvv.ApPaternoVendedor,' ',cvv.ApMaternoVendedor) registro FROM compraventavendedores cvv"
            + "	INNER JOIN casoscliente ON casoscliente.IDNoExpediente = cvv.IDNoExpediente"
            + "	INNER JOIN tipoexpediente ON tipoexpediente.IDTipoExpediente = casoscliente.IDTipoExpediente"
            + "	WHERE"
            + "	CONCAT(cvv.NombreVendedor,' ',cvv.ApPaternoVendedor,' ',cvv.ApMaternoVendedor) LIKE '%" + busqueda + "%'"
            + " UNION "
            + "SELECT cvc.IDNoExpediente, tipoexpediente.TipoExpediente, CONCAT(cvc.NombreComprador,' ',cvc.ApPaternoComprador,' ',cvc.ApMaternoComprador) registro FROM compraventacompradores cvc"
            + "	INNER JOIN casoscliente ON casoscliente.IDNoExpediente = cvc.IDNoExpediente"
            + "	INNER JOIN tipoexpediente ON tipoexpediente.IDTipoExpediente = casoscliente.IDTipoExpediente"
            + "	WHERE "
            + "	CONCAT(cvc.NombreComprador,' ',cvc.ApPaternoComprador,' ',cvc.ApMaternoComprador) LIKE '%" + busqueda + "%'"
            + " UNION "
            + "SELECT cv.IDNoExpediente, tipoexpediente.TipoExpediente, tipocompraventa.TipoCompraVenta registro FROM compraventa cv"
            + "	INNER JOIN tipocompraventa ON tipocompraventa.IDTipoCompraVenta = cv.IDTipoCompraVenta"
            + "	INNER JOIN casoscliente ON casoscliente.IDNoExpediente = cv.IDNoExpediente"
            + "	INNER JOIN tipoexpediente ON tipoexpediente.IDTipoExpediente = casoscliente.IDTipoExpediente"
            + "	WHERE"
            + "	tipocompraventa.TipoCompraVenta LIKE '%" + busqueda + "%'"
            + " UNION "
            + "SELECT cv.IDNoExpediente, tipoexpediente.TipoExpediente, cv.FolioReal registro FROM compraventa cv"
            + "	INNER JOIN casoscliente ON casoscliente.IDNoExpediente = cv.IDNoExpediente"
            + "	INNER JOIN tipoexpediente ON tipoexpediente.IDTipoExpediente = casoscliente.IDTipoExpediente"
            + "	WHERE"
            + "	cv.FolioReal LIKE '%" + busqueda + "%'"
            + " UNION "
            + "SELECT cv.IDNoExpediente, tipoexpediente.TipoExpediente, cv.Instrumento registro FROM compraventa cv"
            + "	INNER JOIN casoscliente ON casoscliente.IDNoExpediente = cv.IDNoExpediente"
            + "	INNER JOIN tipoexpediente ON tipoexpediente.IDTipoExpediente = casoscliente.IDTipoExpediente"
            + "	WHERE"
            + "	cv.Instrumento LIKE '%" + busqueda + "%'"
            + " UNION "
            + "SELECT cv.IDNoExpediente, tipoexpediente.TipoExpediente, cv.Tomo registro FROM compraventa cv"
            + "	INNER JOIN casoscliente ON casoscliente.IDNoExpediente = cv.IDNoExpediente"
            + "	INNER JOIN tipoexpediente ON tipoexpediente.IDTipoExpediente = casoscliente.IDTipoExpediente"
            + "	WHERE"
            + "	cv.Tomo LIKE '%" + busqueda + "%'"
            + " UNION "
            + "SELECT cn.IDNoExpediente, tipoexpediente.TipoExpediente, CONCAT(cn.NombreNombre,' ',cn.ApPaternoNombre,' ',cn.ApMaternoNombre) registro FROM certificacionesnombres  cn "
            + "	INNER JOIN casoscliente ON casoscliente.IDNoExpediente = cn.IDNoExpediente"
            + "	INNER JOIN tipoexpediente ON tipoexpediente.IDTipoExpediente = casoscliente.IDTipoExpediente"
            + "	WHERE "
            + "	CONCAT(cn.NombreNombre,' ',cn.ApPaternoNombre,' ',cn.ApMaternoNombre) LIKE '%" + busqueda + "%'"
            + " UNION "
            + "SELECT ca.IDNoExpediente, tipoexpediente.TipoExpediente, CONCAT(ca.NombreAcreditado,' ',ca.ApPaternoAcreditado,' ',ca.ApMaternoAcreditado) registro FROM cancelacionhipotecaacreditado ca "
            + "	INNER JOIN casoscliente ON casoscliente.IDNoExpediente = ca.IDNoExpediente"
            + "	INNER JOIN tipoexpediente ON tipoexpediente.IDTipoExpediente = casoscliente.IDTipoExpediente"
            + "	WHERE "
            + "	CONCAT(ca.NombreAcreditado,' ',ca.ApPaternoAcreditado,' ',ca.ApMaternoAcreditado) LIKE '%" + busqueda + "%'"
            + " UNION "
            + "SELECT cac.IDNoExpediente, tipoexpediente.TipoExpediente, CONCAT(cac.NombreAcreditante,' ',cac.ApPaternoAcreditante,' ',cac.ApMaternoAcreditante) registro FROM cancelacionhipotecaacreditante cac "
            + "	INNER JOIN casoscliente ON casoscliente.IDNoExpediente = cac.IDNoExpediente"
            + "	INNER JOIN tipoexpediente ON tipoexpediente.IDTipoExpediente = casoscliente.IDTipoExpediente"
            + "	WHERE "
            + "	CONCAT(cac.NombreAcreditante,' ',cac.ApPaternoAcreditante,' ',cac.ApMaternoAcreditante) LIKE '%" + busqueda + "%'"
            + " UNION "
            + "SELECT ac.IDNoExpediente, tipoexpediente.TipoExpediente, CONCAT(ac.NombreAcreditado,' ',ac.ApPaternoAcreditado,' ',ac.ApMaternoAcreditado) registro FROM aperturacreditoacreditado ac "
            + "	INNER JOIN casoscliente ON casoscliente.IDNoExpediente = ac.IDNoExpediente"
            + "	INNER JOIN tipoexpediente ON tipoexpediente.IDTipoExpediente = casoscliente.IDTipoExpediente"
            + "	WHERE "
            + "	CONCAT(ac.NombreAcreditado,' ',ac.ApPaternoAcreditado,' ',ac.ApMaternoAcreditado) LIKE '%" + busqueda + "%'"
            + " UNION "
            + "SELECT aca.IDNoExpediente, tipoexpediente.TipoExpediente, CONCAT(aca.NombreAcreditante,' ',aca.ApPaternoAcreditante,' ',aca.ApMaternoAcreditante) registro FROM aperturacreditoacreditante aca "
            + "	INNER JOIN casoscliente ON casoscliente.IDNoExpediente = aca.IDNoExpediente"
            + "	INNER JOIN tipoexpediente ON tipoexpediente.IDTipoExpediente = casoscliente.IDTipoExpediente"
            + "	WHERE "
            + "	CONCAT(aca.NombreAcreditante,' ',aca.ApPaternoAcreditante,' ',aca.ApMaternoAcreditante) LIKE '%" + busqueda + "%'"
            + " UNION "
            + "SELECT dd.IDNoExpediente, tipoexpediente.TipoExpediente, CONCAT(dd.NombreDonante,' ',dd.ApPaternoDonante,' ',dd.ApMaternoDonante) registro FROM donaciondonantes dd "
            + "	INNER JOIN casoscliente ON casoscliente.IDNoExpediente = dd.IDNoExpediente"
            + "	INNER JOIN tipoexpediente ON tipoexpediente.IDTipoExpediente = casoscliente.IDTipoExpediente"
            + "	WHERE "
            + "	CONCAT(dd.NombreDonante,' ',dd.ApPaternoDonante,' ',dd.ApMaternoDonante) LIKE '%" + busqueda + "%'"
            + " UNION "
            + "SELECT dt.IDNoExpediente, tipoexpediente.TipoExpediente, CONCAT(dt.NombreDonatario,' ',dt.ApPaternoDonatario,' ',dt.ApMaternoDonatario) registro FROM donaciondonatarios dt "
            + "	INNER JOIN casoscliente ON casoscliente.IDNoExpediente = dt.IDNoExpediente"
            + "	INNER JOIN tipoexpediente ON tipoexpediente.IDTipoExpediente = casoscliente.IDTipoExpediente"
            + "	WHERE "
            + "	CONCAT(dt.NombreDonatario,' ',dt.ApPaternoDonatario,' ',dt.ApMaternoDonatario) LIKE '%" + busqueda + "%'"
            + " UNION "
            + "SELECT d.IDNoExpediente, tipoexpediente.TipoExpediente, d.FolioReal registro FROM donacion d"
            + "	INNER JOIN casoscliente ON casoscliente.IDNoExpediente = d.IDNoExpediente"
            + "	INNER JOIN tipoexpediente ON tipoexpediente.IDTipoExpediente = casoscliente.IDTipoExpediente"
            + "	WHERE "
            + "	d.FolioReal LIKE '%" + busqueda + "%'"
            + " UNION "
            + "SELECT d.IDNoExpediente, tipoexpediente.TipoExpediente, d.Instrumento registro FROM donacion d"
            + "	INNER JOIN casoscliente ON casoscliente.IDNoExpediente = d.IDNoExpediente"
            + "	INNER JOIN tipoexpediente ON tipoexpediente.IDTipoExpediente = casoscliente.IDTipoExpediente"
            + "	WHERE "
            + "	d.Instrumento LIKE '%" + busqueda + "%'"
            + " UNION "
            + "SELECT d.IDNoExpediente, tipoexpediente.TipoExpediente, d.Tomo registro FROM donacion d"
            + "	INNER JOIN casoscliente ON casoscliente.IDNoExpediente = d.IDNoExpediente"
            + "	INNER JOIN tipoexpediente ON tipoexpediente.IDTipoExpediente = casoscliente.IDTipoExpediente"
            + "	WHERE "
            + "	d.Tomo LIKE '%" + busqueda + "%'"
            + " UNION "
            + "SELECT op.IDNoExpediente, tipoexpediente.TipoExpediente, CONCAT(op.NombreParticipante,' ',op.ApPaternoParticipante,' ',op.ApMaternoParticipante) registro FROM otrosparticipante op"
            + "	INNER JOIN casoscliente ON casoscliente.IDNoExpediente = op.IDNoExpediente"
            + "	INNER JOIN tipoexpediente ON tipoexpediente.IDTipoExpediente = casoscliente.IDTipoExpediente"
            + "	WHERE "
            + "	CONCAT(op.NombreParticipante,' ',op.ApPaternoParticipante,' ',op.ApMaternoParticipante) LIKE '%" + busqueda + "%'"
            + " UNION "
            + "SELECT po.IDNoExpediente, tipoexpediente.TipoExpediente, CONCAT(po.NombreOtorgante,' ',po.ApPaternoOtorgante,' ',po.ApMaternoOtorgante) registro FROM poderesotorgante po"
            + "	INNER JOIN casoscliente ON casoscliente.IDNoExpediente = po.IDNoExpediente"
            + "	INNER JOIN tipoexpediente ON tipoexpediente.IDTipoExpediente = casoscliente.IDTipoExpediente"
            + "	WHERE "
            + "	CONCAT(po.NombreOtorgante,' ',po.ApPaternoOtorgante,' ',po.ApMaternoOtorgante) LIKE '%" + busqueda + "%'"
            + " UNION "
            + "SELECT pr.IDNoExpediente, tipoexpediente.TipoExpediente, CONCAT(pr.NombreQuienRecibe,' ',pr.ApPaternoQuienRecibe,' ',pr.ApMaternoQuienRecibe) registro FROM poderesquienrecibe pr"
            + "	INNER JOIN casoscliente ON casoscliente.IDNoExpediente = pr.IDNoExpediente"
            + "	INNER JOIN tipoexpediente ON tipoexpediente.IDTipoExpediente = casoscliente.IDTipoExpediente"
            + "	WHERE "
            + "	CONCAT(pr.NombreQuienRecibe,' ',pr.ApPaternoQuienRecibe,' ',pr.ApMaternoQuienRecibe) LIKE '%" + busqueda + "%'"
            + " UNION "
            + "SELECT tot.IDNoExpediente, tipoexpediente.TipoExpediente, CONCAT(tot.NombreOtorgante,' ',tot.ApPaternoOtorgante,' ',tot.ApMaternoOtorgante) registro FROM testamentosotorgante tot"
            + "	INNER JOIN casoscliente ON casoscliente.IDNoExpediente = tot.IDNoExpediente"
            + "	INNER JOIN tipoexpediente ON tipoexpediente.IDTipoExpediente = casoscliente.IDTipoExpediente"
            + "	WHERE "
            + "	CONCAT(tot.NombreOtorgante,' ',tot.ApPaternoOtorgante,' ',tot.ApMaternoOtorgante) LIKE '%" + busqueda + "%'"
            + " UNION "
            + "SELECT t.IDNoExpediente, tipoexpediente.TipoExpediente, t.Folio registro FROM testamentos t"
            + "	INNER JOIN casoscliente ON casoscliente.IDNoExpediente = t.IDNoExpediente"
            + "	INNER JOIN tipoexpediente ON tipoexpediente.IDTipoExpediente = casoscliente.IDTipoExpediente"
            + "	WHERE "
            + "	t.Folio LIKE '%" + busqueda + "%'";

        try
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while(rs.next()) 
            {
                    Principal.modeloBuscarCap.addRow(new Object[]{rs.getString("TipoExpediente"), rs.getString("registro") ,rs.getInt("IDNoExpediente"), "2000/09/17"});
            } 
        } catch (SQLException ex) {
            System.out.println("Error al obtener mis estadisticas" + ex);
        }finally
        //Cuando se termine todo el proceso cierra la conexión y manda llamar al metodo Inicio.
        {
            mysql.desconectar();
        }
    Principal.jTableBusquedaCapturista.setModel(Principal.modeloBuscarCap);
    }
}
