package com.sw.controller;

import com.game.src.main.Game;
import com.sw.model.Cliente;
import com.sw.model.ServicioInicial;
import com.sw.persistence.DAO;
import com.sw.renderer.ComboRenderer;
import com.sw.utilities.Utilities;
import com.sw.view.ClientesRegistradosInterfaz;
import com.sw.view.NuevoServicio;
import com.sw.view.PrendasInterfaz;
import com.sw.view.VistaPrincipal;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mohammed
 */
public class VistaPrincipalController extends MouseAdapter implements ActionListener, Observer
{

    private VistaPrincipal vistaPrincipal;
    private ArrayList<ServicioInicial> serviciosEnCola;
    private ArrayList<ServicioInicial> serviciosEnProceso;
    private ArrayList<ServicioInicial> serviciosTerminados;

    public VistaPrincipalController(VistaPrincipal vistaPrincipal)
    {

        this.vistaPrincipal = vistaPrincipal;
        serviciosEnCola = getServicios(DAO.RUTA_SERVICIOSENCOLA);
        serviciosEnProceso = getServicios(DAO.RUTA_SERVICIOSENPROCESO);
        serviciosTerminados = getServicios(DAO.RUTA_SERVICIOSTERMINADOS);

        initMyComponents();

        renderTableEnCola();

        renderTableEnProceso();

        renderTableTerminado();

        loadComboModel();

        vistaPrincipal.getNuevoServicio().addActionListener(this);
        vistaPrincipal.getVerClientes().addActionListener(this);

        vistaPrincipal.getPanelPrincipal().addMouseListener(this);

        vistaPrincipal.getOrdenarPor().addActionListener(this);

        vistaPrincipal.getWave().addActionListener(this);

    }

    private void initMyComponents()
    {

        if (serviciosEnCola.isEmpty())
            loadBotonesTablaEnCola();

        else
            for (int i = 0; i < serviciosEnCola.size(); i++)
                loadBotonesTablaEnCola();

        if (serviciosEnProceso.isEmpty())
            loadBotonesTablaEnProceso();

        else
            for (int i = 0; i < serviciosEnProceso.size(); i++)
                loadBotonesTablaEnProceso();

        if (serviciosTerminados.isEmpty())
            loadBotonesTablaTerminado();

        else
            for (int i = 0; i < serviciosTerminados.size(); i++)
                loadBotonesTablaTerminado();

    }

    private void loadBotonesTablaEnCola()
    {

        vistaPrincipal.getVerPrendasEnCola().add(new JButton(Utilities.getIcon("/com/src/images/tshirt.png")));
        vistaPrincipal.getMoverLavadoEnCola().add(new JButton(Utilities.getIcon("/com/src/images/down.png")));
        vistaPrincipal.getEliminarEnCola().add(new JButton(Utilities.getIcon("/com/src/images/delete.png")));

    }

    private void loadBotonesTablaEnProceso()
    {

        vistaPrincipal.getVerPrendasEnProceso().add(new JButton(Utilities.getIcon("/com/src/images/tshirt.png")));
        vistaPrincipal.getSubirColaEnProceso().add(new JButton(Utilities.getIcon("/com/src/images/up.png")));
        vistaPrincipal.getMoverTerminadoEnProceso().add(new JButton(Utilities.getIcon("/com/src/images/down.png")));
        vistaPrincipal.getEliminarEnProceso().add(new JButton(Utilities.getIcon("/com/src/images/delete.png")));

    }

    private void loadBotonesTablaTerminado()
    {

        vistaPrincipal.getVerPrendasTerminado().add(new JButton(Utilities.getIcon("/com/src/images/tshirt.png")));
        vistaPrincipal.getSubirProcesoTerminado().add(new JButton(Utilities.getIcon("/com/src/images/up.png")));
        vistaPrincipal.getGenerarTicket().add(new JButton(Utilities.getIcon("/com/src/images/ticket.png")));
        vistaPrincipal.getEliminarTerminado().add(new JButton(Utilities.getIcon("/com/src/images/delete.png")));

    }

    private void loadComboModel()
    {

        vistaPrincipal.getOrdenarPor().setRenderer(new ComboRenderer());
        vistaPrincipal.getOrdenarPor().setModel(loadComboItems());

    }

