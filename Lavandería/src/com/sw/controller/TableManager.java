package com.sw.controller;

import java.util.ArrayList;
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

    public Object[][] recortarFilas(Object[][] items, int row)
    {
        return null;
    }

    public Object[][] recortarFilas(Object[][] items, int rowInicio, int rowFin)
    {
        return null;
    }

    public void rellenarFila(JTable table, Object[][] items, int row)
    {

    }

    /**
     * @deprecated
     *
     * @param items
     * @param botones
     *
     * @return
     */
    public Object[][] getItems(Object[][] items, ArrayList<BotonDinamico> botones)
    {
        for (int i = 0; i < items.length; i++)
            for (int j = 0; j < items[0].length; j++)
                items[i][j] = j == items[i].length - 1 ? botones.get(i) : null;

        return items;

    }

}
