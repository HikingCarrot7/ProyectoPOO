package com.sw.controller;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author Mohammed
 */
public class TableManager
{

    public void updateTableModel(JTable table, Object[][] items)
    {

        TableModel tableModel = table.getModel();

        for (int i = 0; i < table.getRowCount(); i++)
            for (int j = 0; j < table.getColumnCount(); j++)
                tableModel.setValueAt(items[i][j], i, j);

        table.setModel(tableModel);

    }

    public boolean encimaBoton(JTable table, JButton boton, int x, int y, int columnaBoton)
    {

        if (y < 7)
            return false;

        int column = table.getColumnModel().getColumnIndexAtX(x);

        if (column != columnaBoton)
            return false;

        int row = y + 10 >= table.getHeight() ? -1 : y / table.getRowHeight();

        if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0)
            return table.getValueAt(row, column) == boton;

        return false;

    }

    public Object[][] recortarFila(Object[][] items, int row)
    {

        Object[][] newItems = new Object[items.length - 1][items[0].length];

        for (int i = 0; i < items.length - 1; i++)
            System.arraycopy(items[i + (i >= row ? 1 : 0)], 0, newItems[i], 0, items[i].length);

        return newItems;

    }

    public Object[][] recortarFilas(Object[][] items, int rowInicio, int rowFin)
    {

        Object[][] newItems = new Object[items.length - (rowFin - rowInicio) - 1][items[0].length];

        for (int i = 0; i < items.length - (rowFin - rowInicio) - 1; i++)
            System.arraycopy(items[i + (i >= rowInicio ? rowFin : 0)], 0, newItems[i], 0, items[i].length);

        return newItems;

    }

    public void rellenarFilaTabla(JTable table, Object[][] items, int row)
    {

    }

    public void eliminarFilaTabla(JTable table, Object[][] items, int row)
    {

    }

    /**
     * @deprecated
     *
     * Este método está bug xd.
     *
     * @param column
     * @param items
     * @param botones
     *
     * @return
     */
    public Object[][] getItems(Object[][] items, ArrayList<JButton> botones, int column)
    {
        for (int i = 0; i < items.length; i++)
            for (int j = 0; j < items[0].length; j++)
                items[i][j] = j == column ? botones.get(i) : items[i][j];

        return items;

    }

}
