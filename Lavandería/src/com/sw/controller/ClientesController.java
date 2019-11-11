package com.sw.controller;

import com.sw.renderer.ComboRenderer;
import com.sw.utilities.Utilities;
import com.sw.view.ClientesRegistrados;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mohammed
 */
public class ClientesController extends MouseAdapter implements ActionListener
{

    private ClientesRegistrados clientes;

    public ClientesController(ClientesRegistrados clientes)
    {
        this.clientes = clientes;

        loadClientesRegistradosTable();

        loadComboModel();

        clientes.getAnadirCliente().addActionListener(this);
        clientes.getModificarCliente().addActionListener(this);
        clientes.getEliminarCliente().addActionListener(this);

    }

    private void loadClientesRegistradosTable()
    {

        TableManager tableManager = new TableManager();

        tableManager.renderTableModel(clientes.getClientesRegistrados(), this, "Clientes");

        Object[][] items = new Object[5][6];

        clientes.getClientesRegistrados().setModel(new DefaultTableModel(new TableManager().loadItems(items, new int[]
        {

            5

        }, clientes.getVerHistorial()), new String[]
        {

            "Nombre", "Correo", "Teléfono", "Dirección", "N° servicios", "Ver historial"

        }));

    }

    private void loadComboModel()
    {
        clientes.getOrdenarPor().setRenderer(new ComboRenderer());
        clientes.getOrdenarPor().setModel(loadComboItems());

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
                System.out.println("Añadir cliente");
                break;

            case "Modificar":
                System.out.println("Modificar cliente");
                break;

            case "Delete":
                System.out.println(clientes.getClientesRegistrados().getSelectedRow());
                break;

        }

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

        TableManager tableManager = new TableManager();
        JTable tablaClientes = clientes.getClientesRegistrados();

        if (tableManager.encimaBoton(tablaClientes, e.getX(), e.getY()))
            System.out.println(tableManager.getClickedRow(tablaClientes, e.getY()));

    }

    public ClientesRegistrados getClientes()
    {
        return clientes;
    }

}
