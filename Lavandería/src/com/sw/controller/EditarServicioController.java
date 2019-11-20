package com.sw.controller;

import com.sw.model.Cliente;
import com.sw.model.ServicioInicial;
import com.sw.persistence.DAO;
import com.sw.renderer.ComboRenderer;
import com.sw.utilities.Utilities;
import com.sw.view.EditarServicio;
import com.sw.view.TicketInterfaz;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Mohammed
 */
public class EditarServicioController implements ActionListener
{

    private EditarServicio editarServicio;
    private ServicioInicial servicioInicial;
    private VistaPrincipalController vistaPrincipalController;
    private ArrayList<Cliente> clientes;

    public EditarServicioController(EditarServicio editarServicio, ServicioInicial servicioInicial, VistaPrincipalController vistaPrincipalController)
    {

        this.editarServicio = editarServicio;
        this.servicioInicial = servicioInicial;
        this.vistaPrincipalController = vistaPrincipalController;

        clientes = getClientes();

        loadComboModel();

        editarServicio.getOk().addActionListener(this);
        editarServicio.getVerTicket().addActionListener(this);

    }

    private void loadComboModel()
    {

        editarServicio.getClientes().setRenderer(new ComboRenderer());
        editarServicio.getClientes().setModel(loadComboItems());
        editarServicio.getClientes().setSelectedIndex(getCurrentCliente());

    }

    private DefaultComboBoxModel<ComboRenderer.ComboItem> loadComboItems()
    {

        DefaultComboBoxModel<ComboRenderer.ComboItem> dm = new DefaultComboBoxModel<>();

        for (int i = 0; i < clientes.size(); i++)
            dm.addElement(new ComboRenderer.ComboItem(Utilities.getIcon("/com/src/images/clienteCombo.png"), clientes.get(i).getNombre()));

        return dm;

    }

    private int getCurrentCliente()
    {

        for (int i = 0; i < clientes.size(); i++)
            if (servicioInicial.getCliente().getClaveCliente() == clientes.get(i).getClaveCliente())
                return i;

        return -1;

    }

    private ArrayList<Cliente> getClientes()
    {
        return (ArrayList<Cliente>) new DAO(DAO.RUTA_CLIENTESREGISTRADOS).getObjects();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        switch (e.getActionCommand())
        {

            case "ok":

                servicioInicial.setCliente(getClientes().get(editarServicio.getClientes().getSelectedIndex()));

                vistaPrincipalController.updateAllTables();

                vistaPrincipalController.saveAllServices();

                editarServicio.dispose();

                break;

            case "verTicket":

                if (!servicioInicial.getPrendas().isEmpty() && servicioInicial.getTotalKg() != 0)
                    EventQueue.invokeLater(() ->
                    {

                        TicketInterfaz ticketInterfaz = new TicketInterfaz();

                        ticketInterfaz.setVisible(true);
                        ticketInterfaz.setLocationRelativeTo(editarServicio);

                        servicioInicial.setCliente(getClientes().get(editarServicio.getClientes().getSelectedIndex()));

                        new VerTicketController(ticketInterfaz, servicioInicial.getTicket()).mostrarTicket();

                    });

                else
                    JOptionPane.showMessageDialog(editarServicio,
                            "Para generar el ticket al menos una prenda debe estar registrada y el total de kg. no debe ser 0",
                            "Datos inválidos",
                            JOptionPane.ERROR_MESSAGE);

                break;

            default:
                break;

        }

    }

}
