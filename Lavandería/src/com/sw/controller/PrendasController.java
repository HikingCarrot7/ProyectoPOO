package com.sw.controller;

import com.sw.model.Prenda;
import com.sw.model.Servicio;
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
    private Servicio servicio;
    private ArrayList<Prenda> prendas;

    public PrendasController(PrendasInterfaz prendasInterfaz, VistaPrincipalController vistaPrincipalController, Servicio servicio)
    {

        this.vistaPrincipalController = vistaPrincipalController;
        this.prendasInterfaz = prendasInterfaz;
        this.servicio = servicio;

        prendas = servicio.getPrendas();

        initCampos(servicio.getTotalKg(), servicio.getCostoKg());

        initAllMyComponents(servicio.getCostoKg());

    }

    public PrendasController(PrendasInterfaz prendasInterfaz, NuevoServicioController nuevoServicioController, ArrayList<Prenda> prendas, double totalKg, double costoKg)
    {

        this.prendasInterfaz = prendasInterfaz;
        this.nuevoServicioController = nuevoServicioController;
        this.prendas = prendas;

        initCampos(totalKg, costoKg);

        initAllMyComponents(costoKg);

    }

    public PrendasController(PrendasInterfaz prendasInterfaz, ArrayList<Prenda> prendas, double totalKg, double costoKg)
    {

        this.prendasInterfaz = prendasInterfaz;
        this.prendas = prendas;

        prendasInterfaz.getAddPrenda().setEnabled(false);
        prendasInterfaz.getEditarPrenda().setEnabled(false);
        prendasInterfaz.getTotalKg().setEnabled(false);

        initCampos(totalKg, costoKg);

        initAllMyComponents(costoKg);

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

    private void initCampos(double totalKg, double costoKg)
    {

        prendasInterfaz.getTotalKg().setText(String.valueOf(totalKg));
        prendasInterfaz.getTotalPrecio().setText(String.format("$%,.2f", totalKg * costoKg));

    }

    private void initAllMyComponents(double costoKg)
    {

        iniciarLista();

        renderPrendasInterfazTable();

        MyTextFieldListener textFieldListener = new MyTextFieldListener("^[0-9]+(.?[0-9]+)?$", prendasInterfaz.getTotalKg(), prendasInterfaz.getTotalPrecio(), costoKg);

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
                    mostrarMensaje("Error.", "Aún no se ha añadido alguna prenda.", JOptionPane.ERROR_MESSAGE);

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
        {

            if (!prendasInterfaz.getAddPrenda().isEnabled())
            {

                mostrarMensaje("Error.", "No se pueden borrar prendas cuando el ticket ya ha sido generado.", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (prendas.size() == 1)
            {
                mostrarMensaje("Error.", "Al menos una prenda debe ser añadida por servicio", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!tableManager.isFirstRowEmpty(table))
                eliminarPrenda(tableManager.getClickedRow(table, e.getY()));

            else
                mostrarMensaje("Error.", "Aún no se ha añadido alguna prenda.", JOptionPane.ERROR_MESSAGE);

        }

    }

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

            servicio.setPrendas(getPrendas());
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

    private void mostrarMensaje(String titulo, String text, int tipo)
    {
        JOptionPane.showMessageDialog(prendasInterfaz, text, titulo, tipo);
    }

    private class MyTextFieldListener implements DocumentListener, FocusListener
    {

        private JTextField totalKg;
        private JTextField precioTotal;
        private String regex;
        private double costoKg;
        private boolean campoValido;

        public MyTextFieldListener(String regex, JTextField totalKg, JTextField precioTotal, double costoKg)
        {

            this.regex = regex;
            this.totalKg = totalKg;
            this.precioTotal = precioTotal;
            this.costoKg = costoKg;

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
                servicio.setTotalKg(0);

            if (vistaPrincipalController != null)
            {
                vistaPrincipalController.saveAllServices();
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
                precioTotal.setText(valido ? String.format("$%,.2f", Double.parseDouble(totalKg.getText()) * costoKg) : "");
                setCampoValido(valido);

                if (valido && nuevoServicioController != null)
                    nuevoServicioController.setTotalKg(Double.parseDouble(totalKg.getText()));

                else if (valido)
                    servicio.setTotalKg(Double.parseDouble(totalKg.getText()));

                if (vistaPrincipalController != null)
                {

                    vistaPrincipalController.saveAllServices();
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
