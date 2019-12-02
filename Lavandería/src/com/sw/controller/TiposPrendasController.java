package com.sw.controller;

import com.sw.others.MyMouseAdapter;
import com.sw.others.MyWindowListener;
import com.sw.persistence.DAO;
import com.sw.view.AnadirTipoPrendaInterfaz;
import com.sw.view.TiposPrendasInterfaz;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
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

        tiposPrendasInterfaz.getAnadir().addActionListener(this);
        tiposPrendasInterfaz.getEditar().addActionListener(this);

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

    @Override
    public void actionPerformed(ActionEvent e)
    {

        switch (e.getActionCommand())
        {

            case "addTipoPrenda":

                EventQueue.invokeLater(() ->
                {

                    AnadirTipoPrendaInterfaz anadirTipoPrendaInterfaz = new AnadirTipoPrendaInterfaz();

                    anadirTipoPrendaInterfaz.setVisible(true);
                    anadirTipoPrendaInterfaz.setLocationRelativeTo(null);

                    anadirTipoPrendaInterfaz.addWindowListener(new MyWindowListener(tiposPrendasInterfaz));
                    tiposPrendasInterfaz.setVisible(false);

                    new AnadirTipoPrendaController(anadirTipoPrendaInterfaz, this);

                });

                break;

            case "editTipoPrenda":

                if (tiposPrendasInterfaz.getTiposPrendasTable().getSelectedRow() >= 0)
                    EventQueue.invokeLater(() ->
                    {

                        AnadirTipoPrendaInterfaz anadirTipoPrendaInterfaz = new AnadirTipoPrendaInterfaz();

                        anadirTipoPrendaInterfaz.setVisible(true);
                        anadirTipoPrendaInterfaz.setLocationRelativeTo(null);

                        anadirTipoPrendaInterfaz.addWindowListener(new MyWindowListener(tiposPrendasInterfaz));
                        tiposPrendasInterfaz.setVisible(false);

                        new AnadirTipoPrendaController(anadirTipoPrendaInterfaz, this).establecerTipoPrendaDefecto(tiposPrendasInterfaz.getTiposPrendasTable().getSelectedRow());

                    });

                break;

            default:
                break;

        }

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

        TableManager tableManager = new TableManager();
        JTable table = tiposPrendasInterfaz.getTiposPrendasTable();

        if (!tableManager.isFirstRowEmpty(table))
            if (tableManager.encimaBoton(table, e.getX(), e.getY(), 1))
                eliminarTipoPrenda(table.getSelectedRow());

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

    public void anadirTipoPrenda(String nuevoTipoPrenda)
    {

        tiposPrendas.add(nuevoTipoPrenda);

        new TableManager().addRow(tiposPrendasInterfaz.getTiposPrendasTable(), new Object[]
        {
            null, null
        });

        updateTable();

        saveTiposPrendas(tiposPrendas);

    }

    public void modificarTipoPrenda(int index, String nuevoTipoPrenda)
    {

        tiposPrendas.set(index, nuevoTipoPrenda);

        updateTable();
        revalidateTable();
        saveTiposPrendas(tiposPrendas);

    }

    public void eliminarTipoPrenda(int index)
    {

        new TableManager().removeRow(tiposPrendasInterfaz.getTiposPrendasTable(), index);

        tiposPrendas.remove(index);

        saveTiposPrendas(tiposPrendas);

        updateTable();

    }

    public void updateTable()
    {
        new TableManager().setTableItems(tiposPrendasInterfaz.getTiposPrendasTable(), getItems());
    }

    public void revalidateTable()
    {
        tiposPrendasInterfaz.getTiposPrendasTable().getParent().revalidate();
    }

    public void saveTiposPrendas(ArrayList<String> tiposPrendas)
    {
        new DAO(DAO.RUTA_TIPOSPRENDAS).saveObjects(tiposPrendas);
    }

    public ArrayList<String> getTiposPrendas()
    {
        return (ArrayList<String>) new DAO(DAO.RUTA_TIPOSPRENDAS).getObjects();
    }

    public TiposPrendasInterfaz getTiposPrendasInterfaz()
    {
        return tiposPrendasInterfaz;
    }

}
