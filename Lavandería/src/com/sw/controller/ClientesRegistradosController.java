package com.sw.controller;

import com.sw.model.ClienteRegistrado;
import com.sw.persistence.ClientesRegistradosDAO;
import com.sw.renderer.ComboRenderer;
import com.sw.utilities.Utilities;
import com.sw.view.ClientesRegistradosInterfaz;
import com.sw.view.NuevoCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
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
public class ClientesRegistradosController extends MouseAdapter implements ActionListener
{

    private final String RUTA_CLIENTESREGISTRADOS = "res/ClientesRegistrados.txt";
    private ClientesRegistradosInterfaz clientesRegistradosInterfaz;
    private ArrayList<ClienteRegistrado> clientesRegistrados;

    public ClientesRegistradosController(ClientesRegistradosInterfaz clientesRegistradosInterfaz)
    {

        this.clientesRegistradosInterfaz = clientesRegistradosInterfaz;
        clientesRegistrados = new ClientesRegistradosDAO(RUTA_CLIENTESREGISTRADOS).getClientes();

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

        if (clientesRegistrados.isEmpty())
        {
            clientesRegistradosInterfaz.getVerHistorial().add(new JButton(Utilities.getIcon("/com/src/images/historial.png")));
            return;
        }

        clientesRegistrados.forEach((item) ->
        {
            clientesRegistradosInterfaz.getVerHistorial().add(new JButton(Utilities.getIcon("/com/src/images/historial.png")));
        });

    }

    private void loadClientesRegistradosTable()
    {

        TableManager tableManager = new TableManager();

        tableManager.renderTableModel(clientesRegistradosInterfaz.getTablaClientesRegistrados(), this, "Clientes");

        Object[][] items = getItems(clientesRegistrados);

        clientesRegistradosInterfaz.getTablaClientesRegistrados().setModel(new DefaultTableModel(tableManager.loadTableComponents(items, new int[]
        {

            5

        }, clientesRegistradosInterfaz.getVerHistorial()), new String[]
        {

            "Nombre", "Correo", "Teléfono", "Dirección", "N° servicios", "Ver historial"

        }));

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

                    NuevoCliente nuevoCliente = new NuevoCliente();

                    nuevoCliente.setLocationRelativeTo(null);
                    nuevoCliente.setVisible(true);

                    new NuevoClienteController(nuevoCliente, this);

                    break;

                case "Modificar":

                    NuevoCliente nuevoClienteModificar = new NuevoCliente();

                    nuevoClienteModificar.setLocationRelativeTo(null);
                    nuevoClienteModificar.setVisible(true);

                    new NuevoClienteController(nuevoClienteModificar, this).establecerDatosDefecto(
                            clientesRegistrados.get(clientesRegistradosInterfaz.getTablaClientesRegistrados().getSelectedRow()));

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

            switch (((JComboBox) e.getSource()).getSelectedIndex())
            {
                case 0:

                    clientesRegistrados.sort((c1, c2) ->
                    {

                        return c1.getNombre().compareTo(c2.getNombre());

                    });

                    break;

                case 1:

                    clientesRegistrados.sort((c1, c2) ->
                    {
                        return c1.getnServicios() - c2.getnServicios();

                    });

                    break;

                default:
                    break;

            }

            new TableManager().setTableItems(clientesRegistradosInterfaz.getTablaClientesRegistrados(), getItems(clientesRegistrados));

            guardarClientes();

        }

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

        TableManager tableManager = new TableManager();
        JTable tablaClientes = clientesRegistradosInterfaz.getTablaClientesRegistrados();

        if (tableManager.encimaBoton(tablaClientes, e.getX(), e.getY()))
            System.out.println(tableManager.getClickedRow(tablaClientes, e.getY()));

    }

    public void anadirClienteRegistrado(ClienteRegistrado cliente)
    {

        getClientes().add(cliente);

        JTable table = clientesRegistradosInterfaz.getTablaClientesRegistrados();
        TableManager tableManager = new TableManager();

        if (!tableManager.isFirstRowEmpty(table))
            tableManager.addRow(table, tableManager.getEmptyRowData(table));

        tableManager.updateLastRow(table, new Object[]
        {
            cliente.getNombre(), cliente.getCorreo(), cliente.getTelefono(), cliente.getDireccion(), String.valueOf(cliente.getnServicios())
        });

        guardarClientes();

    }

    public void modificarClienteRegistrado(ClienteRegistrado clienteModificar, ClienteRegistrado cliente)
    {

        TableManager tableManager = new TableManager();

        clienteModificar.setNombre(cliente.getNombre());
        clienteModificar.setCorreo(cliente.getCorreo());
        clienteModificar.setTelefono(cliente.getTelefono());
        clienteModificar.setDireccion(cliente.getDireccion());

        tableManager.setTableItems(clientesRegistradosInterfaz.getTablaClientesRegistrados(), getItems(clientesRegistrados));

        guardarClientes();

    }

    public void eliminarClienteRegistrado(int index)
    {

        JTable table = clientesRegistradosInterfaz.getTablaClientesRegistrados();
        TableManager tableManager = new TableManager();

        if (table.getRowCount() != 1)
            tableManager.deleteRow(table, index);
        else
            tableManager.vaciarPrimeraFila(table);

        clientesRegistrados.remove(index);

        guardarClientes();

    }

    /**
     * @deprecated
     *
     * Revisar este método para las futuras implementaciones específicas.
     *
     * @param clientes Los clientes registrados.
     *
     * @return Los items de los clientes registrados en forma de matriz de objetos (este método debe de ignorar aquellas columnas que tengan componentes).
     */
    private Object[][] getItems(ArrayList<ClienteRegistrado> clientes)
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

    private void guardarClientes()
    {
        new ClientesRegistradosDAO(RUTA_CLIENTESREGISTRADOS).guardarClientes(clientesRegistrados);
    }

    public ArrayList<ClienteRegistrado> getClientes()
    {
        return clientesRegistrados;
    }

}
