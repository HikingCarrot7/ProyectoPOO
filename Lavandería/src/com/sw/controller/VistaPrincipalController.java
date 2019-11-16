package com.sw.controller;

import com.sw.model.ServicioInicial;
import com.sw.utilities.Utilities;
import com.sw.view.ClientesRegistradosInterfaz;
import com.sw.view.NuevoServicio;
import com.sw.view.VistaPrincipal;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mohammed
 */
public class VistaPrincipalController extends MouseAdapter implements ActionListener
{

    private VistaPrincipal vistaPrincipal;

    public VistaPrincipalController(VistaPrincipal vistaPrincipal)
    {
        this.vistaPrincipal = vistaPrincipal;

        initMyComponents();

        renderTableEnCola();

        renderTableEnProceso();

        renderTableTerminado();

        vistaPrincipal.getNuevoServicio().addActionListener(this);
        vistaPrincipal.getVerClientes().addActionListener(this);

    }

    private void initMyComponents()
    {

        for (int i = 0; i < 10; i++)
        {

            vistaPrincipal.getVerPrendasEnCola().add(new JButton(Utilities.getIcon("/com/src/images/tshirt.png")));
            vistaPrincipal.getMoverLavadoEnCola().add(new JButton(Utilities.getIcon("/com/src/images/down.png")));
            vistaPrincipal.getEliminarEnCola().add(new JButton(Utilities.getIcon("/com/src/images/delete.png")));

        }

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

    private void renderTableEnCola()
    {

        TableManager tableManager = new TableManager();

        Object[][] items = new Object[10][6];

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

    public void anadirServicioCola(ServicioInicial servicioInicial)
    {

    }

}
