package com.sw.controller;

import com.sw.model.Cliente;
import com.sw.model.Servicio;
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
 * @author Me
 * @since 1.0
 */
public class EditarServicioController implements ActionListener
{

    private EditarServicio editarServicio;
    private Servicio servicio;
    private VistaPrincipalController vistaPrincipalController;
    private ArrayList<Cliente> clientes;

    public EditarServicioController(EditarServicio editarServicio, Servicio servicio, VistaPrincipalController vistaPrincipalController)
    {

        this.editarServicio = editarServicio;
        this.servicio = servicio;
        this.vistaPrincipalController = vistaPrincipalController;

        initMyComponents();

        loadComboModel();

    }

    private void initMyComponents()
    {

        clientes = getClientes();

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

    /**
     * Gestiona los eventos que ocurren cuando se presiona un botón.
     *
     * @param e El objeto de tipo ActionEvent que se crea cuando se presiona un botón en esta interfaz.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {

        switch (e.getActionCommand())
        {

            case "ok":

                servicio.setCliente(getClientes().get(editarServicio.getClientes().getSelectedIndex()));

                vistaPrincipalController.updateAllTables();
                vistaPrincipalController.saveAllServices();
                editarServicio.dispose();

                break;

            case "verTicket": // Se muestra el ticket.

                if (!servicio.getPrendas().isEmpty() && servicio.getTotalKg() != 0)
                    EventQueue.invokeLater(() ->
                    {

                        TicketInterfaz ticketInterfaz = new TicketInterfaz();

                        ticketInterfaz.setVisible(true);
                        ticketInterfaz.setLocationRelativeTo(editarServicio);

                        servicio.setCliente(getClientes().get(editarServicio.getClientes().getSelectedIndex()));

                        new VerTicketController(ticketInterfaz, servicio.getTicket()).mostrarTicket();

                    });

                else
                    mostrarMensaje("Datos inválidos.", "Para generar el ticket al menos una prenda debe estar registrada y el total de kg. no debe ser 0.", JOptionPane.ERROR_MESSAGE);

                break;

            default:
                break;

        }

    }

    /**
     * Mostramos un mensaje.
     *
     * @param titulo El título.
     * @param text El texto a mostrar.
     * @param tipo El tipo de mensaje.
     *
     */
    private void mostrarMensaje(String titulo, String text, int tipo)
    {
        JOptionPane.showMessageDialog(editarServicio, text, titulo, tipo);
    }

    /**
     * Obtenemos al cliente del servicio que se seleccionó para editar.
     *
     * @return El índice del correspondiente cliente para este servicio.
     */
    private int getCurrentCliente()
    {

        for (int i = 0; i < clientes.size(); i++)
            if (servicio.getCliente().getClaveCliente() == clientes.get(i).getClaveCliente())
                return i;

        return -1;

    }

    private ArrayList<Cliente> getClientes()
    {
        return (ArrayList<Cliente>) new DAO(DAO.RUTA_CLIENTESREGISTRADOS).getObjects();
    }

}
