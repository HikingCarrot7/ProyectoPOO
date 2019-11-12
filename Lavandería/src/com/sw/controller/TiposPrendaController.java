package com.sw.controller;

import com.sw.view.TiposPrendasInterfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mohammed
 */
public class TiposPrendaController extends MouseAdapter implements ActionListener
{

    private TiposPrendasInterfaz tiposPrendas;

    public TiposPrendaController(TiposPrendasInterfaz tiposPrendas)
    {
        this.tiposPrendas = tiposPrendas;

        renderTiposPrendasTable();

        tiposPrendas.getAnadir().addActionListener(this);
        tiposPrendas.getEditar().addActionListener(this);

    }

    private void renderTiposPrendasTable()
    {

        TableManager tableManager = new TableManager();

        tableManager.renderTableModel(tiposPrendas.getTiposPrendasTable(), this, "Tipos prenda");

        Object[][] items = new Object[10][2];

        tiposPrendas.getTiposPrendasTable().setModel(new DefaultTableModel(tableManager.loadTableComponents(items, new int[]
        {
            1

        }, tiposPrendas.getEliminar()), new String[]
        {

            "Tipo de prenda", "Eliminar"

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
