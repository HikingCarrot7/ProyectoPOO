package com.sw.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import static javax.swing.BorderFactory.createMatteBorder;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Mohammed
 */
public class TableHeaderRenderer implements TableCellRenderer
{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {

        table.getColumnModel().getColumn(0).setWidth(table.getWidth() == 904 ? 325 : table.getColumnCount() >= 6 ? 450 : table.getWidth() == 901 ? 370 : 255);
        table.getColumnModel().getColumn(1).setWidth(table.getWidth() == 904 ? 115 : table.getColumnCount() >= 6 ? 149 : table.getWidth() == 901 ? 120 : 105);
        table.getColumnModel().getColumn(2).setWidth(table.getWidth() == 904 ? 115 : table.getColumnCount() >= 6 ? 149 : table.getWidth() == 901 ? 120 : 105);
        table.getColumnModel().getColumn(3).setWidth(table.getWidth() == 904 ? 115 : table.getColumnCount() >= 6 ? 149 : table.getWidth() == 901 ? 120 : 105);

        if (table.getColumnCount() >= 5)
            table.getColumnModel().getColumn(4).setWidth(table.getWidth() == 901 ? 172 : table.getWidth() == 904 ? 115 : 150);

        if (table.getColumnCount() >= 6)
            table.getColumnModel().getColumn(5).setWidth(table.getWidth() == 904 ? 120 : 150);

        JComponent jcomponent = new JLabel((String) value);
        ((JLabel) jcomponent).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel) jcomponent).setSize(30, jcomponent.getWidth());
        ((JLabel) jcomponent).setPreferredSize(new Dimension(6, jcomponent.getWidth()));

        jcomponent.setBorder(createMatteBorder(0, 0, 1, 1, new Color(255, 255, 255)));
        jcomponent.setOpaque(true);
        jcomponent.setBackground(new Color(65, 65, 65));
        jcomponent.setToolTipText("Header");
        jcomponent.setForeground(Color.white);

        return jcomponent;

    }

}