    private DefaultComboBoxModel<ComboRenderer.ComboItem> loadComboItems()
    {

        DefaultComboBoxModel<ComboRenderer.ComboItem> dm = new DefaultComboBoxModel<>();

        dm.addElement(new ComboRenderer.ComboItem(Utilities.getIcon("/com/src/images/clienteCombo.png"), "Nombre"));
        dm.addElement(new ComboRenderer.ComboItem(Utilities.getIcon("/com/src/images/precio.png"), "Precio total"));
        dm.addElement(new ComboRenderer.ComboItem(Utilities.getIcon("/com/src/images/kilos.png"), "Kilogramos"));
        dm.addElement(new ComboRenderer.ComboItem(Utilities.getIcon("/com/src/images/tshirt.png"), "Total prendas"));

        return dm;

    }

    private void renderTableEnCola()
    {

        TableManager tableManager = new TableManager();

        Object[][] items = getItemsEnCola();

        vistaPrincipal.getTablaEnCola().setModel(new DefaultTableModel(tableManager.loadTableComponents(items, new int[]
        {

            2, 5, 6

        }, vistaPrincipal.getVerPrendasEnCola(), vistaPrincipal.getMoverLavadoEnCola(), vistaPrincipal.getEliminarEnCola()), new String[]
        {

            "Cliente", "N° ticket", "Prendas", "Kilos", "Total", "Mover a lavado", "Eliminar"

        }));

        tableManager.renderTableModel(vistaPrincipal.getTablaEnCola(), this, "En cola");

        vistaPrincipal.getTablaEnCola().setName("En cola");

    }

    private void renderTableEnProceso()
    {

        TableManager tableManager = new TableManager();

        Object[][] items = getItemsEnProceso();

        vistaPrincipal.getTablaEnProceso().setModel(new DefaultTableModel(tableManager.loadTableComponents(items, new int[]
        {
            2, 5, 6, 7

        }, vistaPrincipal.getVerPrendasEnProceso(), vistaPrincipal.getSubirColaEnProceso(),
                vistaPrincipal.getMoverTerminadoEnProceso(), vistaPrincipal.getEliminarEnProceso()), new String[]
        {

            "Cliente", "N° ticket", "Prendas", "Kilos", "Tiempo estimado", "Subir a la cola", "Mover a terminado", "Eliminar"

        }));

        tableManager.renderTableModel(vistaPrincipal.getTablaEnProceso(), this, "En proceso");

    }

    private void renderTableTerminado()
    {

        TableManager tableManager = new TableManager();

        Object[][] items = getItemsTerminado();

        vistaPrincipal.getTablaTerminado().setModel(new DefaultTableModel(tableManager.loadTableComponents(items, new int[]
        {

            2, 5, 6, 7

        }, vistaPrincipal.getVerPrendasTerminado(), vistaPrincipal.getSubirProcesoTerminado(),
                vistaPrincipal.getGenerarTicket(), vistaPrincipal.getEliminarTerminado()), new String[]
        {

            "Cliente", "N° ticket", "Prendas", "Kilos", "Total", "Subir a proceso", "Generar ticket", "Eliminar"

        }));

        tableManager.renderTableModel(vistaPrincipal.getTablaTerminado(), this, "Terminado");

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

        if (e.getSource() instanceof JTabbedPane)
            revalidateAllTables();

        else if (e.getSource() instanceof JTable)
            switch (((Component) e.getSource()).getName())
            {
                case "En cola":

                    gestionarTablaEnCola(e);

                    break;

                case "En proceso":

                    gestionarTablaEnProceso(e);

                    break;

                case "Terminado":

                    gestionarTablaTerminado(e);

                    break;

                default:
                    break;

            }

    }

    private void gestionarTablaEnCola(MouseEvent e)
    {

        TableManager tableManager = new TableManager();
        JTable table = vistaPrincipal.getTablaEnCola();

        if (!tableManager.isFirstRowEmpty(table))
        {

            if (tableManager.encimaBoton(table, e.getX(), e.getY(), 2))
                EventQueue.invokeLater(() ->
                {

                    PrendasInterfaz prendas = new PrendasInterfaz();

                    prendas.setVisible(true);
                    prendas.setLocationRelativeTo(vistaPrincipal);

                    new PrendasController(prendas, this, serviciosEnCola.get(table.getSelectedRow()));

                });

            else if (tableManager.encimaBoton(table, e.getX(), e.getY(), 5))
            {

                anadirServicioAProceso(serviciosEnCola.get(table.getSelectedRow()));

                eliminarServicioCola(table.getSelectedRow());

            } else if (tableManager.encimaBoton(table, e.getX(), e.getY(), 6))
                switch (mostrarConfirmacion("Confirmar acción", "Se borrará este servicio y toda la información relacionada con él. ¿Continuar?"))
                {

                    case 0: // Si se presiona Sí

                        eliminarServicioCola(table.getSelectedRow());

                        break;

                }

        } else
            mostrarError("No hay servicios", "Aún no hay en la cola servicios");

    }

