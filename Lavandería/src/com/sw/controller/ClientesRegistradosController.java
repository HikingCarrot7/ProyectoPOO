package com.sw.controller;

import com.sw.model.Cliente;
import com.sw.model.Historial;
import com.sw.model.Servicio;
import com.sw.others.MyMouseAdapter;
import com.sw.others.MyWindowListener;
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
    private VistaPrincipalController vistaPrincipalController;
    private ArrayList<Cliente> clientesRegistrados;

    public ClientesRegistradosController(ClientesRegistradosInterfaz clientesRegistradosInterfaz, VistaPrincipalController vistaPrincipalController)
    {

        this.clientesRegistradosInterfaz = clientesRegistradosInterfaz;
        this.vistaPrincipalController = vistaPrincipalController;

        clientesRegistrados = (ArrayList<Cliente>) new DAO(DAO.RUTA_CLIENTESREGISTRADOS).getObjects();

        initMyComponents();

        loadClientesRegistradosTable();

        loadComboModel();

        clientesRegistradosInterfaz.getAnadirCliente().addActionListener(this);
        clientesRegistradosInterfaz.getModificarCliente().addActionListener(this);
        clientesRegistradosInterfaz.getEliminarCliente().addActionListener(this);

        clientesRegistradosInterfaz.getOrdenarPor().addActionListener(this);

    }

    /**
     * Iniciamos los componentes para esta ventana.
     */
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

    /**
     * Cargamos los valores de la tabla.
     */
    private void loadClientesRegistradosTable()
    {

        TableManager tableManager = new TableManager();

        Object[][] items = getItems(getClientes());

        clientesRegistradosInterfaz.getTablaClientesRegistrados().setModel(new DefaultTableModel(tableManager.loadTableComponents(items, new int[]
        {

            5

        }, clientesRegistradosInterfaz.getVerHistorial()), new String[]
        {

            "Nombre", "Correo", "Teléfono", "Dirección", "N° servicios", "Historial"

        }));

        tableManager.renderTableModel(clientesRegistradosInterfaz.getTablaClientesRegistrados(), this, "Clientes");

        clientesRegistradosInterfaz.getTablaClientesRegistrados().getParent().revalidate();

    }

    /**
     * Establece el renderer y los ítems para este JComboBox.
     */
    private void loadComboModel()
    {
        clientesRegistradosInterfaz.getOrdenarPor().setRenderer(new ComboRenderer());
        clientesRegistradosInterfaz.getOrdenarPor().setModel(loadComboItems());

    }

    /**
     * Carga los ítems que muestra este JComoBox.
     *
     * @return El DefaultComboBoxModel con los elementos cargados.
     */
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

                        nuevoCliente.addWindowListener(new MyWindowListener(clientesRegistradosInterfaz));
                        clientesRegistradosInterfaz.setVisible(false);

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

                            nuevoClienteModificar.addWindowListener(new MyWindowListener(clientesRegistradosInterfaz));
                            clientesRegistradosInterfaz.setVisible(false);

                            new NuevoClienteController(nuevoClienteModificar, this).establecerDatosDefecto(
                                    getClientes().get(clientesRegistradosInterfaz.getTablaClientesRegistrados().getSelectedRow()));

                        });

                    break;

                case "Delete":
                    if (!new TableManager().isFirstRowEmpty(clientesRegistradosInterfaz.getTablaClientesRegistrados()))

                        if (!existeServicioEnCurso(clientesRegistrados.get(clientesRegistradosInterfaz.getTablaClientesRegistrados().getSelectedRow())))
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
                                    "No se pueden eliminar a clientes que tengan servicios pendientes a los cuales aún no se les ha generado su ticket.",
                                    "Error.", JOptionPane.ERROR_MESSAGE);

                    else
                        JOptionPane.showMessageDialog(clientesRegistradosInterfaz,
                                "No hay clientes registrados.",
                                "No hay clientes.", JOptionPane.ERROR_MESSAGE);

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

                        historialInterfaz.addWindowListener(new MyWindowListener(clientesRegistradosInterfaz));
                        clientesRegistradosInterfaz.setVisible(false);

                        new HistorialController(historialInterfaz, getHistorialesCliente(clientesRegistrados.get(table.getSelectedRow())));

                    });

        } else
            JOptionPane.showMessageDialog(clientesRegistradosInterfaz, "Aún no hay clientes registrados.", "No hay clientes", JOptionPane.ERROR_MESSAGE);

    }

    /**
     * Añadimos el cliente a la tabla.
     *
     * @param cliente El cliente a añadir.
     *
     */
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

    /**
     * Modificamos los valores de un cliente registrado.
     *
     * @param clienteAModificar El cliente a modificar.
     * @param clienteNuevosDatos El objeto cliente con los nuevos datos.
     *
     */
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

    /**
     * Elimanos a un cliente en esta tabla.
     *
     * @param index El índice del cliente a eliminar.
     */
    public void eliminarClienteRegistrado(int index)
    {

        new TableManager().removeRow(clientesRegistradosInterfaz.getTablaClientesRegistrados(), index);

        getClientes().remove(index);

        guardarClientes();

    }

    /**
     * Obtenemos los ítems para la tabla.
     *
     * @param clientes Los datos de los clientes a rellenar para esta tabla.
     *
     * @return La matriz de los objetos a cargar en la tabla.
     */
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

    /**
     * Actualizamos los datos de un cliente.
     */
    private void notificarCambioCliente()
    {

        setChanged();
        notifyObservers(getClientes().get(clientesRegistradosInterfaz.getTablaClientesRegistrados().getSelectedRow()));
        clearChanged();

    }

    /**
     * Obtemos los historiales para un cliente.
     *
     * @param cliente El cliente al que necesitamos sus historiales.
     * @return Los historiales.
     */
    private ArrayList<Historial> getHistorialesCliente(Cliente cliente)
    {

        ArrayList<Historial> historiales = (ArrayList<Historial>) new DAO(DAO.RUTA_HISTORIALES).getObjects();
        ArrayList<Historial> historialesCliente = new ArrayList<>();

        for (int i = 0; i < historiales.size(); i++)
            if (historiales.get(i).getCliente().getClaveCliente() == cliente.getClaveCliente())
                historialesCliente.add(historiales.get(i));

        return historialesCliente;

    }

    /**
     * Buscamos si existe algún servicio en curso para un cliente en particular.
     *
     * @param cliente El cliente a buscar si existe algún servicio.
     * @return <code>Verdadero</code> si existe algún servicio, <code>falso</code> en caso contrario.
     */
    private boolean existeServicioEnCurso(Cliente cliente)
    {

        ArrayList<Servicio> servicios = vistaPrincipalController.getServicios(DAO.RUTA_SERVICIOSENCOLA);

        for (int i = 0; i < servicios.size(); i++)
            if (servicios.get(i).getCliente().getClaveCliente() == cliente.getClaveCliente())
                return true;

        servicios = vistaPrincipalController.getServicios(DAO.RUTA_SERVICIOSENPROCESO);

        for (int i = 0; i < servicios.size(); i++)
            if (servicios.get(i).getCliente().getClaveCliente() == cliente.getClaveCliente())
                return true;

        servicios = vistaPrincipalController.getServicios(DAO.RUTA_SERVICIOSTERMINADOS);

        for (int i = 0; i < servicios.size(); i++)
            if (servicios.get(i).getCliente().getClaveCliente() == cliente.getClaveCliente() && !servicios.get(i).isTicketGenerado())
                return true;

        return false;

    }

    /**
     * Guardamos los clientes en el archivo de texto.
     */
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
