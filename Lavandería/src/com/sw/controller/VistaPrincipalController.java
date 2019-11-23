package com.sw.controller;

import com.sw.model.Cliente;
import com.sw.model.Historial;
import com.sw.model.Servicio;
import com.sw.model.Ticket;
import com.sw.others.MyMouseAdapter;
import com.sw.persistence.ServicioDAO;
import com.sw.persistence.TicketDAO;
import com.sw.renderer.ComboRenderer;
import com.sw.utilities.Utilities;
import com.sw.view.ClientesRegistradosInterfaz;
import com.sw.view.ConfiguracionInterfaz;
import com.sw.view.HistorialInterfaz;
import com.sw.view.NuevoServicio;
import com.sw.view.PrendasInterfaz;
import com.sw.view.TicketInterfaz;
import com.sw.view.VistaPrincipal;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;
import javax.swing.AbstractButton;
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
public class VistaPrincipalController extends MyMouseAdapter implements ActionListener, Observer
{

    private VistaPrincipal vistaPrincipal;
    private String selectedTable;
    private ArrayList<Servicio> serviciosEnCola;
    private ArrayList<Servicio> serviciosEnProceso;
    private ArrayList<Servicio> serviciosTerminados;

    public VistaPrincipalController(VistaPrincipal vistaPrincipal)
    {

        this.vistaPrincipal = vistaPrincipal;
        serviciosEnCola = getServicios(ServicioDAO.RUTA_SERVICIOSENCOLA);
        serviciosEnProceso = getServicios(ServicioDAO.RUTA_SERVICIOSENPROCESO);
        serviciosTerminados = getServicios(ServicioDAO.RUTA_SERVICIOSTERMINADOS);

        selectedTable = "En cola";

        initMyComponents();

        renderTableEnCola();

        renderTableEnProceso();

        renderTableTerminado();

        loadComboModel();

    }

    private void initMyComponents()
    {

        for (int i = serviciosEnCola.isEmpty() ? -1 : 0; i < serviciosEnCola.size(); i++)
            loadBotonesTablaEnCola();

        for (int i = serviciosEnProceso.isEmpty() ? -1 : 0; i < serviciosEnProceso.size(); i++)
            loadBotonesTablaEnProceso();

        for (int i = serviciosTerminados.isEmpty() ? -1 : 0; i < serviciosTerminados.size(); i++)
            loadBotonesTablaTerminado();

        vistaPrincipal.getNuevoServicio().addActionListener(this);
        vistaPrincipal.getEditar().addActionListener(this);
        vistaPrincipal.getVerClientes().addActionListener(this);
        vistaPrincipal.getVerHIstorial().addActionListener(this);

        vistaPrincipal.getPanelPrincipal().addMouseListener(this);

        vistaPrincipal.getOrdenarPor().addActionListener(this);

        vistaPrincipal.getConfigurar().addActionListener(this);

        vistaPrincipal.getWave().addActionListener(this);

        vistaPrincipal.getScrollTablaEnCola().setName("En cola");
        vistaPrincipal.getScrollTablaEnProceso().setName("En proceso");
        vistaPrincipal.getScrollTablaTerminado().setName("Terminado");

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
        {

            setSelectedTable(((JTabbedPane) e.getSource()).getSelectedComponent().getName());
            revalidateAllTables();

        } else if (e.getSource() instanceof JTable)
        {
            setSelectedTable(((Component) e.getSource()).getName());

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

                    Servicio servicio = serviciosEnCola.get(table.getSelectedRow());

                    new PrendasController(prendas, this, servicio);

                });

            else if (tableManager.encimaBoton(table, e.getX(), e.getY(), 5))

                if (!serviciosEnCola.get(table.getSelectedRow()).getPrendas().isEmpty() && serviciosEnCola.get(table.getSelectedRow()).getTotalKg() != 0)
                {
                    anadirServicioAProceso(serviciosEnCola.get(table.getSelectedRow()));
                    eliminarServicioCola(table.getSelectedRow());

                } else
                    mostrarMensaje("Error en el servicio.",
                            "Este servicio debe tener registrada al menos una prenda y el total de kg. no debe ser 0.", JOptionPane.ERROR_MESSAGE);

