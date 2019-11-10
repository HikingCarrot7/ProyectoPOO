package com.sw.controller;

import com.sw.input.MouseMotionManager;
import com.sw.renderer.TableCellRenderer;
import com.sw.renderer.TableHeaderRenderer;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

/**
 *
 * @author Mohammed
 */
public class TableManager
{

    public void renderTableModel(JTable table, String name)
    {

        TableCellRenderer tableCellRenderer = new TableCellRenderer();

        table.setDefaultRenderer(Object.class, tableCellRenderer);
        JTableHeader jTableHeader = table.getTableHeader();
        jTableHeader.setDefaultRenderer(new TableHeaderRenderer());
        table.setTableHeader(jTableHeader);

        table.addMouseMotionListener(new MouseMotionManager(tableCellRenderer));
        table.setName(name);
        table.revalidate();

    }

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

    public void rellenarFilaTabla(JTable table, Object[] items, int row)
    {

        for (int i = 0; i < items.length; i++)
            table.getModel().setValueAt(items[i], row, i);

    }

    public synchronized void updateField(JTable table, Object item, int row, int column)
    {
        table.getModel().setValueAt(item, row, column);
    }

    public Object[][] loadItems(Object[][] items, int[] columns, ArrayList<? extends Component>... components)
    {

        for (int i = 0; i < components.length; i++)
            for (int j = 0; j < items.length; j++)
                items[j][columns[i]] = components[i].get(j);

        return items;

    }

}