    private void gestionarTablaEnProceso(MouseEvent e)
    {

        TableManager tableManager = new TableManager();
        JTable table = vistaPrincipal.getTablaEnProceso();

        if (!tableManager.isFirstRowEmpty(table))
        {

            if (tableManager.encimaBoton(table, e.getX(), e.getY(), 2))
                EventQueue.invokeLater(() ->
                {

                    PrendasInterfaz prendas = new PrendasInterfaz();

                    prendas.setVisible(true);
                    prendas.setLocationRelativeTo(vistaPrincipal);

                    new PrendasController(prendas, this, serviciosEnProceso.get(table.getSelectedRow()));

                });

            else if (tableManager.encimaBoton(table, e.getX(), e.getY(), 5))
            {

                anadirServicioCola(serviciosEnProceso.get(table.getSelectedRow()));

                eliminarServicioEnProceso(table.getSelectedRow());

            } else if (tableManager.encimaBoton(table, e.getX(), e.getY(), 6))
            {

                anadirServicioTerminado(serviciosEnProceso.get(table.getSelectedRow()));

                eliminarServicioEnProceso(table.getSelectedRow());

            } else if (tableManager.encimaBoton(table, e.getX(), e.getY(), 7))
                switch (mostrarConfirmacion("Confirmar acción", "Se borrará este servicio y toda la información relacionada con él. ¿Continuar?"))
                {

                    case 0: // Si se presiona Sí

                        eliminarServicioEnProceso(table.getSelectedRow());

                        break;

                }

        } else
            mostrarError("No hay servicios", "Aún no hay servicios en proceso");

    }

    private void gestionarTablaTerminado(MouseEvent e)
    {

        TableManager tableManager = new TableManager();
        JTable table = vistaPrincipal.getTablaTerminado();

        if (!tableManager.isFirstRowEmpty(table))
        {

            if (tableManager.encimaBoton(table, e.getX(), e.getY(), 2))
                EventQueue.invokeLater(() ->
                {

                    PrendasInterfaz prendas = new PrendasInterfaz();

                    prendas.setVisible(true);
                    prendas.setLocationRelativeTo(vistaPrincipal);

                    new PrendasController(prendas, this, serviciosTerminados.get(table.getSelectedRow()));

                });

            else if (tableManager.encimaBoton(table, e.getX(), e.getY(), 5))
            {

                anadirServicioAProceso(serviciosTerminados.get(table.getSelectedRow()));

                eliminarServicioTerminado(table.getSelectedRow());

            } else if (tableManager.encimaBoton(table, e.getX(), e.getY(), 6))
            {

                //Aquí debe de ir algo...
            } else if (tableManager.encimaBoton(table, e.getX(), e.getY(), 7))
                switch (mostrarConfirmacion("Confirmar acción", "No podrá generar el ticket para este servicio. ¿Continuar?"))
                {

                    case 0: // Si se presiona Sí

                        eliminarServicioTerminado(table.getSelectedRow());

                        break;

                }

        } else
            mostrarError("No hay servicios", "Aún no hay servicios terminados");

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        if (e.getSource() instanceof JButton)
            switch (e.getActionCommand())
            {

                case "Nuevo servicio":

                    EventQueue.invokeLater(() ->
                    {

                        NuevoServicio nuevoServicio = new NuevoServicio();

                        nuevoServicio.setVisible(true);
                        nuevoServicio.setLocationRelativeTo(vistaPrincipal);

                        new NuevoServicioController(nuevoServicio, this);

                    });

                    break;

                case "Ver clientes":

                    EventQueue.invokeLater(() ->
                    {

                        ClientesRegistradosInterfaz clientesRegistradosInterfaz = new ClientesRegistradosInterfaz();

                        clientesRegistradosInterfaz.setVisible(true);
                        clientesRegistradosInterfaz.setLocationRelativeTo(vistaPrincipal);

                        ClientesRegistradosController clientesRegistradosController = new ClientesRegistradosController(clientesRegistradosInterfaz);

                        clientesRegistradosController.addObserver(this);

                    });

                    break;

                case "Editar":

                    break;

                default:
                    break;

            }

        else if (e.getSource() instanceof JComboBox)
            ordernarPor(e);

        else if (e.getSource() instanceof JMenuItem)
            Game.main(null);

    }

