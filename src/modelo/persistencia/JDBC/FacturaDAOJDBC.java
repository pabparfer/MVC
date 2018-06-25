/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.persistencia.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.entidades.Cliente;
import modelo.entidades.ClienteImpl;
import modelo.entidades.Factura;
import modelo.entidades.FacturaImpl;
import modelo.persistencia.FacturaDAO;

/**
 *
 * @author Norberto Díaz-Díaz
 */
public class FacturaDAOJDBC implements FacturaDAO {

    public List<Factura> listByCliente(String dni) {
        List<Factura> facturas = new ArrayList<Factura>();

        try {
            Statement stmt = Persistencia.createConnection().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM vfacturas where DNI=" + dni);
            String nombre, direccion, id_factura;
            Double importe;
            Date fecha_pago;
            Date fechaLimite;

            while (res.next()) {
                //DNI = res.getString("DNI");
                nombre = res.getString("nombre");
                direccion = res.getString("direccion");
                id_factura = res.getString("identificador");
                importe = res.getDouble("importe");
                fecha_pago = res.getTimestamp("fecha_pago");
                fechaLimite = res.getTimestamp("fecha_limite");

                //creo cliente
                Cliente cliente = new ClienteImpl(dni, nombre, direccion);
                //Añado la factura
                facturas.add(new FacturaImpl(id_factura, cliente, importe, fecha_pago, fechaLimite));
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            Persistencia.closeConnection();
        }
        return facturas;
    }

    public void create(Factura entidad) {
        String sql = "insert into facturas(identificador,id_cliente,importe,fecha_pago,fecha_limite) values (?,?,?,?,?)";
        try {
            PreparedStatement stm = Persistencia.createConnection().prepareStatement(sql);
            stm.setString(1, entidad.getIdentificador());
            stm.setString(2, entidad.getCliente().getDNI());
            stm.setDouble(3, entidad.getImporte());

            java.sql.Date fechaPago = (entidad.getFecha_pago() != null) ? new java.sql.Date(entidad.getFecha_pago().getTime()) : null;
            stm.setDate(4, fechaPago);

            java.sql.Date fechaLimite = (entidad.getFechaLimite() != null) ? new java.sql.Date(entidad.getFechaLimite().getTime()) : null;
            stm.setDate(5, fechaLimite);

            stm.execute();

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            Persistencia.closeConnection();
        }
    }

    public Factura read(String pk) {
        Factura f = null;
        try {
            Statement stmt = Persistencia.createConnection().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM facturas where identificador=" + pk);
            String identificador, id_cliente;
            Double importe;
            Date fecha_pago;
            Date fechaLimite;

            if (res.next()) {
                identificador = res.getString("identificador");
                id_cliente = res.getString("id_cliente");
                importe = res.getDouble("importe");
                fecha_pago = res.getTimestamp("fecha_pago");
                fechaLimite = res.getTimestamp("fecha_limite");
                //Leo el Cliente
                Cliente cliente = (new ClienteDAOJDBC()).read(id_cliente);
                //Creo la factura
                f = new FacturaImpl(identificador, cliente, importe, fecha_pago, fechaLimite);
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            Persistencia.closeConnection();
        }
        return f;
    }

    public void update(Factura entidad) {
        String sql = "update facturas set id_cliente=?, importe=?, fecha_pago=?, fecha_limite=? where identificador like ?";

        try {
            PreparedStatement stm = Persistencia.createConnection().prepareStatement(sql);
            stm.setString(5, entidad.getIdentificador());
            stm.setString(1, entidad.getCliente().getDNI());
            stm.setDouble(2, entidad.getImporte());

            java.sql.Date fechaPago = entidad.getFecha_pago() != null ? new java.sql.Date(entidad.getFecha_pago().getTime()) : null;
            stm.setDate(3, fechaPago);
            
            java.sql.Date fechaLimite = entidad.getFechaLimite() != null ? new java.sql.Date(entidad.getFechaLimite().getTime()) : null;
            stm.setDate(4, fechaLimite);

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            Persistencia.closeConnection();
        }
    }

    public void delete(Factura entidad) {
        try {
            Statement stmt = Persistencia.createConnection().createStatement();
            stmt.executeUpdate("DELETE FROM facturas where identificador=" + entidad.getIdentificador());

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            Persistencia.closeConnection();
        }
    }

    public List<Factura> list() {
        List<Factura> facturas = new ArrayList<Factura>();

        try {
            Statement stmt = Persistencia.createConnection().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM vfacturas");
            String DNI, nombre, direccion, id_factura;
            Double importe;
            Date fecha_pago;
            Date fechaLimite;

            while (res.next()) {
                DNI = res.getString("DNI");
                nombre = res.getString("nombre");
                direccion = res.getString("direccion");
                id_factura = res.getString("identificador");
                importe = res.getDouble("importe");
                fecha_pago = res.getTimestamp("fecha_pago");
                fechaLimite = res.getTimestamp("fecha_limite");

                //creo cliente
                Cliente cliente = new ClienteImpl(DNI, nombre, direccion);
                //Añado la factura
                facturas.add(new FacturaImpl(id_factura, cliente, importe, fecha_pago, fechaLimite));
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            Persistencia.closeConnection();
        }
        return facturas;
    }
}
