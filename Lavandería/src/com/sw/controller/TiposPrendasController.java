package com.sw.controller;

import com.sw.others.MyMouseAdapter;
import com.sw.persistence.DAO;
import com.sw.view.TiposPrendasInterfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mohammed
 */
public class TiposPrendasController extends MyMouseAdapter implements ActionListener
{

    private TiposPrendasInterfaz tiposPrendasInterfaz;
    private ArrayList<String> tiposPrendas;

    public TiposPrendasController(TiposPrendasInterfaz tiposPrendasInterfaz)
    {

        this.tiposPrendasInterfaz = tiposPrendasInterfaz;

        tiposPrendas = getTiposPrendas();

        initMyComponents();

        loadTiposPrendaTable();

    }

    private void initMyComponents()
    {

        for (int i = tiposPrendas.isEmpty() ? -1 : 0; i < tiposPrendas.size(); i++)
            tiposPrendasInterfaz.getEliminar().add(new JButton(new ImageIcon(getClass().getResource("/com/src/images/delete.png"))));

    }

    private void loadTiposPrendaTable()
    {

        TableManager tableManager = new TableManager();

        tableManager.renderTableModel(tiposPrendasInterfaz.getTiposPrendasTable(), this, "Tipos prendas");

        Object[][] items = getItems();

        tiposPrendasInterfaz.getTiposPrendasTable().setModel(new DefaultTableModel(tableManager.loadTableComponents(items, new int[]
        {

            1

        }, tiposPrendasInterfaz.getEliminar()), new String[]
        {

            "Tipos prendas", "Eliminar"

        }));

    }

    private Object[][] getItems()
    {

        if (tiposPrendas.isEmpty())
            return new Object[1][2];

        Object[][] items = new Object[tiposPrendas.size()][2];

        for (int i = 0; i < tiposPrendas.size(); i++)
            items[i][0] = tiposPrendas.get(i);

        return items;

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        switch (e.getActionCommand())
        {

            case "addTipoPrenda":

                break;

            case "editTipoPrenda":

                break;

            default:
                break;

        }

    }

    private void saveTiposPrendas()
    {
        new DAO(DAO.RUTA_TIPOSPRENDAS).saveObjects(tiposPrendas);
    }

    private ArrayList<String> getTiposPrendas()
    {
        return (ArrayList<String>) new DAO(DAO.RUTA_TIPOSPRENDAS).getObjects();

    }

}
