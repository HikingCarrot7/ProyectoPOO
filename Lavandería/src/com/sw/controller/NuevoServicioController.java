package com.sw.controller;

import com.sw.model.Cliente;
import com.sw.persistence.DAO;
import com.sw.renderer.ComboRenderer;
import com.sw.utilities.Utilities;
import com.sw.view.NuevoCliente;
import com.sw.view.NuevoServicio;
import com.sw.view.PrendasInterfaz;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

/**
 *
 * @author Mohammed
 */
public class NuevoServicioController implements ActionListener
{

    private NuevoServicio nuevoServicio;
    private ArrayList<Cliente> clientes;

    public NuevoServicioController(NuevoServicio nuevoServicio)
    {
        this.nuevoServicio = nuevoServicio;

        clientes = obtenerClientes();

        loadComboModel();

        initMyComponents();

        nuevoServicio.getAddCliente().addActionListener(this);
        nuevoServicio.getAnadirPrendas().addActionListener(this);
        nuevoServicio.getOk().addActionListener(this);

        nuevoServicio.getClientes().addActionListener(this);

    }

    private void initMyComponents()
    {
        nuevoServicio.getHoras().setValue(1);
    }

    private void loadComboModel()
    {
        nuevoServicio.getClientes().setRenderer(new ComboRenderer());
        nuevoServicio.getClientes().setModel(loadComboItems());
        nuevoServicio.getClientes().setMaximumRowCount(5);

    }

    private DefaultComboBoxModel<ComboRenderer.ComboItem> loadComboItems()
    {
        DefaultComboBoxModel<ComboRenderer.ComboItem> dm = new DefaultComboBoxModel<>();

        for (int i = 0; i < clientes.size(); i++)
            dm.addElement(new ComboRenderer.ComboItem(Utilities.getIcon("/com/src/images/clienteCombo.png"), clientes.get(i).getNombre()));

        return dm;

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        if (e.getSource() instanceof JButton)
            switch (e.getActionCommand())
            {
                case "addCliente":

                    EventQueue.invokeLater(() ->
                    {

                        NuevoCliente nuevoCliente = new NuevoCliente();

                        nuevoCliente.setLocationRelativeTo(nuevoServicio);
                        nuevoCliente.setVisible(true);

                        new NuevoClienteController(nuevoCliente, this);

                    });

                    break;

                case "addPrendas":

                    EventQueue.invokeLater(() ->
                    {

                        PrendasInterfaz prendasInterfaz = new PrendasInterfaz();

                        prendasInterfaz.setVisible(true);
                        prendasInterfaz.setLocationRelativeTo(nuevoServicio);
                        //nuevoServicio.setVisible(false);

                        new PrendasController(prendasInterfaz);

                    });

                    break;

                default:
                    break;

            }

        else
        {

        }

    }

    public void anadirElementoCombo()
    {

        DefaultComboBoxModel<ComboRenderer.ComboItem> dm = (DefaultComboBoxModel) nuevoServicio.getClientes().getModel();

        dm.addElement(new ComboRenderer.ComboItem(Utilities.getIcon("/com/src/images/clienteCombo.png"), clientes.get(clientes.size() - 1).getNombre()));

        nuevoServicio.getClientes().setSelectedIndex(clientes.size() - 1);

    }

    public void addCliente(Cliente cliente)
    {

        clientes.add(cliente);

        anadirElementoCombo();

        saveClientes();

    }

    private void saveClientes()
    {
        new DAO(DAO.RUTA_CLIENTESREGISTRADOS).saveObjects(clientes);
    }

    private ArrayList<Cliente> obtenerClientes()
    {
        return (ArrayList<Cliente>) new DAO(DAO.RUTA_CLIENTESREGISTRADOS).getObjects();
    }

    public NuevoServicio getNuevoServicio()
    {
        return nuevoServicio;
    }

}
