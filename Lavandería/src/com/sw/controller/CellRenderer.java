package com.sw.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import static javax.swing.BorderFactory.createMatteBorder;
import javax.swing.ImageIcon;
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

    private int mouseX;
    private int mouseY;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {

        table.getColumnModel().getColumn(0).setWidth(table.getWidth() == 904 ? 325 : table.getWidth() == 913 ? 310 : table.getColumnCount() >= 6 ? 450 : 255);
        table.setRowHeight(30);

        if (value instanceof BotonDinamico)
        {
            if (encimaBoton(table, ((JButton) value), getMouseX(), getMouseY(), 5))
                ((JButton) value).setIcon(new ImageIcon(getClass().getResource("/com/src/images/historialSelected.png")));

            else
                ((JButton) value).setIcon(new ImageIcon(getClass().getResource("/com/src/images/historial.png")));

            return (JButton) value;

        }

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

        if (row == table.getSelectedRow())
            jcomponent.setBackground(Color.cyan);

        return jcomponent;

    }

    private boolean encimaBoton(JTable table, JButton boton, int x, int y, int columnaBoton)
    {
        int column = table.getColumnModel().getColumnIndexAtX(x);
        int row = y + 10 >= table.getHeight() ? -1 : y / table.getRowHeight();

        if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0)
            return table.getValueAt(row, column) == boton;

        return false;

    }

    public void setMouseX(int mouseX)
    {
        this.mouseX = mouseX;
    }

    public void setMouseY(int mouseY)
    {
        this.mouseY = mouseY;
    }

    public int getMouseX()
    {
        return mouseX;
    }

    public int getMouseY()
    {
        return mouseY;
    }

}
