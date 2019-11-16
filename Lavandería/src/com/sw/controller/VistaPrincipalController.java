package com.sw.controller;

import com.sw.model.ServicioInicial;
import com.sw.persistence.DAO;
import com.sw.utilities.Utilities;
import com.sw.view.ClientesRegistradosInterfaz;
import com.sw.view.NuevoServicio;
import com.sw.view.PrendasInterfaz;
import com.sw.view.VistaPrincipal;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mohammed
 */
public class VistaPrincipalController extends MouseAdapter implements ActionListener
{

    private VistaPrincipal vistaPrincipal;
    private ArrayList<ServicioInicial> serviciosEnCola;

    public VistaPrincipalController(VistaPrincipal vistaPrincipal)
    {
        this.vistaPrincipal = vistaPrincipal;
        serviciosEnCola = getServiciosEnCola();

        initMyComponents();

        renderTableEnCola();

        renderTableEnProceso();

        renderTableTerminado();

        vistaPrincipal.getNuevoServicio().addActionListener(this);
        vistaPrincipal.getVerClientes().addActionListener(this);

    }

    private void initMyComponents()
    {

        if (serviciosEnCola.isEmpty())
            loadBotonesTablaEnCola();

        else
            for (int i = 0; i < serviciosEnCola.size(); i++)
                loadBotonesTablaEnCola();

        for (int i = 0; i < 10; i++)
        {

            vistaPrincipal.getVerPrendasEnProceso().add(new JButton(Utilities.getIcon("/com/src/images/tshirt.png")));
            vistaPrincipal.getSubirColaEnProceso().add(new JButton(Utilities.getIcon("/com/src/images/up.png")));
            vistaPrincipal.getMoverTerminadoEnProceso().add(new JButton(Utilities.getIcon("/com/src/images/down.png")));
            vistaPrincipal.getEliminarEnProceso().add(new JButton(Utilities.getIcon("/com/src/images/delete.png")));

        }

        for (int i = 0; i < 10; i++)
        {

            vistaPrincipal.getVerPrendasTerminado().add(new JButton(Utilities.getIcon("/com/src/images/tshirt.png")));
            vistaPrincipal.getSubirProcesoTerminado().add(new JButton(Utilities.getIcon("/com/src/images/up.png")));
            vistaPrincipal.getEliminarTerminado().add(new JButton(Utilities.getIcon("/com/src/images/delete.png")));
            vistaPrincipal.getEmpaquetado().add(new JCheckBox());

        }

    }

    private void loadBotonesTablaEnCola()
    {

        vistaPrincipal.getVerPrendasEnCola().add(new JButton(Utilities.getIcon("/com/src/images/tshirt.png")));
        vistaPrincipal.getMoverLavadoEnCola().add(new JButton(Utilities.getIcon("/com/src/images/down.png")));
        vistaPrincipal.getEliminarEnCola().add(new JButton(Utilities.getIcon("/com/src/images/delete.png")));

    }

    private void renderTableEnCola()
    {

        TableManager tableManager = new TableManager();

        Object[][] items = getItemsEnCola();

        vistaPrincipal.getTablaEnCola().setModel(new DefaultTableModel(tableManager.loadTableComponents(items, new int[]
        {
            1, 4, 5

        }, vistaPrincipal.getVerPrendasEnCola(), vistaPrincipal.getMoverLavadoEnCola(), vistaPrincipal.getEliminarEnCola()), new String[]
        {

            "Cliente", "Prendas", "Kilos", "Total", "Mover a lavado", "Eliminar"

        }));

        tableManager.renderTableModel(vistaPrincipal.getTablaEnCola(), this, "En cola");

    }

    private void renderTableEnProceso()
    {
        TableManager tableManager = new TableManager();

        Object[][] items = new Object[10][7];

        vistaPrincipal.getTablaEnProceso().setModel(new DefaultTableModel(tableManager.loadTableComponents(items, new int[]
        {
            1, 4, 5, 6

        }, vistaPrincipal.getVerPrendasEnProceso(), vistaPrincipal.getSubirColaEnProceso(),
                vistaPrincipal.getMoverTerminadoEnProceso(), vistaPrincipal.getEliminarEnProceso()), new String[]
        {

            "Cliente", "Prendas", "Kilos", "Tiempo estimado", "Subir a la cola", "Mover a terminado", "Eliminar"

        }));

        tableManager.renderTableModel(vistaPrincipal.getTablaEnProceso(), this, "En proceso");

    }

