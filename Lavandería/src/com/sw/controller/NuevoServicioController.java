package com.sw.controller;

import com.sw.renderer.ComboRenderer;
import com.sw.utilities.Utilities;
import com.sw.view.NuevoServicio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Mohammed
 */
public class NuevoServicioController implements ActionListener
{

    private NuevoServicio nuevoServicio;

    public NuevoServicioController(NuevoServicio nuevoServicio)
    {
        this.nuevoServicio = nuevoServicio;

        loadComboModel();

        initMyComponents();

        nuevoServicio.getAddCliente().addActionListener(this);
        nuevoServicio.getAnadirPrendas().addActionListener(this);
        nuevoServicio.getOk().addActionListener(this);

    }

    private void initMyComponents()
    {
        nuevoServicio.getHoras().setValue(1);
    }

    private void loadComboModel()
    {
        nuevoServicio.getClientes().setRenderer(new ComboRenderer());
        nuevoServicio.getClientes().setModel(loadComboItems());
        nuevoServicio.getClientes().setMaximumRowCount(5);

    }

    private DefaultComboBoxModel<ComboRenderer.ComboItem> loadComboItems()
    {
        DefaultComboBoxModel<ComboRenderer.ComboItem> dm = new DefaultComboBoxModel<>();

        for (int i = 0; i < 5; i++)
            dm.addElement(new ComboRenderer.ComboItem(Utilities.getIcon("/com/src/images/clienteCombo.png"), "Cliente " + (i + 1)));

        return dm;

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }

    public NuevoServicio getNuevoServicio()
    {
        return nuevoServicio;
    }

}
