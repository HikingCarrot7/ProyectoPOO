package com.sw.renderer;

import com.sw.persistence.ConfigDAO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.Serializable;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Me
 */
public class TableHeaderRenderer implements TableCellRenderer, Serializable
{

    private static final long serialVersionUID = -6170812323573504404L;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {

        table.getColumnModel().getColumn(0).setWidth(table.getName().equals("Historial") ? 250
                : table.getName().equals("Clientes") ? 280
                : table.getName().equals("Tipos prendas") ? 370
                : table.getName().equals("En cola") ? 350
                : table.getColumnCount() == 8 ? 350 : 280);

        table.getColumnModel().getColumn(1).setWidth(table.getName().equals("Historial") ? 220
                : table.getName().equals("Clientes") ? 165
                : table.getName().equals("Tipos prendas") ? 115
                : table.getName().equals("En cola") ? 143
                : table.getColumnCount() == 8 ? 122 : 105);

        if (table.getColumnCount() >= 3)
        {

            table.getColumnModel().getColumn(2).setWidth(table.getName().equals("Historial") ? 125
                    : table.getName().equals("Clientes") ? 165
                    : table.getName().equals("En cola") ? 143
                    : table.getColumnCount() == 8 ? 122 : 93);

            table.getColumnModel().getColumn(3).setWidth(table.getName().equals("Historial") ? 103
                    : table.getName().equals("Clientes") ? 165
                    : table.getName().equals("En cola") ? 143
                    : table.getColumnCount() == 8 ? 122 : 92);

        }

        if (table.getColumnCount() >= 5)
            table.getColumnModel().getColumn(4).setWidth(table.getName().equals("Historial") ? 103
                    : table.getName().equals("Clientes") ? 70
                    : table.getName().equals("En cola") ? 143
                    : table.getColumnCount() == 8 ? 122 : 154);

        if (table.getColumnCount() >= 6)
            table.getColumnModel().getColumn(5).setWidth(table.getName().equals("Historial") ? 104
                    : table.getName().equals("Clientes") ? 70
                    : table.getName().equals("En cola") ? 143
                    : table.getColumnCount() == 8 ? 122 : 154);

        if (table.getColumnCount() >= 7)
            table.getColumnModel().getColumn(6).setWidth(table.getName().equals("En cola") ? 140 : 122);

        if (table.getColumnCount() >= 8)
            table.getColumnModel().getColumn(7).setWidth(123);

        JComponent jcomponent = new JLabel((String) value);
        ((JLabel) jcomponent).setHorizontalAlignment(SwingConstants.CENTER);
        jcomponent.setFont(new Font("Consolas", Font.PLAIN, 11));
        jcomponent.setSize(30, jcomponent.getWidth());
        jcomponent.setPreferredSize(new Dimension(6, jcomponent.getWidth()));

        jcomponent.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(255, 255, 255)));
        jcomponent.setOpaque(true);
        jcomponent.setBackground(new ConfigDAO().getColorTablas().darker());
        jcomponent.setForeground(Color.white);

        return jcomponent;

    }

}
