package com.sw.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import static javax.swing.BorderFactory.createMatteBorder;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Mohammed
 */
public class CellRenderer extends DefaultTableCellRenderer
{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {

        table.getColumnModel().getColumn(0).setWidth(table.getWidth() == 904 ? 325 : table.getColumnCount() >= 6 ? 450 : table.getWidth() == 901 ? 370 : 255);
        table.setRowHeight(30);

        if (value instanceof JButton)
            return (JButton) value;

        if (value instanceof JCheckBox)
        {
            JComponent jcomponent = new JPanel();
            ((JPanel) jcomponent).setLayout(null);
            JCheckBox checkBox = new JCheckBox();
            checkBox.setBounds(68, 7, 16, 16);
            ((JPanel) jcomponent).add(checkBox);
            jcomponent.setOpaque(true);
            jcomponent.setBackground(row % 2 == 0 ? new Color(180, 180, 180) : Color.white);

            return jcomponent;

        }

        JComponent jcomponent = new JLabel((String) value);
        ((JLabel) jcomponent).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel) jcomponent).setSize(30, jcomponent.getWidth());
        ((JLabel) jcomponent).setPreferredSize(new Dimension(6, jcomponent.getWidth()));

        jcomponent.setBorder(createMatteBorder(0, 0, 1, 1, new Color(255, 255, 255)));
        jcomponent.setOpaque(true);
        jcomponent.setBackground(row % 2 == 0 ? new Color(180, 180, 180) : Color.white);
        jcomponent.setForeground(Color.black);

        return jcomponent;

    }

}
