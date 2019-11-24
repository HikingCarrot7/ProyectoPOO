package com.sw.controller;

import com.sw.model.Historial;
import com.sw.others.MyMouseAdapter;
import com.sw.persistence.DAO;
import com.sw.renderer.ComboRenderer;
import com.sw.renderer.ComboRenderer.ComboItem;
import com.sw.utilities.Utilities;
import com.sw.view.HistorialInterfaz;
import com.sw.view.PrendasInterfaz;
import com.sw.view.TicketInterfaz;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mohammed
 */
public class HistorialController extends MyMouseAdapter implements ActionListener
{

    private HistorialInterfaz historialInterfaz;
    private ArrayList<Historial> historiales;
    private boolean historialGeneral;

    public HistorialController(HistorialInterfaz historial)
    {

        this.historialInterfaz = historial;
        historiales = getHistoriales();

        setHistorialGeneral(true);

        initMyComponents();

    }

    public HistorialController(HistorialInterfaz historial, ArrayList<Historial> historiales)
    {

        this.historialInterfaz = historial;
        this.historiales = historiales;

        initMyComponents();

    }

    private void initMyComponents()
    {

        for (int i = historiales.isEmpty() ? -1 : 0; i < historiales.size(); i++)
        {

            historialInterfaz.getVerPrendas().add(new JButton(Utilities.getIcon("/com/src/images/tshirt.png")));
            historialInterfaz.getVerTicket().add(new JButton(Utilities.getIcon("/com/src/images/ticketBoton.png")));
            historialInterfaz.getEliminar().add(new JButton(Utilities.getIcon("/com/src/images/delete.png")));

        }

        loadHistorialTable();

        loadComboModel();

        historialInterfaz.getOrdenarPor().addActionListener(this);

    }

    private void loadHistorialTable()
    {

        TableManager tableManager = new TableManager();

        tableManager.renderTableModel(historialInterfaz.getTablaHistorial(), this, "Historial");

        Object[][] items = getItems();

        historialInterfaz.getTablaHistorial().setModel(new DefaultTableModel(tableManager.loadTableComponents(items, new int[]
        {

            3, 4, 5

        }, historialInterfaz.getVerPrendas(), historialInterfaz.getVerTicket(), historialInterfaz.getEliminar()), new String[]
        {

            "Cliente", "Fecha", "Precio total", "Prendas", "Ver ticket", "Eliminar"

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
        dm.addElement(new ComboRenderer.ComboItem(Utilities.getIcon("/com/src/images/precio.png"), "Precio total"));

        return dm;

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        DataSorterManager dataSorterManager = new DataSorterManager();

        switch (((ComboItem) ((JComboBox) e.getSource()).getSelectedItem()).getText())
        {

            case "Nombre":

                dataSorterManager.ordenarPorNombreHistorial(historiales);

                break;

            case "Fecha":

                dataSorterManager.ordenarPorFechaHistorial(historiales);

                break;

            case "Precio total":

                dataSorterManager.ordenarPorPrecioTotalHistorial(historiales);

                break;

            default:
                break;

        }

        if (isHistorialGeneral())
            saveHistoriales();

        updateTableHistorial();

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

        TableManager tableManager = new TableManager();
        JTable table = historialInterfaz.getTablaHistorial();

        if (!tableManager.isFirstRowEmpty(table))
            if (tableManager.encimaBoton(table, e.getX(), e.getY(), 3))
                EventQueue.invokeLater(() ->
                {

                    PrendasInterfaz prendas = new PrendasInterfaz();

                    prendas.setVisible(true);
                    prendas.setLocationRelativeTo(historialInterfaz);

                    Historial historial = historiales.get(table.getSelectedRow());

                    new PrendasController(prendas, historiales.get(table.getSelectedRow()).getPrendas(), historial.getTotalKg(), historial.getPrecioTotal() / historial.getTotalKg());

                });

            else if (tableManager.encimaBoton(table, e.getX(), e.getY(), 4))
                EventQueue.invokeLater(() ->
                {

                    TicketInterfaz ticketInterfaz = new TicketInterfaz();

                    ticketInterfaz.setVisible(true);
                    ticketInterfaz.setLocationRelativeTo(null);

                    new VerTicketController(ticketInterfaz, historiales.get(table.getSelectedRow()).getTicket()).mostrarTicket();

                });

            else if (tableManager.encimaBoton(table, e.getX(), e.getY(), 5))
                switch (mostrarConfirmacion("Confirmar acción.", "Se eliminará toda la información relacionada con este servicio. ¿Continuar?"))
                {

                    case 0: // Si se presiona Sí

                        eliminarElementoHistorial(table.getSelectedRow());

                        break;

                }

    }

    private Object[][] getItems()
    {

        if (historiales.isEmpty())
            return new Object[1][6];

        Object[][] items = new Object[historiales.size()][6];

        for (int i = 0; i < historiales.size(); i++)
        {

            items[i][0] = historiales.get(i).getCliente().getNombre();
            items[i][1] = String.format("%-30s%tr", String.format("%1$ta, %1$tb %1$te, %1$ty", historiales.get(i).getFecha()), historiales.get(i).getFecha());
            items[i][2] = String.format("$%,.2f", historiales.get(i).getPrecioTotal());

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

    private void eliminarElementoHistorial(int index)
    {

        new TableManager().removeRow(historialInterfaz.getTablaHistorial(), index);

        historiales.remove(index);

        saveHistoriales();

        updateTableHistorial();

    }

    private void updateTableHistorial()
    {
        new TableManager().setTableItems(historialInterfaz.getTablaHistorial(), getItems());
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

    private int mostrarConfirmacion(String titulo, String text)
    {
        return JOptionPane.showConfirmDialog(historialInterfaz, text, titulo, JOptionPane.YES_NO_OPTION);
    }

    public void setHistorialInterfaz(HistorialInterfaz historialInterfaz)
    {
        this.historialInterfaz = historialInterfaz;
    }

    private boolean isHistorialGeneral()
    {
        return historialGeneral;
    }

    private void setHistorialGeneral(boolean historialGeneral)
    {
        this.historialGeneral = historialGeneral;
    }

}
