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
public class FacturaImpl implements Factura {

    private String identifiador;
    private Cliente cliente;
    private Double importe;
    private Date fecha_pago;
    private Date fechaLimite;

    public FacturaImpl(String identificador) {
        this(identificador, null, null, null, null);
    }

    public FacturaImpl(String identifiador, Cliente cliente, Date fecha_pago, Date fechaLimite) {
        this(identifiador, cliente, null, fecha_pago, fechaLimite);
    }

    public FacturaImpl(String identifiador, Cliente cliente, Double importe, Date fecha_pago, Date fechaLimite) {
        this.identifiador = identifiador;
        this.cliente = cliente;
        this.importe = importe;
        this.fecha_pago = fecha_pago;
        this.fechaLimite = fechaLimite;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getIdentificador() {
        return identifiador;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Date getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Date f_pago) {
        this.fecha_pago = f_pago;
    }

  
    public Date getFechaLimite() {
        return this.fechaLimite;
    }

    @Override
    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

}