    private void renderTableTerminado()
    {

        TableManager tableManager = new TableManager();

        Object[][] items = new Object[10][7];

        vistaPrincipal.getTablaTerminado().setModel(new DefaultTableModel(tableManager.loadTableComponents(items, new int[]
        {

            1, 4, 5, 6

        }, vistaPrincipal.getVerPrendasTerminado(), vistaPrincipal.getSubirProcesoTerminado(),
                vistaPrincipal.getEmpaquetado(), vistaPrincipal.getEliminarTerminado()), new String[]
        {

            "Cliente", "Prendas", "Kilos", "Total", "Subir a proceso", "¿Empaquetado?", "Eliminar"

        }));

        tableManager.renderTableModel(vistaPrincipal.getTablaTerminado(), this, "Terminado");

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        TableManager tableManager = new TableManager();

        if (!tableManager.isFirstRowEmpty(vistaPrincipal.getTablaEnCola()))
        {

            if (tableManager.encimaBoton(vistaPrincipal.getTablaEnCola(), e.getX(), e.getY(), 1))
                EventQueue.invokeLater(() ->
                {

                    PrendasInterfaz prendas = new PrendasInterfaz();

                    prendas.setVisible(true);
                    prendas.setLocationRelativeTo(vistaPrincipal);

                    new PrendasController(prendas, this, serviciosEnCola.get(tableManager.getClickedRow(vistaPrincipal.getTablaEnCola(), e.getY())));

                });

            else if (tableManager.encimaBoton(vistaPrincipal.getTablaEnCola(), e.getX(), e.getY(), 5))
                switch (JOptionPane.showConfirmDialog(vistaPrincipal,
                        "Se borrará este servicio y toda la información relacionada en él. ¿Continuar?",
                        "Confirmar acción", JOptionPane.YES_NO_OPTION))
                {

                    case 0: // Si se presiona Sí

                        eliminarServicioCola(tableManager.getClickedRow(vistaPrincipal.getTablaEnCola(), e.getY()));
                        saveServiciosEnCola();

                        break;

                }

        } else
            mostrarError("No hay servicios", "Aún no hay servicios");

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {

            case "Nuevo servicio":

                EventQueue.invokeLater(() ->
                {

                    NuevoServicio nuevoServicio = new NuevoServicio();

                    nuevoServicio.setVisible(true);
                    nuevoServicio.setLocationRelativeTo(vistaPrincipal);

                    new NuevoServicioController(nuevoServicio, this);

                });

                break;

            case "Ver clientes":

                EventQueue.invokeLater(() ->
                {

                    ClientesRegistradosInterfaz clientesRegistradosInterfaz = new ClientesRegistradosInterfaz();

                    clientesRegistradosInterfaz.setVisible(true);
                    clientesRegistradosInterfaz.setLocationRelativeTo(vistaPrincipal);

                    new ClientesRegistradosController(clientesRegistradosInterfaz);

                });

                break;

            default:
                break;

        }

    }

    private Object[][] getItemsEnCola()
    {
        if (serviciosEnCola.isEmpty())
            return new Object[1][6];

        Object[][] items = new Object[serviciosEnCola.size()][6];

        for (int i = 0; i < serviciosEnCola.size(); i++)
        {

            items[i][0] = serviciosEnCola.get(i).getCliente().getNombre();
            items[i][2] = String.valueOf(serviciosEnCola.get(i).getTotalKg());
            items[i][3] = String.format("$%,.2f", serviciosEnCola.get(i).getTotalKg() * 9.5);

        }

        return items;

    }

    public void anadirServicioCola(ServicioInicial servicioInicial)
    {

        serviciosEnCola.add(servicioInicial);

        new TableManager().addRow(vistaPrincipal.getTablaEnCola(), new Object[]
        {
            servicioInicial.getCliente().getNombre(), null, "", "", null, null
        });

        saveServiciosEnCola();

    }

    private void eliminarServicioCola(int index)
    {

        new TableManager().removeRow(vistaPrincipal.getTablaEnCola(), index);

        serviciosEnCola.remove(index);

        saveServiciosEnCola();

    }

    public void updateAllTables()
    {

        TableManager tableManager = new TableManager();

        tableManager.setTableItems(vistaPrincipal.getTablaEnCola(), getItemsEnCola());
        vistaPrincipal.getTablaEnCola().getParent().revalidate();

    }

    private void mostrarError(String titulo, String text)
    {
        JOptionPane.showMessageDialog(vistaPrincipal, text, titulo, JOptionPane.ERROR_MESSAGE);
    }

    public void saveServiciosEnCola()
    {
        new DAO(DAO.RUTA_SERVICIOSINICIALES).saveObjects(serviciosEnCola);
    }

    private ArrayList<ServicioInicial> getServiciosEnCola()
    {
        return (ArrayList<ServicioInicial>) new DAO(DAO.RUTA_SERVICIOSINICIALES).getObjects();
    }

}
