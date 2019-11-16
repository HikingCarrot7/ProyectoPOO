package com.sw.controller;

import com.sw.model.Cliente;
import com.sw.model.Prenda;
import com.sw.model.ServicioInicial;
import com.sw.persistence.DAO;
import com.sw.renderer.ComboRenderer;
import com.sw.utilities.Temporizador;
import com.sw.utilities.Time;
import com.sw.utilities.Utilities;
import com.sw.view.NuevoCliente;
import com.sw.view.NuevoServicio;
import com.sw.view.PrendasInterfaz;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

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

        nuevoServicio.getClientes().addActionListener(this);

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
                    new PrendasController(prendasInterfaz, this);

                });

                break;

            case "ok":

                if (!getPrendas().isEmpty())
                    vistaPrincipalController.anadirServicioCola(new ServicioInicial(clientes.get(nuevoServicio.getClientes().getSelectedIndex()),
                            getTiempoEstimado(),
                            getPrendas(),
                            getTotalKg()));

                else
                    vistaPrincipalController.anadirServicioCola(new ServicioInicial(clientes.get(nuevoServicio.getClientes().getSelectedIndex()),
                            getTiempoEstimado()));

                nuevoServicio.dispose();

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

    public void addCliente(Cliente cliente)
    {

        clientes.add(cliente);

        anadirElementoCombo();

        saveClientes();

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
