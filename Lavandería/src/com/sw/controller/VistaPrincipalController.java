package com.sw.controller;

import com.sw.view.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

        renderTableEnCola();

        renderTableEnProceso();

        renderTableTerminado();

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

    }

}
