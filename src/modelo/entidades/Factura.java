/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.entidades;

import java.util.Date;

/**
 *
 * @author Norberto Díaz-Díaz
 */
public interface Factura extends Entidad{

    Cliente getCliente();

    String getIdentificador();

    Double getImporte();

    void setImporte(Double importe);
    
    Date getFecha_pago();
    
    void setFecha_pago(Date f_pago);
    
    Date getFechaLimite();
    
    void setFechaLimite(Date fechaLimite);



}
