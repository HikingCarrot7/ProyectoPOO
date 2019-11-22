package com.sw.controller;

import com.sw.model.Cliente;
import com.sw.model.Prenda;
import com.sw.model.Servicio;
import com.sw.model.ServicioInicial;
import com.sw.model.Ticket;
import com.sw.persistence.DAO;
import com.sw.renderer.ComboRenderer;
import com.sw.utilities.Temporizador;
import com.sw.utilities.Time;
import com.sw.utilities.Utilities;
import com.sw.view.NuevoCliente;
import com.sw.view.NuevoServicio;
import com.sw.view.PrendasInterfaz;
import com.sw.view.TicketInterfaz;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Mohammed
 */
public class NuevoServicioController implements ActionListener
{

    private NuevoServicio nuevoServicio;
    private VistaPrincipalController vistaPrincipalController;
    private ArrayList<Cliente> clientes;
    private ArrayList<Prenda> prendas;
    private ServicioInicial servicioInicial;
    private boolean editandoServicio;
    private int nTotalPrendas;
    private double totalKg;

    public NuevoServicioController(NuevoServicio nuevoServicio, VistaPrincipalController vistaPrincipalController)
    {

        this.nuevoServicio = nuevoServicio;
        this.vistaPrincipalController = vistaPrincipalController;

        clientes = obtenerClientes();

        loadComboModel();

        nuevoServicio.getAddCliente().addActionListener(this);
        nuevoServicio.getAnadirPrendas().addActionListener(this);
        nuevoServicio.getOk().addActionListener(this);
        nuevoServicio.getVerTicket().addActionListener(this);

        nuevoServicio.getClientes().addActionListener(this);

    }

    private void loadComboModel()
    {

        nuevoServicio.getClientes().setRenderer(new ComboRenderer());
        nuevoServicio.getClientes().setModel(loadComboItems());
        nuevoServicio.getClientes().setMaximumRowCount(4);

    }

