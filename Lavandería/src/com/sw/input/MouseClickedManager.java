package com.sw.input;

import com.sw.controller.TableManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;

/**
 *
 * @author Mohammed
 */
public class MouseClickedManager extends MouseAdapter
{

    private JTable table;
    private int[] columns;

    public MouseClickedManager(JTable table, int[] columns)
    {
        this.table = table;
        this.columns = columns;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

        TableManager tableManager = new TableManager();

        if (tableManager.encimaBoton(table, e.getX(), e.getY()))
        {
            System.out.println("Hola, botón!, " + tableManager.getClickedRow(table, e.getY()) + " - " + tableManager.getClickedColumn(table, columns, e.getX()));
            tableManager.deleteTableRow(table, 0);

        }

    }

    public int[] getColumns()
    {
        return columns;
    }

    public void setColumns(int[] columns)
    {
        this.columns = columns;
    }

}
