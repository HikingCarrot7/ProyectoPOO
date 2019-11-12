package com.sw.controller;

import com.sw.renderer.ComboRenderer;
import com.sw.utilities.Utilities;
import com.sw.view.Historial;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mohammed
 */
public class HistorialController extends MouseAdapter implements ActionListener
{

    private Historial historial;

    public HistorialController(Historial historial)
    {
        this.historial = historial;

        loadHistorialTable();

        loadComboModel();

        historial.getOrdenarPor().addActionListener(this);

    }

    private void loadHistorialTable()
    {

        TableManager tableManager = new TableManager();

        tableManager.renderTableModel(historial.getHistorial(), this, "Historial");

        Object[][] items = new Object[5][6];

        historial.getHistorial().setModel(new DefaultTableModel(tableManager.loadTableComponents(items, new int[]
        {
            1, 5

        }, historial.getVerPrendas(), historial.getEliminar()), new String[]
        {

            "Cliente", "Prendas", "Fecha", "Total kg.", "Precio total", "Eliminar"

        }));

    }

    private void loadComboModel()
    {

        historial.getOrdenarPor().setRenderer(new ComboRenderer());
        historial.getOrdenarPor().setModel(loadComboItems());

    }

    private DefaultComboBoxModel<ComboRenderer.ComboItem> loadComboItems()
    {

        DefaultComboBoxModel<ComboRenderer.ComboItem> dm = new DefaultComboBoxModel<>();

        dm.addElement(new ComboRenderer.ComboItem(Utilities.getIcon("/com/src/images/name.png"), "Nombre"));

        dm.addElement(new ComboRenderer.ComboItem(Utilities.getIcon("/com/src/images/fecha.png"), "Fecha"));

        return dm;

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

}