    private void ordernarPor(ActionEvent e)
    {
        DataSorterManager dataSorterManager = new DataSorterManager();

        switch (((JComboBox) e.getSource()).getSelectedIndex())
        {

            case 0:

                dataSorterManager.ordenarPorNombreServicios(serviciosEnCola);
                dataSorterManager.ordenarPorNombreServicios(serviciosEnProceso);
                dataSorterManager.ordenarPorNombreServicios(serviciosTerminados);

                break;

            case 1:

                dataSorterManager.ordenarPorPrecioTotal(serviciosEnCola);
                dataSorterManager.ordenarPorPrecioTotal(serviciosEnProceso);
                dataSorterManager.ordenarPorPrecioTotal(serviciosTerminados);

                break;

            case 2:

                dataSorterManager.ordenarPorTotalKg(serviciosEnCola);
                dataSorterManager.ordenarPorTotalKg(serviciosEnProceso);
                dataSorterManager.ordenarPorTotalKg(serviciosTerminados);

                break;

            case 3:

                dataSorterManager.ordenarPorTotalPiezas(serviciosEnCola);
                dataSorterManager.ordenarPorTotalPiezas(serviciosEnProceso);
                dataSorterManager.ordenarPorTotalPiezas(serviciosTerminados);

                break;

            default:
                break;

        }

        updateAllTables();

        saveAllServices();

    }

    private Object[][] getItemsEnCola()
    {

        if (serviciosEnCola.isEmpty())
            return new Object[1][7];

        Object[][] items = new Object[serviciosEnCola.size()][7];

        for (int i = 0; i < serviciosEnCola.size(); i++)
        {

            items[i][0] = serviciosEnCola.get(i).getCliente().getNombre();
            items[i][1] = String.valueOf(serviciosEnCola.get(i).getNumeroTicket());
            items[i][3] = String.valueOf(serviciosEnCola.get(i).getTotalKg());
            items[i][4] = String.format("$%,.2f", serviciosEnCola.get(i).getPrecioTotal());

        }

        return items;

    }

    private Object[][] getItemsEnProceso()
    {

        if (serviciosEnProceso.isEmpty())
            return new Object[1][8];

        Object[][] items = new Object[serviciosEnProceso.size()][8];

        for (int i = 0; i < serviciosEnProceso.size(); i++)
        {

            items[i][0] = serviciosEnProceso.get(i).getCliente().getNombre();
            items[i][1] = String.valueOf(serviciosEnProceso.get(i).getNumeroTicket());
            items[i][3] = String.valueOf(serviciosEnProceso.get(i).getTotalKg());
            items[i][4] = serviciosEnProceso.get(i).getTiempoEstimado().toString();

        }

        return items;

    }

    private Object[][] getItemsTerminado()
    {

        if (serviciosTerminados.isEmpty())
            return new Object[1][8];

        Object[][] items = new Object[serviciosTerminados.size()][8];

        for (int i = 0; i < serviciosTerminados.size(); i++)
        {

            items[i][0] = serviciosTerminados.get(i).getCliente().getNombre();
            items[i][1] = String.valueOf(serviciosTerminados.get(i).getNumeroTicket());
            items[i][3] = String.valueOf(serviciosTerminados.get(i).getTotalKg());
            items[i][4] = String.format("$%,.2f", serviciosTerminados.get(i).getPrecioTotal());

        }

        return items;

    }

    public void anadirServicioCola(ServicioInicial servicioInicial)
    {

        serviciosEnCola.add(servicioInicial);

        new TableManager().addRow(vistaPrincipal.getTablaEnCola(), new Object[]
        {

            servicioInicial.getCliente().getNombre(), "", null, "", "", null, null

        });

        updateAllTables();

        saveServiciosEnCola();

    }

