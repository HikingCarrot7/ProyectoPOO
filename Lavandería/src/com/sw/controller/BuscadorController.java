package com.sw.controller;

import com.sw.model.Servicio;
import com.sw.persistence.DAO;
import com.sw.renderer.ListRenderer;
import com.sw.renderer.ListRenderer.ListItem;
import com.sw.utilities.Utilities;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Mohammed
 */
public class BuscadorController implements FocusListener, DocumentListener
{

    private JScrollPane scrollPane;
    private VistaPrincipalController vistaPrincipalController;
    private DefaultListModel<ListItem> modeloLista;
    private JList<ListItem> lista;

    public BuscadorController(JScrollPane scrollPane, VistaPrincipalController vistaPrincipalController, JList<ListItem> lista)
    {

        this.scrollPane = scrollPane;
        this.vistaPrincipalController = vistaPrincipalController;
        this.lista = lista;

        modeloLista = new DefaultListModel<>();
        lista.setModel(modeloLista);

        lista.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lista.setCellRenderer(new ListRenderer());

    }

    @Override
    public void focusGained(FocusEvent e)
    {

        scrollPane.setVisible(false);
        vistaPrincipalController.getVistaPrincipal().getBuscar().selectAll();

    }

    @Override
    public void focusLost(FocusEvent e)
    {
        scrollPane.setVisible(false);
    }

    @Override
    public void insertUpdate(DocumentEvent e)
    {
        updateLista(vistaPrincipalController.getVistaPrincipal().getBuscar().getText());
    }

    @Override
    public void removeUpdate(DocumentEvent e)
    {
        updateLista(vistaPrincipalController.getVistaPrincipal().getBuscar().getText());
    }

    @Override
    public void changedUpdate(DocumentEvent e)
    {
    }

    private void updateLista(String aBuscar)
    {

        modeloLista.removeAllElements();

        if (aBuscar.trim().equals(" ") || aBuscar.trim().equals(""))
        {
            scrollPane.setVisible(false);
            return;

        }

        ArrayList<Servicio> serviciosEnCola = vistaPrincipalController.getServicios(DAO.RUTA_SERVICIOSENCOLA);
        ArrayList<Servicio> serviciosEnProceso = vistaPrincipalController.getServicios(DAO.RUTA_SERVICIOSENPROCESO);
        ArrayList<Servicio> serviciosTerminado = vistaPrincipalController.getServicios(DAO.RUTA_SERVICIOSTERMINADOS);

        buscarEnLista(serviciosEnCola, aBuscar, "EN COLA");
        buscarEnLista(serviciosEnProceso, aBuscar, "EN PROCESO");
        buscarEnLista(serviciosTerminado, aBuscar, "TERMINADO");

        scrollPane.setVisible(!modeloLista.isEmpty());

    }

    private void buscarEnLista(ArrayList<Servicio> servicios, String aBuscar, String status)
    {

        int temp = 0;

        for (int i = 0; i < servicios.size(); i++)
            if (servicios.get(i).getCliente().getNombre().toLowerCase().startsWith(aBuscar.toLowerCase()))
                modeloLista.add(temp++, new ListItem(Utilities.getIcon("/com/src/images/clienteCombo.png"),
                        String.format("%-25s N° TICKET : %-4s PRECIO : $%-10s STATUS : %s",
                                servicios.get(i).getCliente().getNombre(),
                                String.valueOf(servicios.get(i).getNumeroTicket()),
                                String.valueOf(servicios.get(i).getPrecioTotal()),
                                status)));

    }

}
