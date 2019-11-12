package com.sw.controller;

import com.sw.view.PrendasInterfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

        renderPrendasInterfazTable();

        prendas.getAddPrenda().addActionListener(this);
        prendas.getEditarPrenda().addActionListener(this);
        prendas.getEditarPrenda().addActionListener(this);

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

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

}
