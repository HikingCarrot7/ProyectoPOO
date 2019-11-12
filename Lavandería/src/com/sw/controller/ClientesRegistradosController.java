package com.sw.controller;

import com.sw.model.Cliente;
import com.sw.persistence.ClientesDAO;
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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mohammed
 */
public class ClientesRegistradosController extends MouseAdapter implements ActionListener
{

    private ClientesRegistradosInterfaz clientesRegistradosInterfaz;
    private ArrayList<Cliente> clientes;

    public ClientesRegistradosController(ClientesRegistradosInterfaz clientesRegistradosInterfaz)
    {

        this.clientesRegistradosInterfaz = clientesRegistradosInterfaz;
        clientes = new ClientesDAO("res/Clientes.txt").getClientes();

        initMyComponents();

        loadClientesRegistradosTable();

        loadComboModel();

        clientesRegistradosInterfaz.getAnadirCliente().addActionListener(this);
        clientesRegistradosInterfaz.getModificarCliente().addActionListener(this);
        clientesRegistradosInterfaz.getEliminarCliente().addActionListener(this);

    }

    private void initMyComponents()
    {

        if (clientes.isEmpty())
        {
            clientesRegistradosInterfaz.getVerHistorial().add(new JButton(Utilities.getIcon("/com/src/images/historial.png")));
            return;
        }

        clientes.forEach((_item) ->
        {
            clientesRegistradosInterfaz.getVerHistorial().add(new JButton(Utilities.getIcon("/com/src/images/historial.png")));
        });

    }

    private void loadClientesRegistradosTable()
    {

        TableManager tableManager = new TableManager();

        tableManager.renderTableModel(clientesRegistradosInterfaz.getTablaClientesRegistrados(), this, "Clientes");

        Object[][] items = getItems(clientes);

        clientesRegistradosInterfaz.getTablaClientesRegistrados().setModel(new DefaultTableModel(new TableManager().loadTableComponents(items, new int[]
        {

            5

        }, clientesRegistradosInterfaz.getVerHistorial()), new String[]
        {

            "Nombre", "Correo", "Teléfono", "Dirección", "N° servicios", "Ver historial"

        }));

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

        switch (e.getActionCommand())
        {

            case "Add":
                NuevoCliente.iniciarInterfazNuevoCliente(this);
                break;

            case "Modificar":
                System.out.println("Modificar cliente");
                break;

            case "Delete":
                System.out.println(clientesRegistradosInterfaz.getTablaClientesRegistrados().getSelectedRow());
                break;

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

    public void anadirClienteTabla(Cliente cliente)
    {

        getClientes().add(cliente);

        JTable table = clientesRegistradosInterfaz.getTablaClientesRegistrados();
        TableManager tableManager = new TableManager();

        tableManager.addRow(table, tableManager.getEmptyRowData(table));

        tableManager.updateLastRow(table, new Object[]
        {

            "Nicolás Canul", "ricardoibarra2044@gmail.com", "9992676253", "No where"

        });

        new ClientesDAO("res/Clientes.txt").guardarClientes(clientes);

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
            items[i][4] = "0"; // Por el momento.

        }

        return items;

    }

    public ArrayList<Cliente> getClientes()
    {
        return clientes;
    }

}