    private void anadirServicioAProceso(ServicioInicial servicioInicial)
    {

        serviciosEnProceso.add(servicioInicial);

        new TableManager().addRow(vistaPrincipal.getTablaEnProceso(), new Object[]
        {

            servicioInicial.getCliente().getNombre(), "", null, "", "", null, null, null

        });

        saveServiciosEnProceso();

        updateAllTables();

    }

    private void anadirServicioTerminado(ServicioInicial servicioInicial)
    {

        serviciosTerminados.add(servicioInicial);

        new TableManager().addRow(vistaPrincipal.getTablaTerminado(), new Object[]
        {

            servicioInicial.getCliente().getNombre(), "", null, "", "", null, null, null

        });

        saveServiciosTerminados();

        updateAllTables();

    }

    private void eliminarServicioCola(int index)
    {

        new TableManager().removeRow(vistaPrincipal.getTablaEnCola(), index);

        serviciosEnCola.remove(index);

        saveServiciosEnCola();

        updateAllTables();

    }

    private void eliminarServicioEnProceso(int index)
    {

        new TableManager().removeRow(vistaPrincipal.getTablaEnProceso(), index);

        serviciosEnProceso.remove(index);

        saveServiciosEnProceso();

        updateAllTables();

    }

    private void eliminarServicioTerminado(int index)
    {

        new TableManager().removeRow(vistaPrincipal.getTablaTerminado(), index);

        serviciosTerminados.remove(index);

        saveServiciosTerminados();

        updateAllTables();

    }

    public void saveServiciosEnCola()
    {
        new DAO(DAO.RUTA_SERVICIOSENCOLA).saveObjects(serviciosEnCola);
    }

    public void saveServiciosEnProceso()
    {
        new DAO(DAO.RUTA_SERVICIOSENPROCESO).saveObjects(serviciosEnProceso);
    }

    public void saveServiciosTerminados()
    {
        new DAO(DAO.RUTA_SERVICIOSTERMINADOS).saveObjects(serviciosTerminados);
    }

    private ArrayList<ServicioInicial> getServicios(String ruta)
    {
        return (ArrayList<ServicioInicial>) new DAO(ruta).getObjects();
    }

    public void saveAllServices()
    {

        saveServiciosEnCola();
        saveServiciosEnProceso();
        saveServiciosTerminados();

    }

    public void updateAllTables()
    {

        TableManager tableManager = new TableManager();

        tableManager.setTableItems(vistaPrincipal.getTablaEnCola(), getItemsEnCola());

        tableManager.setTableItems(vistaPrincipal.getTablaEnProceso(), getItemsEnProceso());

        tableManager.setTableItems(vistaPrincipal.getTablaTerminado(), getItemsTerminado());

        revalidateAllTables();

    }

    private void revalidateAllTables()
    {

        vistaPrincipal.getTablaEnCola().getParent().revalidate();
        vistaPrincipal.getTablaEnProceso().getParent().revalidate();
        vistaPrincipal.getTablaTerminado().getParent().revalidate();

    }

    private void mostrarError(String titulo, String text)
    {
        JOptionPane.showMessageDialog(vistaPrincipal, text, titulo, JOptionPane.ERROR_MESSAGE);
    }

    private int mostrarConfirmacion(String titulo, String text)
    {
        return JOptionPane.showConfirmDialog(vistaPrincipal, text, titulo, JOptionPane.YES_NO_OPTION);
    }

    @Override
    public void update(Observable o, Object item)
    {

        Cliente clienteNuevo = (Cliente) item;

        updateCliente(serviciosEnCola, clienteNuevo);
        updateCliente(serviciosEnProceso, clienteNuevo);
        updateCliente(serviciosTerminados, clienteNuevo);

        saveAllServices();

        updateAllTables();

    }

    private void updateCliente(ArrayList<ServicioInicial> servicio, Cliente clienteNuevosDatos)
    {

        for (int i = 0; i < servicio.size(); i++)
            if (clienteNuevosDatos.getClaveCliente() == servicio.get(i).getCliente().getClaveCliente())
                servicio.get(i).getCliente().setNombre(clienteNuevosDatos.getNombre());

    }

    public VistaPrincipal getVistaPrincipal()
    {
        return vistaPrincipal;
    }

}
