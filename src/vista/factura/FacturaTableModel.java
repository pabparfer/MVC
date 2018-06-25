/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.factura;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import modelo.entidades.Factura;

/**
 *
 * @author Norberto Díaz-Díaz
 */
public class FacturaTableModel implements TableModel {

    private List<Factura> facturas = new ArrayList<Factura>();
    private List<TableModelListener> tableListeners = new ArrayList<TableModelListener>();

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
        fireContentsChanged();
    }

    protected void fireContentsChanged() {
        fireContentsChangedTableModel();
    }
    //--------------- MÉTODOS PROPIOS DE TableModel -------------------------

    public Factura getRow(int indexRow) {
        return facturas.get(indexRow);
    }

    public int getRowCount() {
        return facturas.size();
    }

    public int getColumnCount() {
        return 5; //ya que tendremos cuatro valores a mostrar por contacto
    }

    public String getColumnName(int columnIndex) {
        String name = null;
        switch (columnIndex) {
            case 0:
                name = "Identificador";
                break;
            case 1:
                name = "Cliente";
                break;
            case 2:
                name = "Importe";
                break;
            case 3:
                name = "Pagado";
                break;
            case 4:
                name = "Fecha Limite";
                break;

        }
        return name;
    }

    public Class<?> getColumnClass(int columnIndex) {
        Class c = null;
        switch (columnIndex) {
            case 0:
                c = String.class;
                break;
            case 1:
                c = String.class;//pondré sólo el nombre del cliente
                break;
            case 2:
                c = Double.class;
                break;
            case 3:
                c = Boolean.class;
                break;
            case 4:
                c = Date.class;
                break;
        }
        return c;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false; //No permito editar celdas
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;

        if (rowIndex < facturas.size()) {
            Factura factura = facturas.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    value = factura.getIdentificador();
                    break;
                case 1:
                    value = factura.getCliente().getDNI();
                    break;
                case 2:
                    value = factura.getImporte();
                    break;
                case 3:
                    value = factura.getFecha_pago() != null;
                    break;
                case 4:
                    value = factura.getFechaLimite();
                    break;
            }
        }

        return value;

    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addTableModelListener(TableModelListener l) {
        tableListeners.add(l);
    }

    public void removeTableModelListener(TableModelListener l) {
        tableListeners.remove(l);
    }

    protected void fireContentsChangedTableModel() {

        TableModelEvent event = new TableModelEvent(this, 0, this.getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
        for (TableModelListener listener : tableListeners) {
            listener.tableChanged(event);
        }
    }

}
