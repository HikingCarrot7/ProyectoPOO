package com.sw.controller;

import com.sw.utilities.Utilities;
import com.sw.view.PrendasInterfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mohammed
 */
public class PrendasController extends MouseAdapter implements ActionListener
{

    private PrendasInterfaz prendas;

    public PrendasController(PrendasInterfaz prendas)
    {
        this.prendas = prendas;

        initMyComponents();

        renderPrendasInterfazTable();

        prendas.getAddPrenda().addActionListener(this);
        prendas.getEditarPrenda().addActionListener(this);

    }

    private void initMyComponents()
    {

        for (int i = 0; i < 10; i++)
        {
            prendas.getTipoPrenda().add(new JButton(Utilities.getIcon("/com/src/images/tshirt.png")));
            prendas.getEliminar().add(new JButton(Utilities.getIcon("/com/src/images/delete.png")));

        }

    }

    private void renderPrendasInterfazTable()
    {

        TableManager tableManager = new TableManager();

        tableManager.renderTableModel(prendas.getPrendasTable(), this, "Prendas");

        Object[][] items = new Object[10][4];

        prendas.getPrendasTable().setModel(new DefaultTableModel(tableManager.loadTableComponents(items, new int[]
        {

            1, 3

        }, prendas.getTipoPrenda(), prendas.getEliminar()), new String[]
        {

            "Prenda", "Tipo de prenda", "Cantidad (piezas)", "Eliminar"

        }));

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        switch (e.getActionCommand())
        {
            case "addPrenda":

                break;

            case "Modificar":

                break;

            default:
                break;

        }

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

}
