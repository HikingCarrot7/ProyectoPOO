package com.sw.controller;

import com.sw.renderer.TableCellManager;
import com.sw.renderer.TableCellRenderer;
import com.sw.renderer.TableHeaderRenderer;
import com.sw.utilities.Utilities;
import java.awt.Component;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Mohammed
 */
public class TableManager
{

    public void renderTableModel(JTable table, MouseListener controller, String name)
    {

        TableCellRenderer tableCellRenderer = new TableCellRenderer();

        table.setDefaultRenderer(Object.class, tableCellRenderer);
        JTableHeader jTableHeader = table.getTableHeader();
        jTableHeader.setDefaultRenderer(new TableHeaderRenderer());
        table.setTableHeader(jTableHeader);

        table.addMouseMotionListener(new MouseMotionManager(tableCellRenderer));
        table.addMouseListener(controller);
        table.setCellEditor(new TableCellManager());
        table.setName(name);
        table.setDragEnabled(false);
        table.getParent().revalidate();

    }

    public boolean encimaBoton(JTable table, JButton boton, int x, int y, int columnaBoton)
    {

        if (y <= 7)
            return false;

        int column = table.getColumnModel().getColumnIndexAtX(x);

        if (column != columnaBoton)
            return false;

        int row = y + 10 >= table.getHeight() ? -1 : getClickedRow(table, y);

        if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0)
            return table.getValueAt(row, column) == boton;

        return false;

    }

    public boolean encimaBoton(JTable table, int x, int y)
    {
        return table.getValueAt(getClickedRow(table, y), table.getColumnModel().getColumnIndexAtX(x)) instanceof JButton;
    }

    public int getClickedColumn(JTable table, int[] columns, int x)
    {
        int column = table.getColumnModel().getColumnIndexAtX(x);

        for (int i = 0; i < columns.length; i++)
            if (column == columns[i])
                return columns[i];

        return -1;

    }

    public int getClickedRow(JTable table, int y)
    {
        return y / table.getRowHeight();
    }

    public void deleteRow(JTable table, int row)
    {

        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

        tableModel.removeRow(row);

        table.getParent().revalidate();

    }

    public void addRow(JTable table, Object[] rowData)
    {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

        tableModel.addRow(rowData);

        table.getParent().revalidate();

    }

    public void updateRow(JTable table, Object[] items, int row)
    {

        for (int i = 0; i < items.length; i++)
            table.getModel().setValueAt(items[i], row, i);

    }

    public void updateLastRow(JTable table, Object[] items)
    {

        for (int i = 0; i < items.length; i++)
            table.getModel().setValueAt(items[i], table.getRowCount() - 1, i);

    }

    public Object[][] getTableItems(JTable table)
    {

        int filas = 0, columnas = 0;
        Object[][] items = new Object[table.getRowCount()][table.getColumnCount() - getColumnasConComponentes(table).length];

        for (int i = 0; i < items.length; i++)
            for (int j = 0; j < items[i].length; j++)
                if (!(table.getValueAt(i, j) instanceof Component))
                {
                    items[filas][columnas++] = table.getValueAt(i, j);

                    if (columnas == items[i].length)
                    {
                        filas++;
                        columnas = 0;

                    }

                }

        return items;

    }

    public synchronized void updateField(JTable table, Object item, int row, int column)
    {
        table.getModel().setValueAt(item, row, column);
    }

    public Object[][] loadTableComponents(Object[][] items, int[] columns, ArrayList<? extends Component>... components)
    {

        for (int i = 0; i < components.length; i++)
            for (int j = 0; j < items.length; j++)
                items[j][columns[i]] = components[i].get(j);

        return items;

    }

    public int[] getColumnasConComponentes(JTable table)
    {

        ArrayList<Integer> lista = new ArrayList<>();

        for (int i = 0; i < table.getColumnCount(); i++)
            if (table.getValueAt(0, i) instanceof Component)
                lista.add(i);

        return Utilities.asArray(lista);

    }

    public Object[] getEmptyRowData(JTable table)
    {

        if (table.getRowCount() == 0)
            return null;

        Object[] items = new Object[table.getColumnCount()];

        for (int i = 0; i < items.length; i++)
            if (table.getValueAt(table.getRowCount() - 1, i) instanceof JButton)
                items[i] = new JButton(((JButton) table.getValueAt(table.getRowCount() - 1, i)).getIcon());

        return items;

    }

    public Object[][] recortarFilaItems(Object[][] items, int row)
    {

        Object[][] newItems = new Object[items.length - 1][items[0].length];

        for (int i = 0; i < items.length - 1; i++)
            System.arraycopy(items[i + (i >= row ? 1 : 0)], 0, newItems[i], 0, items[i].length);

        return newItems;

    }

    public Object[][] recortarFilasItems(Object[][] items, int rowInicio, int rowFin)
    {

        Object[][] newItems = new Object[items.length - (rowFin - rowInicio) - 1][items[0].length];

        for (int i = 0; i < items.length - (rowFin - rowInicio) - 1; i++)
            System.arraycopy(items[i + (i >= rowInicio ? rowFin : 0)], 0, newItems[i], 0, items[i].length);

        return newItems;

    }

}
