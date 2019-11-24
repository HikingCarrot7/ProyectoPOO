package com.sw.controller;

import com.sw.model.Cliente;
import com.sw.model.Historial;
import com.sw.others.MyMouseAdapter;
import com.sw.persistence.ClienteDAO;
import com.sw.persistence.DAO;
import com.sw.renderer.ComboRenderer;
import com.sw.utilities.Utilities;
import com.sw.view.ClientesRegistradosInterfaz;
import com.sw.view.HistorialInterfaz;
import com.sw.view.NuevoCliente;
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
public class ClientesRegistradosController extends MyMouseAdapter implements ActionListener
{

    private ClientesRegistradosInterfaz clientesRegistradosInterfaz;
    private ArrayList<Cliente> clientesRegistrados;

    public ClientesRegistradosController(ClientesRegistradosInterfaz clientesRegistradosInterfaz)
    {

        this.clientesRegistradosInterfaz = clientesRegistradosInterfaz;
        clientesRegistrados = (ArrayList<Cliente>) new DAO(DAO.RUTA_CLIENTESREGISTRADOS).getObjects();

        initMyComponents();

        loadClientesRegistradosTable();

        loadComboModel();

        clientesRegistradosInterfaz.getAnadirCliente().addActionListener(this);
        clientesRegistradosInterfaz.getModificarCliente().addActionListener(this);
        clientesRegistradosInterfaz.getEliminarCliente().addActionListener(this);

        clientesRegistradosInterfaz.getOrdenarPor().addActionListener(this);

    }

    private void initMyComponents()
    {

        if (getClientes().isEmpty())
        {
            clientesRegistradosInterfaz.getVerHistorial().add(new JButton(Utilities.getIcon("/com/src/images/historial.png")));
            return;
        }

        getClientes().forEach((item) ->
        {
            clientesRegistradosInterfaz.getVerHistorial().add(new JButton(Utilities.getIcon("/com/src/images/historial.png")));
        });

    }

    private void loadClientesRegistradosTable()
    {

        TableManager tableManager = new TableManager();

        Object[][] items = getItems(getClientes());

        clientesRegistradosInterfaz.getTablaClientesRegistrados().setModel(new DefaultTableModel(tableManager.loadTableComponents(items, new int[]
        {

            5

        }, clientesRegistradosInterfaz.getVerHistorial()), new String[]
        {

            "Nombre", "Correo", "Teléfono", "Dirección", "N° servicios", "Ver historial"

        }));

        tableManager.renderTableModel(clientesRegistradosInterfaz.getTablaClientesRegistrados(), this, "Clientes");

        clientesRegistradosInterfaz.getTablaClientesRegistrados().getParent().revalidate();

    }

    private void loadComboModel()
    {
        clientesRegistradosInterfaz.getOrdenarPor().setRenderer(new ComboRenderer());
        clientesRegistradosInterfaz.getOrdenarPor().setModel(loadComboItems());

    }

    private DefaultComboBoxModel<ComboRenderer.ComboItem> loadComboItems()
    {

        DefaultComboBoxModel<ComboRenderer.ComboItem> dm = new DefaultComboBoxModel<>();

        dm.addElement(new ComboRenderer.ComboItem(Utilities.getIcon("/com/src/images/name.png"), "Nombre"));

        dm.addElement(new ComboRenderer.ComboItem(Utilities.getIcon("/com/src/images/numbers.png"), "N° de servicios"));

        return dm;

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        if (e.getSource() instanceof JButton)
            switch (e.getActionCommand())
            {

                case "Add":

                    EventQueue.invokeLater(() ->
                    {

                        NuevoCliente nuevoCliente = new NuevoCliente();

                        nuevoCliente.setLocationRelativeTo(clientesRegistradosInterfaz);
                        nuevoCliente.setVisible(true);

                        new NuevoClienteController(nuevoCliente, this);

                    });

                    break;

                case "Modificar":

                    if (clientesRegistradosInterfaz.getTablaClientesRegistrados().getSelectedRow() >= 0)
                        EventQueue.invokeLater(() ->
                        {

                            NuevoCliente nuevoClienteModificar = new NuevoCliente();

                            nuevoClienteModificar.setLocationRelativeTo(clientesRegistradosInterfaz);
                            nuevoClienteModificar.setVisible(true);

                            new NuevoClienteController(nuevoClienteModificar, this).establecerDatosDefecto(
                                    getClientes().get(clientesRegistradosInterfaz.getTablaClientesRegistrados().getSelectedRow()));

                        });

                    break;

                case "Delete":
                    if (!new TableManager().isFirstRowEmpty(clientesRegistradosInterfaz.getTablaClientesRegistrados()))
                        switch (JOptionPane.showConfirmDialog(clientesRegistradosInterfaz,
                                "Se borrará toda la información relacionado con este cliente. ¿Continuar?",
                                "Confirmar acción", JOptionPane.YES_NO_OPTION))
                        {

                            case 0: // Si se presiona Sí

                                eliminarClienteRegistrado(clientesRegistradosInterfaz.getTablaClientesRegistrados().getSelectedRow());

                                break;

                        }

                    else
                        JOptionPane.showMessageDialog(clientesRegistradosInterfaz,
                                "No hay clientes registrados",
                                "No hay clientes", JOptionPane.ERROR_MESSAGE);

                    break;

                default:
                    break;

            }

        else
        {

            DataSorterManager dataSorterManager = new DataSorterManager();

            switch (((JComboBox) e.getSource()).getSelectedIndex())
            {

                case 0:

                    dataSorterManager.ordenarPorNombreClientes(clientesRegistrados);

                    break;

                case 1:

                    dataSorterManager.ordenarPorNServiciosClientes(clientesRegistrados);

                    break;

                default:
                    break;

            }

            new TableManager().setTableItems(clientesRegistradosInterfaz.getTablaClientesRegistrados(), getItems(getClientes()));

            guardarClientes();

        }

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

        TableManager tableManager = new TableManager();
        JTable table = clientesRegistradosInterfaz.getTablaClientesRegistrados();

        if (!tableManager.isFirstRowEmpty(table))
        {

            if (tableManager.encimaBoton(table, e.getX(), e.getY(), 5))
                if (getHistorialesCliente(clientesRegistrados.get(table.getSelectedRow())).isEmpty())
                    JOptionPane.showMessageDialog(clientesRegistradosInterfaz, "Este cliente aún no tiene historiales", "Historial vacío", JOptionPane.ERROR_MESSAGE);

                else
                    EventQueue.invokeLater(() ->
                    {

                        HistorialInterfaz historialInterfaz = new HistorialInterfaz();

                        historialInterfaz.setVisible(true);
                        historialInterfaz.setLocationRelativeTo(clientesRegistradosInterfaz);

                        new HistorialController(historialInterfaz, getHistorialesCliente(clientesRegistrados.get(table.getSelectedRow())));

                    });

        } else
            JOptionPane.showMessageDialog(clientesRegistradosInterfaz, "Aún no hay clientes registrados.", "No hay clientes", JOptionPane.ERROR_MESSAGE);

    }

