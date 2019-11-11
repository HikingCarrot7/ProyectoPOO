package com.sw.renderer;

import com.sw.controller.TableManager;
import com.sw.controller.MouseMotionModel;
import com.sw.utilities.Utilities;
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
public class TableCellRenderer extends DefaultTableCellRenderer implements MouseMotionModel
{

    private int x;
    private int y;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {

        table.getColumnModel().getColumn(0).setWidth(table.getWidth() == 904 ? 325 : table.getWidth() == 913 ? 310 : table.getColumnCount() >= 6 ? 450 : table.getColumnCount() == 2 ? 370 : 255);
        table.setRowHeight(30);

        if (value instanceof JButton)
            switch (table.getName())
            {
                case "Clientes":
                    updateIcon(((JButton) value), table, 5, "/com/src/images/historialSelected.png", "/com/src/images/historial.png");
                    return (JButton) value;

                case "Prendas":
                case "Historial":

                    updateIcon(((JButton) value), table, column == 1 ? 1 : table.getName().equals("Historial") ? 5 : 3,
                            column == 1 ? "/com/src/images/tshirtSelected.png" : "/com/src/images/deleteSelected.png",
                            column == 1 ? "/com/src/images/tshirt.png" : "/com/src/images/delete.png");

                    return (JButton) value;

                case "En cola":
                    switch (column)
                    {
                        case 1:
                            updateIcon(((JButton) value), table, column, "/com/src/images/tshirtSelected.png", "/com/src/images/tshirt.png");
                            return (JButton) value;

                        case 4:
                            updateIcon(((JButton) value), table, column, "/com/src/images/downSelected.png", "/com/src/images/down.png");
                            return (JButton) value;

                        case 5:
                            updateIcon(((JButton) value), table, column, "/com/src/images/deleteSelected.png", "/com/src/images/delete.png");
                            return (JButton) value;

                    }

                case "Terminado":
                case "En proceso":
                    switch (column)
                    {
                        case 1:
                            updateIcon(((JButton) value), table, column, "/com/src/images/tshirtSelected.png", "/com/src/images/tshirt.png");
                            return (JButton) value;

                        case 4:
                            updateIcon(((JButton) value), table, column, "/com/src/images/upSelected.png", "/com/src/images/up.png");
                            return (JButton) value;

                        case 5:
                            if (!table.getName().equals("Terminado"))
                                updateIcon(((JButton) value), table, column, "/com/src/images/downSelected.png", "/com/src/images/down.png");

                            return (JButton) value;

                        case 6:
                            updateIcon(((JButton) value), table, column, "/com/src/images/deleteSelected.png", "/com/src/images/delete.png");
                            return (JButton) value;

                    }

                case "Tipos prenda":
                    updateIcon(((JButton) value), table, 1, "/com/src/images/deleteSelected.png", "/com/src/images/delete.png");
                    return (JButton) value;

                default:
                    return (JButton) value;

            }

        if (value instanceof JCheckBox)
        {
            JComponent jcomponent = new JPanel();
            ((JPanel) jcomponent).setLayout(null);
            ((JCheckBox) value).setBounds(table.getColumnModel().getColumn(5).getWidth() / 2 - 8, 7, 16, 16);
            ((JPanel) jcomponent).add(((JCheckBox) value));
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

    private void updateIcon(JButton boton, JTable table, int column, String rutaSelected, String rutaDefault)
    {

        boton.setIcon(Utilities.getIcon(new TableManager().encimaBoton(table, boton, getX(), getY(), column)
                ? rutaSelected : rutaDefault));

    }

    @Override
    public int getX()
    {
        return x;
    }

    @Override
    public void setX(int x)
    {
        this.x = x;
    }

    @Override
    public int getY()
    {
        return y;
    }

    @Override
    public void setY(int y)
    {
        this.y = y;
    }

}
