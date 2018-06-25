/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import modelo.FacturaModel;
import modelo.entidades.Cliente;
import modelo.entidades.Factura;
import modelo.entidades.FacturaImpl;
import vista.factura.FacturaView;

/**
 *
 * @author Norberto Díaz-Díaz
 */
public class FacturaControllerImpl extends AbstractControllerImpl<FacturaModel, FacturaView, Serializable> implements FacturaController {

    protected Factura generaEntidad(List<Serializable> datos) {
        String identificador = (String) datos.get(0);
        Cliente cliente = (Cliente) datos.get(1);
        Double importe = new Double((String) datos.get(2));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaPago = null; // Variable que almacenará la fecha

        String sFechaPago = (String) datos.get(3);
        if (sFechaPago.length() > 0) {
            try {
                fechaPago = formatter.parse(sFechaPago);
            } catch (ParseException ex) {

                System.out.println("Error en el parseo de fecha.");
            }
        }
        
        Date fechaLimite = null;
        String sFechaLimite = (String) datos.get(4);
        if (sFechaLimite.length() > 0){
            try{
                fechaLimite = formatter.parse(sFechaLimite);
            }
            catch(ParseException ex){
                System.out.println("Error en el parseo de fecha.");
            }
        }

        Factura f = new FacturaImpl(identificador, cliente, fechaPago, fechaLimite);
        f.setImporte(importe);

        return f;

    }

    protected Factura generaEntidad(Serializable pk) {
        return new FacturaImpl((String) pk);
    }

}
