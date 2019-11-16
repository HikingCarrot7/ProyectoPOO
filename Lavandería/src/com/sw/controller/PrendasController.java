package com.sw.controller;

import com.sw.model.Prenda;
import com.sw.utilities.Utilities;
import com.sw.view.AnadirPrendaInterfaz;
import com.sw.view.PrendasInterfaz;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mohammed
 */
public class PrendasController extends MouseAdapter implements ActionListener
{

    private PrendasInterfaz prendasInterfaz;
    private NuevoServicioController nuevoServicioController;
    private ArrayList<Prenda> prendas;

    public PrendasController(PrendasInterfaz prendasInterfaz, ArrayList<Prenda> prendas)
    {

        this.prendasInterfaz = prendasInterfaz;
        this.prendas = prendas;

        initMyComponents();

        renderPrendasInterfazTable();

        prendasInterfaz.getAddPrenda().addActionListener(this);
        prendasInterfaz.getEditarPrenda().addActionListener(this);

        //Caso especial, crear clase privada aparte.
        //prendasInterfaz.getTotalKg().getDocument().addDocumentListener(new TextFieldListener("^[0-9]+(.?[0-9]+)?$", prendasInterfaz.getTotalKg()));
    }

    public PrendasController(PrendasInterfaz prendasInterfaz)
    {
        this(prendasInterfaz, new ArrayList<>());
    }

    public PrendasController(PrendasInterfaz prendasInterfaz, NuevoServicioController nuevoServicioController)
    {
        this(prendasInterfaz);

        this.nuevoServicioController = nuevoServicioController;

    }

    private void initMyComponents()
    {

        if (prendas.isEmpty())
        {
            prendasInterfaz.getEliminar().add(new JButton(Utilities.getIcon("/com/src/images/delete.png")));
            return;

        }

        prendas.forEach((prenda) ->
        {
            prendasInterfaz.getEliminar().add(new JButton(Utilities.getIcon("/com/src/images/delete.png")));
        });

    }

    private void renderPrendasInterfazTable()
    {

        TableManager tableManager = new TableManager();

        tableManager.renderTableModel(prendasInterfaz.getPrendasTable(), this, "Prendas");

        Object[][] items = getItems();

        prendasInterfaz.getPrendasTable().setModel(new DefaultTableModel(tableManager.loadTableComponents(items, new int[]
        {

            3

        }, prendasInterfaz.getEliminar()), new String[]
        {

            "Prenda", "Tipo de prenda", "Cantidad (piezas)", "Eliminar"

        }));

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        EventQueue.invokeLater(() ->
        {

            AnadirPrendaInterfaz anadirPrendaInterfaz = new AnadirPrendaInterfaz();

            anadirPrendaInterfaz.setVisible(true);
            anadirPrendaInterfaz.setLocationRelativeTo(prendasInterfaz);

            if (e.getActionCommand().equals("addPrenda"))
                new AnadirPrendaController(anadirPrendaInterfaz, this);

            else
                new AnadirPrendaController(anadirPrendaInterfaz, this).establecerPrendaDefecto(prendas.get(prendasInterfaz.getPrendasTable().getSelectedRow()));

        });

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

        TableManager tableManager = new TableManager();
        JTable table = prendasInterfaz.getPrendasTable();

        if (tableManager.encimaBoton(table, e.getX(), e.getY(), 3))
            if (!tableManager.isFirstRowEmpty(table))
                eliminarPrenda(tableManager.getClickedRow(table, e.getY()));

            else
                JOptionPane.showMessageDialog(prendasInterfaz, "Aún no hay prendas", "No hay prendas", JOptionPane.ERROR_MESSAGE);

    }

    /**
     *
     * @deprecated
     *
     * Revisar para futuras implementaziones.
     *
     * @return
     */
    private Object[][] getItems()
    {

        if (prendas.isEmpty())
            return new Object[1][4];

        Object[][] items = new Object[prendas.size()][4];

        for (int i = 0; i < items.length; i++)
        {

            items[i][0] = prendas.get(i).getDescripcion();
            items[i][1] = prendas.get(i).getTipo();
            items[i][2] = String.valueOf(prendas.get(i).getCantidad());

        }

        return items;

    }

    public void anadirPrenda(Prenda prenda)
    {
        prendas.add(prenda);

        new TableManager().addRow(prendasInterfaz.getPrendasTable(), new Object[]
        {
            prenda.getDescripcion(), prenda.getTipo(), String.valueOf(prenda.getCantidad())
        });

        establecerPrendas();

    }

    public void modificarPrenda(Prenda prendaAModificar, Prenda prendaNuevosDatos)
    {

        prendaAModificar.setDescripcion(prendaNuevosDatos.getDescripcion());
        prendaAModificar.setTipo(prendaNuevosDatos.getTipo());
        prendaAModificar.setCantidad(prendaNuevosDatos.getCantidad());

        new TableManager().setTableItems(prendasInterfaz.getPrendasTable(), getItems());

        establecerPrendas();

    }

    private void eliminarPrenda(int index)
    {

        new TableManager().removeRow(prendasInterfaz.getPrendasTable(), index);

        getPrendas().remove(index);

        establecerPrendas();

    }

    private void establecerPrendas()
    {
        nuevoServicioController.setPrendas(getPrendas());
    }

    public ArrayList<Prenda> getPrendas()
    {
        return prendas;
    }

    public void setPrendas(ArrayList<Prenda> prendas)
    {
        this.prendas = prendas;
    }

}
