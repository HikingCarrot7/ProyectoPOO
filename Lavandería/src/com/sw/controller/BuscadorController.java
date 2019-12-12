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
 * Controlador para la barra de búsqueda de clientes.
 *
 * @author Me
 * @since 1.0
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

    /**
     * Cuando el foco se gana en el buscador, ocultamos la tabla que muestra la búsqueda de los clientes.
     *
     * @param e El objeto FocusEvent que se crea cuando se gana el foco.
     */
    @Override
    public void focusGained(FocusEvent e)
    {

        scrollPane.setVisible(false);
        vistaPrincipalController.getVistaPrincipal().getBuscar().selectAll();

    }

    /**
     * Cuando el foco se píerde en el buscador, ocultamos la tabla que muestra la búsqueda de los clientes.
     *
     * @param e El objeto FocusEvent que se crea cuando se pierde el foco.
     */
    @Override
    public void focusLost(FocusEvent e)
    {
        scrollPane.setVisible(false);
    }

    /**
     * Cada vez que se escriba algo en el buscador, actualizamos las búsquedas.
     *
     * @param e El objeto DocumentEvent que se crea cuando se escribe algo en el buscador.
     */
    @Override
    public void insertUpdate(DocumentEvent e)
    {
        updateLista(vistaPrincipalController.getVistaPrincipal().getBuscar().getText());
    }

    /**
     * Cada vez que se borre algo en el buscador, actualizamos las búsquedas.
     *
     * @param e El objeto DocumentEvent que se crea cuando se borra algo en el buscador.
     */
    @Override
    public void removeUpdate(DocumentEvent e)
    {
        updateLista(vistaPrincipalController.getVistaPrincipal().getBuscar().getText());
    }

    @Override
    public void changedUpdate(DocumentEvent e)
    {
    }

    /**
     * Actualizamos la busqueda.
     *
     * @param aBuscar La cadena a buscar para mostrar los resultados de la búsqueda.
     */
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

    /**
     * Si la cadena a buscar concuerda con alguna búsqueda, se añade a la lista.
     *
     * @param servicios La lista con los elementos a buscar.
     * @param aBuscar La cadena a buscar.
     * @param status El status del servicio.
     */
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
