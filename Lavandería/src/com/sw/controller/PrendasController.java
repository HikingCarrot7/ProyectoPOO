package com.sw.controller;

import com.sw.model.Prenda;
import com.sw.model.ServicioInicial;
import com.sw.utilities.Utilities;
import com.sw.view.AnadirPrendaInterfaz;
import com.sw.view.PrendasInterfaz;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mohammed
 */
public class PrendasController extends MouseAdapter implements ActionListener
{

    private PrendasInterfaz prendasInterfaz;
    private NuevoServicioController nuevoServicioController;
    private VistaPrincipalController vistaPrincipalController;
    private ServicioInicial servicioInicial;
    private ArrayList<Prenda> prendas;

    public PrendasController(PrendasInterfaz prendasInterfaz, ArrayList<Prenda> prendas)
    {

        this.prendasInterfaz = prendasInterfaz;
        this.prendas = prendas;

        initAllMyComponents();

    }

    public PrendasController(PrendasInterfaz prendasInterfaz, VistaPrincipalController vistaPrincipalController, ServicioInicial servicioInicial)
    {

        this.vistaPrincipalController = vistaPrincipalController;
        this.prendasInterfaz = prendasInterfaz;
        this.servicioInicial = servicioInicial;

        prendas = servicioInicial.getPrendas();

        initCamposPrecio(servicioInicial.getTotalKg());

        initAllMyComponents();

    }

    public PrendasController(PrendasInterfaz prendasInterfaz, NuevoServicioController nuevoServicioController, ArrayList<Prenda> prendas, double totalKg)
    {

        this.prendasInterfaz = prendasInterfaz;
        this.nuevoServicioController = nuevoServicioController;
        this.prendas = prendas;

        initCamposPrecio(totalKg);

        initAllMyComponents();

    }

    private void iniciarLista()
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

    private void initCamposPrecio(double totalKg)
    {
        prendasInterfaz.getTotalKg().setText(String.valueOf(totalKg));
        prendasInterfaz.getTotalPrecio().setText(String.format("$%,.2f", totalKg * 9.5));

    }

    private void initAllMyComponents()
    {

        iniciarLista();

        renderPrendasInterfazTable();

        TextFieldListener textFieldListener = new TextFieldListener("^[0-9]+(.?[0-9]+)?$", prendasInterfaz.getTotalKg(), prendasInterfaz.getTotalPrecio());

        prendasInterfaz.getTotalKg().getDocument().addDocumentListener(textFieldListener);
        prendasInterfaz.getTotalKg().addFocusListener(textFieldListener);

        if (!prendas.isEmpty())
            prendasInterfaz.getTotalPiezas().setText(String.valueOf(getNumTotalPrendas()));

        prendasInterfaz.getAddPrenda().addActionListener(this);
        prendasInterfaz.getEditarPrenda().addActionListener(this);

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

            "Prenda", "Tipo de prenda", "Cantidad", "Eliminar"

        }));

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        switch (e.getActionCommand())
        {
            case "addPrenda":

                EventQueue.invokeLater(() ->
                {
                    AnadirPrendaInterfaz anadirPrendaInterfaz = new AnadirPrendaInterfaz();

                    anadirPrendaInterfaz.setVisible(true);
                    anadirPrendaInterfaz.setLocationRelativeTo(prendasInterfaz);

                    new AnadirPrendaController(anadirPrendaInterfaz, this);

                });

                break;

            case "modificar":

                if (!new TableManager().isFirstRowEmpty(prendasInterfaz.getPrendasTable()))
                    EventQueue.invokeLater(() ->
                    {
                        AnadirPrendaInterfaz anadirPrendaInterfaz = new AnadirPrendaInterfaz();

                        anadirPrendaInterfaz.setVisible(true);
                        anadirPrendaInterfaz.setLocationRelativeTo(prendasInterfaz);

                        new AnadirPrendaController(anadirPrendaInterfaz, this).establecerPrendaDefecto(prendas.get(prendasInterfaz.getPrendasTable().getSelectedRow()));

                    });

                else
                    mostrarError();

            default:
                break;

        }

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
                mostrarError();

    }

    /**
     *
     * @deprecated
     *
     * Revisar para futuras implementaciones.
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

        if (nuevoServicioController != null)
            nuevoServicioController.setPrendas(getPrendas());

        else
        {

            servicioInicial.setPrendas(getPrendas());
            vistaPrincipalController.saveAllServices();

        }

        prendasInterfaz.getTotalPiezas().setText(String.valueOf(getNumTotalPrendas()));

    }

    public ArrayList<Prenda> getPrendas()
    {
        return prendas;
    }

    public void setPrendas(ArrayList<Prenda> prendas)
    {
        this.prendas = prendas;
    }

    private int getNumTotalPrendas()
    {

        int nTotal = 0;

        for (int i = 0; i < getPrendas().size(); i++)
            nTotal += getPrendas().get(i).getCantidad();

        if (nuevoServicioController != null)
            nuevoServicioController.setNTotalPrendas(nTotal);

        return nTotal;

    }

    private void mostrarError()
    {
        JOptionPane.showMessageDialog(prendasInterfaz, "Aún no hay prendas", "No hay prendas", JOptionPane.ERROR_MESSAGE);
    }

    private class TextFieldListener implements DocumentListener, FocusListener
    {

        private JTextField totalKg;
        private JTextField precioTotal;
        private String regex;
        private double precioKg;
        private boolean campoValido;

        public TextFieldListener(String regex, JTextField totalKg, JTextField precioTotal)
        {
            this.regex = regex;
            this.totalKg = totalKg;
            this.precioTotal = precioTotal;

            this.precioKg = 9.5;

        }

        @Override
        public void insertUpdate(DocumentEvent e)
        {
            updateCampo();
        }

        @Override
        public void removeUpdate(DocumentEvent e)
        {
            updateCampo();
        }

        @Override
        public void focusGained(FocusEvent e)
        {

            totalKg.selectAll();
            totalKg.setForeground(Color.black);
            precioTotal.setText("");
            setCampoValido(false);

            if (nuevoServicioController != null)
                nuevoServicioController.setTotalKg(0);
            else
                servicioInicial.setTotalKg(0);

            if (vistaPrincipalController != null)
            {
                vistaPrincipalController.saveServiciosEnCola();
                vistaPrincipalController.updateAllTables();

            }

        }

        @Override
        public void focusLost(FocusEvent e)
        {
            updateCampo();
        }

        private void updateCampo()
        {

            if (!totalKg.getText().trim().equals(""))
            {

                boolean valido = totalKg.getText().matches(regex);

                totalKg.setForeground(valido ? Color.green : Color.red);
                precioTotal.setText(valido ? String.format("$%,.2f", Double.parseDouble(totalKg.getText()) * precioKg) : "");
                setCampoValido(valido);

                if (valido && nuevoServicioController != null)
                    nuevoServicioController.setTotalKg(Double.parseDouble(totalKg.getText()));

                else if (valido)
                    servicioInicial.setTotalKg(Double.parseDouble(totalKg.getText()));

                if (vistaPrincipalController != null)
                {
                    vistaPrincipalController.saveServiciosEnCola();
                    vistaPrincipalController.updateAllTables();

                }

            } else
            {

                totalKg.setForeground(Color.black);
                precioTotal.setText("");
                setCampoValido(false);

            }

        }

        @Override
        public void changedUpdate(DocumentEvent e)
        {

        }

        public boolean isCampoValido()
        {
            return campoValido;
        }

        private void setCampoValido(boolean campoValido)
        {
            this.campoValido = campoValido;
        }

    }

}
