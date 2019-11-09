package com.sw.renderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
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

        table.getColumnModel().getColumn(0).setWidth(table.getWidth() == 904 ? 325 : table.getWidth() == 913 ? 310 : table.getColumnCount() >= 6 ? 450 : 255);
        table.getColumnModel().getColumn(1).setWidth(table.getWidth() == 904 ? 115 : table.getWidth() == 913 ? 190 : table.getColumnCount() >= 6 ? 149 : 105);
        table.getColumnModel().getColumn(2).setWidth(table.getWidth() == 904 ? 115 : table.getWidth() == 913 ? 130 : table.getColumnCount() >= 6 ? 149 : 105);
        table.getColumnModel().getColumn(3).setWidth(table.getWidth() == 904 ? 115 : table.getWidth() == 913 ? 130 : table.getColumnCount() >= 6 ? 149 : 105);

        if (table.getColumnCount() >= 5)
            table.getColumnModel().getColumn(4).setWidth(table.getWidth() == 913 ? 70 : table.getWidth() == 904 ? 115 : table.getColumnCount() == 7 ? 104 : 154);

        if (table.getColumnCount() >= 6)
            table.getColumnModel().getColumn(5).setWidth(table.getWidth() == 913 ? 85 : table.getWidth() == 904 ? 120 : table.getColumnCount() == 7 ? 104 : 154);

        if (table.getColumnCount() >= 7)
            table.getColumnModel().getColumn(6).setWidth(100);

        JComponent jcomponent = new JLabel((String) value);
        ((JLabel) jcomponent).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel) jcomponent).setSize(30, jcomponent.getWidth());
        ((JLabel) jcomponent).setPreferredSize(new Dimension(6, jcomponent.getWidth()));

        jcomponent.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(255, 255, 255)));
        jcomponent.setOpaque(true);
        jcomponent.setBackground(new Color(65, 65, 65));
        jcomponent.setToolTipText("Header");
        jcomponent.setForeground(Color.white);

        return jcomponent;

    }

}