    public void addClienteRegistrado(Cliente cliente)
    {

        getClientes().add(cliente);

        TableManager tableManager = new TableManager();

        tableManager.addRow(clientesRegistradosInterfaz.getTablaClientesRegistrados(), new Object[]
        {

            cliente.getNombre(), cliente.getCorreo(), cliente.getTelefono(), cliente.getDireccion(), String.valueOf(cliente.getnServicios())

        });

        guardarClientes();

    }

    public void modificarClienteRegistrado(Cliente clienteAModificar, Cliente clienteNuevosDatos)
    {

        clienteAModificar.setNombre(clienteNuevosDatos.getNombre());
        clienteAModificar.setCorreo(clienteNuevosDatos.getCorreo());
        clienteAModificar.setTelefono(clienteNuevosDatos.getTelefono());
        clienteAModificar.setDireccion(clienteNuevosDatos.getDireccion());

        new TableManager().setTableItems(clientesRegistradosInterfaz.getTablaClientesRegistrados(), getItems(getClientes()));

        notificarCambioCliente();

        guardarClientes();

    }

    public void eliminarClienteRegistrado(int index)
    {

        new TableManager().removeRow(clientesRegistradosInterfaz.getTablaClientesRegistrados(), index);

        getClientes().remove(index);

        guardarClientes();

    }

    private Object[][] getItems(ArrayList<Cliente> clientes)
    {

        if (clientes.isEmpty())
            return new Object[1][6];

        Object[][] items = new Object[clientes.size()][6];

        for (int i = 0; i < clientes.size(); i++)
        {

            items[i][0] = clientes.get(i).getNombre();
            items[i][1] = clientes.get(i).getCorreo();
            items[i][2] = clientes.get(i).getTelefono();
            items[i][3] = clientes.get(i).getDireccion();
            items[i][4] = String.valueOf(clientes.get(i).getnServicios());

        }

        return items;

    }

    private void notificarCambioCliente()
    {

        setChanged();
        notifyObservers(getClientes().get(clientesRegistradosInterfaz.getTablaClientesRegistrados().getSelectedRow()));
        clearChanged();

    }

    private ArrayList<Historial> getHistorialesCliente(Cliente cliente)
    {

        ArrayList<Historial> historiales = (ArrayList<Historial>) new DAO(DAO.RUTA_HISTORIALES).getObjects();
        ArrayList<Historial> historialesCliente = new ArrayList<>();

        for (int i = 0; i < historiales.size(); i++)
            if (historiales.get(i).getCliente().getClaveCliente() == cliente.getClaveCliente())
                historialesCliente.add(historiales.get(i));

        return historialesCliente;

    }

    private void guardarClientes()
    {

        new DAO(DAO.RUTA_CLIENTESREGISTRADOS).saveObjects(getClientes());

        new ClienteDAO().saveClaveClientes(Cliente.getClaves());

    }

    public ArrayList<Cliente> getClientes()
    {
        return clientesRegistrados;
    }

}
