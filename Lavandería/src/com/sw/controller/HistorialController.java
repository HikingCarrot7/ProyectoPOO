package com.sw.controller;

import com.sw.model.Historial;
import com.sw.persistence.DAO;
import com.sw.renderer.ComboRenderer;
import com.sw.utilities.Utilities;
import com.sw.view.HistorialInterfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mohammed
 */
public class HistorialController extends MouseAdapter implements ActionListener
{

    private HistorialInterfaz historialInterfaz;
    private ArrayList<Historial> historiales;

    public HistorialController(HistorialInterfaz historial)
    {
        this.historialInterfaz = historial;

        historiales = getHistoriales();

        initMyComponents();

        loadHistorialTable();

        loadComboModel();

        historial.getOrdenarPor().addActionListener(this);

    }

    private void initMyComponents()
    {

        for (int i = historiales.isEmpty() ? -1 : 0; i < historiales.size(); i++)
        {

            historialInterfaz.getVerPrendas().add(new JButton(Utilities.getIcon("/com/src/images/tshirt.png")));
            historialInterfaz.getVerTicket().add(new JButton(Utilities.getIcon("/com/src/images/ticketBoton.png")));
            historialInterfaz.getEliminar().add(new JButton(Utilities.getIcon("/com/src/images/delete.png")));

        }

    }

    private void loadHistorialTable()
    {

        TableManager tableManager = new TableManager();

        tableManager.renderTableModel(historialInterfaz.getTablaHistorial(), this, "Historial");

        Object[][] items = getItems();

        historialInterfaz.getTablaHistorial().setModel(new DefaultTableModel(tableManager.loadTableComponents(items, new int[]
        {

            1, 4, 5

        }, historialInterfaz.getVerPrendas(), historialInterfaz.getVerTicket(), historialInterfaz.getEliminar()), new String[]
        {

            "Cliente", "Prendas", "Fecha", "Precio total", "Ver ticket", "Eliminar"

        }));

    }

    private void loadComboModel()
    {

        historialInterfaz.getOrdenarPor().setRenderer(new ComboRenderer());
        historialInterfaz.getOrdenarPor().setModel(loadComboItems());

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

    private Object[][] getItems()
    {

        if (historiales.isEmpty())
            return new Object[1][6];

        Object[][] items = new Object[historiales.size()][6];

        for (int i = 0; i < historiales.size(); i++)
        {

            items[i][0] = historiales.get(i).getCliente().getNombre();
            items[i][2] = String.format("%tF", historiales.get(i).getFecha());
            items[i][3] = String.format("$%,.2f", historiales.get(i).getPrecioTotal());

        }

        return items;

    }

    public void anadirHistorial(Historial historial)
    {

        historiales.add(historial);

        new TableManager().addRow(historialInterfaz.getTablaHistorial(), new Object[]
        {

            historial.getCliente().getNombre(), null, "", "", null, null

        });

        updateTableHistorial();

        saveHistoriales();

    }

    private void updateTableHistorial()
    {

        TableManager tableManager = new TableManager();

        tableManager.setTableItems(historialInterfaz.getTablaHistorial(), getItems());

    }

    private void saveHistoriales()
    {
        new DAO(DAO.RUTA_HISTORIALES).saveObjects(historiales);
    }

    private ArrayList<Historial> getHistoriales()
    {
        return (ArrayList<Historial>) new DAO(DAO.RUTA_HISTORIALES).getObjects();
    }

    public HistorialInterfaz getHistorialInterfaz()
    {
        return historialInterfaz;
    }

    public void setHistorialInterfaz(HistorialInterfaz historialInterfaz)
    {
        this.historialInterfaz = historialInterfaz;
    }

}
