package com.sw.renderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.Serializable;
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
public class TableHeaderRenderer implements TableCellRenderer, Serializable
{

    private static final long serialVersionUID = -6170812323573504404L;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {

        table.getColumnModel().getColumn(0).setWidth(table.getWidth() == 904 ? 325
                : table.getWidth() == 913 ? 280
                : table.getColumnCount() == 2 ? 370
                : table.getColumnCount() == 6 ? 450
                : table.getColumnCount() == 7 ? 350
                : table.getColumnCount() == 8 ? 350 : 280);

        table.getColumnModel().getColumn(1).setWidth(table.getWidth() == 904 ? 115
                : table.getWidth() == 913 ? 165
                : table.getColumnCount() == 2 ? 115
                : table.getColumnCount() == 6 ? 149
                : table.getColumnCount() == 7 ? 143
                : table.getColumnCount() == 8 ? 122 : 105);

        if (table.getColumnCount() >= 3)
        {

            table.getColumnModel().getColumn(2).setWidth(table.getWidth() == 904 ? 115
                    : table.getWidth() == 913 ? 165
                    : table.getColumnCount() == 6 ? 149
                    : table.getColumnCount() == 7 ? 143
                    : table.getColumnCount() == 8 ? 122 : 93);

            table.getColumnModel().getColumn(3).setWidth(table.getWidth() == 904 ? 115
                    : table.getWidth() == 913 ? 165
                    : table.getColumnCount() == 6 ? 149
                    : table.getColumnCount() == 7 ? 143
                    : table.getColumnCount() == 8 ? 122 : 92);

        }

        if (table.getColumnCount() >= 5)
            table.getColumnModel().getColumn(4).setWidth(table.getWidth() == 913 ? 70
                    : table.getWidth() == 904 ? 115
                    : table.getColumnCount() == 7 ? 143
                    : table.getColumnCount() == 8 ? 122 : 154);

        if (table.getColumnCount() >= 6)
            table.getColumnModel().getColumn(5).setWidth(table.getWidth() == 913 ? 70
                    : table.getWidth() == 904 ? 122
                    : table.getColumnCount() == 7 ? 143
                    : table.getColumnCount() == 8 ? 122 : 154);

        if (table.getColumnCount() >= 7)
            table.getColumnModel().getColumn(6).setWidth(table.getColumnCount() == 7 ? 140 : 122);

        if (table.getColumnCount() >= 8)
            table.getColumnModel().getColumn(7).setWidth(123);

        JComponent jcomponent = new JLabel((String) value);
        ((JLabel) jcomponent).setHorizontalAlignment(SwingConstants.CENTER);
        jcomponent.setSize(30, jcomponent.getWidth());
        jcomponent.setPreferredSize(new Dimension(6, jcomponent.getWidth()));

        jcomponent.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(255, 255, 255)));
        jcomponent.setOpaque(true);
        jcomponent.setBackground(new Color(65, 65, 65));
        jcomponent.setToolTipText("Header");
        jcomponent.setForeground(Color.white);

        return jcomponent;

    }

}
