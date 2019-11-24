package com.sw.renderer;

import com.sw.controller.TableManager;
import com.sw.others.MouseMotionModel;
import com.sw.persistence.ConfigDAO;
import com.sw.utilities.Utilities;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Mohammed
 */
public class TableCellRenderer extends DefaultTableCellRenderer implements MouseMotionModel
{

    private static final long serialVersionUID = -2422440758692459668L;

    private int x;
    private int y;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {

        table.getColumnModel().getColumn(0).setWidth(table.getWidth() == 904 ? 250
                : table.getWidth() == 913 ? 280
                : table.getColumnCount() == 2 ? 370
                : table.getColumnCount() == 6 ? 350
                : table.getColumnCount() == 7 ? 350
                : table.getColumnCount() == 8 ? 350 : 280);

        table.setRowHeight(30);

        if (value instanceof JButton)
            switch (table.getName())
            {

                case "Clientes":

                    updateIcon(((JButton) value), table, 5, "/com/src/images/historialSelected.png", "/com/src/images/historial.png");
                    return (Component) value;

                case "Prendas":

                    updateIcon(((JButton) value), table, 3, "/com/src/images/deleteSelected.png", "/com/src/images/delete.png");

                    return (Component) value;

                case "Historial":

                    switch (column)
                    {

                        case 3:

                            updateIcon(((JButton) value), table, column, "/com/src/images/tshirtSelected.png", "/com/src/images/tshirt.png");
                            break;

                        case 4:

                            updateIcon(((JButton) value), table, column, "/com/src/images/ticketSelected.png", "/com/src/images/ticket.png");
                            break;

                        case 5:

                            updateIcon(((JButton) value), table, column, "/com/src/images/deleteSelected.png", "/com/src/images/delete.png");

                    }

                    return (Component) value;

                case "En cola":
                    switch (column)
                    {

                        case 2:

                            updateIcon(((JButton) value), table, column, "/com/src/images/tshirtSelected.png", "/com/src/images/tshirt.png");
                            break;

                        case 5:

                            updateIcon(((JButton) value), table, column, "/com/src/images/downSelected.png", "/com/src/images/down.png");
                            break;

                        case 6:

                            updateIcon(((JButton) value), table, column, "/com/src/images/deleteSelected.png", "/com/src/images/delete.png");
                            break;

                    }

                    return (Component) value;

                case "Terminado":
                case "En proceso":
                    switch (column)
                    {

                        case 2:

                            updateIcon(((JButton) value), table, column, "/com/src/images/tshirtSelected.png", "/com/src/images/tshirt.png");
                            break;

                        case 5:

                            updateIcon(((JButton) value), table, column, "/com/src/images/upSelected.png", "/com/src/images/up.png");
                            break;

                        case 6:

                            if (!table.getName().equals("Terminado"))
                                updateIcon(((JButton) value), table, column, "/com/src/images/downSelected.png", "/com/src/images/down.png");

                            else
                                updateIcon(((JButton) value), table, column, "/com/src/images/ticketSelected.png", "/com/src/images/ticket.png");

                            break;

                        case 7:

                            updateIcon(((JButton) value), table, column, "/com/src/images/deleteSelected.png", "/com/src/images/delete.png");

                    }

                    return (Component) value;

                case "Tipos prendas":
                    updateIcon(((JButton) value), table, 1, "/com/src/images/deleteSelected.png", "/com/src/images/delete.png");
                    return (Component) value;

                default:
                    return (Component) value;

            }

        else if (value instanceof JLabel)
        {

            ((JLabel) value).setHorizontalAlignment(SwingConstants.LEFT);
            ((Component) value).setSize(30, ((Component) value).getWidth());
            ((Component) value).setPreferredSize(new Dimension(6, ((Component) value).getWidth()));

            Color color = new ConfigDAO().getColorTablas();

            ((JComponent) value).setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(255, 255, 255)));
            ((JComponent) value).setOpaque(true);
            ((Component) value).setBackground(row % 2 == 0 ? color.brighter() : Color.white);
            ((Component) value).setForeground(Color.black);

            if (row == table.getSelectedRow())
                ((Component) value).setBackground(color.darker());

            return ((Component) value);

        }

        JComponent jcomponent = new JLabel((String) value);
        ((JLabel) jcomponent).setHorizontalAlignment(SwingConstants.LEFT);
        jcomponent.setSize(30, jcomponent.getWidth());
        jcomponent.setPreferredSize(new Dimension(6, jcomponent.getWidth()));

        Color color = new ConfigDAO().getColorTablas();

        jcomponent.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(255, 255, 255)));
        jcomponent.setOpaque(true);
        jcomponent.setBackground(row % 2 == 0 ? color.brighter() : Color.white);
        jcomponent.setForeground(Color.black);

        if (row == table.getSelectedRow())
            jcomponent.setBackground(color.darker());

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