    private DefaultComboBoxModel<ComboRenderer.ComboItem> loadComboItems()
    {

        DefaultComboBoxModel<ComboRenderer.ComboItem> dm = new DefaultComboBoxModel<>();

        if (clientes.isEmpty())
        {
            dm.addElement(new ComboRenderer.ComboItem(Utilities.getIcon("/com/src/images/clienteCombo.png"), "No hay clientes"));
            return dm;

        }

        for (int i = 0; i < clientes.size(); i++)
            dm.addElement(new ComboRenderer.ComboItem(Utilities.getIcon("/com/src/images/clienteCombo.png"), clientes.get(i).getNombre()));

        return dm;

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

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

                    //prendasInterfaz.addWindowListener(new WindowsListener(nuevoServicio));
                    new PrendasController(prendasInterfaz, this, prendas != null ? prendas : new ArrayList<>(), getTotalKg());

                });

                break;

            case "ok":

                if (!isEditandoServicio())
                    if (!clientes.isEmpty())
                    {

                        vistaPrincipalController.anadirServicioCola(new ServicioInicial(
                                obtenerClientes().get(nuevoServicio.getClientes().getSelectedIndex()),
                                Calendar.getInstance(),
                                getTiempoEstimado(),
                                getPrendas(),
                                getTotalKg()));

                        saveClaveNumTickets();

                        nuevoServicio.dispose();

                    } else
                        mostrarMensaje("Cliente inválido.", "El cliente no es válido.", JOptionPane.ERROR_MESSAGE);

                else
                {

                    servicioInicial.setCliente(getClientes().get(nuevoServicio.getClientes().getSelectedIndex()));
                    servicioInicial.setPrendas(getPrendas());
                    servicioInicial.setTiempoEstimado(getTiempoEstimado());
                    servicioInicial.setTotalKg(getTotalKg());

                    vistaPrincipalController.updateAllTables();

                    vistaPrincipalController.saveAllServices();

                    nuevoServicio.dispose();

                }

                break;

            case "verTicket":

                if (prendas != null && !prendas.isEmpty() && getTotalKg() != 0 && !clientes.isEmpty())
                    EventQueue.invokeLater(() ->
                    {

                        TicketInterfaz ticketInterfaz = new TicketInterfaz();

                        ticketInterfaz.setVisible(true);
                        ticketInterfaz.setLocationRelativeTo(null);

                        new VerTicketController(ticketInterfaz,
                                new Ticket(
                                        Servicio.getNumeroTickets() + 1,
                                        isEditandoServicio() ? servicioInicial.getFecha() : Calendar.getInstance(),
                                        getClientes().get(nuevoServicio.getClientes().getSelectedIndex()).getNombre(),
                                        prendas,
                                        getNTotalPrendas(),
                                        getTotalKg() * 9.5,
                                        getTotalKg())).mostrarTicket();

                    });

                else
                    mostrarMensaje(clientes.isEmpty() ? "Cliente inválido." : "Error.", clientes.isEmpty() ? "El cliente no es válido."
                            : "Para generar el ticket al menos una prenda debe estar registrada y el total de kg. no debe ser 0.", JOptionPane.ERROR_MESSAGE);

                break;

            default:
                break;

        }

    }

    public void anadirElementoCombo()
    {

        DefaultComboBoxModel<ComboRenderer.ComboItem> dm = (DefaultComboBoxModel) nuevoServicio.getClientes().getModel();

        dm.addElement(new ComboRenderer.ComboItem(Utilities.getIcon("/com/src/images/clienteCombo.png"), clientes.get(clientes.size() - 1).getNombre()));

        nuevoServicio.getClientes().setSelectedIndex(clientes.size() - 1);

    }

    public void eliminarElementoCombo(int index)
    {

        DefaultComboBoxModel<ComboRenderer.ComboItem> dm = (DefaultComboBoxModel) nuevoServicio.getClientes().getModel();

        dm.removeElementAt(index);

    }

    public void addCliente(Cliente cliente)
    {

        if (clientes.isEmpty())
            eliminarElementoCombo(0);

        clientes.add(cliente);

        anadirElementoCombo();

        saveClientes();

    }

    public void establecerDatosDefecto(ServicioInicial servicioInicial)
    {

        setEditandoServicio(true);

        this.servicioInicial = servicioInicial;
        prendas = servicioInicial.getPrendas();

        nuevoServicio.getClientes().setSelectedIndex(getCurrentCliente());

        nuevoServicio.getHoras().setValue(servicioInicial.getTiempoEstimado().getTime().getHours());
        nuevoServicio.getMinutos().setValue(servicioInicial.getTiempoEstimado().getTime().getMinutes());
        nuevoServicio.getSegundos().setValue(servicioInicial.getTiempoEstimado().getTime().getSeconds());

        setTotalKg(servicioInicial.getTotalKg());
        setNTotalPrendas(servicioInicial.getTotalPrendas());

    }

    private int getCurrentCliente()
    {

        for (int i = 0; i < clientes.size(); i++)
            if (servicioInicial.getCliente().getClaveCliente() == clientes.get(i).getClaveCliente())
                return i;

        return 0;

    }

    private void mostrarMensaje(String titulo, String text, int tipo)
    {
        JOptionPane.showMessageDialog(nuevoServicio, text, titulo, tipo);
    }

    public ArrayList<Cliente> getClientes()
    {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes)
    {
        this.clientes = clientes;
    }

    public ArrayList<Prenda> getPrendas()
    {
        return prendas != null ? prendas : new ArrayList<>();
    }

    public void setPrendas(ArrayList<Prenda> prendas)
    {
        this.prendas = prendas;
    }

    public Temporizador getTiempoEstimado()
    {

        return new Temporizador(new Time(Integer.parseInt(String.valueOf(getNuevoServicio().getHoras().getValue())),
                Integer.parseInt(String.valueOf(getNuevoServicio().getMinutos().getValue())),
                Integer.parseInt(String.valueOf(getNuevoServicio().getSegundos().getValue()))));

    }

    private double getTotalKg()
    {
        return totalKg;
    }

    public void setTotalKg(double totalKg)
    {
        this.totalKg = totalKg;
    }

    public void setNTotalPrendas(int nTotalPrendas)
    {
        this.nTotalPrendas = nTotalPrendas;
    }

    private int getNTotalPrendas()
    {
        return nTotalPrendas;
    }

    private void saveClientes()
    {

        new DAO(DAO.RUTA_CLIENTESREGISTRADOS).saveObjects(clientes);

        new DAO(DAO.RUTA_CLAVECLIENTES).saveClaves(Cliente.getClaves());

    }

    private void saveClaveNumTickets()
    {
        new DAO(DAO.RUTA_NUMTICKETS).saveClaves(Servicio.getNumeroTickets());
    }

    private ArrayList<Cliente> obtenerClientes()
    {
        return (ArrayList<Cliente>) new DAO(DAO.RUTA_CLIENTESREGISTRADOS).getObjects();
    }

    public NuevoServicio getNuevoServicio()
    {
        return nuevoServicio;
    }

    public boolean isEditandoServicio()
    {
        return editandoServicio;
    }

    public void setEditandoServicio(boolean editandoServicio)
    {
        this.editandoServicio = editandoServicio;
    }

}
