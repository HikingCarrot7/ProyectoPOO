package com.sw.controller;

import com.sw.model.Prenda;
import com.sw.others.TextFieldListener;
import com.sw.persistence.DAO;
import com.sw.renderer.ComboRenderer;
import com.sw.utilities.Utilities;
import com.sw.view.AnadirPrendaInterfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Mohammed
 */
public class AnadirPrendaController implements ActionListener
{

    private AnadirPrendaInterfaz anadirPrendaInterfaz;
    private PrendasController prendasController;
    private TextFieldListener[] textFieldListeners;
    private ArrayList<String> tiposPrendas;
    private Prenda prendaAModificar;
    private boolean modificandoPrenda;

    public AnadirPrendaController(AnadirPrendaInterfaz anadirPrendaInterfaz, PrendasController prendasController)
    {

        this.anadirPrendaInterfaz = anadirPrendaInterfaz;
        this.prendasController = prendasController;

        initMyComponents();

    }

    private void initMyComponents()
    {

        textFieldListeners = new TextFieldListener[2];

        textFieldListeners[0] = new TextFieldListener("^[á-úÁ-Úa-zA-Z0-9 ,_-]+$", anadirPrendaInterfaz.getPrenda());
        textFieldListeners[1] = new TextFieldListener("^[1-90*]+$", anadirPrendaInterfaz.getCantidad());

        anadirPrendaInterfaz.getPrenda().getDocument().addDocumentListener(textFieldListeners[0]);
        anadirPrendaInterfaz.getCantidad().getDocument().addDocumentListener(textFieldListeners[1]);

        anadirPrendaInterfaz.getPrenda().addFocusListener(textFieldListeners[0]);
        anadirPrendaInterfaz.getCantidad().addFocusListener(textFieldListeners[1]);

        anadirPrendaInterfaz.getCantidad().setText("1");

        anadirPrendaInterfaz.getOk().addActionListener(this);

        loadComboModel();

    }

    private void loadComboModel()
    {

        anadirPrendaInterfaz.getTiposPrenda().setRenderer(new ComboRenderer());
        anadirPrendaInterfaz.getTiposPrenda().setModel(loadComboItems());

    }

    private DefaultComboBoxModel<ComboRenderer.ComboItem> loadComboItems()
    {

        DefaultComboBoxModel<ComboRenderer.ComboItem> dm = new DefaultComboBoxModel<>();
        tiposPrendas = getTiposPrendas();

        for (int i = tiposPrendas.isEmpty() ? -1 : 0; i < tiposPrendas.size(); i++)
            dm.addElement(new ComboRenderer.ComboItem(Utilities.getIcon("/com/src/images/tshirtCombo.png"),
                    tiposPrendas.isEmpty() ? "No existen tipos de prendas" : tiposPrendas.get(i)));

        return dm;

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        if (!todosLosCampoValidos())
            JOptionPane.showMessageDialog(anadirPrendaInterfaz, "Alguno de los campos no es válido", "Campo no válido", JOptionPane.ERROR_MESSAGE);

        else if (!isModificandoPrenda())
            prendasController.anadirPrenda(getPrenda());

        else
            prendasController.modificarPrenda(prendaAModificar, getPrenda());

        if (todosLosCampoValidos())
            anadirPrendaInterfaz.dispose();

    }

    public void establecerPrendaDefecto(Prenda prenda)
    {

        setModificandoPrenda(true);

        prendaAModificar = prenda;

        anadirPrendaInterfaz.getPrenda().setText(prenda.getDescripcion());
        anadirPrendaInterfaz.getCantidad().setText(String.valueOf(prenda.getCantidad()));
        anadirPrendaInterfaz.getTiposPrenda().setSelectedIndex(getIndexTipoPrenda(prenda));

    }

    private Prenda getPrenda()
    {

        return new Prenda(anadirPrendaInterfaz.getPrenda().getText(),
                tiposPrendas.isEmpty() ? " " : tiposPrendas.get(anadirPrendaInterfaz.getTiposPrenda().getSelectedIndex()),
                Integer.parseInt(anadirPrendaInterfaz.getCantidad().getText()));

    }

    private int getIndexTipoPrenda(Prenda prenda)
    {

        for (int i = 0; i < tiposPrendas.size(); i++)
            if (tiposPrendas.get(i).equals(prenda.getTipo()))
                return i;

        return 0;

    }

    private boolean todosLosCampoValidos()
    {

        for (TextFieldListener textFieldListener : textFieldListeners)
            if (!textFieldListener.isValido())
                return false;

        return true;

    }

    private ArrayList<String> getTiposPrendas()
    {
        return (ArrayList<String>) new DAO(DAO.RUTA_TIPOSPRENDAS).getObjects();
    }

    private boolean isModificandoPrenda()
    {
        return modificandoPrenda;
    }

    private void setModificandoPrenda(boolean modificandoPrenda)
    {
        this.modificandoPrenda = modificandoPrenda;
    }

}
