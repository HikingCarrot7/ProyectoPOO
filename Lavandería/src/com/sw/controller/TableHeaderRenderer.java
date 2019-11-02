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

        table.getColumnModel().getColumn(0).setWidth(450);
        table.getColumnModel().getColumn(1).setWidth(150);
        table.getColumnModel().getColumn(2).setWidth(150);
        table.getColumnModel().getColumn(3).setWidth(150);
        table.getColumnModel().getColumn(4).setWidth(150);
        table.getColumnModel().getColumn(5).setWidth(150);

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