            else if (tableManager.encimaBoton(table, e.getX(), e.getY(), 6))
                switch (mostrarConfirmacion("Confirmar acción.", "Se borrará este servicio y toda la información relacionada con él. ¿Continuar?"))
                {

                    case 0: // Si se presiona Sí

                        eliminarServicioCola(table.getSelectedRow());

                        break;

                }

        } else
            mostrarMensaje("No hay servicios.", "Aún no hay en la cola servicios.", JOptionPane.ERROR_MESSAGE);

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
                if (serviciosEnProceso.get(table.getSelectedRow()).getTotalKg() != 0)
                {

                    anadirServicioTerminado(serviciosEnProceso.get(table.getSelectedRow()));
                    eliminarServicioEnProceso(table.getSelectedRow());

                } else
                    mostrarMensaje("Error.", "El total de kg. no puede ser 0.", JOptionPane.ERROR_MESSAGE);

            else if (tableManager.encimaBoton(table, e.getX(), e.getY(), 7))
                switch (mostrarConfirmacion("Confirmar acción.", "Se borrará este servicio y toda la información relacionada con él. ¿Continuar?"))
                {

                    case 0: // Si se presiona Sí

                        eliminarServicioEnProceso(table.getSelectedRow());

                        break;

                }

        } else
            mostrarMensaje("No hay servicios.", "Aún no hay servicios en proceso.", JOptionPane.ERROR_MESSAGE);

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

                    if (!serviciosTerminados.get(table.getSelectedRow()).isTicketGenerado())
                        new PrendasController(prendas, this, serviciosTerminados.get(table.getSelectedRow()));

                    else
                    {

                        Servicio servicio = serviciosTerminados.get(table.getSelectedRow());

                        new PrendasController(prendas, servicio.getPrendas(),
                                servicio.getTotalKg(), servicio.getPrecioTotal());
                    }

                });

            else if (tableManager.encimaBoton(table, e.getX(), e.getY(), 5))
                if (!serviciosTerminados.get(table.getSelectedRow()).isTicketGenerado())
                {

                    anadirServicioAProceso(serviciosTerminados.get(table.getSelectedRow()));
                    eliminarServicioTerminado(table.getSelectedRow());

                } else
                    mostrarMensaje("Error.", "Ya ha generado el ticket para este servicio, no puede subirlo a proceso.", JOptionPane.ERROR_MESSAGE);

            else if (tableManager.encimaBoton(table, e.getX(), e.getY(), 6))
            {

                Servicio servicio = serviciosTerminados.get(table.getSelectedRow());

                EventQueue.invokeLater(() ->
                {

                    TicketInterfaz ticketInterfaz = new TicketInterfaz();

                    ticketInterfaz.setVisible(true);
                    ticketInterfaz.setLocationRelativeTo(vistaPrincipal);

                    new VerTicketController(ticketInterfaz, servicio.getTicket()).mostrarTicket();

                });

                anadirServicioAHistorial(servicio);

            } else if (tableManager.encimaBoton(table, e.getX(), e.getY(), 7))
                if (!serviciosTerminados.get(table.getSelectedRow()).isTicketGenerado())
                    switch (mostrarConfirmacion("Confirmar acción.", "No podrá generar el ticket para este servicio. ¿Continuar?"))
                    {

                        case 0: // Si se presiona Sí

                            eliminarServicioTerminado(table.getSelectedRow());

                            break;

                    }
                else
                    eliminarServicioTerminado(table.getSelectedRow());

        } else
            mostrarMensaje("No hay servicios.", "Aún no hay servicios terminados.", JOptionPane.ERROR_MESSAGE);

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

                    Servicio servicio = obtenerServicioSeleccionado();

                    if (servicio != null)
                        if (!selectedTable.equals("Terminado"))
                            EventQueue.invokeLater(() ->
                            {

                                NuevoServicio nuevoServicio = new NuevoServicio();

                                nuevoServicio.setVisible(true);
                                nuevoServicio.setLocationRelativeTo(vistaPrincipal);

                                nuevoServicio.getTitleLabel().setIcon(Utilities.getIcon("/com/src/images/editarTitle.png"));
                                nuevoServicio.getTitleLabel().setText("Editar servicio");

                                new NuevoServicioController(nuevoServicio, this).establecerDatosDefecto(servicio);

                            });
                        else
                            mostrarMensaje("Error.", "No se pueden editar servicios en este punto.", JOptionPane.ERROR_MESSAGE);

                    else
                        mostrarMensaje("Error", "No ha seleccionado ninguna fila o aún no hay servicios en esta tabla", JOptionPane.ERROR_MESSAGE);

                    break;

                case "Historial":

                    EventQueue.invokeLater(() ->
                    {

                        HistorialInterfaz historialInterfaz = new HistorialInterfaz();

                        historialInterfaz.setVisible(true);
                        historialInterfaz.setLocationRelativeTo(vistaPrincipal);

                        HistorialController historialController = new HistorialController(historialInterfaz);

                    });

                    break;

                default:
                    break;

            }

        else if (e.getSource() instanceof JComboBox)
            ordernarPor(e);

        else if (e.getSource() instanceof JMenuItem)
            switch (((AbstractButton) e.getSource()).getActionCommand())
            {

                case "Config":

                    EventQueue.invokeLater(() ->
                    {

                        ConfiguracionInterfaz configInterfaz = new ConfiguracionInterfaz();

                        configInterfaz.setVisible(true);
                        configInterfaz.setLocationRelativeTo(vistaPrincipal);

                        new ConfigController(configInterfaz, this);

                    });

                    break;

                default:
                    break;

            }

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

    public void anadirServicioCola(Servicio servicio)
    {

        serviciosEnCola.add(servicio);

        new TableManager().addRow(vistaPrincipal.getTablaEnCola(), new Object[]
        {

            servicio.getCliente().getNombre(), "", null, "", "", null, null

        });

        updateAllTables();

        saveServiciosEnCola();

    }

    private void anadirServicioAProceso(Servicio servicio)
    {

        serviciosEnProceso.add(servicio);

        new TableManager().addRow(vistaPrincipal.getTablaEnProceso(), new Object[]
        {

            servicio.getCliente().getNombre(), "", null, "", "", null, null, null

        });

        saveServiciosEnProceso();

        updateAllTables();

    }

    private void anadirServicioTerminado(Servicio servicio)
    {

        serviciosTerminados.add(servicio);

        new TableManager().addRow(vistaPrincipal.getTablaTerminado(), new Object[]
        {

            servicio.getCliente().getNombre(), "", null, "", "", null, null, null

        });

        saveServiciosTerminados();

        updateAllTables();

    }

    private void anadirServicioAHistorial(Servicio servicio)
    {

        if (!servicio.isTicketGenerado())
        {

            HistorialInterfaz historialInterfaz = new HistorialInterfaz();

            Historial historial = new Historial(
                    servicio.getCliente(),
                    servicio.getPrendas(),
                    Calendar.getInstance(),
                    servicio.getTicket(),
                    servicio.getTotalKg(),
                    servicio.getPrecioTotal());

            new HistorialController(historialInterfaz).anadirHistorial(historial);

            historialInterfaz.dispose();
            saveTicket(servicio.getTicket());
            servicio.setTicketGenerado(true);
            saveServiciosTerminados();

        }

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

    public void saveAllServices()
    {

        saveServiciosEnCola();
        saveServiciosEnProceso();
        saveServiciosTerminados();

    }

    public void saveServiciosEnCola()
    {
        new ServicioDAO(ServicioDAO.RUTA_SERVICIOSENCOLA).saveObjects(serviciosEnCola);
    }

    public void saveServiciosEnProceso()
    {
        new ServicioDAO(ServicioDAO.RUTA_SERVICIOSENPROCESO).saveObjects(serviciosEnProceso);
    }

    public void saveServiciosTerminados()
    {
        new ServicioDAO(ServicioDAO.RUTA_SERVICIOSTERMINADOS).saveObjects(serviciosTerminados);
    }

    public void saveHistoriales(ArrayList<Historial> historiales)
    {
        new ServicioDAO(ServicioDAO.RUTA_HISTORIALES).saveObjects(historiales);
    }

    public void saveClientesRegistrados(ArrayList<Cliente> clientesRegistrados)
    {
        new ServicioDAO(ServicioDAO.RUTA_CLIENTESREGISTRADOS).saveObjects(clientesRegistrados);
    }

    public void saveTicket(Ticket ticket)
    {
        new TicketDAO().saveTicket(ticket);
    }

    private ArrayList<Servicio> getServicios(String ruta)
    {
        return (ArrayList<Servicio>) new ServicioDAO(ruta).getObjects();
    }

    private ArrayList<Historial> getHistoriales()
    {
        return (ArrayList<Historial>) new ServicioDAO(ServicioDAO.RUTA_HISTORIALES).getObjects();
    }

    private ArrayList<Cliente> getClientesRegistrados()
    {
        return (ArrayList<Cliente>) new ServicioDAO(ServicioDAO.RUTA_CLIENTESREGISTRADOS).getObjects();
    }

    public void updateAllTables()
    {

        TableManager tableManager = new TableManager();

        tableManager.setTableItems(vistaPrincipal.getTablaEnCola(), getItemsEnCola());

        tableManager.setTableItems(vistaPrincipal.getTablaEnProceso(), getItemsEnProceso());

        tableManager.setTableItems(vistaPrincipal.getTablaTerminado(), getItemsTerminado());

        revalidateAllTables();

    }

    public void updateCostoKg(double constoKg)
    {

        for (int i = 0; i < serviciosEnCola.size(); i++)
            serviciosEnCola.get(i).setCostoKg(constoKg);

        for (int i = 0; i < serviciosEnProceso.size(); i++)
            serviciosEnProceso.get(i).setCostoKg(constoKg);

        for (int i = 0; i < serviciosTerminados.size(); i++)
            if (!serviciosTerminados.get(i).isTicketGenerado())
                serviciosTerminados.get(i).setCostoKg(constoKg);

    }

    public void revalidateAllTables()
    {

        vistaPrincipal.getTablaEnCola().getParent().revalidate();
        vistaPrincipal.getTablaEnProceso().getParent().revalidate();
        vistaPrincipal.getTablaTerminado().getParent().revalidate();

    }

    private void mostrarMensaje(String titulo, String text, int tipo)
    {
        JOptionPane.showMessageDialog(vistaPrincipal, text, titulo, tipo);
    }

    private int mostrarConfirmacion(String titulo, String text)
    {
        return JOptionPane.showConfirmDialog(vistaPrincipal, text, titulo, JOptionPane.YES_NO_OPTION);
    }

    @Override
    public void update(Observable o, Object item)
    {

        Cliente clienteNuevosDatos = (Cliente) item;

        updateCliente(serviciosEnCola, clienteNuevosDatos);
        updateCliente(serviciosEnProceso, clienteNuevosDatos);
        updateCliente(serviciosTerminados, clienteNuevosDatos);

        updateHistoriales(clienteNuevosDatos);

        saveAllServices();

        updateAllTables();

    }

    private void updateCliente(ArrayList<Servicio> servicio, Cliente clienteNuevosDatos)
    {

        for (int i = 0; i < servicio.size(); i++)
            if (clienteNuevosDatos.getClaveCliente() == servicio.get(i).getCliente().getClaveCliente())
                servicio.get(i).getCliente().setNombre(clienteNuevosDatos.getNombre());

    }

    private void updateHistoriales(Cliente clienteNuevosDatos)
    {

        ArrayList<Historial> historiales = getHistoriales();

        for (int i = 0; i < historiales.size(); i++)
            if (historiales.get(i).getCliente().getClaveCliente() == clienteNuevosDatos.getClaveCliente())
                historiales.get(i).getCliente().setNombre(clienteNuevosDatos.getNombre());

        saveHistoriales(historiales);

    }

    private Servicio obtenerServicioSeleccionado()
    {

        switch (getSelectedTable())
        {

            case "En cola":

                if (!serviciosEnCola.isEmpty() && vistaPrincipal.getTablaEnCola().getSelectedRow() >= 0)
                    return serviciosEnCola.get(vistaPrincipal.getTablaEnCola().getSelectedRow());

                break;

            case "En proceso":

                if (!serviciosEnProceso.isEmpty() && vistaPrincipal.getTablaEnProceso().getSelectedRow() >= 0)
                    return serviciosEnProceso.get(vistaPrincipal.getTablaEnProceso().getSelectedRow());

                break;

            case "Terminado":

                if (!serviciosTerminados.isEmpty() && vistaPrincipal.getTablaTerminado().getSelectedRow() >= 0)
                    return serviciosTerminados.get(vistaPrincipal.getTablaTerminado().getSelectedRow());

                break;

        }

        return null;

    }

    public String getSelectedTable()
    {
        return selectedTable;
    }

    public void setSelectedTable(String selectedTable)
    {
        this.selectedTable = selectedTable;
    }

    public VistaPrincipal getVistaPrincipal()
    {
        return vistaPrincipal;
    }

}
